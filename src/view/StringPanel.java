package view;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.PlainDocument;

public class StringPanel extends JPanel {
  private JLabel display;
  private JTextField input;
  
  public StringPanel() {
    SpringLayout stringLayout = new SpringLayout();
    setLayout(stringLayout);
    display = new JLabel("Enter 81 characters (Blank is replaced by 0):");
    add(display);

    //input must be 81 integers
    input = new JTextField(10);
    PlainDocument doc = (PlainDocument) input.getDocument();
    //doc.setDocumentFilter(new InputFilter(5));
    //doc.setDocumentFilter(new SizeFilter(5));
    doc.setDocumentFilter(new InputFilter(81));
    add(input);

    stringLayout.putConstraint(SpringLayout.WEST, display,
            5,
            SpringLayout.WEST, this);
    stringLayout.putConstraint(SpringLayout.NORTH, display,
            5,
            SpringLayout.NORTH, this);

    stringLayout.putConstraint(SpringLayout.WEST, input,
            5,
            SpringLayout.EAST, display);
    stringLayout.putConstraint(SpringLayout.NORTH, input,
            5,
            SpringLayout.NORTH, this);

    stringLayout.putConstraint(SpringLayout.EAST, this,
            5,
            SpringLayout.EAST, input);
    stringLayout.putConstraint(SpringLayout.SOUTH, this,
            5,
            SpringLayout.SOUTH, input);
  }

  public JLabel getDisplay(){
    return display;
  }

  public JTextField getInput(){
    return input;
  }
}
