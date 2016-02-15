/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.server;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoenix.server.data.TestCaseBodyRepository;
import com.phoenix.server.data.TestCaseHeadRepository;
import com.phoenix.server.data.TestCaseRepository;
import com.phoenix.to.TestCase;
import com.phoenix.to.TestCaseBody;
import com.phoenix.to.TestCaseHead;
import com.phoenix.to.TestCaseStep;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Jan 27, 2016
 */
// @RequiredArgsConstructor(onConstructor = @__(@Autowired))
// @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.phoenix")
public class Application implements CommandLineRunner {
  @Autowired
  TestCaseHeadRepository repo1;
  @Autowired
  TestCaseRepository repo2;
  @Autowired
  TestCaseBodyRepository repo3;

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
    this.repo3.deleteAll();
    this.repo2.save(TestCase
        .builder()
        .name("Testname")
        .tcHead(
            this.repo1.save(new ObjectMapper().readValue(
                this.getClass().getResourceAsStream("setup.tc"), TestCaseHead.class)))
                .tcBody(
                    this.repo3.save(TestCaseBody
                        .builder()
                        .lines(
                            Arrays.asList(new TestCaseStep[] {TestCaseStep.builder()
                                .methodName("button.click").args(new String[] {"button"}).build()}))
                                .build())).build());
    System.out.println(this.repo2.findByNameIgnoreCaseContains("testnam"));
  }
}
