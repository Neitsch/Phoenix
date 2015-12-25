/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.exception;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 21, 2015
 */
public class SetupException extends Exception {
  /**
   * @author nschuste
   * @version 1.0.0
   * @since Dec 21, 2015
   */
  public SetupException(final Exception e) {
    super(e);
  }
}
