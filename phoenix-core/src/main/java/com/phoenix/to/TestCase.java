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

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

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
public class TestCase implements Serializable {
  /**
   * @author nschuste
   * @version 1.0.0
   * @since Feb 13, 2016
   */
  private static final long serialVersionUID = -8547117539340733237L;
  @Id
  public String id;
  private String name;
  @DBRef
  private TestCaseBody tcBody;
  @DBRef
  private TestCaseHead tcHead;
}
