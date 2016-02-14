/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.data;

import com.phoenix.to.TestCase;
import com.phoenix.to.TestCaseHead;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Feb 11, 2016
 */
public interface ToRequestModule {

  /**
   * @author nschuste
   * @version 1.0.0
   * @param resourcePath
   * @return
   * @since Feb 11, 2016
   */
  TestCaseHead requestHead(String resourcePath) throws Exception;

  /**
   * @author nschuste
   * @version 1.0.0
   * @param resourcePath
   * @since Feb 11, 2016
   */
  TestCase requestTestCase(String resourcePath) throws Exception;

}
