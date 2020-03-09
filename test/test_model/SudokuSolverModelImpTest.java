package test_model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import model.Grid;
import model.SudokuSolverModelImp;

import static org.junit.Assert.*;

public class SudokuSolverModelImpTest {

  @Test
  public void printBoard() {

  }

  @Test
  public void isValid() {
    Grid g = new Grid();
    g.replaceNumber(2,3,4);
    g.replaceNumber(5,6,7);
    SudokuSolverModelImp sudoku = new SudokuSolverModelImp(g);
    assertEquals(true, sudoku.isValid());

  }

  @Test
  public void fillBoard() {
  }

  @Test
  public void collectColumn() {
  }

  @Test
  public void collectSubsection() {
    Grid g = new Grid();
    g.replaceNumber(2,3,4);
    g.replaceNumber(5,6,7);
    SudokuSolverModelImp sudoku = new SudokuSolverModelImp(g);
    ArrayList<Integer> nl = new ArrayList<>(Arrays.asList(0,0,0,0,0,0,4,0,0));
    assertEquals(nl, sudoku.collectSubsection(2,3));
  }

  @Test
  public void validSet() {
  }
}