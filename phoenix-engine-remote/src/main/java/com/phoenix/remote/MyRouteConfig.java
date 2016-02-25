/**
 * Copyright 2016 Nigel Schuster. Configuration for Apache Camel - Routing engine.
 */

package com.phoenix.remote;

import java.net.MalformedURLException;

import lombok.extern.slf4j.XSlf4j;

import org.apache.camel.Component;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.amqp.AMQPComponent;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.spring.javaconfig.SingleRouteCamelConfiguration;
import org.apache.qpid.amqp_1_0.jms.ConnectionFactory;
import org.apache.qpid.amqp_1_0.jms.impl.ConnectionFactoryImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.phoenix.to.TestCase;

/**
 * Apache Camel Route configuration.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Feb 2, 2016
 */
@XSlf4j
@Configuration
public class MyRouteConfig extends SingleRouteCamelConfiguration implements InitializingBean {

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
   * @since Feb 2, 2016
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
  // public com.rabbitmq.client.ConnectionFactory fact() throws IOException {
  // com.rabbitmq.client.ConnectionFactory facto = new com.rabbitmq.client.ConnectionFactory();
  // facto.setUsername("guest");
  // facto.setPassword("guest");
  // facto.setHost(System.getenv("QUEUE_HOST"));
  // return facto;
  // }

  /**
   * AMQP factory to rabbitmq
   *
   * @author nschuste
   * @version 1.0.0
   * @return
   * @since Feb 24, 2016
   */
  @Bean
  public ConnectionFactory facto() throws MalformedURLException {
    String con = "amqp://guest:guest@" + System.getenv("QUEUE_HOST") + ":5672";
    log.info(con);
    ConnectionFactoryImpl factory = ConnectionFactoryImpl.createFromURL(con);
    return factory;
  }

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see org.apache.camel.spring.javaconfig.SingleRouteCamelConfiguration#route()
   * @since Feb 2, 2016
   */
  @Override
  public RouteBuilder route() {
    /*
     * Note, that I am polling from AMQP, but sending results to RabbitMQ. RabbitMQ did not work
     * very well with Camel, however this way functions as expected, though it is wasteful on
     * resources.
     */
    return new RouteBuilder() {
      @Override
      public void configure() throws Exception {
        // Polling from queue to execute testcase
        this.from("amqp:queue:testcase").unmarshal().json(JsonLibrary.Jackson, TestCase.class)
        .throttle(1).to("direct:doTestcase");
        // actual Testcase execution
        this.from("direct:doTestcase").bean(ExecWrapper.class).to("direct:finishedTc");
        // reporting results
        this.from("direct:finishedTc")
        .marshal()
        .json(JsonLibrary.Jackson)
        .to("rabbitmq://" + System.getenv("QUEUE_HOST")
                + ":5672/testresult?username=guest&password=guest&autoDelete=false");
      }
    };
  }

  /**
   * AMQP connection.
   *
   * @author nschuste
   * @version 1.0.0
   * @return
   * @throws MalformedURLException
   * @since Feb 24, 2016
   */
  @Bean
  public Component securedAmqpConnection() throws MalformedURLException {
    log.entry();
    return log.exit(new AMQPComponent(this.facto()));
  }

}
