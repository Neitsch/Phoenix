/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.execution;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedList;
import java.util.List;

import lombok.extern.slf4j.XSlf4j;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.phoenix.to.DownloadWorker;
import com.phoenix.to.TestCaseSetup;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 25, 2015
 */
@XSlf4j
public class DefaultTcExecutor_setup_Test {
  DefaultTcExecutor exec;

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Dec 25, 2015
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {}

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Dec 25, 2015
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception {}

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Dec 25, 2015
   */
  @Before
  public void setUp() throws Exception {
    this.exec = new DefaultTcExecutor();
  }

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Dec 25, 2015
   */
  @After
  public void tearDown() throws Exception {
    this.exec.env.getRobot().cleanUp();
    Files.walkFileTree(this.exec.env.getDir(), new SimpleFileVisitor<Path>() {

      @Override
      public FileVisitResult postVisitDirectory(final Path dir, final IOException exc)
          throws IOException {
        if (exc == null) {
          Files.delete(dir);
          return FileVisitResult.CONTINUE;
        } else {
          throw exc;
        }
      }

      @Override
      public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs)
          throws IOException {
        Files.delete(file);
        return FileVisitResult.CONTINUE;
      }

    });
  }

  @Test
  public final void test() throws Exception {
    final TestCaseSetup setup = new TestCaseSetup();
    setup.setTimeout(2);
    final List<DownloadWorker> downloads = new LinkedList<>();
    downloads.add(downloads1 -> {
      try {
        java.nio.file.Files.copy(this.getClass().getResourceAsStream("run.sh"),
            downloads1.resolve("run.sh"));
        java.nio.file.Files.copy(this.getClass().getResourceAsStream("program.jar"),
            downloads1.resolve("program.jar"));
      } catch (final Exception e) {
        log.catching(e);
      }
    });
    setup.setStartArgs(new String[0]);
    setup.setFrameName("Frame1");
    setup.setStartClass("com.phoenix.MyFrame");
    setup.setStartupScript("./run.sh");
    setup.setDownloads(downloads);
    this.exec.setUp(setup);
    Assert.assertNotNull(this.exec.env.getFrame());
    Assert.assertNotNull(this.exec.env.getRobot());
  }
}
