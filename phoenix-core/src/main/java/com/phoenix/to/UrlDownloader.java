/**
 * Copyright 2016 Nigel Schuster. Downloads a file from a provided link to the local file system,
 * given by destination.
 */


package com.phoenix.to;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Downloads file from provided URL to filesystem.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Feb 14, 2016
 */
@Data
@Accessors(chain = true)
public class UrlDownloader implements DownloadWorker {
  /**
   * Destination to where the file should be copied.
   * 
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private String destination;
  /**
   * URL to retrieve the file from.
   * 
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
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
    URL website = new URL(this.origin);
    try (InputStream in = website.openConnection().getInputStream()) {
      Files.copy(in, downloads.resolve(this.destination), StandardCopyOption.REPLACE_EXISTING);
    }
  }
}
