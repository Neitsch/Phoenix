/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.recorder;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

import lombok.extern.slf4j.XSlf4j;

import com.phoenix.to.ResultWithMessage;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Feb 11, 2016
 */
@XSlf4j
public class CmdInterface implements UserInterface {
  private final Scanner scan;

  /**
   * @author nschuste
   * @version 1.0.0
   * @since Feb 11, 2016
   */
  public CmdInterface() {
    this.scan = new Scanner(System.in);
  }

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.recorder.UserInterface#getResourcePath()
   * @since Feb 11, 2016
   */
  @Override
  public String getResourcePath() {
    System.out.println("Please enter the path to the resource:");
    return this.scan.nextLine();
  }

  /**
   * {@inheritDoc}
   * 
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.recorder.UserInterface#getTcName()
   * @since Feb 23, 2016
   */
  @Override
  public String getTcName() {
    System.out.println("Please enter the name of the testcase:");
    return this.scan.nextLine();
  }

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.recorder.UserInterface#phase(com.phoenix.recorder.UserInterface.PHASE)
   * @since Feb 11, 2016
   */
  @Override
  public void phase(final PHASE preparation) {
    log.info("Now in phase " + preparation);
    if (preparation == PHASE.EXECUTION) {
      this.scan.nextLine();
    }
  }

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.recorder.UserInterface#proceed()
   * @since Feb 12, 2016
   */
  @Override
  public void proceed(final Semaphore sem) {
    this.scan.nextLine();
    sem.release();
  }

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.recorder.UserInterface#remoteResource()
   * @since Feb 11, 2016
   */
  @Override
  public boolean remoteResource() {
    String read;
    do {
      System.out.println("Will you be using a server to get the data?");
      read = this.scan.nextLine();
    } while (!read.equals("y") && !read.equals("n"));
    return read.equals("y");
  }

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.recorder.UserInterface#saveTestCase()
   * @since Feb 13, 2016
   */
  @Override
  public boolean saveTestCase() {
    String read;
    do {
      System.out.println("Save testcase?");
      read = this.scan.nextLine();
    } while (!read.equals("y") && !read.equals("n"));
    return read.equals("y");
  }

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.recorder.UserInterface#shouldCreateNew()
   * @since Feb 11, 2016
   */
  @Override
  public boolean shouldCreateNew() {
    String read;
    do {
      System.out.println("Do you want to create a new testcase?");
      read = this.scan.nextLine();
    } while (!read.equals("y") && !read.equals("n"));
    return read.equals("y");
  }

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.recorder.UserInterface#stepResult(com.phoenix.to.TestCaseStepResult)
   * @since Feb 11, 2016
   */
  @Override
  public void stepResult(final ResultWithMessage e) {
    log.info("Step result: " + e.toString());
  }
}
