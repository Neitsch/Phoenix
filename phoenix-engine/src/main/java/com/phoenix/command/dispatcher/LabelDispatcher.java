/**
 * Copyright 2016 Nigel Schuster. Dispatches any events involving a JLabel.
 */


package com.phoenix.command.dispatcher;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.Optional;

import javax.swing.JLabel;

import lombok.extern.slf4j.XSlf4j;

import com.phoenix.command.GuiDispatcher;
import com.phoenix.to.TestCaseStep;

/**
 * Dispatches JLabel events.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Feb 23, 2016
 */
@XSlf4j
@com.phoenix.spi.GuiDispatcher
public class LabelDispatcher implements GuiDispatcher {
  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.command.GuiDispatcher#dispatch(java.awt.AWTEvent, java.util.Optional)
   * @since Feb 23, 2016
   */
  @Override
  public Optional<TestCaseStep> dispatch(final AWTEvent e, final Optional<Component> c) {
    Optional<TestCaseStep> op = Optional.empty();
    if (c.isPresent() && JLabel.class.isAssignableFrom(c.get().getClass())) {
      log.trace("Label event: " + e);
      if (e.getID() == MouseEvent.MOUSE_CLICKED) {
        log.info("Mouse was clicked on JLabel, returning event.");
        op =
            Optional.of(TestCaseStep.builder().methodName("label.check")
                .args(new String[] {((JLabel) c.get()).getName(), ((JLabel) c.get()).getText()})
                .build());
      }
    }
    return op;
  }
}
