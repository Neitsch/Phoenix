/**
 * Copyright 2016 Nigel Schuster. Will retrieve resources from the local filesystem.
 */


package com.phoenix.data;

import java.nio.file.Path;
import java.nio.file.Paths;

import lombok.extern.slf4j.XSlf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoenix.to.TestCase;
import com.phoenix.to.TestCaseHead;

/**
 * LocalRequestModule expects to find resources in the local system.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Feb 11, 2016
 */
@XSlf4j
@EnableSpringConfigured
@Configurable
public class LocalRequestModule implements ToRequestModule {
  @Autowired
  private ObjectMapper mapper;
  /**
   * Location of loaded TestCase(Head)
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  private Path saved;

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.data.ToRequestModule#requestHead(java.lang.String)
   * @since Feb 11, 2016
   */
  @Override
  public TestCaseHead requestHead(final String resourcePath) throws Exception {
    log.entry(resourcePath);
    try {
      return log.exit(this.load(TestCaseHead.class, resourcePath));
    } finally {
      this.saved = this.saved.resolveSibling(this.saved.getFileName().toString() + ".tc");
    }
  }

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.data.ToRequestModule#requestTestCase(java.lang.String)
   * @since Feb 11, 2016
   */
  @Override
  public TestCase requestTestCase(final String resourcePath) throws Exception {
    log.entry(resourcePath);
    return log.exit(this.load(TestCase.class, resourcePath));
  }

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.data.ToRequestModule#saveTc(com.phoenix.to.TestCase)
   * @since Feb 13, 2016
   */
  @Override
  public String saveTc(final TestCase tc) throws Exception {
    log.entry(tc);
    this.mapper.writeValue(this.saved.toFile(), tc);
    return log.exit(this.saved.toAbsolutePath().toString());
  }

  /**
   * Loads given Element from Path.
   *
   * @author nschuste
   * @version 1.0.0
   * @param clazz Class to instanciate for loading
   * @param path path to json file
   * @return instance of class based on json file
   * @throws Exception
   * @since Feb 23, 2016
   */
  private <T> T load(final Class<T> clazz, final String path) throws Exception {
    log.entry(clazz, path);
    this.saved = Paths.get(path);
    return log.exit(this.mapper.readValue(this.saved.toFile(), clazz));
  }
}
