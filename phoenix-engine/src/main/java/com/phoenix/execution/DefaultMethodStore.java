/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.execution;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import com.phoenix.spi.GuiMethod;
import com.phoenix.spi.GuiPackage;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 7, 2015
 */
@Repository
public class DefaultMethodStore implements MethodStore {
  private final Map<String, Method> store;
  @Autowired
  ApplicationContext context;

  /**
   * @author nschuste
   * @version 1.0.0
   * @since Dec 7, 2015
   */
  public DefaultMethodStore() {
    this.store = new HashMap<>();
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

  @PostConstruct
  public void init() {
    final Map<String, Object> beans = this.context.getBeansWithAnnotation(GuiPackage.class);
    final Collection<Method> mm = new HashSet<>();
    for (final String s : beans.keySet()) {
      for (final Method m : beans.get(s).getClass().getDeclaredMethods()) {
        if (m.isAnnotationPresent(GuiMethod.class)) {
          final GuiMethod g = m.getAnnotation(GuiMethod.class);
          this.store.put(g.methodName(), m);
        }
      }
    }
  }

}