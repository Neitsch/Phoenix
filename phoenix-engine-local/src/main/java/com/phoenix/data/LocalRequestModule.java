/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.data;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phoenix.to.TestCase;
import com.phoenix.to.TestCaseHead;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Feb 11, 2016
 */
@EnableSpringConfigured
@Configurable
public class LocalRequestModule implements ToRequestModule {
  @Autowired
  private ObjectMapper mapper;

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
    return this.load(TestCaseHead.class, resourcePath);
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
    return this.load(TestCase.class, resourcePath);
  }

  private <T> T load(final Class<T> clazz, final String path) throws Exception {
    return this.mapper.readValue(Paths.get(path).toUri().toURL(), clazz);
  }
}
