/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.command.gui;

import java.awt.Frame;

import javax.swing.JButton;

import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.JButtonFixture;

import com.phoenix.command.Environment;
import com.phoenix.spi.GuiMethod;
import com.phoenix.spi.GuiPackage;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 7, 2015
 */
@GuiPackage(packageName = "button")
public class ButtonCommands {
  private static JButtonFixture getFixture(final Robot r, final Frame f, final String name,
      final boolean showing) {
    return new JButtonFixture(r, r.finder().findByName(f, name, JButton.class, showing));
  }

  /**
   * @author nschuste
   * @version 1.0.0
   * @param env
   * @param string
   * @param string2
   * @since Dec 10, 2015
   */
  @GuiMethod(methodName = "check")
  public void check(final Environment env, final String... varargs) {
    final JButtonFixture fixture = getFixture(env.getRobot(), env.getFrame(), varargs[0], true);
    fixture.requireText(varargs[1]);
  }

  @GuiMethod(methodName = "click")
  public void click(final Environment env, final String... varargs) {
    final JButtonFixture fixture = getFixture(env.getRobot(), env.getFrame(), varargs[0], true);
    fixture.click();
  }
}
