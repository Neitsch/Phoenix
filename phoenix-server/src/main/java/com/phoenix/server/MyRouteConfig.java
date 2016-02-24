/**
 * Copyright 2016 Nigel Schuster. Configuration of Routes in Apache Camel.
 */


package com.phoenix.server;

import java.net.MalformedURLException;

import lombok.extern.slf4j.XSlf4j;

import org.apache.camel.Component;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.amqp.AMQPComponent;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spring.javaconfig.SingleRouteCamelConfiguration;
import org.apache.qpid.amqp_1_0.jms.ConnectionFactory;
import org.apache.qpid.amqp_1_0.jms.impl.ConnectionFactoryImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.phoenix.server.data.TestCaseRepository;
import com.phoenix.to.TestCase;
import com.phoenix.to.TestResult;


/**
 * Apache Camel Route configuration
 *
 * @author nschuste
 * @version 1.0.0
 * @since Feb 1, 2016
 */
@XSlf4j
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

  // @Bean
  // public ConnectionFactory bla() {
  // final ActiveMQConnectionFactory act =
  // new ActiveMQConnectionFactory("tcp://" + System.getenv("QUEUE_HOST") + ":61616");
  // act.setUserName("admin");
  // act.setPassword("admin");
  // act.setTrustAllPackages(true);
  // return act;
  // }
  //
  // @Bean(name = "rabbit")
  // public com.rabbitmq.client.ConnectionFactory fact() {
  // com.rabbitmq.client.ConnectionFactory facto = new com.rabbitmq.client.ConnectionFactory();
  // facto.setUsername("guest");
  // facto.setPassword("guest");
  // facto.setHost(System.getenv("QUEUE_HOST"));
  // return facto;
  // }

  @Bean
  public ConnectionFactory facto() throws MalformedURLException {
    ConnectionFactoryImpl factory =
        ConnectionFactoryImpl.createFromURL("amqp://guest:guest@localhost:5672");
    return factory;
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
        // Enqueue Testcase by ID
        this.log.info("Configuring direct:startTestCase");
        this.from("direct:startTestCase")
            .marshal()
            .json(JsonLibrary.Jackson, String.class)
            .log("${body}")
            .to("rabbitmq://localhost:5672/testcaseid?username=guest&password=guest&autoDelete=false");
        // Fetch body of testcase
        this.log.info("Configuring amqp:queue:testcaseid");
        this.from("amqp:queue:testcaseid")
            .unmarshal()
            .json(JsonLibrary.Jackson, String.class)
            .log("${body}")
            .process(arg0 -> {
              final Message m = arg0.getIn();
              final String id = (String) m.getBody();
              final TestCase tc = this.repository.findOne(id);
              m.setBody(tc);
              m.setHeader("id", id);
            })
            .log("${body}")
            .choice()
            .when(this.body().isNull())
            .throwException(new NullPointerException())
            .otherwise()
            .marshal()
            .json(JsonLibrary.Jackson)
            .to("rabbitmq://localhost:5672/testcase?username=guest&password=guest&autoDelete=false");
        // store result
        this.log.info("Configuring amqp:queue:testresult");
        this.from("amqp:queue:testresult").unmarshal().json(JsonLibrary.Jackson, TestResult.class)
            .log("${body}").beanRef("defaultTestResultService");
        this.log.info("Done configuring Routes.");
      }

      public RouteBuilder init(final TestCaseRepository repository) {
        this.repository = repository;
        return this;
      }
    }.init(this.repository);
  }

  @Bean
  public Component securedAmqpConnection() throws MalformedURLException {
    log.entry();
    return log.exit(new AMQPComponent(this.facto()));
  }
}
