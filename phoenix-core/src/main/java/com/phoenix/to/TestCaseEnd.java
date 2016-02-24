/**
 * Copyright 2015 Nigel Schuster. Contains information to remove traces of the application after the
 * testcase execution. Currently not used - low priority.
 */


package com.phoenix.to;

import java.io.Serializable;
import java.nio.file.Path;
import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * TestCaseEnd contains information to reset the state of the system to it's state before the
 * testcase was started.
 * 
 * @author nschuste
 * @version 1.0.0
 * @since Dec 1, 2015
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class TestCaseEnd implements Serializable {
  /**
   * List of Paths that should be removed before shutting down.
   * 
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private Collection<Path> removeFiles;
}
