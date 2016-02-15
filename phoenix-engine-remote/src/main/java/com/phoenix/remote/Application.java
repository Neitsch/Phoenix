/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.remote;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Feb 2, 2016
 */
@ComponentScan("com.phoenix")
@SpringApplicationConfiguration
public class Application implements CommandLineRunner {

  public static void main(final String[] args) throws Exception {
    SpringApplication.run(Application.class, args);
    while (true) {
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
