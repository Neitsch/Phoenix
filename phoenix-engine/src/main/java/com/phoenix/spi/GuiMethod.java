/**
 * Copyright 2015 Nigel Schuster. This is annotated to Methods that will perform a testcasestep
 */


package com.phoenix.spi;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for methods implementing a testcase step
 * 
 * @author nschuste
 * @version 1.0.0
 * @since Dec 6, 2015
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GuiMethod {
  /**
   * Name of method in (packageName).(methodName)
   * 
   * @author nschuste
   * @version 1.0.0
   * @return
   * @since Feb 23, 2016
   */
  public String methodName();
}
