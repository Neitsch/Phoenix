/**
 * Copyright 2016 Nigel Schuster. Configuration for Apache Camel - Routing engine.
 */

package com.phoenix.remote;

import lombok.extern.slf4j.XSlf4j;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.amqp.Amqp;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.dsl.support.Transformers;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.messaging.MessageHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoenix.to.TestCase;
import com.phoenix.to.TestResult;

/**
 * Apache Camel Route configuration.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Feb 2, 2016
 */
@XSlf4j
@Configuration
public class MyRouteConfig {
  @Autowired
  ExecWrapper wrapper;

  @Bean
  public static Jackson2JsonObjectMapper getMapper() {
    ObjectMapper mapper = new ObjectMapper();
    return new Jackson2JsonObjectMapper(mapper);
  }

  @Bean
  public org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory() {
    com.rabbitmq.client.ConnectionFactory f = new com.rabbitmq.client.ConnectionFactory();
    f.setHost(System.getenv("QUEUE_HOST"));
    f.setPassword("guest");
    f.setUsername("guest");
    f.setPort(5672);
    return new CachingConnectionFactory(f);
  }

  @Bean
  public MessageConverter jsonMessageConverter() {
    return new JsonMessageConverter();
  }

  @Bean
  public MessageHandler logger() {
    LoggingHandler loggingHandler = new LoggingHandler(LoggingHandler.Level.INFO.name());
    loggingHandler.setLoggerName("com.phoenix.server");
    return loggingHandler;
  }

  @Bean(name = PollerMetadata.DEFAULT_POLLER)
  public PollerMetadata poller() {
    return Pollers.fixedDelay(1000).get();
  }

  @Bean
  public IntegrationFlow store_testresult() {
    return IntegrationFlows
        .from(
            Amqp.inboundGateway(this.connectionFactory(), new org.springframework.amqp.core.Queue(
                "testcase"))).wireTap(f -> f.handle(this.logger()))
        .transform(Transformers.fromJson()).wireTap(f -> f.handle(this.logger()))
        .transform(arg0 -> {
                  try {
                    return this.wrapper.result((TestCase) arg0);
                  } catch (Exception e) {
                    log.catching(e);
                    return TestResult.builder().success(false).build();
                  }
                }).transform(Transformers.toJson()).wireTap(f -> f.handle(this.logger()))
                .handle(Amqp.outboundAdapter(this.template()).exchangeName("testresult")).get();
  }

  @Bean
  public AmqpTemplate template() {
    return new RabbitTemplate(this.connectionFactory());
  }
}
