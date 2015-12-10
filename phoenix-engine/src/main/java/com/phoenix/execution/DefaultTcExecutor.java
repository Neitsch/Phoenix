/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.execution;

import java.util.Iterator;

import lombok.extern.slf4j.XSlf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phoenix.to.TestCaseBody;
import com.phoenix.to.TestCaseBodyResult;
import com.phoenix.to.TestCaseEnd;
import com.phoenix.to.TestCaseSetup;
import com.phoenix.to.TestCaseStep;
import com.phoenix.to.TestCaseStepResult;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Nov 21, 2015
 */
@XSlf4j
@Service
public class DefaultTcExecutor implements TcExecutor {
  @Autowired
  StepExecutor exec;

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.execution.TcExecutor#execute(com.phoenix.to.TestCaseBody)
   * @since Dec 1, 2015
   */
  @Override
  public TestCaseBodyResult execute(final TestCaseBody tc) {
    final TestCaseBodyResult res = new TestCaseBodyResult();
    final Iterator<TestCaseStep> it = tc.getLines().iterator();
    while (it.hasNext()) {
      try {
        final TestCaseStep step = it.next();
        final TestCaseStepResult myRes = this.exec.doStep(step);
        res.getStepResults().add(myRes);
      } catch (final Exception e) {
        log.catching(e);
      }
    }
    return res;
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
