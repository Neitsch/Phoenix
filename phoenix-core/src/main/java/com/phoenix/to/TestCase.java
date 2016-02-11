/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.to;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class TestCase implements Serializable {
  @Id
  public String id;
  private String name;
  @DBRef
  private TestCaseBody tcBody;
  @DBRef
  private TestCaseHead tcHead;
}
