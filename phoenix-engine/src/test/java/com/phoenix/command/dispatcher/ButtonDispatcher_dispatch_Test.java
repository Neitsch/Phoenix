/**
 * Copyright 2016 Nigel Schuster. Checks whether the the ButtonDispatcher records click correctly.
 */


package com.phoenix.command.dispatcher;


import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.JButtonFixture;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;

import com.phoenix.command.GuiEventDispatcher;
import com.phoenix.to.TestCaseStep;
import com.phoenix.util.MyEventListener;

/**
 * Verifies ButtonDispatcher functionality.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Jan 21, 2016
 */
@RunWith(MockitoJUnitRunner.class)
public class ButtonDispatcher_dispatch_Test {
  @Mock
  private ApplicationContext context;
  @InjectMocks
  private GuiEventDispatcher dispatcher;
  @Mock
  private MyEventListener<TestCaseStep> lstr;
  Robot r;

  @Before
  public void setup() {
    this.r = BasicRobot.robotWithNewAwtHierarchy();
  }

  @After
  public void tearDown() {
    this.r.cleanUp();
  }

  /**
   * Simple test.
   *
   * @author nschuste
   * @version 1.0.0
   * @throws InterruptedException
   * @since Feb 23, 2016
   */
  @Test(timeout = 10000)
  public void test() throws InterruptedException {
    final Map<String, Object> mp = new HashMap<>();
    mp.put("abc", new ButtonDispatcher());
    Mockito.when(this.context.getBeansWithAnnotation(Matchers.any())).thenReturn(mp);
    final JFrame frame = new JFrame();
    final JButton b = new JButton();
    b.setText("abc");
    b.setName("def");
    frame.add(b);
    frame.pack();
    frame.setVisible(true);
    final JButtonFixture fix = new JButtonFixture(this.r, "def");
    this.dispatcher.initialize(this.lstr);
    final ArgumentCaptor<TestCaseStep> captor = ArgumentCaptor.forClass(TestCaseStep.class);
    fix.click();
    try {
      // Ensure that AWT EventQueue is cleared.
      SwingUtilities.invokeAndWait(() -> {

      });
    } catch (final InvocationTargetException e) {
      e.printStackTrace();
    }
    Mockito.verify(this.lstr, Mockito.times(1)).event(captor.capture());
    final TestCaseStep capt = captor.getValue();
    Assert.assertEquals(capt.getMethodName(), "button.click");
    Assert.assertEquals(capt.getArgs().length, 1);
    Assert.assertEquals(capt.getArgs()[0], "def");
  }
}
