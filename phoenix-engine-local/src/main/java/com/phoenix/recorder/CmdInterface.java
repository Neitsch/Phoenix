/**
 * Copyright 2016 Nigel Schuster. Command line interface (only UI currently implemented) to get and
 * present information from/to the user
 */


package com.phoenix.recorder;

import java.util.Scanner;
import java.util.concurrent.Semaphore;

import lombok.extern.slf4j.XSlf4j;

import com.phoenix.to.ResultWithMessage;

/**
 * CmdInterface is an interface to interact with the user.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Feb 11, 2016
 */
@XSlf4j
public class CmdInterface implements UserInterface {
  /**
   * Scanner to read from std in
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
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
    log.entry();
    System.out.println("Please enter the path to the resource:");
    return log.exit(this.scan.nextLine());
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
    log.entry();
    System.out.println("Please enter the name of the testcase:");
    return log.exit(this.scan.nextLine());
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
    log.entry(sem);
    this.scan.nextLine();
    sem.release();
    log.exit();
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
    log.entry();
    String read;
    do {
      System.out.println("Will you be using a server to get the data?");
      read = this.scan.nextLine();
    } while (!read.equals("y") && !read.equals("n"));
    return log.exit(read.equals("y"));
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
    log.entry();
    String read;
    do {
      System.out.println("Save testcase?");
      read = this.scan.nextLine();
    } while (!read.equals("y") && !read.equals("n"));
    return log.exit(read.equals("y"));
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
    log.entry();
    String read;
    do {
      System.out.println("Do you want to create a new testcase?");
      read = this.scan.nextLine();
    } while (!read.equals("y") && !read.equals("n"));
    return log.exit(read.equals("y"));
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
