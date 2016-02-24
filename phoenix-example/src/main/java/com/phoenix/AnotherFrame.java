/**
 * Copyright 2016 Nigel Schuster.
 */


package com.phoenix;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @author nschuste
 * @version 1.0.0
 * @since Feb 23, 2016
 */
public class AnotherFrame extends JFrame {
  private JTextField textField;
  private JTextField textField_1;
  private JTextField textField_2;
  private JTextField textField_3;

  /**
   * @author nschuste
   * @version 1.0.0
   * @since Feb 23, 2016
   */
  public AnotherFrame() {
    this.getContentPane().setLayout(null);
    this.setName("Frame2");
    this.textField = new JTextField();
    this.textField.setBounds(233, 21, 134, 28);
    this.getContentPane().add(this.textField);
    this.textField.setColumns(10);
    this.textField.setName("txtName");

    this.textField_1 = new JTextField();
    this.textField_1.setBounds(233, 61, 134, 28);
    this.getContentPane().add(this.textField_1);
    this.textField_1.setColumns(10);
    this.textField_1.setName("txtAge");

    this.textField_2 = new JTextField();
    this.textField_2.setBounds(233, 101, 134, 28);
    this.getContentPane().add(this.textField_2);
    this.textField_2.setColumns(10);
    this.textField_2.setName("txtGender");

    this.textField_3 = new JTextField();
    this.textField_3.setBounds(233, 141, 134, 28);
    this.getContentPane().add(this.textField_3);
    this.textField_3.setColumns(10);
    this.textField_3.setName("txtUniversity");

    JLabel lblName = new JLabel("Name");
    lblName.setBounds(50, 27, 61, 16);
    this.getContentPane().add(lblName);
    lblName.setName("lblName");

    JLabel lblAge = new JLabel("Age");
    lblAge.setBounds(50, 67, 61, 16);
    this.getContentPane().add(lblAge);
    lblAge.setName("lblAge");

    JLabel lblGendermf = new JLabel("Gender (M/F)");
    lblGendermf.setBounds(50, 107, 98, 16);
    this.getContentPane().add(lblGendermf);
    lblGendermf.setName("lblGender");

    JLabel lblUniversity = new JLabel("University");
    lblUniversity.setBounds(50, 147, 72, 16);
    this.getContentPane().add(lblUniversity);
    lblUniversity.setName("lblUni");

    final JLabel label = new JLabel("");
    label.setBounds(133, 203, 311, 16);
    this.getContentPane().add(label);
    label.setName("result");

    final JButton btnDone = new JButton("Done");
    btnDone.addActionListener(new ActionListener() {
      public void actionPerformed(final ActionEvent e) {
        btnDone.setEnabled(false);
        btnDone.setText("Thanks!");
        String address = "";
        if (AnotherFrame.this.textField_2.getText().equals("M")) {
          address = "Mr.";
        } else if (AnotherFrame.this.textField_2.getText().equals("F")) {
          address = "Mrs.";
        }
        label.setText(address + " " + AnotherFrame.this.textField.getText() + " is older than "
            + (Integer.parseInt(AnotherFrame.this.textField_1.getText()) - 1) + " years.");
      }
    });
    btnDone.setBounds(50, 203, 84, 29);
    btnDone.setName("done");
    this.getContentPane().add(btnDone);
    this.setBounds(0, 0, 400, 300);
  }

  public static void main(final String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          final AnotherFrame frame = new AnotherFrame();
          frame.setVisible(true);
        } catch (final Exception e) {
          e.printStackTrace();
        }
      }
    });
  }
}
