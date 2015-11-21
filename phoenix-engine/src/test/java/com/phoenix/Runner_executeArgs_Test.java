/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix;

import java.net.URISyntaxException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoenix.config.CmdArguments;
import com.phoenix.execution.TcExecutor;
import com.phoenix.to.TestCase;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Nov 21, 2015
 */
@RunWith(MockitoJUnitRunner.class)
public class Runner_executeArgs_Test {
  @Mock
  private TcExecutor executor;
  @Spy
  private ObjectMapper mapper;
  @InjectMocks
  private RunnerImpl runner;

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Nov 21, 2015
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {}

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Nov 21, 2015
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception {}

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Nov 21, 2015
   */
  @Before
  public void setUp() throws Exception {}

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Nov 21, 2015
   */
  @After
  public void tearDown() throws Exception {}

  @Test
  public final void test() throws URISyntaxException {
    final CmdArguments args = new CmdArguments();
    final String inputFile = this.getClass().getResource("sample.tc").getFile();
    args.setInputFile(inputFile);
    this.runner.executeArgs(args);
    Mockito.verify(this.executor, Mockito.only()).run(Matchers.any(TestCase.class));
    Mockito.verifyNoMoreInteractions(this.executor);
  }

}
