/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.execution;

import com.phoenix.to.TestCase;
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
   * @since Nov 21, 2015
   */
  public TestResult run(final TestCase tc);

}
