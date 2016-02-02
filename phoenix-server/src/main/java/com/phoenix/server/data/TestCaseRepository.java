/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.server.data;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.phoenix.to.TestCase;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Jan 27, 2016
 */
public interface TestCaseRepository extends MongoRepository<TestCase, String> {
  /**
   * @author nschuste
   * @version 1.0.0
   * @param string
   * @return
   * @since Feb 1, 2016
   */
  Collection<TestCase> findByNameIgnoreCaseContains(String string);

}
