/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.spi;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 6, 2015
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GuiMethod {
  public String methodName();
}
