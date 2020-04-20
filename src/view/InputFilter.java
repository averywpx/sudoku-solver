package view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;


class InputFilter extends DocumentFilter {

  private int maxCharacters;

  public InputFilter(int maxChars) {
    maxCharacters = maxChars;
  }

  @Override
  public void insertString(FilterBypass fb, int offs, String str, AttributeSet a)
          throws BadLocationException {

    Document doc = fb.getDocument();
    StringBuilder sb = new StringBuilder();
    sb.append(doc.getText(0, doc.getLength()));
    sb.insert(offs, str);

    boolean size = (fb.getDocument().getLength() + str.length()) <= maxCharacters;
    boolean intLimit = test(sb.toString());
    if (size && intLimit) {
      super.insertString(fb, offs, str, a);
    } else {
      Toolkit.getDefaultToolkit().beep();
    }
  }

  @Override
  public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet a)
          throws BadLocationException {

    Document doc = fb.getDocument();
    StringBuilder sb = new StringBuilder();
    sb.append(doc.getText(0, doc.getLength()));
    sb.replace(offs, offs + length, str);

    boolean size = (fb.getDocument().getLength() + str.length()
            - length) <= maxCharacters;
    boolean intLimit = test(sb.toString());

    if (size && intLimit) {
      super.replace(fb, offs, length, str, a);
    } else {
      Toolkit.getDefaultToolkit().beep();
    }
  }

  @Override
  public void remove(FilterBypass fb, int offset, int length)
          throws BadLocationException {
    Document doc = fb.getDocument();
    StringBuilder sb = new StringBuilder();
    sb.append(doc.getText(0, doc.getLength()));
    sb.delete(offset, offset + length);

    if (sb.toString().length() == 0) {
      super.replace(fb, offset, length, "", null);
    } else {
      if (test(sb.toString())) {
        super.remove(fb, offset, length);
      } else { // warn the user and don't allow the insert }
      }

    }

  }

  private boolean test(String text) {
    try {
      Integer.parseInt(text);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}


