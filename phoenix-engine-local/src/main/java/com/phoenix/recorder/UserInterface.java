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
  /**
   * Phases of execution
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  public enum PHASE {
    /**
     * TestCase execution and tear down completed
     *
     * @author nschuste
     * @version 1.0.0
     * @since Feb 23, 2016
     */
    DONE, /**
     * TestCase is currently being executed
     *
     * @author nschuste
     * @version 1.0.0
     * @since Feb 23, 2016
     */
    EXECUTION, /**
     * Gathering necessary information from user
     *
     * @author nschuste
     * @version 1.0.0
     * @since Feb 23, 2016
     */
    PREPARATION, /**
     * Provisioning system
     *
     * @author nschuste
     * @version 1.0.0
     * @since Feb 23, 2016
     */
    SETUP, /**
     * Removing traces of application
     *
     * @author nschuste
     * @version 1.0.0
     * @since Feb 23, 2016
     */
    TEAR_DOWN
  }

  /**
   * Should return String that points to requested object
   *
   * @author nschuste
   * @version 1.0.0
   * @return
   * @since Feb 11, 2016
   */
  String getResourcePath();

  /**
   * Should return desired name of Testcase that is created
   *
   * @author nschuste
   * @version 1.0.0
   * @return
   * @since Feb 23, 2016
   */
  String getTcName();

  /**
   * publishes current phase of execution synchronously
   *
   * @author nschuste
   * @version 1.0.0
   * @param preparation
   * @since Feb 11, 2016
   */
  void phase(PHASE preparation);

  /**
   * Use to halt execution.
   *
   * @author nschuste
   * @version 1.0.0
   * @param sem
   * @since Feb 12, 2016
   */
  void proceed(Semaphore sem);

  /**
   * Should return whether to use the server to retrieve data from
   *
   * @author nschuste
   * @version 1.0.0
   * @return
   * @since Feb 11, 2016
   */
  boolean remoteResource();

  /**
   * Should return whether to save the testcase that was created/modified.
   *
   * @author nschuste
   * @version 1.0.0
   * @return
   * @since Feb 13, 2016
   */
  boolean saveTestCase();

  /**
   * Should return whether to create a new testcase or work on an existing one
   * 
   * @author nschuste
   * @version 1.0.0
   * @return
   * @since Feb 11, 2016
   */
  boolean shouldCreateNew();

  /**
   * Reports result of single testcase step.
   * 
   * @author nschuste
   * @version 1.0.0
   * @param message
   * @since Feb 11, 2016
   */
  void stepResult(ResultWithMessage message);

}
