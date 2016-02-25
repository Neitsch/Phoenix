/**
 * Copyright 2016 Nigel Schuster. Dispatches any events involving a JTextComponent.
 */


package com.phoenix.command.dispatcher;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.event.FocusEvent;
import java.util.Optional;

import javax.swing.text.JTextComponent;

import lombok.extern.slf4j.XSlf4j;

import com.phoenix.command.GuiDispatcher;
import com.phoenix.to.TestCaseStep;

/**
 * Dispatches JTextComponent events.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Jan 21, 2016
 */
@XSlf4j
@com.phoenix.spi.GuiDispatcher
public class TextDispatcher implements GuiDispatcher {
  private String curText = null;

  /**
   * {@inheritDoc}
   *
   * @author nschuste
   * @version 1.0.0
   * @see com.phoenix.command.GuiDispatcher#dispatch(java.awt.AWTEvent)
   * @since Jan 21, 2016
   */
  @Override
  public Optional<TestCaseStep> dispatch(final AWTEvent e, final Optional<Component> c) {
    Optional<TestCaseStep> op = Optional.empty();
    if (JTextComponent.class.isAssignableFrom(e.getSource().getClass())) {
      log.trace("TextComponent event: " + e);
      if (e.getID() == FocusEvent.FOCUS_GAINED) {
        this.curText = ((JTextComponent) e.getSource()).getText();
      } else if (e.getID() == FocusEvent.FOCUS_LOST) {
        log.info("JTextComponent lost focus, returning event.");
        final JTextComponent tc = (JTextComponent) e.getSource();
        String newText = tc.getText();
        if (!newText.equals(this.curText)) {
          op =
              Optional.of(TestCaseStep.builder().methodName("text.enter")
                  .args(new String[] {tc.getName(), tc.getText()}).build());
        }
      }
    }
    return op;
  }

}
