/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.execution;

import java.lang.reflect.Method;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;

import com.phoenix.to.TestCaseStep;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 7, 2015
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultStepExecutor_doStep_Test {
  public class tCl {
    public void TestMethod() {
      DefaultStepExecutor_doStep_Test.this.invoked = true;
    }
  }

  private boolean invoked;
  @Mock
  ApplicationContext context;
  @InjectMocks
  DefaultStepExecutor exec;
  @Mock
  MethodStore store;

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Dec 7, 2015
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {}

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Dec 7, 2015
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception {}

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Dec 7, 2015
   */
  @Before
  public void setUp() throws Exception {}

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Dec 7, 2015
   */
  @After
  public void tearDown() throws Exception {}

  @Test
  public void test() throws Exception {
    final String mName = "TestMethod";
    final TestCaseStep step = new TestCaseStep();
    final tCl t = new tCl();
    Mockito.when(this.context.getBean(tCl.class)).thenReturn(t);
    step.setMethodName(mName);
    final Method m = tCl.class.getMethod(mName);
    Mockito.when(this.store.getMethod(mName)).thenReturn(m);
    this.invoked = false;
    this.exec.doStep(step);
    Assert.assertEquals(true, this.invoked);
  }
}
