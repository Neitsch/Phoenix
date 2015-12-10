/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Nov 21, 2015
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TestCaseStep {
  private String[] args;
  private String methodName;
}
