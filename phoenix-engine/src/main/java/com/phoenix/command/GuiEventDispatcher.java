/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.command;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import lombok.extern.slf4j.XSlf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.phoenix.to.TestCaseStep;
import com.phoenix.util.MyEventListener;
import com.phoenix.util.Widgettracker;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Jan 21, 2016
 */
@XSlf4j
@Service
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
  @Autowired
  ApplicationContext context;

  public void initialize(final MyEventListener<TestCaseStep> listener) {
    final List<GuiDispatcher> dispatchers = new ArrayList<>();
    final Map<String, Object> beans =
        this.context.getBeansWithAnnotation(com.phoenix.spi.GuiDispatcher.class);
    for (final String s : beans.keySet()) {
      try {
        dispatchers.add((GuiDispatcher) beans.get(s));
      } catch (final Exception e) {
        log.warn("Bean " + s + " should implement " + GuiDispatcher.class.getName()
            + ", but does not.");
        e.printStackTrace();
      }
    }
    this.initialize(listener, dispatchers);
  }

  public void initialize(final MyEventListener<TestCaseStep> listener,
      final List<GuiDispatcher> dispatchers) {
    Toolkit.getDefaultToolkit().addAWTEventListener(event -> {
      Optional<TestCaseStep> step;
      final Optional<Component> c = Widgettracker.getComponent();
      final Iterator<GuiDispatcher> disp = dispatchers.iterator();
      while (disp.hasNext()) {
        step = disp.next().dispatch(event, c);
        if (step.isPresent()) {
          listener.event(step.get());
          break;
        }
      }
    }, AWT_EVENT_MASK);
  }
}
