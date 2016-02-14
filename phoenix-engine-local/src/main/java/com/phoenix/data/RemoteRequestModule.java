/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.data;

import org.springframework.web.client.RestTemplate;

import com.phoenix.to.TestCase;
import com.phoenix.to.TestCaseHead;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Feb 11, 2016
 */
public class RemoteRequestModule implements ToRequestModule {
  private static String resolvePath(final Class<?> clazz, final String path) {
    final StringBuilder builder = new StringBuilder();
    builder.append(System.getProperty("server.host.address"));
    if (clazz.isAssignableFrom(TestCase.class)) {
      builder.append("/tc");
    } else if (clazz.isAssignableFrom(TestCaseHead.class)) {
      builder.append("/tch");
    }
    return builder.append(path).toString();
  }

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
    return this.request(TestCaseHead.class, resourcePath);
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
    return this.request(TestCase.class, resourcePath);
  }

  private <T> T request(final Class<T> clazz, final String path) {
    final RestTemplate restTemplate = new RestTemplate();
    return restTemplate.getForObject(resolvePath(clazz, path), clazz);
  }
}
