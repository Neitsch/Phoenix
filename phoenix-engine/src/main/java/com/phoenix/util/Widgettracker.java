/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix.util;

import java.awt.Component;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Window;
import java.util.Optional;

import javax.swing.SwingUtilities;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Jan 21, 2016
 */
public class Widgettracker {
  public static Optional<Component> getComponent() {
    return Optional.ofNullable(findComponentUnderMouse());
  }

  private static Component findComponentUnderMouse() {
    try {
      final Window window = findWindow();
      final Point location = MouseInfo.getPointerInfo().getLocation();
      SwingUtilities.convertPointFromScreen(location, window);
      return SwingUtilities.getDeepestComponentAt(window, location.x, location.y);
    } catch (final Exception e) {
      return null;
    }
  }

  private static Window findWindow() {
    for (final Window window : Window.getWindows()) {
      if (window.getMousePosition(true) != null) {
        return window;
      }
    }
    return null;
  }
}
