/**
 * Copyright 2015 Nigel Schuster. TestResult itself stores all meta information of the Testcase
 * execution.
 */


package com.phoenix.to;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

/**
 * TestResult contains meta information on the execution as well as the step results itself.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Nov 21, 2015
 */
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestResult implements Serializable {
  @Version
  private long version;
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
   * ID of testcase that was executed.
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private String tcId;
  /**
   * Title of the testcase that was executed.
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private String title;
  /**
   * result of the execution.
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private TestCaseBodyResult result;
  /**
   * Overall status of the testcase execution.
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private boolean success;
  /**
   * Time the provisioning of the system prior to the testcase execution was started.
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private Date start = new Date();
  /**
   * Time the tearing down of the system after the testcase execution was completed.
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private Date end = new Date();
}
