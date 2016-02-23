/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.command.dispatcher;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.Optional;

import javax.swing.JLabel;

import com.phoenix.command.GuiDispatcher;
import com.phoenix.to.TestCaseStep;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Feb 23, 2016
 */
@com.phoenix.spi.GuiDispatcher
public class LabelDispatcher implements GuiDispatcher {
  @Override
  public Optional<TestCaseStep> dispatch(final AWTEvent e, final Optional<Component> c) {
    Optional<TestCaseStep> op = Optional.empty();
    if (c.isPresent() && JLabel.class.isAssignableFrom(c.get().getClass())) {
      if (e.getID() == MouseEvent.MOUSE_CLICKED) {
        op =
            Optional.of(TestCaseStep.builder().methodName("label.check")
                .args(new String[] {((JLabel) c.get()).getName(), ((JLabel) c.get()).getText()})
                .build());
      }
    }
    return op;
  }
}
