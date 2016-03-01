/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.server.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.phoenix.to.TestSuite;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Feb 26, 2016
 */
public interface TestSuiteRepository extends MongoRepository<TestSuite, String> {

}
