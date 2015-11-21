/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import lombok.extern.slf4j.XSlf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoenix.config.CmdArguments;
import com.phoenix.execution.TcExecutor;
import com.phoenix.to.TestCase;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Nov 21, 2015
 */
@Service
@XSlf4j
public class RunnerImpl implements Runner {
  @Autowired
  private TcExecutor executor;
  @Autowired
  private ObjectMapper mapper;

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.Runner#executeArgs(com.phoenix.config.CmdArguments)
   * @since Nov 21, 2015
   */
  @Override
  public void executeArgs(final CmdArguments args) {
    try {
      final TestCase tc = this.loadTC(args.getInputFile());
      this.executor.run(tc);
    } catch (final Exception e) {
      log.catching(e);
    }
  }

  /**
   * @author nschuste
   * @version 1.0.0
   * @param inputFile
   * @return
   * @throws Exception
   * @since Nov 21, 2015
   */
  protected TestCase loadTC(final String inputFile) throws Exception {
    try {
      final File file = new File(inputFile);
      final InputStream stream = new BufferedInputStream(new FileInputStream(file));
      final TestCase tc = this.mapper.readValue(stream, TestCase.class);
      return tc;
    } catch (final IOException e) {
      log.error("Unable to load testcase");
      log.catching(e);
      throw e;
    }
  }
}
