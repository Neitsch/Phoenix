/**
 * Copyright 2016 Nigel Schuster. Controller to retrieve information about TestCases.
 */


package com.phoenix.server.rest;

import java.util.Collection;
import java.util.List;

import lombok.extern.slf4j.XSlf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.phoenix.server.data.TestCaseBodyRepository;
import com.phoenix.server.data.TestCaseRepository;
import com.phoenix.server.service.TestCaseService;
import com.phoenix.to.TestCase;

/**
 * RestController providing basic Verbs to fetch {@TestCase}
 *
 * @author nschuste
 * @version 1.0.0
 * @since Jan 27, 2016
 */
@CrossOrigin
@XSlf4j
@RequestMapping("/tc")
@RestController
public class TestCaseRestController {
  @Autowired
  private TestCaseRepository repository;
  @Autowired
  private TestCaseBodyRepository bodyRepository;
  @Autowired
  TestCaseService service;

  /**
   * Saves TestCase (and possibly TestCaseBody)
   *
   * @author nschuste
   * @version 1.0.0
   * @param tc Testcase to save
   * @return id of testcase
   * @since Feb 24, 2016
   */
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  @RequestMapping(method = RequestMethod.POST)
  public String bla(@RequestBody final TestCase tc) {
    log.entry(tc);
    if (tc.getTcBody().getId() == null) {
      this.bodyRepository.save(tc.getTcBody());
    }
    return log.exit(this.repository.save(tc)).getId();
  }

  /**
   * Enqueues TestCaseID to be executed.
   *
   * @author nschuste
   * @version 1.0.0
   * @param id id of TC to enqueue
   * @return nothing
   * @since Feb 24, 2016
   */
  @RequestMapping("/enqueue/{tcid}")
  public String enqueue(@PathVariable("tcid") final String id) {
    log.entry(id);
    this.service.start(id);
    return "";
  }

  /**
   * Returns all available Testcases.
   *
   * @author nschuste
   * @version 1.0.0
   * @return List of TCs.
   * @since Feb 24, 2016
   */
  @RequestMapping("")
  public List<TestCase> getAllTestCases() {
    log.entry();
    return log.exit(this.repository.findAll());
  }

  /**
   * Returns specific Testcase by ID.
   *
   * @author nschuste
   * @version 1.0.0
   * @param id id of testcase
   * @return requested TC.
   * @since Feb 24, 2016
   */
  @RequestMapping("/id/{tcid}")
  public TestCase getTestCase(@PathVariable("tcid") final String id) {
    log.entry(id);
    return log.exit(this.repository.findOne(id));
  }

  /**
   * Requestmethod to query a testcase by its name
   *
   * @author nschuste
   * @version 1.0.0
   * @param name case insensitive part of the name
   * @return all testcases that match the query
   * @since Feb 24, 2016
   */
  @RequestMapping("/name/{name}")
  public Collection<TestCase> getTestCaseByName(@PathVariable("name") final String name) {
    log.entry(name);
    return log.exit(this.repository.findByNameIgnoreCaseContains(name));
  }
}
