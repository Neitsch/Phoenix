/**
 * Copyright 2016 Nigel Schuster. Offers a collection of commands for a label: check text
 */


package com.phoenix.command.gui;

import java.awt.Frame;

import javax.swing.JLabel;

import lombok.extern.slf4j.XSlf4j;

import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.JLabelFixture;

import com.phoenix.command.Environment;
import com.phoenix.spi.GuiMethod;
import com.phoenix.spi.GuiPackage;
import com.phoenix.to.ResultWithMessage;
import com.phoenix.to.TestCaseStepResultStatus;

/**
 * Provides a set of commands that can be performed on a label.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Feb 23, 2016
 */
@XSlf4j
@GuiPackage(packageName = "label")
public class LabelCommand {
  /**
   * Creates a fixture for a label
   *
   * @author nschuste
   * @version 1.0.0
   * @param r Robot used to create fixture
   * @param f Frame where label is located in
   * @param name Name of label
   * @param showing Required showing?
   * @return Fixture of label.
   * @since Feb 23, 2016
   */
  private static JLabelFixture getFixture(final Robot r, final Frame f, final String name,
      final boolean showing) {
    log.entry(r, f, name, showing);
    return log.exit(new JLabelFixture(r, r.finder().findByName(f, name, JLabel.class, showing)));
  }

  /**
   * Asserts that label text equals given text
   *
   * @author nschuste
   * @version 1.0.0
   * @param env
   * @param varargs [0] --> name of label [1] --> required text.
   * @return Outcome of check.
   * @since Feb 23, 2016
   */
  @GuiMethod(methodName = "check")
  public ResultWithMessage enter(final Environment env, final String... varargs) {
    log.entry((Object) varargs);
    getFixture(env.getRobot(), env.getFrame(), varargs[0], true).requireText(varargs[1]);
    return log.exit(ResultWithMessage.builder().status(TestCaseStepResultStatus.SUCCESS).build());
  }
}
