/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.server.rest;

import java.util.Collection;
import java.util.HashSet;

import lombok.extern.slf4j.XSlf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
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

  @ResponseStatus(code = HttpStatus.OK)
  @RequestMapping(value = "/addTc/{tsId}/{tcId}", method = RequestMethod.GET)
  public TestSuite addTestcase(@PathVariable("tsId") final String tsId,
      @PathVariable("tcId") final String tcId) {
    log.entry(tsId, tcId);
    TestSuite ts = this.repository.findOne(tsId);
    ts.getTestcaseids().add(tcId);
    return log.exit(this.repository.save(ts));
  }

  @RequestMapping("/create")
  public TestSuite createTestSuite() {
    log.entry();
    return log.exit(this.repository.save(TestSuite.builder().testcaseids(new HashSet<>()).build()));
  }

  @RequestMapping("")
  public Collection<TestSuite> getAllTestResults() {
    log.entry();
    return log.exit(this.repository.findAll());
  }
}
