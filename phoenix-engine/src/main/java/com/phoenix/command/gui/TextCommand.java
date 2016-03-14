/**
 * Copyright 2015 Nigel Schuster. Offers a collection of commands for a text component: check text,
 * enter text
 */


package com.phoenix.command.gui;

import java.awt.Frame;

import javax.swing.text.JTextComponent;

import lombok.extern.slf4j.XSlf4j;

import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.JTextComponentFixture;

import com.phoenix.command.Environment;
import com.phoenix.spi.GuiMethod;
import com.phoenix.spi.GuiPackage;
import com.phoenix.to.ResultWithMessage;
import com.phoenix.to.TestCaseStepResultStatus;

/**
 * Provides a set of commands that can be performed on a text component.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Dec 10, 2015
 */
@XSlf4j
@GuiPackage(packageName = "text")
public class TextCommand {
  /**
   * Creates a fixture for a text component
   *
   * @author nschuste
   * @version 1.0.0
   * @param r Robot used to create fixture
   * @param f Frame where text component is located in
   * @param name Name of text component
   * @param showing Required showing?
   * @return Fixture of text component.
   * @since Feb 23, 2016
   */
  private static JTextComponentFixture getFixture(final Robot r, final Frame f, final String name,
      final boolean showing) {
    return new JTextComponentFixture(r, r.finder().findByName(f, name, JTextComponent.class,
        showing));
  }

  /**
   * Asserts that text component text equals given text
   *
   * @author nschuste
   * @version 1.0.0
   * @param env
   * @param varargs [0] --> name of text component [1] --> required text.
   * @return Outcome of check.
   * @since Feb 23, 2016
   */
  @GuiMethod(methodName = "check")
  public ResultWithMessage check(final Environment env, final String... varargs) {
    log.entry((Object) varargs);
    getFixture(env.getRobot(), env.getFrame(), varargs[0], true).requireText(varargs[1]);
    return log.exit(ResultWithMessage.builder().status(TestCaseStepResultStatus.SUCCESS).build());
  }

  /**
   * Enters a given String into a text component.
   *
   * @author nschuste
   * @version 1.0.0
   * @param env
   * @param varargs [0] --> text component name [1] --> text to enter
   * @return
   * @since Feb 23, 2016
   */
  @GuiMethod(methodName = "enter")
  public ResultWithMessage enter(final Environment env, final String... varargs) {
    log.entry((Object) varargs);
    getFixture(env.getRobot(), env.getFrame(), varargs[0], true).deleteText().enterText(varargs[1]);
    return log.exit(ResultWithMessage.builder().status(TestCaseStepResultStatus.SUCCESS).build());
  }
}
