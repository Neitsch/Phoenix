/**
 * Copyright 2016 Nigel Schuster.
 */


package org.phoenix.server.rest;

import java.util.Collection;

import javax.websocket.server.PathParam;

import lombok.extern.slf4j.XSlf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.phoenix.server.data.TestCaseHeadRepository;
import com.phoenix.to.TestCaseHead;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Jan 27, 2016
 */
@RequestMapping("/tch")
@RestController
@XSlf4j
public class TestCaseHeadRestController {
  @Autowired
  private TestCaseHeadRepository repository;

  @RequestMapping(method = RequestMethod.POST)
  public String createOrUpdate(@RequestBody final TestCaseHead head) {
    log.entry(head);
    return log.exit(this.repository.save(head).getId());
  }

  @RequestMapping
  public Collection<TestCaseHead> getAllSetups() {
    log.entry();
    return log.exit(this.repository.findAll());
  }

  @RequestMapping("/{setupId}")
  public TestCaseHead getSetup(@PathParam("setupId") final String id) {
    log.entry(id);
    return log.exit(this.repository.findOne(id));
  }
}
