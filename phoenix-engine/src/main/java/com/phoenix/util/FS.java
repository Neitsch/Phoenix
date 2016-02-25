/**
 * Copyright 2015 Nigel Schuster. Convenience class and enabled mocking with Powermock.
 */


package com.phoenix.util;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import lombok.extern.slf4j.XSlf4j;

/**
 * Convenience class for deleting a directory.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Dec 21, 2015
 */
@XSlf4j
public class FS {
  /**
   * Deletes directory and all files in it.
   *
   * @author nschuste
   * @version 1.0.0
   * @param p
   * @throws IOException
   * @since Feb 23, 2016
   */
  public static void delete(final Path p) throws IOException {
    log.entry(p);
    Files.walkFileTree(p, new SimpleFileVisitor<Path>() {

      @Override
      public FileVisitResult postVisitDirectory(final Path dir, final IOException exc)
          throws IOException {
        log.entry(dir, exc);
        if (exc == null) {
          log.trace("Deleting: " + dir);
          Files.delete(dir);
          return log.exit(FileVisitResult.CONTINUE);
        } else {
          log.catching(exc);
          throw exc;
        }
      }

      @Override
      public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs)
          throws IOException {
        log.entry(file);
        Files.delete(file);
        return log.exit(FileVisitResult.CONTINUE);
      }

    });
  }
}
