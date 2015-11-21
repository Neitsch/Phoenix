/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.to;

import java.util.List;

import lombok.Data;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Nov 21, 2015
 */
@Data
public class TestCaseBody {
  private List<TestCaseLine> lines;
}
