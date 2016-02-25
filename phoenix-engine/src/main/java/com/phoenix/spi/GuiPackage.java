/**
 * Copyright 2015 Nigel Schuster. This should be annotated on packages containing GuiMethods, so
 * that these can be discovered
 */


package com.phoenix.spi;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * Annotation for beans containing GuiMethods
 * 
 * @author nschuste
 * @version 1.0.0
 * @since Feb 23, 2016
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface GuiPackage {
  /**
   * Package name for the form (packageName).(methodName)
   *
   * @author nschuste
   * @version 1.0.0
   * @return
   * @since Feb 23, 2016
   */
  public String packageName();
}
