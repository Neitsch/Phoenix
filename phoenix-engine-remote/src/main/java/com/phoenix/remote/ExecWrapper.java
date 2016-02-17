/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.remote;

import lombok.extern.slf4j.XSlf4j;

import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phoenix.execution.TcExecutor;
import com.phoenix.to.TestCase;
import com.phoenix.to.TestCaseBodyResult;
import com.phoenix.to.TestCaseStepResultStatus;
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
      TestCaseBodyResult res = this.exec.execute(tc.getTcBody());
      return TestResult
          .builder()
          .result(res)
          .title(tc.getName())
          .tcId(tc.getId())
          .success(
              !IterableUtils.matchesAny(res.getStepResults(),
                  arg0 -> arg0.getResult() == TestCaseStepResultStatus.EXCEPTION)).build();
    } catch (Exception e) {
      log.catching(e);
      throw e;
    } finally {
      this.exec.tearDown(null);
    }
  }
}
