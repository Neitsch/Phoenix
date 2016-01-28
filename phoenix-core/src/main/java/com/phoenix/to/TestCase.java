/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.to;

import lombok.Data;

import org.springframework.data.annotation.Id;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Nov 21, 2015
 */
@Data
public class TestCase {
  @Id
  public String id;
  private TestCaseBody tcBody;
  private TestCaseHead tcHead;
}
