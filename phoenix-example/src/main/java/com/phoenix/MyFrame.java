/**
 * Copyright 2015 Nigel Schuster.
 */


package com.phoenix;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Nov 22, 2015
 */
public class MyFrame extends JFrame {

  private static Object notify;
  private final JPanel contentPane;
  private final JTextField textField;

  /**
   * Create the frame.
   */
  public MyFrame() {
    this.setName("Frame1");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setBounds(100, 100, 450, 300);
    final JButton button = new JButton("Hii!!");
    button.setBounds(131, 32, 263, 150);
    button.setName("button");
    this.contentPane = new JPanel();
    this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    this.contentPane.setLayout(null);
    this.contentPane.add(button);
    this.setContentPane(this.contentPane);

    this.textField = new JTextField();
    this.textField.setBounds(6, 72, 134, 28);
    this.textField.setName("text");
    this.contentPane.add(this.textField);
    this.textField.setColumns(10);
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
