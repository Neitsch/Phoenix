/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Nov 21, 2015
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestResult {
  @Id
  private String id;
  private TestCaseBodyResult result;
  private boolean success;
}
