/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.execution;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.assertj.swing.core.Robot;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.phoenix.command.Environment;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 25, 2015
 */
@RunWith(PowerMockRunner.class)
public class DefaultTcExecutor_tearDown_Test {

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
  public void setUp() throws Exception {}

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Dec 25, 2015
   */
  @After
  public void tearDown() throws Exception {}

  @PrepareForTest({Files.class})
  @Test
  public final void test() throws IOException {
    final Environment env = Mockito.mock(Environment.class);
    final Robot r = Mockito.mock(Robot.class);
    final DefaultTcExecutor exec = new DefaultTcExecutor();
    exec.env = env;
    Mockito.when(env.getRobot()).thenReturn(r);
    Mockito.when(env.getDir()).thenReturn(Paths.get(""));
    PowerMockito.mockStatic(Files.class);
    PowerMockito.when(Files.walkFileTree(Matchers.any(), Matchers.any())).thenReturn(null);
    exec.tearDown(null);
  }

}
