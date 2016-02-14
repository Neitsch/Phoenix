/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 1, 2015
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class TestCaseStepResult {
  private TestCaseStepResultStatus result;
  private TestCaseStep step;
}
