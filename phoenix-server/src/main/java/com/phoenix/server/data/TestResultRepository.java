/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.server.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.phoenix.to.TestResult;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Jan 27, 2016
 */
public interface TestResultRepository extends MongoRepository<TestResult, String> {
}
