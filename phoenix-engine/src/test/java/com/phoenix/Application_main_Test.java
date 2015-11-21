/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

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

import com.phoenix.config.CmdArguments;

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

  @Test
  public void mysql() {
    final Map<String, String> env = System.getenv();
    System.out.println(env.get("MYSQL_USER"));
    try {
      final Connection conn =
          DriverManager.getConnection("jdbc:mysql://localhost/test?" + "user="
              + env.get("MYSQL_USER") + "&password=" + env.get("MYSQL_PASSWORD"));

      // Do something with the Connection
    } catch (final SQLException ex) {
      // handle any errors
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }

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
    Mockito.verify(this.runner, Mockito.only()).executeArgs(
        org.mockito.Matchers.any(CmdArguments.class));
    Mockito.verifyNoMoreInteractions(this.runner);
  }
}
