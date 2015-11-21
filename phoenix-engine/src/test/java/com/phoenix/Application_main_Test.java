/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kohsuke.args4j.CmdLineException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Nov 21, 2015
 */
@RunWith(MockitoJUnitRunner.class)
public class Application_main_Test {

  @InjectMocks
  private Application app;

  @Mock
  private Runner runner;

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Nov 21, 2015
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {}

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Nov 21, 2015
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception {}

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Nov 21, 2015
   */
  @Before
  public void setUp() throws Exception {}

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Nov 21, 2015
   */
  @After
  public void tearDown() throws Exception {}

  @Test(expected = RuntimeException.class)
  public final void test_too_few_arguments() throws CmdLineException {
    this.app.doMain(new String[] {""});
  }

  @Test(expected = RuntimeException.class)
  public final void test_too_many_arguments() throws CmdLineException {
    this.app.doMain(new String[] {""});
  }

  @Test()
  public final void test_working() throws CmdLineException {
    this.app.doMain(new String[] {"-in", "test"});
    Mockito.verify(this.runner, Mockito.only()).executeArgs(org.mockito.Matchers.any());;
    Mockito.verifyNoMoreInteractions(this.runner);
  }
}
