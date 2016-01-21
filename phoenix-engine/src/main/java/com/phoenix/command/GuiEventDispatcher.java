/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.command;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Toolkit;
import java.util.Optional;

import com.phoenix.command.dispatcher.ButtonDispatcher;
import com.phoenix.command.dispatcher.TextDispatcher;
import com.phoenix.to.TestCaseStep;
import com.phoenix.util.MyEventListener;
import com.phoenix.util.Widgettracker;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Jan 21, 2016
 */
public class GuiEventDispatcher {
  public static final long AWT_EVENT_MASK = AWTEvent.ACTION_EVENT_MASK
      + AWTEvent.ADJUSTMENT_EVENT_MASK + AWTEvent.COMPONENT_EVENT_MASK
      + AWTEvent.CONTAINER_EVENT_MASK + AWTEvent.FOCUS_EVENT_MASK
      + AWTEvent.HIERARCHY_BOUNDS_EVENT_MASK + AWTEvent.HIERARCHY_EVENT_MASK
      + AWTEvent.INPUT_METHOD_EVENT_MASK + AWTEvent.INVOCATION_EVENT_MASK
      + AWTEvent.ITEM_EVENT_MASK + AWTEvent.KEY_EVENT_MASK + AWTEvent.MOUSE_EVENT_MASK
      + AWTEvent.MOUSE_MOTION_EVENT_MASK + AWTEvent.MOUSE_WHEEL_EVENT_MASK
      + AWTEvent.PAINT_EVENT_MASK + AWTEvent.TEXT_EVENT_MASK + AWTEvent.WINDOW_EVENT_MASK
      + AWTEvent.WINDOW_FOCUS_EVENT_MASK + AWTEvent.WINDOW_STATE_EVENT_MASK;
  private static Thread t;

  public static void initialize(final MyEventListener<TestCaseStep> listener) {
    final GuiDispatcher patch1 = new ButtonDispatcher();
    final GuiDispatcher patch2 = new TextDispatcher();
    t = new Thread(() -> {
      while (true) {
        try {
          Thread.sleep(100);
          final Optional<Component> c = Widgettracker.getComponent();
          if (c.isPresent()) {
            System.out.println(c.get());
          }
        } catch (final Exception e) {
          e.printStackTrace();
        }
      }
    });
    t.start();
    Toolkit.getDefaultToolkit().addAWTEventListener(event -> {
      Optional<TestCaseStep> step = patch1.dispatch(event);
      if (step.isPresent()) {
        listener.event(step.get());
      } else {
        step = patch2.dispatch(event);
        if (step.isPresent()) {
          listener.event(step.get());
        }
      }
    }, AWT_EVENT_MASK);
  }
}
