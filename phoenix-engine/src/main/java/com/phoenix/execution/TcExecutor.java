/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.execution;

import com.phoenix.exception.SetupException;
import com.phoenix.to.TestCaseBody;
import com.phoenix.to.TestCaseBodyResult;
import com.phoenix.to.TestCaseEnd;
import com.phoenix.to.TestCaseSetup;

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
  public TestCaseBodyResult execute(final TestCaseBody tc);

  public void setUp(TestCaseSetup setup) throws SetupException;

  public void tearDown(TestCaseEnd end);
}
