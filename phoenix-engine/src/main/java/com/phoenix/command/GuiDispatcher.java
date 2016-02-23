/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.command;

import java.awt.AWTEvent;
import java.awt.Component;
import java.util.Optional;

import com.phoenix.to.TestCaseStep;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Jan 21, 2016
 */
public interface GuiDispatcher {
  public Optional<TestCaseStep> dispatch(AWTEvent e, Optional<Component> c);
}
