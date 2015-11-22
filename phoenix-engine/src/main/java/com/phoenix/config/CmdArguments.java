/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.config;

import lombok.Data;

import org.kohsuke.args4j.Option;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Nov 21, 2015
 */
@Data
public class CmdArguments {
  @Option(hidden = false, required = true, name = "-config",
      usage = "relative or absolute path to the configuration file.", metaVar = "FILEPATH")
  private String configLocation;

  @Option(hidden = false, required = true, name = "-in",
      usage = "relative or absolute path to file that should be executed.", metaVar = "FILEPATH")
  private String inputFile;
}
