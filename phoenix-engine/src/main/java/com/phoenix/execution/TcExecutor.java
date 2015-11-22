/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.execution;

import com.phoenix.config.Configuration;
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
   * @param config
   * @since Nov 21, 2015
   */
  public TestResult run(final TestCase tc, Configuration config);

}
