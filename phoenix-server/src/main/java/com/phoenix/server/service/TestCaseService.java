/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.server.service;

/**
 * Simple Service interface to controll TestCases
 * 
 * @author nschuste
 * @version 1.0.0
 * @since Feb 1, 2016
 */
public interface TestCaseService {
  /**
   * Starts testcase by its ID
   * 
   * @author nschuste
   * @version 1.0.0
   * @param id
   * @since Feb 24, 2016
   */
  public void start(String id);
}
