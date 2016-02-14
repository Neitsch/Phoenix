/**
 * Copyright 2015 Nigel Schuster.
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
  private List<TestCaseStepResult> stepResults = new LinkedList<>();
}
