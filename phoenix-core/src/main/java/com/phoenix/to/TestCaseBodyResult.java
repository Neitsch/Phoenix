/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.to;

import java.util.LinkedList;
import java.util.List;

import lombok.Data;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 10, 2015
 */
@Data
public class TestCaseBodyResult {
  private List<TestCaseStepResult> stepResults = new LinkedList<>();
}
