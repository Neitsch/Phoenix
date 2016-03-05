/**
 * Copyright 2016 Nigel Schuster. Simple implementation of a TestCaseService to launch a testcase.
 */


package com.phoenix.server.service;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Service;

import com.phoenix.to.TestSuite;

/**
 * TestCaseService to start a testcase.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Feb 1, 2016
 */
@Service
public class DefaultTestCaseService implements TestCaseService {
  @MessagingGateway
  public interface EchoGateway {
    @Gateway(requestChannel = "testcaseidChannel")
    void echo(TestSuite message);
  }

  // @Produce(uri = "direct:startTestCase")
  // ProducerTemplate template;

  /**
   * Launches the camel route.
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.server.service.TestCaseService#start(java.lang.String)
   * @since Feb 24, 2016
   */
  @Override
  public void start(final String id) {
    // this.template.sendBody(id);
  }
}
