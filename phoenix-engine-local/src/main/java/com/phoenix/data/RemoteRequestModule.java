/**
 * Copyright 2016 Nigel Schuster. Will retrieve resources from central server.
 */


package com.phoenix.data;

import lombok.extern.slf4j.XSlf4j;

import org.springframework.web.client.RestTemplate;

import com.phoenix.to.TestCase;
import com.phoenix.to.TestCaseHead;


/**
 * RemoteRequestModule expects to find resources on the server.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Feb 11, 2016
 */
@XSlf4j
public class RemoteRequestModule implements ToRequestModule {
  /**
   * Builds post request according to server standards.
   *
   * @author nschuste
   * @version 1.0.0
   * @param object
   * @return
   * @since Feb 23, 2016
   */
  private static <T> String post(final T object) {
    log.entry(object);
    RestTemplate restTemplate = new RestTemplate();
    final StringBuilder builder = new StringBuilder().append("http://");
    builder.append(System.getProperty("server.host.address"));
    @SuppressWarnings("unchecked")
    Class<T> clazz = (Class<T>) object.getClass();
    if (clazz.isAssignableFrom(TestCase.class)) {
      builder.append("/tc/");
    } else if (clazz.isAssignableFrom(TestCaseHead.class)) {
      builder.append("/tch/");
    }
    return log.exit(restTemplate.postForObject(builder.toString(), object, String.class));
  }

  /**
   * Builds get request according to server standards.
   *
   * @author nschuste
   * @version 1.0.0
   * @param clazz
   * @param path
   * @return
   * @since Feb 23, 2016
   */
  private static String resolvePath(final Class<?> clazz, final String path) {
    log.entry(clazz, path);
    final StringBuilder builder = new StringBuilder().append("http://");
    builder.append(System.getProperty("server.host.address"));
    if (clazz.isAssignableFrom(TestCase.class)) {
      builder.append("/tc/id/");
    } else if (clazz.isAssignableFrom(TestCaseHead.class)) {
      builder.append("/tch/");
    }
    return log.exit(builder.append(path).toString());
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
    log.entry(resourcePath);
    return log.exit(this.request(TestCaseHead.class, resourcePath));
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
    return log.exit(this.request(TestCase.class, resourcePath));
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
    return log.exit(post(tc));
  }

  /**
   * Executes request to retrieve Object.
   * 
   * @author nschuste
   * @version 1.0.0
   * @param clazz
   * @param path
   * @return
   * @since Feb 23, 2016
   */
  private <T> T request(final Class<T> clazz, final String path) {
    log.entry(clazz, path);
    final RestTemplate restTemplate = new RestTemplate();
    return log.exit(restTemplate.getForObject(resolvePath(clazz, path), clazz));
  }
}
