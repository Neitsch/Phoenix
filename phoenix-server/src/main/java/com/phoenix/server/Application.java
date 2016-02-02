/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoenix.server.data.TestCaseHeadRepository;
import com.phoenix.server.data.TestCaseRepository;
import com.phoenix.to.TestCase;
import com.phoenix.to.TestCaseHead;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Jan 27, 2016
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.phoenix")
public class Application implements CommandLineRunner {
  @Autowired
  TestCaseHeadRepository repo1;
  @Autowired
  TestCaseRepository repo2;


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
    this.repo1.deleteAll();
    this.repo2.deleteAll();
    this.repo1.save(new ObjectMapper().readValue(this.getClass().getResourceAsStream("setup.tc"),
        TestCaseHead.class));
    final TestCase entity = new TestCase();
    entity.setName("Testname");
    this.repo2.save(entity);
    System.out.println(this.repo2.findByNameIgnoreCaseContains("testnam"));
    // final TestCase tc = new TestCase();
    // tc.setTcBody(new TestCaseBody());
    // TestCaseHead tch = new TestCaseHead();
    // tch.setName("name");
    // this.repo1.save(tch);
    // tch = new TestCaseHead();
    // tch.setId(this.repo1.findAll().get(0).getId());
    // tc.setTcHead(tch);
    // this.repo2.save(tc);
    // final List<TestCase> tcs = this.repo2.findAll();
    // System.out.println(tcs);
  }
}
