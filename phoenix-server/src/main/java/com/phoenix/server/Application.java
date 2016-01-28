/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Jan 27, 2016
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.phoenix")
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
   * @since Jan 27, 2016
   */
  @Override
  public void run(final String... args) throws Exception {

  }
}
