/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix;

import java.util.LinkedList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoenix.to.TestCase;
import com.phoenix.to.TestCaseBody;
import com.phoenix.to.TestCaseHead;
import com.phoenix.to.TestCaseLine;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Nov 21, 2015
 */
@RunWith(MockitoJUnitRunner.class)
public class Runner_loadTc_Test {
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
  public final void test_readTc() throws Exception {
    final String inputFile = this.getClass().getResource("sample.tc").getFile();
    final TestCase tc = this.runner.loadTC(inputFile);
    final TestCase test = new TestCase();
    final TestCaseBody tcb = new TestCaseBody();
    final TestCaseHead tch = new TestCaseHead();
    tcb.setLines(new LinkedList<TestCaseLine>());
    tch.setName("MyName");
    test.setTcBody(tcb);
    test.setTcHead(tch);
    Assert.assertEquals(test, tc);
  }
}
