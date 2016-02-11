/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.remote;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Feb 2, 2016
 */
@SpringApplicationConfiguration
public class Application implements CommandLineRunner {

  public static void main(final String[] args) throws Exception {
    SpringApplication.run(Application.class, args);
  }

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
   * @since Feb 2, 2016
   */
  public void run(final String... args) throws Exception {}

}
