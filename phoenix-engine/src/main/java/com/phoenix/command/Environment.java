/**
 * Copyright 2015 Nigel Schuster. Holds information about the running environment for the testcase.
 */


package com.phoenix.command;

import java.awt.Frame;
import java.nio.file.Path;

import lombok.Data;

import org.assertj.swing.core.Robot;

/**
 * The environment stores meta information of the running testcase.
 * 
 * @author nschuste
 * @version 1.0.0
 * @since Dec 7, 2015
 */
@Data
public class Environment {
  /**
   * Working directory.
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private Path dir;
  /**
   * Frame of the target application
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private Frame frame;
  /**
   * Robot to execute GUI actions.
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private Robot robot;
}
