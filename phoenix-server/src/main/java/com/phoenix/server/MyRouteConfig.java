/**
 * Copyright 2016 Nigel Schuster. Configuration of Routes in Apache Camel.
 */


package com.phoenix.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.XSlf4j;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
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
import org.springframework.integration.router.AbstractMessageRouter;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.integration.store.MessageStore;
import org.springframework.integration.store.SimpleMessageStore;
import org.springframework.integration.support.json.Jackson2JsonObjectMapper;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoenix.server.data.TestCaseRepository;
import com.phoenix.server.data.TestResultRepository;
import com.phoenix.server.data.TestSuiteRepository;
import com.phoenix.to.TestResult;
import com.phoenix.to.TestSuite;


/**
 * Apache Camel Route configuration
 *
 * @author nschuste
 * @version 1.0.0
 * @since Feb 1, 2016
 */
@XSlf4j
@Configuration
public class MyRouteConfig {
  @Autowired
  TestCaseRepository repository1;
  @Autowired
  TestResultRepository repository2;
  @Autowired
  TestSuiteRepository repository3;

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
  public IntegrationFlow correlate() {
    return IntegrationFlows.from("testcase_testsuite").wireTap(f -> f.handle(this.logger()))
        .aggregate().handle(this.logger()).get();
  }

  @Bean
  public IntegrationFlow deadLetter() {
    return IntegrationFlows.from("deadLetterChannel").handle(this.logger()).get();
  }

  @Bean
  public IntegrationFlow do_testsuite() {
    return IntegrationFlows
        .from(
            Amqp.inboundGateway(this.connectionFactory(), new Queue("testsuite"))
                .mappedRequestHeaders("*"))
        .transform(Transformers.fromJson())
        // .claimCheckIn(this.store()).handle(this.logger()).get();
        .transform(arg0 -> ((TestSuite) arg0).getTestcaseids())
        .split()
        .transform(Transformers.toJson())
        .wireTap(f -> f.handle(this.logger()))
        .handle(
            Amqp.outboundAdapter(this.template()).exchangeName("testcaseid")
                .mappedRequestHeaders("*")).get();
  }

  @Bean
  public IntegrationFlow fetch_testcase() {
    return IntegrationFlows
        .from(
            Amqp.inboundGateway(this.connectionFactory(),
                new org.springframework.amqp.core.Queue("testcaseid")).mappedRequestHeaders("*"))
                .transform(Transformers.objectToString())
        .transform(Transformers.fromJson(String.class))
        .transform(arg0 -> log.exit(this.repository1.findOne(log.exit((String) arg0))))
        .transform(Transformers.toJson())
        .handle(
                    Amqp.outboundAdapter(this.template()).exchangeName("testcase")
                    .mappedRequestHeaders("*")).get();
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
    return Pollers.fixedDelay(1000).taskExecutor(Executors.newCachedThreadPool()).get();
  }

  @Bean
  public IntegrationFlow receive_testresult() {
    AbstractMessageRouter router = new AbstractMessageRouter() {

      @Override
      protected Collection<MessageChannel> determineTargetChannels(final Message<?> message) {
        if (message.getHeaders().containsKey("correlationId")) {
          return Arrays.asList(new MessageChannel[] {this.getChannelResolver().resolveDestination(
              "testcase_testsuite")});
        }
        return new ArrayList<>();
      }
    };
    router.setDefaultOutputChannelName("deadLetterChannel");
    return IntegrationFlows
        .from(
            Amqp.inboundGateway(this.connectionFactory(), new Queue("testresult"))
                .mappedRequestHeaders("*")).transform(Transformers.fromJson())
        .transform(arg0 -> this.repository2.save((TestResult) arg0)).route(router).get();
    // .aggregate() // TODO: filter tc with correlation id
    // .handle(this.logger()).get();
  }

  @Bean
  public MessageStore store() {
    return new SimpleMessageStore();
  }

  @Bean
  public IntegrationFlow tcid() {
    return IntegrationFlows.from("testcaseidChannel").transform(Transformers.toJson())
        .handle(Amqp.outboundAdapter(this.template()).exchangeName("testcaseid")).get();
  }

  @Bean
  public AmqpTemplate template() {
    return new RabbitTemplate(this.connectionFactory());
  }

  @Bean
  public IntegrationFlow ts() {
    return IntegrationFlows.from("testsuiteChannel").transform(Transformers.toJson())
        .handle(Amqp.outboundAdapter(this.template()).exchangeName("testsuite")).get();
  }

  @Bean
  public IntegrationFlow tsid() {
    return IntegrationFlows
        .from(
            Amqp.inboundAdapter(this.connectionFactory(),
                new org.springframework.amqp.core.Queue("testsuiteid")).mappedRequestHeaders("*"))
        .transform(Transformers.objectToString()).transform(Transformers.fromJson(String.class))
                .transform(source -> MyRouteConfig.this.repository3.findOne((String) source))
                .channel("testsuiteChannel").get();
  }
}
