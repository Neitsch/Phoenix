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
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestResult {
  private boolean success;
}
