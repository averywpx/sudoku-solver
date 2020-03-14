package test_model;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import model.Grid;
import model.SudokuSolverModelImp;

import static org.junit.Assert.*;

public class SudokuSolverModelImpTest {

  @Test
  public void printBoard() {
    String s = "007500816"
            + "002160490"
            + "196804000"
            + "573601000"
            + "260040105"
            + "000025638"
            + "349008060"
            + "000473509"
            + "720010084";
    SudokuSolverModelImp sudoku = new SudokuSolverModelImp(s);
    //assertEquals("", sudoku.printBoard());

  }

  @Test
  public void isValid() {
    Grid g = new Grid();
    g.replaceNumber(2,3,4);
    g.replaceNumber(5,6,7);
    SudokuSolverModelImp sudoku = new SudokuSolverModelImp(g);
    assertEquals(true, sudoku.isValid());
    g.replaceNumber(2,2,4);
    assertEquals(false, sudoku.isValid());


  }

  //check hashmap
  @Test
  public void isValid2() {
    Grid g = new Grid();
    g.replaceNumber(2, 3, 4);
    g.replaceNumber(5, 6, 7);
    SudokuSolverModelImp sudoku = new SudokuSolverModelImp(g);
    ArrayList<Integer> sub = new ArrayList<Integer>(Arrays.asList(0,3));
    sudoku.isValid();
    assertEquals(0, sudoku.rows.get(2));
    assertEquals(0, sudoku.columns.get(3));
    assertEquals(0, sudoku.subsection.get(sub));
  }

  //test for sudoku difficulty level 1
  @Test
  public void fillBoard() {
    String s = "007500816"
            + "002160490"
            + "196804000"
            + "573601000"
            + "260040105"
            + "000025638"
            + "349008060"
            + "000473509"
            + "720010084";
    SudokuSolverModelImp sudoku = new SudokuSolverModelImp(s);
//    ArrayList<Integer> coord = new ArrayList<>(Arrays.asList(0,0));
//    assertEquals(0, sudoku.rows.get(0));
//    assertEquals(0, sudoku.columns.get(0));
//    assertEquals(0, sudoku.subsection.get(coord));
    sudoku.fillBoard();
    assertEquals("437592816\n"
            + "852167493\n"
            + "196834257\n"
            + "573681942\n"
            + "268349175\n"
            + "914725638\n"
            + "349258761\n"
            + "681473529\n"
            + "725916384\n", sudoku.printBoard());

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

  @Test
  public void intersection(){
    ArrayList<Integer> a = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
    ArrayList<Integer> b = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
    ArrayList<Integer> c = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
    ArrayList<Integer> d = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
    Grid g = new Grid();
    SudokuSolverModelImp sudoku = new SudokuSolverModelImp(g);
    assertEquals(d, sudoku.intersection(a,b,c));
  }
}