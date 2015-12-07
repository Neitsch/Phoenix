/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.command.gui;

import java.awt.Component;

import org.assertj.swing.core.Robot;

import com.phoenix.command.Environment;
import com.phoenix.spi.GuiMethod;
import com.phoenix.spi.GuiPackage;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 7, 2015
 */
@GuiPackage(packageName = "base")
public class BaseCommands {
  @GuiMethod(methodName = "click")
  public void click(final Environment env, final String... varargs) {
    final Robot r = env.getRobot();
    final Component c = r.finder().findByName(env.getFrame(), varargs[0]);
    r.click(c);
  }
}
