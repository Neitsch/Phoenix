/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.server;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.javaconfig.SingleRouteCamelConfiguration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.phoenix.server.data.TestCaseRepository;
import com.phoenix.to.TestCase;


/**
 * @author nschuste
 * @version 1.0.0
 * @since Feb 1, 2016
 */
@Configuration
public class MyRouteConfig extends SingleRouteCamelConfiguration implements InitializingBean {
  @Autowired
  TestCaseRepository repository;

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
   * @since Feb 1, 2016
   */
  @Override
  public void afterPropertiesSet() throws Exception {}

  @Bean
  public ConnectionFactory bla() {
    final ActiveMQConnectionFactory act =
        new ActiveMQConnectionFactory("tcp://" + System.getenv("QUEUE_HOST") + ":61616");
    act.setUserName("admin");
    act.setPassword("admin");
    return act;
  }

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see org.apache.camel.spring.javaconfig.SingleRouteCamelConfiguration#route()
   * @since Feb 1, 2016
   */
  @Override
  public RouteBuilder route() {
    return new RouteBuilder() {
      TestCaseRepository repository;

      @Override
      public void configure() throws Exception {
        this.from("direct:startTestCase").log("${body}").process(arg0 -> {
          final Message m = arg0.getIn();
          final String id = (String) m.getBody();
          final TestCase tc = this.repository.findOne(id);
          m.setBody(tc);
          m.setHeader("id", id);
        }).log("${body}").choice().when(this.body().isNull())
            .throwException(new NullPointerException()).otherwise().to("jms:queue:testcase");
        // this.from("jms:queue:testcase").log("LOG");
        this.from("jms:topic:testresult").log("${body}");
      }

      public RouteBuilder init(final TestCaseRepository repository) {
        this.repository = repository;
        return this;
      }
    }.init(this.repository);
  }
}
