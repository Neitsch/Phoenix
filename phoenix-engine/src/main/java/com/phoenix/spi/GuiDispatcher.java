/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.spi;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Jan 22, 2016
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface GuiDispatcher {
  public String dispatcherName() default "";
}
