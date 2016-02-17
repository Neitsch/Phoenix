/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.server.service;

import com.phoenix.to.TestResult;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Feb 15, 2016
 */
public interface TestResultService {
  /**
   * @author nschuste
   * @version 1.0.0
   * @param result
   * @return
   * @since Feb 15, 2016
   */
  String save(TestResult result);
}
