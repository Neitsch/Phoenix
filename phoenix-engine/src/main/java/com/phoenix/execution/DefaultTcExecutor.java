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
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
    int position = 0;
    tc.getLines().forEach(
        t -> res.getStepResults().add(
            TestCaseStepResult.builder().step(t).result(TestCaseStepResultStatus.NOT_EXECUTED)
                .build()));;
    while (position < res.getStepResults().size()) {
      try {
        final TestCaseStep step = res.getStepResults().get(position).getStep();
        final ResultWithMessage myRes = this.exec.doStep(step, this.env);
        res.getStepResults().get(position).setResult(myRes.getStatus());
        position++;
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
      setup.getDownloads().parallelStream().forEach(download -> {
        download.doDownload(downloads);
      });
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

}
