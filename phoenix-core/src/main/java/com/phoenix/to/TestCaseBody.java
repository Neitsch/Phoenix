/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.to;

import java.util.LinkedList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Nov 21, 2015
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TestCaseBody {
  private List<TestCaseStep> lines = new LinkedList<>();
}
