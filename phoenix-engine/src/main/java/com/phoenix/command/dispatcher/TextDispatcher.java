/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.command.dispatcher;

import java.awt.AWTEvent;
import java.awt.event.FocusEvent;
import java.util.Optional;

import javax.swing.text.JTextComponent;

import com.phoenix.command.GuiDispatcher;
import com.phoenix.to.TestCaseStep;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Jan 21, 2016
 */
public class TextDispatcher implements GuiDispatcher {

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.command.GuiDispatcher#dispatch(java.awt.AWTEvent)
   * @since Jan 21, 2016
   */
  @Override
  public Optional<TestCaseStep> dispatch(final AWTEvent e) {
    Optional<TestCaseStep> op = Optional.empty();
    if (JTextComponent.class.isAssignableFrom(e.getSource().getClass())) {
      if (e.getID() == FocusEvent.FOCUS_LOST) {
        final JTextComponent tc = (JTextComponent) e.getSource();
        op =
            Optional.of(TestCaseStep.builder().methodName("enter")
                .args(new String[] {tc.getName(), tc.getText()}).build());
      }
    }
    return op;
  }

}
