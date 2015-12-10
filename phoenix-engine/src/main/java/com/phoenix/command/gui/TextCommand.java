/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.command.gui;

import java.awt.Frame;

import javax.swing.text.JTextComponent;

import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.JTextComponentFixture;

import com.phoenix.command.Environment;
import com.phoenix.spi.GuiPackage;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 10, 2015
 */
@GuiPackage(packageName = "text")
public class TextCommand {
  private static JTextComponentFixture getFixture(final Robot r, final Frame f, final String name,
      final boolean showing) {
    return new JTextComponentFixture(r, r.finder().findByName(f, name, JTextComponent.class,
        showing));
  }

  /**
   * @author nschuste
   * @version 1.0.0
   * @param env
   * @param string
   * @param string2
   * @since Dec 10, 2015
   */
  public void enter(final Environment env, final String... varargs) {
    getFixture(env.getRobot(), env.getFrame(), varargs[0], true).deleteText().enterText(varargs[1]);
  }

}