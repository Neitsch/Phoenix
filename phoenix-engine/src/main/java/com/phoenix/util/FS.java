/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.util;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 21, 2015
 */
public class FS {
  public static void delete(final Path p) throws IOException {
    Files.walkFileTree(p, new SimpleFileVisitor<Path>() {

      @Override
      public FileVisitResult postVisitDirectory(final Path dir, final IOException exc)
          throws IOException {
        if (exc == null) {
          Files.delete(dir);
          return FileVisitResult.CONTINUE;
        } else {
          throw exc;
        }
      }

      @Override
      public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs)
          throws IOException {
        Files.delete(file);
        return FileVisitResult.CONTINUE;
      }

    });
  }
}
