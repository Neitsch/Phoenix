/**
 * Copyright 2015 Nigel Schuster. Analogous to TestCaseBody, but holds the results of the execution
 * phase. Not stored in a separate collection, because the results of each step will be accessed
 * more frequently.
 */


package com.phoenix.to;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * TestCaseBodyResult holds the results of the steps in the execution.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Dec 10, 2015
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Accessors(chain = true)
public class TestCaseBodyResult implements Serializable {
  /**
   * List of results for each step in the execution.
   * 
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private List<TestCaseStepResult> stepResults = new LinkedList<>();
}
