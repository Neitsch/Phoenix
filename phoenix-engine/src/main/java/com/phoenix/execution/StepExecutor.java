/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.execution;

import com.phoenix.command.Environment;
import com.phoenix.to.TestCaseStep;
import com.phoenix.to.TestCaseStepResult;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 1, 2015
 */
public interface StepExecutor {
  public TestCaseStepResult doStep(TestCaseStep step, Environment env) throws Exception;
}
