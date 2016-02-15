/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.server.service;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Feb 1, 2016
 */
@Service
public class DefaultTestCaseService implements TestCaseService {
  @Produce(uri = "direct:startTestCase")
  ProducerTemplate template;

  @Override
  public void start(final String id) {
    this.template.sendBody(id);
  }
}
