/**
 * Copyright 2016 Nigel Schuster.
 */

package com.phoenix.remote;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spring.javaconfig.SingleRouteCamelConfiguration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
   * @since Feb 2, 2016
   */
  @Override
  public RouteBuilder route() {
    return new RouteBuilder() {
      @Override
      public void configure() throws Exception {
        this.from("jms:queue:testcase").throttle(1).to("direct:doTestcase");
        this.from("direct:doTestcase").bean(ExecWrapper.class).to("jms:topic:testresult");
      }
    };
  }

}
