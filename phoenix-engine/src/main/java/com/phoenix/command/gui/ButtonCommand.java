/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.command.gui;

import java.awt.Frame;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;

import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.JButtonFixture;

import com.phoenix.command.Environment;
import com.phoenix.spi.GuiMethod;
import com.phoenix.spi.GuiPackage;
import com.phoenix.to.ResultWithMessage;
import com.phoenix.to.TestCaseStepResultStatus;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 7, 2015
 */
@GuiPackage(packageName = "button")
public class ButtonCommand {
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
  public ResultWithMessage check(final Environment env, final String... varargs) {
    final JButtonFixture fixture = getFixture(env.getRobot(), env.getFrame(), varargs[0], true);
    fixture.requireText(varargs[1]);
    return ResultWithMessage.builder().status(TestCaseStepResultStatus.SUCCESS).build();
  }

  @GuiMethod(methodName = "disabled")
  public ResultWithMessage checkDisabled(final Environment env, final String... varargs) {
    final JButtonFixture fixture = getFixture(env.getRobot(), env.getFrame(), varargs[0], true);
    fixture.requireDisabled();
    return ResultWithMessage.builder().status(TestCaseStepResultStatus.SUCCESS).build();
  }

  @GuiMethod(methodName = "click")
  public ResultWithMessage click(final Environment env, final String... varargs) throws Exception {
    final JButtonFixture fixture = getFixture(env.getRobot(), env.getFrame(), varargs[0], true);
    Semaphore sem = new Semaphore(0);
    fixture.target().addActionListener(e -> {
      sem.release();
    });
    fixture.click();
    if (!sem.tryAcquire(2, TimeUnit.SECONDS)) {
      throw new Exception("Click did not occur!");
    }
    return ResultWithMessage.builder().status(TestCaseStepResultStatus.SUCCESS).build();
  }
}
