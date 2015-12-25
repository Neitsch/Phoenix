/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Nov 22, 2015
 */
public class MyFrame extends JFrame {

  private static Object notify;
  private final JPanel contentPane;

  /**
   * Create the frame.
   */
  public MyFrame() {
    this.setName("Frame1");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setBounds(100, 100, 450, 300);
    this.contentPane = new JPanel();
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    this.contentPane.setLayout(new BorderLayout(0, 0));
    this.contentPane.add(new Button("Hi!!"));
    this.setContentPane(this.contentPane);
    this.pack();
  }

  /**
   * Launch the application.
   */
  public static void main(final String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          final MyFrame frame = new MyFrame();
          frame.setVisible(true);
          if (MyFrame.notify != null) {
            synchronized (MyFrame.notify) {
              MyFrame.notify.notifyAll();
            }
          }
        } catch (final Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Launch the application.
   */
  public static void main(final String[] args, final Object notify) {
    MyFrame.notify = notify;
    main(args);
  }

}
