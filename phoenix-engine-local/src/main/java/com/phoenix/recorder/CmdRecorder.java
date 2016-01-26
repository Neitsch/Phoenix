/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.recorder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import lombok.extern.slf4j.XSlf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoenix.Runner;
import com.phoenix.command.GuiEventDispatcher;
import com.phoenix.config.CmdArguments;
import com.phoenix.exception.SetupException;
import com.phoenix.execution.TcExecutor;
import com.phoenix.to.TestCase;
import com.phoenix.to.TestCaseBody;
import com.phoenix.to.TestCaseHead;
import com.phoenix.to.TestCaseSetup;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Jan 23, 2016
 */
@XSlf4j
@Service
public class CmdRecorder implements Runner {
  private static final String EXIT_STRING = "exit";
  @Autowired
  GuiEventDispatcher dispatcher;
  @Autowired
  TcExecutor executor;
  @Autowired
  ObjectMapper mapper;

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.Runner#executeArgs(com.phoenix.config.CmdArguments)
   * @since Jan 23, 2016
   */
  @Override
  public void executeArgs(final CmdArguments args) {
    final Scanner scan = new Scanner(System.in);
    TestCaseSetup setup = null;
    final TestCase tc = new TestCase();
    final TestCaseBody body = new TestCaseBody();
    Path tc_setup_path =
        Paths.get(args.getInputFile() != null ? args.getInputFile() : "data/setup.tc");
    boolean failure = false;
    do {
      while (!Files.exists(tc_setup_path)) {
        System.out.print("Please enter a valid testcase setup location (CWD: "
            + new File("").getAbsolutePath() + "): ");
        final String setup_path = scan.nextLine();
        tc_setup_path = Paths.get(setup_path);
      };
      try {
        setup = this.mapper.readValue(tc_setup_path.toFile(), TestCaseSetup.class);
      } catch (final Exception e2) {
        log.catching(e2);
        failure = true;
      }
    } while (failure);
    tc.setTcBody(body);
    tc.setTcHead(new TestCaseHead());
    tc.getTcHead().setSetup(setup);
    body.setLines(new ArrayList<>());
    try {
      this.executor.setUp(setup);
    } catch (final SetupException e1) {
      log.catching(e1);
    }
    this.dispatcher.initialize(e -> {
      log.info(e.toString());
      body.getLines().add(e);
    });
    String input;
    while (!(input = scan.nextLine()).toLowerCase().equals(EXIT_STRING)) {
    }
    try {
      this.mapper.writerWithDefaultPrettyPrinter().writeValue(System.out, tc);
    } catch (final IOException e1) {
      log.catching(e1);
    }
    this.executor.tearDown(null);
    scan.close();
  }
}
