/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.recorder;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoenix.command.GuiEventDispatcher;
import com.phoenix.command.dispatcher.ButtonDispatcher;
import com.phoenix.command.dispatcher.TextDispatcher;
import com.phoenix.config.CmdArguments;
import com.phoenix.execution.DefaultMethodStore;
import com.phoenix.execution.DefaultStepExecutor;
import com.phoenix.execution.DefaultTcExecutor;
import com.phoenix.execution.MethodStore;
import com.phoenix.execution.StepExecutor;
import com.phoenix.execution.TcExecutor;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Jan 26, 2016
 */
@SpringBootApplication
public class TestRunner {
  @Autowired
  CmdRecorder rec;

  public static void main(final String[] args) {
    SpringApplication.run(TestRunner.class, args);
  }

  @Bean
  public GuiEventDispatcher disp() {
    return new GuiEventDispatcher();
  }

  @Bean
  public TcExecutor disp1() {
    return new DefaultTcExecutor();
  }

  @Bean
  public StepExecutor disp2() {
    return new DefaultStepExecutor();
  }

  @Bean
  public MethodStore disp3() {
    return new DefaultMethodStore();
  }

  @Bean
  public ObjectMapper disp4() {
    return new ObjectMapper();
  }

  @Bean
  public ButtonDispatcher disp5() {
    return new ButtonDispatcher();
  }

  @Bean
  public TextDispatcher disp6() {
    return new TextDispatcher();
  }

  @PostConstruct
  public void run() {
    this.rec.executeArgs(new CmdArguments());
  }
}
