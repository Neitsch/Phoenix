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
  public void executeArgs(final CmdArguments args);
}
