package view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.text.PlainDocument;

public class SudokuBoard extends JPanel {
  //todo: limit input to 1-9
  public static final int ROWS = 9;
  public static final int COLUMNS = 9;

  private JTextField[] fields;

  public SudokuBoard() {
    setBorder(new EmptyBorder(5, 5, 5, 5));
    setLayout(new GridLayout(ROWS, COLUMNS, 2, 2));
    fields = new JTextField[ROWS * COLUMNS];
    for (int row = 0; row < ROWS; row++) {
      for (int col = 0; col < COLUMNS; col++) {
        int index = (row * COLUMNS) + col;
        JTextField field = new JTextField(4);
        field.setBorder(BorderFactory.createEmptyBorder());

        //limit input to a integer
        PlainDocument doc = (PlainDocument) field.getDocument();
        doc.setDocumentFilter(new InputFilter(1));

        //set font and position
        field.setHorizontalAlignment(JTextField.CENTER);
        field.setFont(field.getFont().deriveFont(Font.BOLD));

        if (isGrey(row, col)) {
          field.setBackground(new Color(240, 248, 255));
        }
        fields[index] = field;
        add(field);
      }
    }
  }

  //change the background to make it similar to cross shape
  private boolean isGrey(int row, int col) {
    Boolean rowConstrain1 = row >= 0 && row < 3;
    Boolean columnConstrain1 = col > 2 && col < 6;
    Boolean rowConstrain2 = row > 2 && row < 6;
    Boolean columnConstrain21 = col >= 0 && col < 3;
    Boolean columnConstrain22 = col > 5 && col < 9;
    Boolean rowConstrain3 = row > 5 && row < 9;
    return (rowConstrain1 && columnConstrain1) || (rowConstrain2 && columnConstrain21) || (
            rowConstrain2 && columnConstrain22) || (rowConstrain3 && columnConstrain1);

  }

  public String getBoardString() {
    String result = "";
    String empty = "";
    for (int i = 0; i < 81; i++) {
      String current = fields[i].getText();
      if (current.equals("")) {
        result = result + "0";
      } else {
        result = result + current;
      }

      empty = empty + "0";
    }


    if (result.equals(empty)) {
      result = "";
    }

    System.out.print(result + "\n");
    return result;
  }

  public void setBoardText(String s){
    if(s.length() != 81){
      throw new IllegalArgumentException("Model return illegal length of output: " + s.length() + "\n");
    }
    for (int i = 0; i < 81; i++) {
      String current = Character.toString(s.charAt(i));
      fields[i].setText(current);
    }
  }

  public void clearBoard() {
    for (int i = 0; i < 81; i++) {
      fields[i].setText("");
    }
  }
}
