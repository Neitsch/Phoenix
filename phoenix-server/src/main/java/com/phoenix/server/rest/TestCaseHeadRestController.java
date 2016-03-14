/**
 * Copyright 2016 Nigel Schuster. Controller to retrieve information about TestCaseHeads.
 */


package com.phoenix.server.rest;

import java.util.Collection;

import lombok.extern.slf4j.XSlf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.phoenix.server.data.TestCaseHeadRepository;
import com.phoenix.to.TestCaseHead;

/**
 * RestController providing basic Verbs to fetch {@TestCaseHead}
 *
 * @author nschuste
 * @version 1.0.0
 * @since Jan 27, 2016
 */
@RequestMapping("/tch")
@RestController
@XSlf4j
@CrossOrigin
public class TestCaseHeadRestController {
  @Autowired
  private TestCaseHeadRepository repository;

  /**
   * Saves the given TestCaseHead to the database
   *
   * @author nschuste
   * @version 1.0.0
   * @param head Object to save
   * @return ID of saved object.
   * @since Feb 24, 2016
   */
  @RequestMapping(method = RequestMethod.POST)
  public String createOrUpdate(@RequestBody final TestCaseHead head) {
    log.entry(head);
    return log.exit(this.repository.save(head).getId());
  }

  /**
   * Fetches all available Heads.
   *
   * @author nschuste
   * @version 1.0.0
   * @return List of setups.
   * @since Feb 24, 2016
   */
  @RequestMapping
  public Collection<TestCaseHead> getAllSetups() {
    log.entry();
    return log.exit(this.repository.findAll());
  }

  /**
   * Returns specific Head by ID.
   *
   * @author nschuste
   * @version 1.0.0
   * @param id id of Head
   * @return requested Head
   * @since Feb 24, 2016
   */
  @RequestMapping("/{setupId}")
  public TestCaseHead getSetup(@PathVariable("setupId") final String id) {
    log.entry(id);
    return log.exit(this.repository.findOne(id));
  }
}
