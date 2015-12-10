/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.to;

import lombok.Data;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 1, 2015
 */
@Data
public class TestCaseStepResult {
  private TestCaseStepResultStatus result;
  private TestCaseStep step;
}
