/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.execution;

import java.lang.reflect.Method;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 7, 2015
 */
public interface MethodStore {
  public Method getMethod(String methodName);
}
