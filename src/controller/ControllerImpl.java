package controller;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import model.SudokuSolverModel;
import view.SdkButtonListener;
import view.SdkKeyboardListener;
import view.SudokuSolverView;

public class ControllerImpl implements Controller {
  private final SudokuSolverModel model;
  private final SudokuSolverView view;

  public ControllerImpl(SudokuSolverModel m, SudokuSolverView v) {
    this.model = m;
    this.view = v;
    configureSdkKeyboardListener();
    configureSdkButtonListener();
  }

  /**
   * Creates and sets a keyboard listener for the view. In effect it creates snippets of code as
   * Runnable object, one for each time a key is typed, pressed and released, only for those that
   * the program needs.
   *
   * In this example, we need to toggle color when user TYPES 'd', make the message all caps when
   * the user PRESSES 'c' and reverts to the original string when the user RELEASES 'c'. Thus we
   * create three snippets of code (ToggleColor,MakeCaps and MakeOriginalCase) and put them in the
   * appropriate map.
   *
   * Last we create our SdkKeyboardListener object, set all its maps and then give it to the view.
   */
  private void configureSdkKeyboardListener() {
    Map<Character, Runnable> keyTypes = new HashMap<>();
    Map<Integer, Runnable> keyPresses = new HashMap<>();
    Map<Integer, Runnable> keyReleases = new HashMap<>();

    keyPresses.put(KeyEvent.VK_C, new MakeCaps());
    keyReleases.put(KeyEvent.VK_C, new MakeOriginalCase());
    // Another possible syntax: instead of defining a new class, just to make a single instance,
    // you can create an "anonymous class" that implements a particular interface, by writing
    // "new Interfacename() { all the methods you need to implement }"
    // Note that "view" is in scope inside this Runnable!  But, also note that within the Runnable,
    // "this" refers to the Runnable and not to the Controller, so we don't say "this.view".
    keyTypes.put('r', new Runnable() {
      public void run() {
        view.toggleColor();
      }
    });


    SdkKeyboardListener kbd = new SdkKeyboardListener();
    kbd.setKeyTypedMap(keyTypes);
    kbd.setKeyPressedMap(keyPresses);
    kbd.setKeyReleasedMap(keyReleases);

    view.addKeyListener(kbd);

  }

  private void configureSdkButtonListener() {
    Map<String, Runnable> buttonClickedMap = new HashMap<String, Runnable>();
    SdkButtonListener SdkButtonListener = new SdkButtonListener();

    buttonClickedMap.put("Solve Button", new SolveButtonAction());
    buttonClickedMap.put("Help Button", new HelpButtonAction());
    buttonClickedMap.put("Reset Button", new ResetButtonAction());
    buttonClickedMap.put("Exit Button", new ExitButtonAction());

    SdkButtonListener.setButtonClickedActionMap(buttonClickedMap);
    this.view.addActionListener(SdkButtonListener);
  }

  // THESE CLASSES ARE NESTED INSIDE THE CONTROLLER,
  // SO THAT THEY HAVE ACCESS TO THE VIEW
  class MakeCaps implements Runnable {
    public void run() {
      String text = model.getString();
      text = text.toUpperCase();
      view.setSolveOutput(text);
    }
  }

  class MakeOriginalCase implements Runnable {
    public void run() {
      String text = model.getString();
      view.setSolveOutput(text);
    }
  }

  class SolveButtonAction implements Runnable {
    public void run() {
      String text = view.getInputString();
      String boardText = view.getBoardInput();

      //send text to the model
      if (boardText.equals("") && text.equals("")) {
        JOptionPane.showMessageDialog(view.getContainer(),
                "Input can't be empty",
                "Illegal Input Warning",
                JOptionPane.ERROR_MESSAGE);
        throw new IllegalArgumentException("Input can't be empty");
      } else if (boardText == "") {
        model.setString(text);
      } else {
        model.setString(boardText);
      }

      try {
        model.initialization();
      } catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(view.getContainer(),
                e.getMessage(),
                "Illegal Input Warning",
                JOptionPane.ERROR_MESSAGE);
        view.clearBoard();
        throw new IllegalArgumentException(e.getMessage());
      }


      //calculate result
      try {
        model.fillBoard();
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
//        JOptionPane.showMessageDialog(view.getContainer(),
//                e.getMessage(),
//                "Illegal Input Warning",
//                JOptionPane.WARNING_MESSAGE);
      }


      //clear input textfield
      view.clearInputString();
      view.clearBoard();

      //return answer
      text = model.getString();
//      JOptionPane.showMessageDialog(view.getContainer(),
//              text);
      view.setSolveOutput(text);


      //set focus back to main frame so that keyboard events work
      view.resetFocus();

    }
  }

  class ResetButtonAction implements Runnable {
    public void run() {
      view.clearBoard();
    }
  }

  //todo: add more at the end of design
  class HelpButtonAction implements Runnable {
    public void run() {
      JOptionPane.showMessageDialog(view.getContainer(),
              "Support functions: \n - Enter sudoku in board.\n - Enter sudoku by string.\n "
                      + "- Reset board.");

    }
  }

  class ExitButtonAction implements Runnable {
    public void run() {
      System.exit(0);
    }
  }

}
