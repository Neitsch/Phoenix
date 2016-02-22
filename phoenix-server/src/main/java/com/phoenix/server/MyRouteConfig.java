/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.server;

import java.net.MalformedURLException;

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
        this.from("direct:startTestCase")
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
        // this.from("jms:queue:testcase").log("LOG");
        this.from("amqp:queue:testresult").unmarshal().json(JsonLibrary.Jackson, TestResult.class)
            .log("${body}").beanRef("defaultTestResultService");
      }

      public RouteBuilder init(final TestCaseRepository repository) {
        this.repository = repository;
        return this;
      }
    }.init(this.repository);
  }

  @Bean
  public Component securedAmqpConnection() throws MalformedURLException {
    return new AMQPComponent(this.facto());
  }
}
