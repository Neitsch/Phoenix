/**
 * Copyright 2015 Nigel Schuster. Sample class to test method discovery.
 */


package com.phoenix.execution;

import com.phoenix.spi.GuiMethod;
import com.phoenix.spi.GuiPackage;
import com.phoenix.to.ResultWithMessage;
import com.phoenix.to.TestCaseStepResultStatus;

/**
 * Simple class to test method discovery with annotations.
 * 
 * @author nschuste
 * @version 1.0.0
 * @since Dec 6, 2015
 */
@GuiPackage(packageName = "Sample")
public class Sample {

  @GuiMethod(methodName = "MyMethod1")
  public ResultWithMessage method1() {
    System.out.println("Print 1");
    return ResultWithMessage.builder().status(TestCaseStepResultStatus.SUCCESS).build();
  }

  @GuiMethod(methodName = "MyMethod2")
  public ResultWithMessage method2() {
    System.out.println("Print 2");
    return ResultWithMessage.builder().status(TestCaseStepResultStatus.SUCCESS).build();
  }

  @GuiMethod(methodName = "MyMethod3")
  public ResultWithMessage method3() {
    System.out.println("Print 3");
    return ResultWithMessage.builder().status(TestCaseStepResultStatus.SUCCESS).build();
  }
}
