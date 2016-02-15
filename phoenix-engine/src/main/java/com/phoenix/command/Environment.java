/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.command;

import java.awt.Frame;
import java.nio.file.Path;

import lombok.Data;

import org.assertj.swing.core.Robot;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 7, 2015
 */
@Data
public class Environment {
  private Path dir;
  private Frame frame;
  private Robot robot;
}
