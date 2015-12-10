/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.execution;

import com.phoenix.spi.GuiMethod;
import com.phoenix.spi.GuiPackage;
import com.phoenix.to.SuccessResult;
import com.phoenix.to.TestCaseStepResultStatus;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 6, 2015
 */
@GuiPackage(packageName = "Smaple")
public class Sample {

  @GuiMethod(methodName = "MyMethod1")
  public TestCaseStepResultStatus method1() {
    System.out.println("Print 1");
    return new SuccessResult();
  }

  @GuiMethod(methodName = "MyMethod2")
  public TestCaseStepResultStatus method2() {
    System.out.println("Print 2");
    return new SuccessResult();
  }

  @GuiMethod(methodName = "MyMethod3")
  public TestCaseStepResultStatus method3() {
    System.out.println("Print 3");
    return new SuccessResult();
  }
}
