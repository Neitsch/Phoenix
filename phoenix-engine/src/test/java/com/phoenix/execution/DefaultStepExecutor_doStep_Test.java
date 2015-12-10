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

import com.phoenix.command.Environment;
import com.phoenix.to.SuccessResult;
import com.phoenix.to.TestCaseStep;
import com.phoenix.to.TestCaseStepResult;
import com.phoenix.to.TestCaseStepResultStatus;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 7, 2015
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultStepExecutor_doStep_Test {
  public class tCl {
    public TestCaseStepResultStatus TestMethod(final Environment env, final String... varargs) {
      DefaultStepExecutor_doStep_Test.this.invoked = true;
      return new SuccessResult();
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
    final Environment env = null;
    final String mName = "TestMethod";
    final TestCaseStep step = new TestCaseStep();
    final tCl t = new tCl();
    Mockito.when(this.context.getBean(tCl.class)).thenReturn(t);
    step.setMethodName(mName);
    step.setArgs(new String[0]);
    final Method m = tCl.class.getMethod(mName, Environment.class, String[].class);
    Mockito.when(this.store.getMethod(mName)).thenReturn(m);
    this.invoked = false;
    final TestCaseStepResult res = this.exec.doStep(step, env);
    Assert.assertEquals(true, this.invoked);
    Assert.assertThat(res.getResult(), org.hamcrest.CoreMatchers.instanceOf(SuccessResult.class));
    Assert.assertEquals(step, res.getStep());
  }
}
