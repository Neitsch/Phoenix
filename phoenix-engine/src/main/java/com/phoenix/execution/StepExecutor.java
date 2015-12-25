/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.execution;

import com.phoenix.command.Environment;
import com.phoenix.to.ResultWithMessage;
import com.phoenix.to.TestCaseStep;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 1, 2015
 */
public interface StepExecutor {
  public ResultWithMessage doStep(TestCaseStep step, Environment env) throws Exception;
}
