/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.to;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.Data;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Jan 7, 2016
 */
@Data
public class CopyWorker implements DownloadWorker {
  private String destination;
  private String origin;

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @throws IOException
   * @see com.phoenix.to.DownloadWorker#doDownload(java.nio.file.Path)
   * @since Jan 7, 2016
   */
  @Override
  public void doDownload(final Path downloads) throws IOException {
    Files.copy(Paths.get(this.origin), downloads.resolve(this.destination));
  }

}
