/**
 * Copyright 2015 Nigel Schuster. This is the "body" of the testcase. This is, it hold all steps for
 * the testcase exeution. The testcasebody has it's own table, as the body might be big compared to
 * the meta information. Hence we might not want to fetch it when loading meta information.
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
 * Body of the testcase. Thus contains all information necessary to do the EXECUTION phase of a
 * testcase.
 *
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
  /**
   * Unique identifier
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  @Id
  private String id;
  /**
   * The list holds the steps of the testcase.
   * 
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private List<TestCaseStep> lines = new LinkedList<>();
}
