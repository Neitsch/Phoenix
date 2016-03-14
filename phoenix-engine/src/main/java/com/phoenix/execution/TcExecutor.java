/**
 * Copyright 2015 Nigel Schuster. Provides methods to handle lifecycle of testcase execution
 */


package com.phoenix.execution;

import com.phoenix.command.Environment;
import com.phoenix.exception.SetupException;
import com.phoenix.to.TestCaseBody;
import com.phoenix.to.TestCaseBodyResult;
import com.phoenix.to.TestCaseEnd;
import com.phoenix.to.TestCaseSetup;
import com.phoenix.to.TestCaseStepResult;
import com.phoenix.util.MyEventListener;

/**
 * TcExecutor provides methods to setup, execute and teardown a testcase as well as several hooks to
 * retrieve information about the state of the execution.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Nov 21, 2015
 */
public interface TcExecutor {

  /**
   * Executes the actual steps in a testcase
   * 
   * @author nschuste
   * @version 1.0.0
   * @param tc
   * @param config
   * @since Nov 21, 2015
   */
  public TestCaseBodyResult execute(final TestCaseBody tc);

  /**
   * Returns the current environment for the testcase
   * 
   * @author nschuste
   * @version 1.0.0
   * @return
   * @since Feb 12, 2016
   */
  public Environment getEnvironment();

  /**
   * Enables to listen to results of executed testcase steps.
   * 
   * @author nschuste
   * @version 1.0.0
   * @param tcBody
   * @param myEventListener
   * @since Feb 11, 2016
   */
  public void registerListener(MyEventListener<TestCaseStepResult> myEventListener);

  /**
   * Way to provision system before executing testcase
   * 
   * @author nschuste
   * @version 1.0.0
   * @param setup
   * @throws SetupException
   * @since Feb 23, 2016
   */
  public void setUp(TestCaseSetup setup) throws SetupException;

  /**
   * Removes traces of application after execution.
   * 
   * @author nschuste
   * @version 1.0.0
   * @param end
   * @since Feb 23, 2016
   */
  public void tearDown(TestCaseEnd end);
}
