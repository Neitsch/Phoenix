/**
 * Copyright 2016 Nigel Schuster. Simple Repository for TestCaseBodies.
 */


package com.phoenix.server.data;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.phoenix.to.TestCaseBody;

/**
 * Default Repository for {@TestCaseBody}
 * 
 * @author nschuste
 * @version 1.0.0
 * @since Jan 27, 2016
 */
public interface TestCaseBodyRepository extends MongoRepository<TestCaseBody, String> {
}
