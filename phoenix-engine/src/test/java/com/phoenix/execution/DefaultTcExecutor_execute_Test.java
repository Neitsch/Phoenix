/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.execution;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.phoenix.command.Environment;
import com.phoenix.to.TestCaseBody;
import com.phoenix.to.TestCaseStep;
import com.phoenix.to.TestCaseStepResult;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 10, 2015
 */
@RunWith(MockitoJUnitRunner.class)
public class DefaultTcExecutor_execute_Test {
  @InjectMocks
  DefaultTcExecutor exec;
  @Mock
  StepExecutor stepExec;

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Dec 10, 2015
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {}

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Dec 10, 2015
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception {}

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Dec 10, 2015
   */
  @Before
  public void setUp() throws Exception {}

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Dec 10, 2015
   */
  @After
  public void tearDown() throws Exception {}

  @Test
  public final void test() throws Exception {
    final TestCaseStep step1 = TestCaseStep.builder().methodName("click").build();
    final TestCaseStep step2 = TestCaseStep.builder().methodName("check").build();
    final TestCaseBody tc = new TestCaseBody();
    tc.getLines().add(step1);
    tc.getLines().add(step2);
    Mockito.doReturn(new TestCaseStepResult()).when(this.stepExec)
        .doStep(Matchers.any(TestCaseStep.class), Matchers.any(Environment.class));
    final InOrder order = Mockito.inOrder(this.stepExec);
    this.exec.execute(tc);
    order.verify(this.stepExec).doStep(Matchers.eq(step1), Matchers.any(Environment.class));
    order.verify(this.stepExec).doStep(Matchers.eq(step2), Matchers.any(Environment.class));
    order.verifyNoMoreInteractions();
  }
}
