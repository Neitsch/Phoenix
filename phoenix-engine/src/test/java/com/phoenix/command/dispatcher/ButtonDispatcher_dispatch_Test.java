/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.command.dispatcher;


import javax.swing.JButton;
import javax.swing.JFrame;

import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.JButtonFixture;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.phoenix.command.GuiEventDispatcher;
import com.phoenix.to.TestCaseStep;
import com.phoenix.util.MyEventListener;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Jan 21, 2016
 */
@RunWith(MockitoJUnitRunner.class)
public class ButtonDispatcher_dispatch_Test {
  @Mock
  private MyEventListener<TestCaseStep> lstr;

  @Test
  public void test() throws InterruptedException {
    final Robot r = BasicRobot.robotWithNewAwtHierarchy();
    final JFrame frame = new JFrame();
    final JButton b = new JButton();
    b.setText("abc");
    b.setName("def");
    frame.add(b);
    frame.pack();
    frame.setVisible(true);
    final JButtonFixture fix = new JButtonFixture(r, "def");
    GuiEventDispatcher.initialize(this.lstr);
    final ArgumentCaptor<TestCaseStep> captor = ArgumentCaptor.forClass(TestCaseStep.class);
    fix.click();
    Mockito.verify(this.lstr, Mockito.times(1)).event(captor.capture());
    final TestCaseStep capt = captor.getValue();
    Assert.assertEquals(capt.getMethodName(), "click");
    Assert.assertEquals(capt.getArgs().length, 1);
    Assert.assertEquals(capt.getArgs()[0], "def");
  }
}
