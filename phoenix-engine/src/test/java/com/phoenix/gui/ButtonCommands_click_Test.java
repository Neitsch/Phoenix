/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.gui;

import org.assertj.swing.core.BasicRobot;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.phoenix.command.Environment;
import com.phoenix.command.gui.ButtonCommands;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 7, 2015
 */
@RunWith(MockitoJUnitRunner.class)
public class ButtonCommands_click_Test {
  @InjectMocks
  SampleFrame frame;
  @Mock
  ClickTracker tracker;

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Dec 7, 2015
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {}

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Dec 7, 2015
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception {}

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Dec 7, 2015
   */
  @Before
  public void setUp() throws Exception {}

  /**
   * @author nschuste
   * @version 1.0.0
   * @throws java.lang.Exception
   * @since Dec 7, 2015
   */
  @After
  public void tearDown() throws Exception {}

  @Test
  public final void test() throws InterruptedException {
    final Environment env = new Environment();
    env.setRobot(BasicRobot.robotWithNewAwtHierarchy());
    this.frame.run();
    env.setFrame(this.frame.getFrame());
    final ButtonCommands com = new ButtonCommands();
    com.click(env, "button1");
    Mockito.verify(this.tracker, Mockito.times(1)).click();
    Mockito.verifyNoMoreInteractions(this.tracker);
  }

}
