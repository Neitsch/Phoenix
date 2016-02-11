/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.remote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phoenix.exception.SetupException;
import com.phoenix.execution.TcExecutor;
import com.phoenix.to.TestCase;
import com.phoenix.to.TestResult;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Feb 2, 2016
 */
@Service
public class ExecWrapper {
  @Autowired
  TcExecutor exec;

  public TestResult result(final TestCase tc) throws SetupException {
    try {
      this.exec.setUp(tc.getTcHead().getSetup());
      return TestResult.builder().result(this.exec.execute(tc.getTcBody())).success(true).build();
    } finally {
      this.exec.tearDown(null);
    }
  }
}
