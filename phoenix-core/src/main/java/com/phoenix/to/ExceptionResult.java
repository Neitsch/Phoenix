/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.to;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 10, 2015
 */
public class ExceptionResult implements TestCaseStepResultStatus {
  private final Exception e;

  /**
   * @author nschuste
   * @version 1.0.0
   * @param e
   * @since Dec 10, 2015
   */
  public ExceptionResult(final Exception e) {
    this.e = e;
  }

}
