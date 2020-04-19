package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import controller.ControllerImpl;

public class MenuPanel extends JPanel {
  private JButton solve;
  private JButton reset;
  private JButton help;
  private JButton exit;

  public MenuPanel() {
    setBorder(new EmptyBorder(4, 4, 4, 4));
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.weightx = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;

    solve = new JButton("Solve");
    solve.setActionCommand("Solve Button");
    add(solve, gbc);
    gbc.gridx++;
    reset = new JButton("Reset");
    reset.setActionCommand("Reset Button");
    add(reset, gbc);
    gbc.gridx++;
    help = new JButton("Help");
    help.setActionCommand("Help Button");
    add(help, gbc);
    gbc.gridx++;
    exit = new JButton("Exit");
    exit.setActionCommand("Exit Button");
    add(exit, gbc);

  }

  public void bAddActionListener(ActionListener actionListener){
    solve.addActionListener(actionListener);
    reset.addActionListener(actionListener);
    help.addActionListener(actionListener);
    exit.addActionListener(actionListener);
  }


}
