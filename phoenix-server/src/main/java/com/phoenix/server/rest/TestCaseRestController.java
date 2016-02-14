/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.server.rest;

import java.util.Collection;
import java.util.List;

import lombok.extern.slf4j.XSlf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.phoenix.server.data.TestCaseRepository;
import com.phoenix.server.service.TestCaseService;
import com.phoenix.to.TestCase;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Jan 27, 2016
 */
@XSlf4j
@RequestMapping("/tc")
@RestController
public class TestCaseRestController {
  @Autowired
  private TestCaseRepository repository;
  @Autowired
  TestCaseService service;

  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(method = RequestMethod.POST)
  public void bla(@RequestBody final TestCase tc) {
    log.entry(tc);
    log.exit(this.repository.save(tc));
  }

  @RequestMapping("/enqueue/{tcid}")
  public String enqueue(@PathVariable("tcid") final String id) {
    log.entry(id);
    this.service.start(id);
    return "";
  }

  @RequestMapping("")
  public List<TestCase> getAllTestCases() {
    log.entry();
    return log.exit(this.repository.findAll());
  }

  @RequestMapping("/id/{tcid}")
  public TestCase getTestCase(@PathVariable("tcid") final String id) {
    log.entry(id);
    return log.exit(this.repository.findOne(id));
  }

  @RequestMapping("/name/{name}")
  public Collection<TestCase> getTestCaseByName(@PathVariable("name") final String name) {
    log.entry(name);
    return log.exit(this.repository.findByNameIgnoreCaseContains(name));
  }
}
