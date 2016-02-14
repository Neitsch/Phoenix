/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.command.dispatcher;

import java.awt.AWTEvent;
import java.awt.event.MouseEvent;
import java.util.Optional;

import javax.swing.JButton;

import com.phoenix.command.GuiDispatcher;
import com.phoenix.to.TestCaseStep;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Jan 21, 2016
 */
@com.phoenix.spi.GuiDispatcher
public class ButtonDispatcher implements GuiDispatcher {

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.command.GuiDispatcher#dispatch(java.awt.event.ActionEvent)
   * @since Jan 21, 2016
   */
  @Override
  public Optional<TestCaseStep> dispatch(final AWTEvent e) {
    Optional op = Optional.empty();
    if (JButton.class.isAssignableFrom(e.getSource().getClass())) {
      if (e.getID() == MouseEvent.MOUSE_CLICKED) {
        op =
            Optional.of(TestCaseStep.builder().methodName("button.click")
                .args(new String[] {((JButton) e.getSource()).getName()}).build());
      }
    }
    return op;
  }

}
