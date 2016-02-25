/**
 * Copyright 2015 Nigel Schuster. This stores the result of a testcase. Note that it also holds the
 * testcase step to circumvent modifications of the original testcase.
 */


package com.phoenix.to;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * TestCaseStepResult holds information on the result of a testcase step.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Dec 1, 2015
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class TestCaseStepResult implements Serializable {
  /**
   * Outcome of the performed step.
   * 
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private TestCaseStepResultStatus result;
  /**
   * Step that was performed.
   * 
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private TestCaseStep step;
}
