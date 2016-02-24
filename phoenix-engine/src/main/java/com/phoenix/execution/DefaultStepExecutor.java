/**
 * Copyright 2015 Nigel Schuster. Singleton that handles the execution of a single step and reports
 * its result.
 */


package com.phoenix.execution;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;

import lombok.extern.slf4j.XSlf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.phoenix.command.Environment;
import com.phoenix.to.ResultWithMessage;
import com.phoenix.to.TestCaseStep;
import com.phoenix.to.TestCaseStepResultStatus;

/**
 * Class to executes single {@TestCaseStep}.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Dec 7, 2015
 */
@Service
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
  public ResultWithMessage doStep(final TestCaseStep step, final Environment env) throws Exception {
    log.entry(step, env);
    final String mName = step.getMethodName();
    final Method method = this.store.getMethod(mName);
    final Object bean = this.context.getBean(method.getDeclaringClass());
    log.debug("Using method: " + method.toString());
    ResultWithMessage res;
    try {
      res = (ResultWithMessage) method.invoke(bean, env, step.getArgs());
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      e.printStackTrace();
      log.catching(e);
      res =
          ResultWithMessage.builder().status(TestCaseStepResultStatus.EXCEPTION)
          .exception(Optional.of(e)).build();
    }
    return log.exit(res);
  }
}
