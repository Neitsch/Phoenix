/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix;

import com.phoenix.config.CmdArguments;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Nov 21, 2015
 */
public interface Runner {
  /**
   * Executes a Testcase based on the arguments.
   * 
   * @author nschuste
   * @version 1.0.0
   * @param args
   * @since Nov 21, 2015
   */
  public void executeArgs(final CmdArguments args);
}
