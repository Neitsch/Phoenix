/**
 * Copyright 2016 Nigel Schuster. Very simple controller that only provides all
 */


package com.phoenix.server.rest;

import java.util.List;

import lombok.extern.slf4j.XSlf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phoenix.server.data.TestResultRepository;
import com.phoenix.to.TestResult;

/**
 * Rest Controller only providing a method to fetch all TestResults
 *
 * @author nschuste
 * @version 1.0.0
 * @since Feb 16, 2016
 */
@CrossOrigin
@XSlf4j
@RequestMapping("/tr")
@RestController
public class TestResultRestController {
  @Autowired
  TestResultRepository repository;

  /**
   * Method to fetch all TestResults in the database
   *
   * @author nschuste
   * @version 1.0.0
   * @return Collection of TestResults
   * @since Feb 24, 2016
   */
  @RequestMapping("")
  public List<TestResult> getAllTestResults() {
    // TODO(nigel): Feb 24, 2016 Pageable
    log.entry();
    return log.exit(this.repository.findAll());
  }
}
