/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.execution;

import com.phoenix.to.TestCaseBody;
import com.phoenix.to.TestCaseEnd;
import com.phoenix.to.TestCaseSetup;
import com.phoenix.to.TestResult;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Nov 21, 2015
 */
public interface TcExecutor {

  /**
   * @author nschuste
   * @version 1.0.0
   * @param tc
   * @param config
   * @since Nov 21, 2015
   */
  public TestResult execute(final TestCaseBody tc);

  public void setUp(TestCaseSetup setup);

  public void tearDown(TestCaseEnd end);
}
