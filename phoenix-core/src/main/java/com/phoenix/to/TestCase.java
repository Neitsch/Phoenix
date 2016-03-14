/**
 * Copyright 2015 Nigel Schuster. This contains all information about one full executable testcase.
 */


package com.phoenix.to;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * Class that hold all information about an executable testcase.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Nov 21, 2015
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class TestCase implements Serializable {
  private static final long serialVersionUID = -8547117539340733237L;
  /**
   * Unique identifier of the testcase.
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  @Id
  public String id;
  /**
   * Name of the testcase.
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private String name;
  /**
   * {@link TestCaseBody} hold the steps of the testcase.
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  @DBRef
  private TestCaseBody tcBody;
  /**
   * {@link TestCaseHead} holds information to provision and teardown the testcase.
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  @DBRef
  private TestCaseHead tcHead;
}
