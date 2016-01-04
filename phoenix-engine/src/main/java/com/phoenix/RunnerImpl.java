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
import com.phoenix.config.Configuration;
import com.phoenix.execution.TcExecutor;
import com.phoenix.to.TestCase;
import com.phoenix.to.TestResult;

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
      final Configuration config = this.loadConfig(args.getConfigLocation());
      final TestResult result = this.executor.execute(tc.getTcBody());
      log.info(result.toString());
    } catch (final Exception e) {
      log.catching(e);
    }
  }

  protected <T> T loadFile(final String input, final Class<T> clazz) throws IOException {
    try {
      final File file = new File(input);
      final InputStream stream = new BufferedInputStream(new FileInputStream(file));
      final T res = this.mapper.readValue(stream, clazz);
      return res;
    } catch (final IOException e) {
      log.error("Unable to load " + clazz.toString());
      log.catching(e);
      throw e;
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
    return this.loadFile(inputFile, TestCase.class);
  }

  /**
   * @author nschuste
   * @version 1.0.0
   * @param configLocation
   * @return
   * @throws IOException
   * @since Nov 21, 2015
   */
  private Configuration loadConfig(final String configLocation) throws IOException {
    return this.loadFile(configLocation, Configuration.class);
  }
}
