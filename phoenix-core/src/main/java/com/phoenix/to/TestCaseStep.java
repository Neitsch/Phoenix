/**
 * Copyright 2015 Nigel Schuster. This is one single step for a testcase, e.g. clicking a button or
 * checking a textfield.
 */


package com.phoenix.to;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * TestCaseStep hold information to perform a single step in the testcase / perform a single action
 * in the testcase.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Nov 21, 2015
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class TestCaseStep implements Serializable {
  /**
   * Arguments to give to the method that performs the step.
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private String[] args;
  /**
   * Identifier of the method in the format (package name).(method name)
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private String methodName;
}
