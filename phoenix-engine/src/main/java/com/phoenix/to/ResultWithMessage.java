/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.to;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 25, 2015
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResultWithMessage {
  private Optional<Exception> exception;
  private Optional<String> message;
  private TestCaseStepResultStatus status;
}
