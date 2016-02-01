/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.recorder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import lombok.extern.slf4j.XSlf4j;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
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
    final HttpClient client = HttpClientBuilder.create().build();
    final Scanner scan = new Scanner(System.in);
    TestCaseHead setup = null;
    try {
      final List<TestCaseHead> ddd =
          this.mapper.readValue(client.execute(new HttpGet("http://localhost:8080/tch"))
              .getEntity().getContent(),
              this.mapper.getTypeFactory().constructCollectionType(List.class, TestCaseHead.class));
      System.out.println("Available Tch:");
      System.out.println(ddd);
    } catch (UnsupportedOperationException | IOException e2) {
      log.catching(e2);
    }
    final TestCase tc = new TestCase();
    final TestCaseBody body = new TestCaseBody();
    boolean failure = false;
    do {
      final String id = scan.nextLine();
      final HttpGet getReq = new HttpGet("http://localhost:8080/tch/" + id);
      try {
        final HttpResponse response = client.execute(getReq);
        final InputStream stream = response.getEntity().getContent();
        setup = this.mapper.readValue(stream, TestCaseHead.class);
      } catch (final IOException e1) {
        log.catching(e1);
        failure = true;
      }
    } while (failure);
    tc.setTcBody(body);
    tc.setTcHead(new TestCaseHead());
    tc.setTcHead(setup);
    body.setLines(new ArrayList<>());
    try {
      this.executor.setUp(setup.getSetup());
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
    System.out.println("Save? (y/n)");
    String line = scan.nextLine();
    try {
      while (true) {
        if (line.toLowerCase().equals("y")) {
          final HttpPost req = new HttpPost("http://localhost:8080/tc");
          req.setEntity(new StringEntity(this.mapper.writeValueAsString(tc)));
          req.addHeader("Content-type", "application/json");
          final HttpResponse resp = client.execute(req);
          log.info(Integer.toString(resp.getStatusLine().getStatusCode()));
          break;
        } else if (line.toLowerCase().equals("n")) {
          break;
        }
        line = scan.nextLine();
      }
    } catch (final Exception e1) {
      e1.printStackTrace();
      log.catching(e1);
    }
    this.executor.tearDown(null);
    scan.close();
  }
}
