/**
 * Copyright 2015 Nigel Schuster. Convenience class to better return the result of a testcase step.
 */


package com.phoenix.to;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ResultWithMessage contains the outcome of a TestCaseStep with a possible error message.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Dec 25, 2015
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultWithMessage {
  /**
   * empty if no error, otherwise should contain exception that occured
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private Optional<Exception> exception;
  /**
   * Message for user to resolve exception
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private Optional<String> message;
  /**
   * Status of the step.
   * 
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private TestCaseStepResultStatus status;
}
