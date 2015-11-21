/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.config;

import org.kohsuke.args4j.Option;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Nov 21, 2015
 */
public class CmdArguments {
  @Option(hidden = false, required = true, name = "-in",
      usage = "relative or absolute path to file that should be executed.", help = true,
      metaVar = "FILEPATH")
  private String inputFile;

  /**
   * Gets value for inputFile.
   * 
   * @author nschuste
   * @version 1.0.0
   * @return inputFile of type {@link String}
   * @since Nov 21, 2015
   */
  public final String getInputFile() {
    return this.inputFile;
  }

  /**
   * Sets value for inputFile.
   * 
   * @author nschuste
   * @version 1.0.0
   * @param inputFile inputFile to set of type {@link String}
   * @since Nov 21, 2015
   */
  public final void setInputFile(final String inputFile) {
    this.inputFile = inputFile;
  }
}
