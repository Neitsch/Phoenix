/**
 * Copyright 2016 Nigel Schuster. Dispatches any events involving a JButton.
 */


package com.phoenix.command.dispatcher;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.Optional;

import javax.swing.JButton;

import lombok.extern.slf4j.XSlf4j;

import com.phoenix.command.GuiDispatcher;
import com.phoenix.to.TestCaseStep;

/**
 * Dispatches JButton events.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Jan 21, 2016
 */
@XSlf4j
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
  public Optional<TestCaseStep> dispatch(final AWTEvent e, final Optional<Component> c) {
    Optional<TestCaseStep> op = Optional.empty();
    if (JButton.class.isAssignableFrom(e.getSource().getClass())) {
      log.trace("Button event: " + e);
      if (e.getID() == MouseEvent.MOUSE_CLICKED) {
        log.info("Mouse was clicked on JButton, returning event.");
        op =
            Optional.of(TestCaseStep.builder().methodName("button.click")
                .args(new String[] {((JButton) e.getSource()).getName()}).build());
      }
    }
    return op;
  }

}
