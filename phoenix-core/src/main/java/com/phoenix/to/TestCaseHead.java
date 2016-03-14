/**
 * Copyright 2015 Nigel Schuster. TestCaseHead contains all information for provisioning the system
 * for an application. In future versions it will also contain information on how to reset the
 * status of the system using TestCaseEnd
 */


package com.phoenix.to;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import org.springframework.data.annotation.Id;

/**
 * TestCaseHead contains information for provisioning and resetting the state of the system.
 * 
 * @author nschuste
 * @version 1.0.0
 * @since Nov 21, 2015
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Accessors(chain = true)
public class TestCaseHead implements Serializable {
  /**
   * Unique identifier. Note multiple Testcases can share the same TestCaseHead
   * 
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  @Id
  private String id;
  /**
   * Optional: Name to identify the TestCaseHead
   * 
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private String name;
  /**
   * Information on how to provision the system.
   * 
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private TestCaseSetup setup;
}
