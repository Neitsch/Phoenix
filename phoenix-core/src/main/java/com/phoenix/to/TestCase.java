/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.to;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Nov 21, 2015
 */
@Data
public class TestCase {
  @Id
  public String id;
  @DBRef
  private TestCaseBody tcBody;
  @DBRef
  private TestCaseHead tcHead;
}
