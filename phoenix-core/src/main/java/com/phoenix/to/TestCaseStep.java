/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.to;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Nov 21, 2015
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class TestCaseStep implements Serializable {
  private String[] args;
  private String methodName;
}
