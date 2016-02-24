/**
 * Copyright 2016 Nigel Schuster. Enhancement of Swing EventListener
 */


package com.phoenix.util;

/**
 * Handles a given event
 * 
 * @author nschuste
 * @version 1.0.0
 * @since Jan 21, 2016
 */
public interface MyEventListener<E> {
  /**
   * Called for an event.
   * 
   * @author nschuste
   * @version 1.0.0
   * @param e
   * @since Feb 23, 2016
   */
  public void event(E e);
}
