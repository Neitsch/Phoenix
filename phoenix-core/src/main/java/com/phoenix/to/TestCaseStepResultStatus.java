/**
 * Copyright 2015 Nigel Schuster. Simple enum that contains the possible outcome. Possibly I should
 * add warning as an option, when a non blocking command fails (e.g. a check).
 */


package com.phoenix.to;

/**
 * TestCaseStepResultStatus contains the possible outcomes of performing a testcase step.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Dec 10, 2015
 */
public enum TestCaseStepResultStatus {
  /**
   * If method threw an exception.
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  EXCEPTION, /**
   * Step was not executed (possibly due to previous failure).
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  NOT_EXECUTED, /**
   * Step worked.
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  SUCCESS
}
