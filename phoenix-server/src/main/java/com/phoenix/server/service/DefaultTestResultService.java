/**
 * Copyright 2016 Nigel Schuster. Simple service to save a TestResult, not necessarily needed,
 * however an extra level of indirection might come in handy for analytics.
 */


package com.phoenix.server.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.XSlf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phoenix.server.data.TestResultRepository;
import com.phoenix.to.TestResult;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Feb 15, 2016
 */
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@XSlf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service(value = "defaultTestResultService")
public class DefaultTestResultService implements TestResultService {
  TestResultRepository repository;

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.server.service.TestResultService#save()
   * @since Feb 15, 2016
   */
  @Override
  public String save(final TestResult result) {
    log.entry(result);
    return log.exit(this.repository.save(result).getId());
  }
}
