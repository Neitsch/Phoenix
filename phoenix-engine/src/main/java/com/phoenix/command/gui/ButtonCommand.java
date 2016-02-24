/**
 * Copyright 2015 Nigel Schuster. Offers a collection of commands for a button: check text, check
 * disabled, click
 */


package com.phoenix.command.gui;

import java.awt.Frame;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;

import lombok.extern.slf4j.XSlf4j;

import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.JButtonFixture;

import com.phoenix.command.Environment;
import com.phoenix.spi.GuiMethod;
import com.phoenix.spi.GuiPackage;
import com.phoenix.to.ResultWithMessage;
import com.phoenix.to.TestCaseStepResultStatus;

/**
 * Provides a set of commands that can be performed on a button.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Dec 7, 2015
 */
@XSlf4j
@GuiPackage(packageName = "button")
public class ButtonCommand {
  /**
   * Creates a fixture for a button
   *
   * @author nschuste
   * @version 1.0.0
   * @param r Robot used to create fixture
   * @param f Frame where button is located in
   * @param name Name of Button
   * @param showing Required showing?
   * @return Fixture of button.
   * @since Feb 23, 2016
   */
  private static JButtonFixture getFixture(final Robot r, final Frame f, final String name,
      final boolean showing) {
    log.entry(r, f, name, showing);
    return log.exit(new JButtonFixture(r, r.finder().findByName(f, name, JButton.class, showing)));
  }

  /**
   * Asserts text in button equals given value
   *
   * @author nschuste
   * @version 1.0.0
   * @param env
   * @param varargs [0] --> Button name [1] --> required text
   * @return outcome of check
   * @since Feb 23, 2016
   */
  @GuiMethod(methodName = "check")
  public ResultWithMessage check(final Environment env, final String... varargs) {
    log.entry((Object) varargs);
    final JButtonFixture fixture = getFixture(env.getRobot(), env.getFrame(), varargs[0], true);
    fixture.requireText(varargs[1]);
    return log.exit(ResultWithMessage.builder().status(TestCaseStepResultStatus.SUCCESS).build());
  }

  /**
   * Ensures that button is disabled
   *
   * @author nschuste
   * @version 1.0.0
   * @param env
   * @param varargs [0] --> Button name
   * @return outcome of check
   * @since Feb 23, 2016
   */
  @GuiMethod(methodName = "disabled")
  public ResultWithMessage checkDisabled(final Environment env, final String... varargs) {
    log.entry((Object) varargs);
    final JButtonFixture fixture = getFixture(env.getRobot(), env.getFrame(), varargs[0], true);
    // TODO(nigel): Feb 23, 2016 require enabled instead
    fixture.requireDisabled();
    return log.exit(ResultWithMessage.builder().status(TestCaseStepResultStatus.SUCCESS).build());
  }

  /**
   * Clicks button
   *
   * @author nschuste
   * @version 1.0.0
   * @param env
   * @param varargs [0] --> Button name
   * @return whether click was successful
   * @throws Exception
   * @since Feb 23, 2016
   */
  @GuiMethod(methodName = "click")
  public ResultWithMessage click(final Environment env, final String... varargs) throws Exception {
    log.entry((Object) varargs);
    final JButtonFixture fixture = getFixture(env.getRobot(), env.getFrame(), varargs[0], true);
    // Ensure that button was actually clicked
    Semaphore sem = new Semaphore(0);
    fixture.target().addActionListener(e -> {
      sem.release();
    });
    fixture.click();
    // TODO(nigel): Feb 23, 2016 dynamic timeout
    if (!sem.tryAcquire(2, TimeUnit.SECONDS)) {
      throw new InterruptedException("Click did not occur!");
    }
    return log.exit(ResultWithMessage.builder().status(TestCaseStepResultStatus.SUCCESS).build());
  }
}
