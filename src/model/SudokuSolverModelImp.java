package model;

import java.lang.reflect.Array;
import java.util.*;

public class SudokuSolverModelImp implements SudokuSolverModel {
  public Grid board;

  /**
   * standard constructor
   * @param board
   */
  public SudokuSolverModelImp(Grid board){
    this.board = board;

  }
  @Override
  public String printBoard() {
    if(!this.board.validSize()){
      throw new IllegalArgumentException("Sudoku have invalid size");
    }
    String board = "";
    for (int i = 0; i < 9; i++){
      board = board + this.board.printRow(i);
    }

    return board;
  }

  @Override
  public boolean isValid() {
    if(!this.board.validSize()){
      throw new IllegalArgumentException("invalid board size");
    }    boolean column = true;
    boolean row = true;
    boolean subsection = true;
    //check each row
    for(int i = 0; i < 9; i++){
      row = row && validSet(this.board.getRow(i));
    }
    for(int j = 0; j< 9; j++){
      column = column && validSet(this.collectColumn(j));
    }
    for(int i = 0; i < 9; i += 3){
      for(int j = 0; j < 9; j += 3){
        subsection = subsection && validSet(this.collectSubsection(i,j));
      }
    }
    return row && column && subsection;
  }

  @Override
  public void fillBoard() {

  }


  /**
   * collect column by given number
   * @param j index of column
   * @return desired column
   */
  public ArrayList<Integer> collectColumn(int j){
    ArrayList<Integer> column = new ArrayList<>();
    for (int i = 0; i < 9; i++){
      int current = this.board.getNumber(i,j);
      column.add(current);
    }
    if(column.size() == 9){
      return column;
    }else{
      return new ArrayList<>();
    }
  }

  /**
   * collect all the numbers in given section index
   * @param i which row it is
   * @param j which column it is
   * @return correct square of given index
   */
  public ArrayList<Integer> collectSubsection(int i, int j){
    int columnStart = i - i % 3;
    int rowStart = j - j % 3;
    ArrayList<Integer> subsection = new ArrayList<>();
    for(int a = columnStart; a < columnStart + 3; a++ ){
      for(int b = rowStart; b < rowStart + 3; b++){
        subsection.add(this.board.getNumber(a, b));
      }
    }
    return subsection;
  }


  /**
   * check if the set have repetition except for 0
   * @param set nine numbers
   * @return true if the set is valid
   */
  public boolean validSet(ArrayList<Integer> set){
    ArrayList<Integer> template = new ArrayList<>();
    if(set.size() != 9){
      throw new IllegalArgumentException("Given invalid set size: " + set.size());
    }
    //template include integer 1 to 9
    for(int i = 0; i < 9; i++){
      template.add(i + 1);
    }


    for(int i = 0; i < 9; i++){
      if(template.contains(set.get(i))){
        template.remove(set.get(i));
      }else if(set.get(i) == 0){
        //do nothing
      }else{
        return false;}
    }
    return true;
  }



}

