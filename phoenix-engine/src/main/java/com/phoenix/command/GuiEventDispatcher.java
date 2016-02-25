/**
 * Copyright 2016 Nigel Schuster. Maintains all dispatchers and runs each AWTEvent through each
 * registered dispatcher.
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
 * GuiEventDispatcher forwards all events to registered dispatchers and brodcasts any results of
 * dispatching
 *
 * @author nschuste
 * @version 1.0.0
 * @since Jan 21, 2016
 */
@XSlf4j
@Service
public class GuiEventDispatcher {
  /**
   * Flag for any AWT Event
   *
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  public static final long AWT_EVENT_MASK = AWTEvent.ACTION_EVENT_MASK
      + AWTEvent.ADJUSTMENT_EVENT_MASK + AWTEvent.COMPONENT_EVENT_MASK
      + AWTEvent.CONTAINER_EVENT_MASK + AWTEvent.FOCUS_EVENT_MASK
      + AWTEvent.HIERARCHY_BOUNDS_EVENT_MASK + AWTEvent.HIERARCHY_EVENT_MASK
      + AWTEvent.INPUT_METHOD_EVENT_MASK + AWTEvent.INVOCATION_EVENT_MASK
      + AWTEvent.ITEM_EVENT_MASK + AWTEvent.KEY_EVENT_MASK + AWTEvent.MOUSE_EVENT_MASK
      + AWTEvent.MOUSE_MOTION_EVENT_MASK + AWTEvent.MOUSE_WHEEL_EVENT_MASK
      + AWTEvent.PAINT_EVENT_MASK + AWTEvent.TEXT_EVENT_MASK + AWTEvent.WINDOW_EVENT_MASK
      + AWTEvent.WINDOW_FOCUS_EVENT_MASK + AWTEvent.WINDOW_STATE_EVENT_MASK;
  @Autowired
  ApplicationContext context;

  /**
   * Hooks into AWTEventQueue and discovers dispatchers.
   *
   * @author nschuste
   * @version 1.0.0
   * @param listener
   * @since Feb 23, 2016
   */
  public void initialize(final MyEventListener<TestCaseStep> listener) {
    log.entry(listener);
    final List<GuiDispatcher> dispatchers = new ArrayList<>();
    final Map<String, Object> beans =
        this.context.getBeansWithAnnotation(com.phoenix.spi.GuiDispatcher.class);
    for (final String s : beans.keySet()) {
      log.info("Found dispatcher: " + s);
      try {
        dispatchers.add((GuiDispatcher) beans.get(s));
        log.debug("Added dispatcher " + dispatchers.get(dispatchers.size() - 1));
      } catch (final Exception e) {
        log.warn("Bean " + s + " should implement " + GuiDispatcher.class.getName()
            + ", but does not. Continuing...");
        e.printStackTrace();
      }
    }
    this.initialize(listener, dispatchers);
    log.exit();
  }

  /**
   * Hooks into AWTEventQueue and dispatches Events with given lists.
   *
   * @author nschuste
   * @version 1.0.0
   * @param listener
   * @param dispatchers
   * @since Feb 23, 2016
   */
  public void initialize(final MyEventListener<TestCaseStep> listener,
      final List<GuiDispatcher> dispatchers) {
    log.entry(listener, dispatchers);
    Toolkit.getDefaultToolkit().addAWTEventListener(event -> {
      Optional<TestCaseStep> step;
      final Optional<Component> c = Widgettracker.getComponent();
      final Iterator<GuiDispatcher> disp = dispatchers.iterator();
      log.trace("Event " + event + ", component: " + c);
      while (disp.hasNext()) {
        GuiDispatcher dispatcher = disp.next();
        step = dispatcher.dispatch(event, c);
        if (step.isPresent()) {
          log.info("Got step: " + step + " from dispatcher : " + dispatcher.getClass());
          listener.event(step.get());
          log.debug("Broadcasted event");
          break;
        }
      }
    }, AWT_EVENT_MASK);
    log.exit();
  }
}
