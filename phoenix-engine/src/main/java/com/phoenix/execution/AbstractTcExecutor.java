/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.execution;

import lombok.extern.slf4j.XSlf4j;

import com.phoenix.to.TestCaseBody;
import com.phoenix.to.TestCaseEnd;
import com.phoenix.to.TestCaseSetup;
import com.phoenix.to.TestResult;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Nov 21, 2015
 */
@XSlf4j
public class AbstractTcExecutor implements TcExecutor {
  /**
   * {@inheritDoc}
   * 
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.execution.TcExecutor#execute(com.phoenix.to.TestCaseBody)
   * @since Dec 1, 2015
   */
  @Override
  public TestResult execute(final TestCaseBody tc) {
    return null;
  }

  /**
   * {@inheritDoc}
   * 
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.execution.TcExecutor#setUp(com.phoenix.to.TestCaseSetup)
   * @since Dec 1, 2015
   */
  @Override
  public void setUp(final TestCaseSetup setup) {}

  /**
   * {@inheritDoc}
   * 
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.execution.TcExecutor#tearDown(com.phoenix.to.TestCaseEnd)
   * @since Dec 1, 2015
   */
  @Override
  public void tearDown(final TestCaseEnd end) {}

}
