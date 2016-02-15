/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.to;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 21, 2015
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "type")
public interface DownloadWorker extends Serializable {

  /**
   * @author nschuste
   * @version 1.0.0
   * @param downloads
   * @throws IOException
   * @since Dec 21, 2015
   */
  void doDownload(Path downloads) throws IOException;

}
