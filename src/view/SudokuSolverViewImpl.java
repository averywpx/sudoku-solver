package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.*;

public class SudokuSolverViewImpl implements SudokuSolverView {
  private static final int WINDOW_WIDTH = 400;
  private static final int WINDOW_HEIGHT = 300;

  private JFrame container;
  private JLabel display;
  private JButton echoButton, exitButton;
  private MenuPanel buttons;
  private JTextField input;
  private SudokuBoard board;

  public SudokuSolverViewImpl(String caption) {

    container = new JFrame();
    container.setTitle("Sudoku Solver");
    container.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
    container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    container.setLayout(new BorderLayout());

    //this.setResizable(false);
//		this.setMinimumSize(new Dimension(300,300));


    //string panel
    StringPanel sp = new StringPanel();
    container.add(sp, BorderLayout.NORTH);
    display = sp.getDisplay();
    input = sp.getInput();

    //add board
    //todo: adjust size to square
    board = new SudokuBoard();
    container.add(board, BorderLayout.CENTER);

    //add buttons
    buttons = new MenuPanel();
    container.add(buttons, BorderLayout.SOUTH);

    container.pack();
    container.setVisible(true);

  }

  @Override
  public void addActionListener(ActionListener actionListener) {
    buttons.bAddActionListener(actionListener);


  }


  @Override
  public void solve() {

  }

  @Override
  public JFrame getContainer() {
    return container;
  }

  @Override
  public String getBoardInput() {
    return board.getBoardString();
  }

  @Override
  public void clearBoard() {
    board.clearBoard();
  }

  /*
      In order to make this frame respond to keyboard events, it must be within strong focus.
      Since there could be multiple components on the screen that listen to keyboard events,
      we must set one as the "currently focussed" one so that all keyboard events are
      passed to that component. This component is said to have "strong focus".

      We do this by first making the component focusable and then requesting focus to it.
      Requesting focus makes the component have focus AND removes focus from whoever had it
      before.
       */
  @Override
  public void resetFocus() {
    container.setFocusable(true);
    container.requestFocus();
  }

  @Override
  public void toggleColor() {
    if (this.display.getForeground().equals(Color.RED))
      this.display.setForeground(Color.BLACK);
    else
      this.display.setForeground(Color.RED);
  }

  @Override
  public void addKeyListener(KeyListener listener) {

  }


  @Override
  public void setSolveOutput(String s) {
    board.setBoardText(s);
  }

  @Override
  public String getInputString() {
    return input.getText();
  }

  @Override
  public void clearInputString() {
    input.setText("");
  }

}
