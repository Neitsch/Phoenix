/**
 * Copyright 2016 Nigel Schuster. This is annotated to any bean dispatching AWT events, picked up by
 * Spring.
 */


package com.phoenix.spi;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * Annotation for Beans wanting to dispatch AWTEvents.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Jan 22, 2016
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface GuiDispatcher {
  /**
   * Name of the dispatcher for ease of use.
   *
   * @author nschuste
   * @version 1.0.0
   * @return
   * @since Feb 23, 2016
   */
  public String dispatcherName() default "";
}
