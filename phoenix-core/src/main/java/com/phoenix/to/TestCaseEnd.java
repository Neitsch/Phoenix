/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.to;

import java.io.Serializable;
import java.nio.file.Path;
import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 1, 2015
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class TestCaseEnd implements Serializable {
  private Collection<Path> removeFiles;
}
