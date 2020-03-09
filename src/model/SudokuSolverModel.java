package model;

public interface SudokuSolverModel {
  /**
   * print the current board
   * @return
   */
  public String printBoard();


  /**
   * is the board valid
   */
  public boolean isValid();

  /**
   *fill the board
   */
  void fillBoard();

}
