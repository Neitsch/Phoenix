/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.command.gui;

import java.awt.Frame;

import javax.swing.JLabel;

import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.JLabelFixture;

import com.phoenix.command.Environment;
import com.phoenix.spi.GuiMethod;
import com.phoenix.spi.GuiPackage;
import com.phoenix.to.ResultWithMessage;
import com.phoenix.to.TestCaseStepResultStatus;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Feb 23, 2016
 */
@GuiPackage(packageName = "label")
public class LabelCommand {
  private static JLabelFixture getFixture(final Robot r, final Frame f, final String name,
      final boolean showing) {
    return new JLabelFixture(r, r.finder().findByName(f, name, JLabel.class, showing));
  }

  @GuiMethod(methodName = "check")
  public ResultWithMessage enter(final Environment env, final String... varargs) {
    getFixture(env.getRobot(), env.getFrame(), varargs[0], true).requireText(varargs[1]);
    return ResultWithMessage.builder().status(TestCaseStepResultStatus.SUCCESS).build();
  }
}
