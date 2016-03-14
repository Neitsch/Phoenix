/**
 * Copyright 2016 Nigel Schuster.
 *
 * This class is a way to copy files inside the local file system before the testcase execution.
 */


package com.phoenix.to;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * Copies a file inside the local filesystem system.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Jan 7, 2016
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
public class CopyWorker implements DownloadWorker {
  /**
   * The name that the file should have after copying it over.
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  String destination;
  /**
   * The origin of the file on the local filesystem.
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  String origin;

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.to.DownloadWorker#doDownload(java.nio.file.Path)
   * @since Feb 23, 2016
   */
  @Override
  public void doDownload(final Path downloads) throws IOException {
    Files.copy(Paths.get(this.origin), downloads.resolve(this.destination));
  }

}
