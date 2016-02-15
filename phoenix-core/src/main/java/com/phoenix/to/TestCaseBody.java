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

import org.springframework.data.annotation.Id;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Nov 21, 2015
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Accessors(chain = true)
public class TestCaseBody implements Serializable {
  @Id
  private String id;
  private List<TestCaseStep> lines = new LinkedList<>();
}
