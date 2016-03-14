/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.to;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import org.springframework.data.annotation.Id;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Feb 26, 2016
 */
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestSuite {
  @Id
  private String id;
  private String name;
  // https://docs.mongodb.org/manual/reference/database-references/
  // Unless you have a compelling reason to use DBRefs, use manual references instead.
  private Set<String> testcaseids;
}
