/**
 * Copyright 2016 Nigel Schuster.
 */

package com.phoenix.remote;

import java.net.MalformedURLException;

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
 * @author nschuste
 * @version 1.0.0
 * @since Feb 2, 2016
 */
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

  @Bean
  public ConnectionFactory fact() {
    return new ConnectionFactoryImpl("amqp", "localhost", 5672, "guest", "guest", "", "/", false, 1);
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
    return new RouteBuilder() {
      @Override
      public void configure() throws Exception {
        this.from("amqp:queue:testcase").unmarshal().json(JsonLibrary.Jackson, TestCase.class)
            .throttle(1).to("direct:doTestcase");
        this.from("direct:doTestcase").bean(ExecWrapper.class).to("direct:finishedTc");
        this.from("direct:finishedTc")
            .marshal()
            .json(JsonLibrary.Jackson)
            .to("rabbitmq://localhost:5672/testresult?username=guest&password=guest&autoDelete=false");
      }
    };
  }

  @Bean
  public Component securedAmqpConnection() throws MalformedURLException {
    return AMQPComponent.amqpComponent("amqp://guest:guest@localhost:5672");
  }

}
