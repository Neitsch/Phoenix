/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.execution;

import java.awt.Frame;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import lombok.extern.slf4j.XSlf4j;

import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.matcher.FrameMatcher;
import org.assertj.swing.security.ExitException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phoenix.command.Environment;
import com.phoenix.exception.SetupException;
import com.phoenix.to.ResultWithMessage;
import com.phoenix.to.TestCaseBody;
import com.phoenix.to.TestCaseBodyResult;
import com.phoenix.to.TestCaseEnd;
import com.phoenix.to.TestCaseSetup;
import com.phoenix.to.TestCaseStep;
import com.phoenix.to.TestCaseStepResult;
import com.phoenix.to.TestCaseStepResultStatus;
import com.phoenix.util.FS;
import com.phoenix.util.MyEventListener;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Nov 21, 2015
 */
@XSlf4j
@Service
public class DefaultTcExecutor implements TcExecutor {
  protected Environment env;

  @Autowired
  private StepExecutor exec;
  private int position = 0;
  private final List<MyEventListener<TestCaseStepResult>> resultListener = new ArrayList<>();

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.execution.TcExecutor#execute(com.phoenix.to.TestCaseBody)
   * @since Dec 1, 2015
   */
  @Override
  public TestCaseBodyResult execute(final TestCaseBody tc) {
    final TestCaseBodyResult res = new TestCaseBodyResult();
    this.position = 0;
    tc.getLines().forEach(
        t -> res.getStepResults().add(
            TestCaseStepResult.builder().step(t).result(TestCaseStepResultStatus.NOT_EXECUTED)
            .build()));;
            while (this.position < res.getStepResults().size()) {
              try {
                log.info("Next step is: " + res.getStepResults().get(this.position).getStep());
                final TestCaseStep step = res.getStepResults().get(this.position).getStep();
                final ResultWithMessage myRes = this.exec.doStep(step, this.env);
                res.getStepResults().get(this.position).setResult(myRes.getStatus());
                this.report(res.getStepResults().get(this.position));
                log.info("Step result is: " + myRes);
                this.position++;
              } catch (final Exception e) {
                log.catching(e);
              }
            }
            return res;
  }

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.execution.TcExecutor#getEnvironment()
   * @since Feb 12, 2016
   */
  @Override
  public Environment getEnvironment() {
    return this.env;
  }

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.execution.TcExecutor#registerListener(com.phoenix.util.MyEventListener)
   * @since Feb 11, 2016
   */
  @Override
  public void registerListener(final MyEventListener<TestCaseStepResult> myEventListener) {
    this.resultListener.add(myEventListener);
  }

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @return
   * @see com.phoenix.execution.TcExecutor#setUp(com.phoenix.to.TestCaseSetup)
   * @since Dec 1, 2015
   */
  @Override
  public void setUp(final TestCaseSetup setup) throws SetupException {
    try {
      // Create new Env for TC
      this.env = new Environment();
      // Configure working directory
      this.env.setDir(Files.createTempDirectory(Paths.get(""), "temp-gui-"));
      log.debug("Working directory: " + this.env.getDir().toAbsolutePath().toString());
      System.setProperty("user.dir", this.env.getDir().toAbsolutePath().toString());
      // Download Assets
      final Path downloads = Files.createDirectories(this.env.getDir().resolve("downloads"));
      final List<Exception> exceptions = Collections.synchronizedList(new LinkedList<Exception>());
      setup.getDownloads().parallelStream().forEach(download -> {
        try {
          download.doDownload(downloads);
        } catch (final Exception e) {
          log.catching(e);
          exceptions.add(e);
        }
      });
      if (exceptions.size() > 0) {
        throw exceptions.get(0);
      }
      // First create Robot, so that it will pick up on the created frame
      this.env.setRobot(BasicRobot.robotWithNewAwtHierarchy());
      // Execute setup script if present
      final Path script = downloads.resolve("run.sh");
      if (Files.exists(script)) {
        final Set<PosixFilePermission> perms = Files.getPosixFilePermissions(script);
        perms.add(PosixFilePermission.OWNER_EXECUTE);
        Files.setPosixFilePermissions(script, perms);
        script.toFile().setExecutable(true);
        final Process p =
            new ProcessBuilder().directory(downloads.toFile()).command(setup.getStartupScript())
            .start();
        // Assert that it completed successfully
        if (!p.waitFor(setup.getTimeout(), TimeUnit.SECONDS)) {
          p.destroyForcibly();
          throw new SetupException(new TimeoutException());
        }
        if (p.exitValue() != 0) {
          throw new SetupException(new ExitException("Exit code: " + p.exitValue()));
        }
      }
      @SuppressWarnings("resource")
      final ClassLoader loader =
      new URLClassLoader(new URL[] {downloads.resolve("program.jar").toUri().toURL()});
      final Class<?> main = loader.loadClass(setup.getStartClass());
      main.getMethod("main", String[].class).invoke(null, (Object) setup.getStartArgs());
      this.env.getRobot().waitForIdle();
      final Frame frame =
          this.env.getRobot().finder().find(FrameMatcher.withName(setup.getFrameName()));
      this.env.setFrame(frame);
      if (frame == null) {
        throw new SetupException(new java.awt.AWTException("Frame not found"));
      }
      if (JFrame.class.isAssignableFrom(frame.getClass())) {
        ((JFrame) frame).setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
      }
    } catch (final Exception e) {
      throw new SetupException(e);
    }
  }

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.execution.TcExecutor#tearDown(com.phoenix.to.TestCaseEnd)
   * @since Dec 1, 2015
   */
  @Override
  public void tearDown(final TestCaseEnd end) {
    this.env.getRobot().cleanUp();
    try {
      FS.delete(this.env.getDir());
    } catch (final IOException e) {
      log.catching(e);
    }
  }

  /**
   * @author nschuste
   * @version 1.0.0
   * @param testCaseStepResult
   * @since Feb 11, 2016
   */
  private void report(final TestCaseStepResult testCaseStepResult) {
    this.resultListener.forEach(t -> t.event(testCaseStepResult));
  }

}
