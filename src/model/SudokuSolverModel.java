package model;

public interface SudokuSolverModel {
  /**
   * print the current board
   */
  public String printBoard();


  /**
   * is the board valid
   */
  public boolean isValid();

  /**
   * fill the board
   */
  void fillBoard();


  void initialization();

  //-----------------------------------VIEW INTERFACE TEST----------------------------
  void setString(String i);

  String getString();

}
