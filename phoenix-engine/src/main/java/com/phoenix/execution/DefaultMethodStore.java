/**
 * Copyright 2015 Nigel Schuster. This singleton stores all methods that are annotated with
 * GuiMethod in a GuiPackage annotated class.
 */


package com.phoenix.execution;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import lombok.extern.slf4j.XSlf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.phoenix.spi.GuiMethod;
import com.phoenix.spi.GuiPackage;
import com.phoenix.to.ResultWithMessage;

/**
 * Stores all GuiMethods.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Dec 7, 2015
 */
@XSlf4j
@Repository
public class DefaultMethodStore implements MethodStore {
  /**
   * Map that stores all Method references.
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private final Map<String, Method> store;
  @Autowired
  ApplicationContext context;

  /**
   * @author nschuste
   * @version 1.0.0
   * @since Dec 7, 2015
   */
  public DefaultMethodStore() {
    log.entry();
    this.store = new HashMap<>();
    log.exit();
  }

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.execution.MethodStore#getMethod(java.lang.String)
   * @since Dec 7, 2015
   */
  @Override
  public Method getMethod(final String methodName) {
    return this.store.get(methodName);
  }

  /**
   * Scanns all Spring Beans for GuiMethods.
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  @PostConstruct
  public void init() {
    log.info("Scanning for methods.");
    final Class<?> target = ResultWithMessage.class;
    final Map<String, Object> beans = this.context.getBeansWithAnnotation(GuiPackage.class);
    for (final String s : beans.keySet()) {
      final GuiPackage p = beans.get(s).getClass().getAnnotation(GuiPackage.class);
      for (final Method m : beans.get(s).getClass().getDeclaredMethods()) {
        if (m.isAnnotationPresent(GuiMethod.class)) {
          if (target.isAssignableFrom(m.getReturnType())) {
            final GuiMethod g = m.getAnnotation(GuiMethod.class);
            log.info("Found " + p.packageName() + "." + g.methodName() + " as " + m.toString());
            this.store.put(p.packageName() + "." + g.methodName(), m);
          } else {
            log.warn("Method is assigned " + GuiMethod.class.getName()
                + " annotation, but does not return " + target.getName() + ". Skipped "
                + m.getDeclaringClass().getName() + "." + m.getName());
          }
        }
      }
    }
  }
}
