/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.pack;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.phoenix.spi.GuiMethod;
import com.phoenix.spi.GuiPackage;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 6, 2015
 */
@ComponentScan
@SpringBootApplication
public class Test_Load {
  @Test
  public void toTest() {
    final ApplicationContext con = SpringApplication.run(Test_Load.class);
    final Map<String, Object> map = con.getBeansWithAnnotation(GuiPackage.class);
    Assert.assertEquals(1, map.size());
    final Collection<Method> mm = new HashSet<>();
    for (final String s : map.keySet()) {
      for (final Method m : map.get(s).getClass().getDeclaredMethods()) {
        if (m.isAnnotationPresent(GuiMethod.class)) {
          mm.add(m);
        }
      }
    }
    for (final Method my : mm) {
      final Object o = con.getBean(my.getDeclaringClass());
      try {
        final String pack = o.getClass().getAnnotation(GuiPackage.class).packageName();
        final String methodName = my.getAnnotation(GuiMethod.class).methodName();
        System.out.print(pack + "." + methodName + ": ");
        my.invoke(o);
      } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
        e.printStackTrace();
      }
    }
  }
}
