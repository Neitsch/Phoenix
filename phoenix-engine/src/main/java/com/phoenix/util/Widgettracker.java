/**
 * Copyright 2016 Nigel Schuster. Important class to get component under mouse.
 */


package com.phoenix.util;

import java.awt.Component;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Window;
import java.util.Optional;

import javax.swing.SwingUtilities;

import lombok.extern.slf4j.XSlf4j;

/**
 * Tracks component under the cursor.
 *
 * @author nschuste
 * @version 1.0.0
 * @since Jan 21, 2016
 */
@XSlf4j
public class Widgettracker {
  /**
   * Returns optional component under mouse.
   *
   * @author nschuste
   * @version 1.0.0
   * @return
   * @since Feb 23, 2016
   */
  public static Optional<Component> getComponent() {
    return Optional.ofNullable(findComponentUnderMouse());
  }

  /**
   * finds the actual component under the mouse by converting the cursors XY position to components
   * in our AWT hierarchy
   *
   * @author nschuste
   * @version 1.0.0
   * @return
   * @since Feb 23, 2016
   */
  private static Component findComponentUnderMouse() {
    Component com = null;
    try {
      final Window window = findWindow();
      final Point location = MouseInfo.getPointerInfo().getLocation();
      SwingUtilities.convertPointFromScreen(location, window);
      com = SwingUtilities.getDeepestComponentAt(window, location.x, location.y);
    } catch (final Exception e) {
    }
    return com;
  }

  /**
   * Finds the window the mouse is hovering over.
   *
   * @author nschuste
   * @version 1.0.0
   * @return
   * @since Feb 23, 2016
   */
  private static Window findWindow() {
    Window w = null;
    for (final Window window : Window.getWindows()) {
      if (window.getMousePosition(true) != null) {
        w = window;
        break;
      }
    }
    return w;
  }
}
