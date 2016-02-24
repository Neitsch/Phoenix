/**
 * Copyright 2015 Nigel Schuster. Should provide all registered GuiMethods.
 */


package com.phoenix.execution;

import java.lang.reflect.Method;

/**
 * Provides available GuiMethods.
 * 
 * @author nschuste
 * @version 1.0.0
 * @since Dec 7, 2015
 */
public interface MethodStore {
  /**
   * Returns GuiMethod that matches identifier.
   * 
   * @author nschuste
   * @version 1.0.0
   * @param methodName
   * @return
   * @since Feb 23, 2016
   */
  public Method getMethod(String methodName);
}
