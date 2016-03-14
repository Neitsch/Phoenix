/**
 * Copyright 2016 Nigel Schuster. Used to fetch files for user.
 */


package com.phoenix.data;

import com.phoenix.to.TestCase;
import com.phoenix.to.TestCaseHead;

/**
 * ToRequestModule retrieves files for user (e.g. TestCase)
 * 
 * @author nschuste
 * @version 1.0.0
 * @since Feb 11, 2016
 */
public interface ToRequestModule {

  /**
   * Fetches the TestCaseHead with the given resourcePath id
   * 
   * @author nschuste
   * @version 1.0.0
   * @param resourcePath
   * @return
   * @since Feb 11, 2016
   */
  TestCaseHead requestHead(String resourcePath) throws Exception;

  /**
   * Fetches the TestCase with the given resourcePath id
   * 
   * @author nschuste
   * @version 1.0.0
   * @param resourcePath
   * @since Feb 11, 2016
   */
  TestCase requestTestCase(String resourcePath) throws Exception;

  /**
   * Saves a given TestCase back in the system.
   * 
   * @author nschuste
   * @version 1.0.0
   * @param tc
   * @since Feb 13, 2016
   */
  String saveTc(TestCase tc) throws Exception;

}
