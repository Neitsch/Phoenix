/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.server.rest;

import java.util.ArrayList;
import java.util.Collection;

import lombok.extern.slf4j.XSlf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phoenix.server.data.TestSuiteRepository;
import com.phoenix.to.TestSuite;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Mar 5, 2016
 */
@CrossOrigin
@XSlf4j
@RequestMapping("/ts")
@RestController
public class TestSuiteRestController {
  @Autowired
  TestSuiteRepository repository;

  @RequestMapping("/create")
  public TestSuite createTestSuite() {
    log.entry();
    return log.exit(this.repository
        .save(TestSuite.builder().testcaseids(new ArrayList<>()).build()));
  }

  @RequestMapping("")
  public Collection<TestSuite> getAllTestResults() {
    log.entry();
    return log.exit(this.repository.findAll());
  }
}
