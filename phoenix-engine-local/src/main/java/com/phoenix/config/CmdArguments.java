/**
 * Copyright 2015 Nigel Schuster. Arguments parsed from Application launch.
 */


package com.phoenix.config;

import lombok.Data;

import org.kohsuke.args4j.Option;

/**
 * Some basic information prior to setup.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Nov 21, 2015
 */
@Data
public class CmdArguments {
  /**
   * Location of configuration file for instance.
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  @Option(hidden = false, required = true, name = "-config",
      usage = "relative or absolute path to the configuration file.", metaVar = "FILEPATH")
  private String configLocation;

  /**
   * Graphic User interface or not.
   * 
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  @Option(hidden = false, required = false, name = "-g", usage = "GUI interface")
  private boolean gui;
}
