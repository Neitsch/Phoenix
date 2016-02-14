/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import org.springframework.data.annotation.Id;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Nov 21, 2015
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Accessors(chain = true)
public class TestCaseHead {
  @Id
  private String id;
  private String name;
  private TestCaseSetup setup;
}
