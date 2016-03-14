/**
 * Copyright 2015 Nigel Schuster. The Downloadworker can be used to provision the application with
 * files. Downloads points to the root directory of where the downloads should be placed.
 */


package com.phoenix.to;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Interface to perform an operation to provision the filesystem so that all needed files are
 * available to the target application.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Dec 21, 2015
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "type")
public interface DownloadWorker extends Serializable {

  /**
   * @author nschuste
   * @version 1.0.0
   * @param downloads root of the application directory
   * @throws IOException thrown if operation to transfer file fails.
   * @since Dec 21, 2015
   */
  void doDownload(Path downloads) throws IOException;
}
