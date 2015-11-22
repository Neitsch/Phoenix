/**
 * Copyright 2015 Nigel Schuster.
 */
package com.phoenix;

import lombok.extern.slf4j.XSlf4j;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.phoenix.config.CmdArguments;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Nov 21, 2015
 */
@SpringBootApplication
@XSlf4j
public class Application {
  @Autowired
  private ApplicationContext context;
  @Autowired
  private Runner runner;

  /**
   * Main method to execute TC locally.
   *
   * @author nschuste
   * @version 1.0.0
   * @param args program arguments
   * @throws CmdLineException get's thrown if parsing of args fails.
   * @since Nov 21, 2015
   */
  public static void main(final String[] args) throws CmdLineException {
    final ApplicationContext con = SpringApplication.run(Application.class);
    con.getBean(Application.class).doMain(args);
  }

  protected void doMain(final String[] args) {
    final CmdArguments arguments = this.extractArgs(args);
    this.runner.executeArgs(arguments);
  }

  /**
   * Creates an object based on the arguments.
   *
   * @author nschuste
   * @version 1.0.0
   * @param args
   * @return
   * @since Nov 21, 2015
   */
  protected CmdArguments extractArgs(final String[] args) {
    final CmdArguments arguments = new CmdArguments();
    final CmdLineParser parser = new CmdLineParser(arguments);
    try {
      parser.parseArgument(args);
      return arguments;
    } catch (final CmdLineException e) {
      log.catching(e);
      System.err.println(e.getMessage());
      parser.printUsage(System.err);
      throw new RuntimeException("Not able to correctly handle Command Line arguments.");
    }
  }
}
