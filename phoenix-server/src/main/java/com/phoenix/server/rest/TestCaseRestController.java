/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.server.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import lombok.extern.slf4j.XSlf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phoenix.server.data.TestCaseRepository;
import com.phoenix.to.TestCase;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Jan 27, 2016
 */
@XSlf4j
@RestController
public class TestCaseRestController {
  @Autowired
  private TestCaseRepository repository;

  @RequestMapping("")
  public List<TestCase> getAllTestCases() {
    log.entry();
    return log.exit(this.repository.findAll());
  }

  @RequestMapping("/{tcid}")
  public TestCase getTestCase(@PathParam("tcid") final String id) {
    log.entry(id);
    return log.exit(this.repository.findOne(id));
  }
}
