/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.recorder;

import java.util.concurrent.Semaphore;

import com.phoenix.to.ResultWithMessage;


/**
 * @author nschuste
 * @version 1.0.0
 * @since Feb 11, 2016
 */
public interface UserInterface {
  public enum PHASE {
    DONE, EXECUTION, PREPARATION, SETUP, TEAR_DOWN
  }

  /**
   * @author nschuste
   * @version 1.0.0
   * @return
   * @since Feb 11, 2016
   */
  String getResourcePath();

  /**
   * @author nschuste
   * @version 1.0.0
   * @param preparation
   * @since Feb 11, 2016
   */
  void phase(PHASE preparation);

  /**
   * @author nschuste
   * @version 1.0.0
   * @param sem
   * @since Feb 12, 2016
   */
  void proceed(Semaphore sem);

  /**
   * @author nschuste
   * @version 1.0.0
   * @return
   * @since Feb 11, 2016
   */
  boolean remoteResource();

  /**
   * @author nschuste
   * @version 1.0.0
   * @return
   * @since Feb 11, 2016
   */
  boolean shouldCreateNew();

  /**
   * @author nschuste
   * @version 1.0.0
   * @param message
   * @since Feb 11, 2016
   */
  void stepResult(ResultWithMessage message);

}
