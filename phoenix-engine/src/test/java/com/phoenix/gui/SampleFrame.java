/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix.gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import lombok.extern.slf4j.XSlf4j;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Dec 7, 2015
 */
@XSlf4j
public class SampleFrame implements Runnable {
  static Object not;
  public JTextComponent textComponent;
  private JFrame frame;
  @Autowired
  private ClickTracker tracker;

  /**
   * @author nschuste
   * @version 1.0.0
   * @since Dec 7, 2015
   */
  public SampleFrame() {
    SampleFrame.not = new Object();
  }

  public static void main(final String[] args) {
    javax.swing.SwingUtilities.invokeLater(() -> new SampleFrame().createAndShowGUI());
  }

  public JFrame getFrame() {
    return this.frame;
  }

  @Override
  public void run() {
    // Schedule a job for the event-dispatching thread:
    // creating and showing this application's GUI.
    synchronized (SampleFrame.not) {
      javax.swing.SwingUtilities.invokeLater(() -> this.createAndShowGUI());
      try {
        SampleFrame.not.wait();
      } catch (final InterruptedException e) {
        log.catching(e);
      }
    }
  }

  private void createAndShowGUI() {
    // Create and set up the window.
    this.frame = new JFrame("HelloWorldSwing");
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // Add the ubiquitous "Hello World" label.
    final JButton button = new JButton("Hello World");
    button.setName("button1");
    button.addActionListener(e -> SampleFrame.this.tracker.click());
    this.frame.getContentPane().add(button);
    this.textComponent = new JTextField("Hi World");
    this.textComponent.setName("textField1");
    this.frame.getContentPane().add(this.textComponent);
    this.frame.getContentPane().setLayout(new GridLayout());
    // Display the window.
    this.frame.pack();
    this.frame.setBounds(0, 0, 500, 100);
    this.frame.setVisible(true);
    synchronized (not) {
      not.notify();
    }
  }
}
