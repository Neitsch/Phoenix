/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.util;

import java.io.File;
import java.io.IOException;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 21, 2015
 */
public class FS {
  public static File createTempDirectory() throws IOException {
    final File temp;
    temp = File.createTempFile("temp", Long.toString(System.nanoTime()));
    if (!(temp.delete())) {
      throw new IOException("Could not delete temp file: " + temp.getAbsolutePath());
    }
    if (!(temp.mkdir())) {
      throw new IOException("Could not create temp directory: " + temp.getAbsolutePath());
    }
    return (temp);
  }
}
