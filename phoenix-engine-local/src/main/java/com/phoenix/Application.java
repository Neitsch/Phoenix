/**
 * Copyright 2016 Nigel Schuster. Main entry point for local application.
 */


package com.phoenix;

import lombok.extern.slf4j.XSlf4j;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoenix.config.CmdArguments;
import com.phoenix.recorder.CmdInterface;
import com.phoenix.recorder.GuiInterface;
import com.phoenix.recorder.UserInterface;

/**
 * Parses command line arguments and passes execution on.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Feb 11, 2016
 */
@XSlf4j
@ComponentScan
public class Application implements CommandLineRunner {
  /**
   * Argument stored as a field to be used after SpringApplication has booted up.
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private CmdArguments arguments;
  @Autowired
  private ApplicationContext ctx;
  @Autowired
  private SuperRunner sup;

  public static void main(final String[] args) {
    log.entry((Object) args);
    SpringApplication.run(Application.class, args);
    log.exit();
    System.exit(0);
  }

  private static final CmdArguments parse(final String... args) throws Exception {
    log.entry((Object) args);
    return log.exit(parseCmd(CmdArguments.class, args));
  }

  private static <T> T parseCmd(final Class<T> clazz, final String... args) throws Exception {
    log.entry(clazz, args);
    final T myArgs = clazz.newInstance();
    final CmdLineParser parser = new CmdLineParser(myArgs);
    try {
      log.debug("Parsing CMD args");
      parser.parseArgument(args);
    } catch (final CmdLineException e) {
      log.catching(e);
      parser.printUsage(System.out);
      throw e;
    }
    return log.exit(myArgs);
  }

  @Bean
  public ObjectMapper mapper() {
    log.debug("Creating Jackson ObjectMapper");
    return new ObjectMapper();
  }

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
   * @since Feb 11, 2016
   */
  @Override
  public void run(final String... args) throws Exception {
    log.entry((Object) args);
    this.arguments = parse(args);
    final UserInterface intf = this.arguments.isGui() ? new GuiInterface() : new CmdInterface();
    this.ctx.getAutowireCapableBeanFactory().autowireBean(intf);
    log.info("Autowired UI, Starting execution");
    final Thread t = new Thread(new Runnable() {
      private CmdArguments arguments;
      private UserInterface intf;
      private SuperRunner runner;

      public Runnable init(final UserInterface intf, final SuperRunner runner,
          final CmdArguments arguments) {
        this.intf = intf;
        this.runner = runner;
        this.arguments = arguments;
        return this;
      }

      @Override
      public void run() {
        log.entry();
        this.runner.run(this.arguments.getConfigLocation(), this.intf);
        log.exit();
      }
    }.init(intf, this.sup, this.arguments));
    t.run();
    log.exit();
  }
}
