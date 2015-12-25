/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.to;

import java.nio.file.Path;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 21, 2015
 */
public interface DownloadWorker {

  /**
   * @author nschuste
   * @version 1.0.0
   * @param downloads
   * @since Dec 21, 2015
   */
  void doDownload(Path downloads);

}
