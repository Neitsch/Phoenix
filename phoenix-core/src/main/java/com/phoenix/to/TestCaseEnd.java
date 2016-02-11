/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.to;

import java.nio.file.Path;
import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 1, 2015
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TestCaseEnd {
  private Collection<Path> removeFiles;
}
