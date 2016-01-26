/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.recorder;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoenix.Runner;
import com.phoenix.command.GuiEventDispatcher;
import com.phoenix.config.CmdArguments;
import com.phoenix.execution.TcExecutor;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Jan 26, 2016
 */
public class GuiRecorder implements Runner {

  @Autowired
  GuiEventDispatcher dispatcher;
  @Autowired
  TcExecutor executor;
  @Autowired
  ObjectMapper mapper;

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.Runner#executeArgs(com.phoenix.config.CmdArguments)
   * @since Jan 23, 2016
   */
  @Override
  public void executeArgs(final CmdArguments args) {}
}
