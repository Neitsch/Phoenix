/**
 * Copyright 2015 Nigel Schuster. Implementation should handle execution of a single step.
 */


package com.phoenix.execution;

import com.phoenix.command.Environment;
import com.phoenix.to.ResultWithMessage;
import com.phoenix.to.TestCaseStep;

/**
 * StepExecutor provides way to execute a {@TestCaseStep}
 *
 * @author nschuste
 * @version 1.0.0
 * @since Dec 1, 2015
 */
public interface StepExecutor {
  /**
   * Executes single step and returns result.
   *
   * @author nschuste
   * @version 1.0.0
   * @param step step to execute
   * @param env
   * @return result of step.
   * @throws Exception
   * @since Feb 23, 2016
   */
  public ResultWithMessage doStep(TestCaseStep step, Environment env) throws Exception;
}
