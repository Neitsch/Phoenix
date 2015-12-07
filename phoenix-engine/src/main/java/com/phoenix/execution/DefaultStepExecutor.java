/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.execution;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import lombok.extern.slf4j.XSlf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.phoenix.to.TestCaseStep;
import com.phoenix.to.TestCaseStepResult;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 7, 2015
 */
@XSlf4j
public class DefaultStepExecutor implements StepExecutor {
  @Autowired
  private ApplicationContext context;
  @Autowired
  private MethodStore store;

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @throws Exception
   * @see com.phoenix.execution.StepExecutor#doStep(com.phoenix.to.TestCaseStep)
   * @since Dec 7, 2015
   */
  @Override
  public TestCaseStepResult doStep(final TestCaseStep step) throws Exception {
    final String mName = step.getMethodName();
    final Method method = this.store.getMethod(mName);
    final Object bean = this.context.getBean(method.getDeclaringClass());
    try {
      method.invoke(bean);
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      log.catching(e);
      throw e;
    }
    return null;
  }
}
