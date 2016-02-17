/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.server.rest;

import java.util.List;

import lombok.extern.slf4j.XSlf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phoenix.server.data.TestResultRepository;
import com.phoenix.to.TestResult;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Feb 16, 2016
 */
@XSlf4j
@RequestMapping("/tr")
@RestController
public class TestResultRestController {
  @Autowired
  TestResultRepository repository;

  @RequestMapping("")
  public List<TestResult> getAllTestCases() {
    log.entry();
    return log.exit(this.repository.findAll());
  }
}
