/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.recorder;

import java.util.concurrent.Semaphore;

import com.phoenix.to.ResultWithMessage;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Feb 11, 2016
 */
public class GuiInterface implements UserInterface {

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.recorder.UserInterface#getResourcePath()
   * @since Feb 12, 2016
   */
  @Override
  public String getResourcePath() {
    throw new RuntimeException();
  }

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.recorder.UserInterface#phase(com.phoenix.recorder.UserInterface.PHASE)
   * @since Feb 12, 2016
   */
  @Override
  public void phase(final PHASE preparation) {
    throw new RuntimeException();
  }

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.recorder.UserInterface#proceed(java.util.concurrent.Semaphore)
   * @since Feb 12, 2016
   */
  @Override
  public void proceed(final Semaphore sem) {
    throw new RuntimeException();
  }

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.recorder.UserInterface#remoteResource()
   * @since Feb 12, 2016
   */
  @Override
  public boolean remoteResource() {
    throw new RuntimeException();
  }

  /**
   * {@inheritDoc}
   * 
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.recorder.UserInterface#saveTestCase()
   * @since Feb 13, 2016
   */
  @Override
  public boolean saveTestCase() {
    throw new RuntimeException();
  }

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.recorder.UserInterface#shouldCreateNew()
   * @since Feb 12, 2016
   */
  @Override
  public boolean shouldCreateNew() {
    throw new RuntimeException();
  }

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.recorder.UserInterface#stepResult(com.phoenix.to.TestCaseStepResult)
   * @since Feb 12, 2016
   */
  @Override
  public void stepResult(final ResultWithMessage e) {
    throw new RuntimeException();
  }

}
