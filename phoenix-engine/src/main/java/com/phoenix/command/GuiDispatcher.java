/**
 * Copyright 2016 Nigel Schuster. Interface to implement by any class that wants to dispatch
 * AWTEvents for the testcase.
 */


package com.phoenix.command;

import java.awt.AWTEvent;
import java.awt.Component;
import java.util.Optional;

import com.phoenix.to.TestCaseStep;

/**
 * Interface to implement for dispatching AWTEvents in the application.
 * 
 * @author nschuste
 * @version 1.0.0
 * @since Jan 21, 2016
 */
public interface GuiDispatcher {
  /**
   * Invoked on any AWTEvent.
   *
   * @author nschuste
   * @version 1.0.0
   * @param e the given AWT Event
   * @param c current Component the mouse is hovering on
   * @return empty, if not handled, otherwise corresponing testcase step.
   * @since Feb 23, 2016
   */
  public Optional<TestCaseStep> dispatch(AWTEvent e, Optional<Component> c);
}
