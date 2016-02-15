/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.remote;

import lombok.extern.slf4j.XSlf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phoenix.execution.TcExecutor;
import com.phoenix.to.TestCase;
import com.phoenix.to.TestResult;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Feb 2, 2016
 */
@XSlf4j
@Service
public class ExecWrapper {
  @Autowired
  TcExecutor exec;

  public TestResult result(final TestCase tc) throws Exception {
    try {
      this.exec.setUp(tc.getTcHead().getSetup());
      return TestResult.builder().result(this.exec.execute(tc.getTcBody())).success(true).build();
    } catch (Exception e) {
      log.catching(e);
      throw e;
    } finally {
      this.exec.tearDown(null);
    }
  }
}
