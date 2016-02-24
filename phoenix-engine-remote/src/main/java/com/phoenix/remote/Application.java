/**
 * Copyright 2016 Nigel Schuster. This is the entry point for a remote engine, polls from queue
 * whenever it's free.
 */


package com.phoenix.remote;

import lombok.extern.slf4j.XSlf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Main entry point for a remote engine.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Feb 2, 2016
 */
@XSlf4j
@ComponentScan("com.phoenix")
@SpringApplicationConfiguration
public class Application implements CommandLineRunner {

  public static void main(final String[] args) throws Exception {
    log.entry((Object) args);
    SpringApplication.run(Application.class, args);
    while (true) {
      // holds main thread open - otherwise app shuts down
      Thread.sleep(1000);
    }
  }

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
   * @since Feb 2, 2016
   */
  @Override
  public void run(final String... args) throws Exception {}

}
