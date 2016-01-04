/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.execution;

import com.phoenix.spi.GuiMethod;
import com.phoenix.spi.GuiPackage;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 6, 2015
 */
@GuiPackage(packageName = "Smaple")
public class Sample {

  @GuiMethod(methodName = "MyMethod1")
  public void method1() {
    System.out.println("Print 1");
  }

  @GuiMethod(methodName = "MyMethod2")
  public void method2() {
    System.out.println("Print 2");
  }

  @GuiMethod(methodName = "MyMethod3")
  public void method3() {
    System.out.println("Print 3");
  }
}
