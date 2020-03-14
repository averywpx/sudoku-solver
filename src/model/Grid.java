package model;

import java.util.*;

public class Grid {
  //todo: too complicated, change back to in[][]
  private ArrayList<ArrayList<Integer>> grid;

  /**
   * basic constructor
   */
  public Grid(ArrayList<ArrayList<Integer>> grid){
    //todo check row length and no repetion
    if(grid.size() != 9){
      throw new IllegalArgumentException("incorrect grid size");
    }
    this.grid = grid;
  }

  /**
   * initial constructor
   */
  public Grid(){
    ArrayList<ArrayList<Integer>> newGrid = new ArrayList<>();
    for(int i = 0; i < 9; i++){
      ArrayList<Integer> newLine = new ArrayList<>();
      for(int j = 0; j < 9; j++){
        newLine.add(0);
      }
      newGrid.add(newLine);
    }
    this.grid = newGrid;
  }

  //todo: replace it by randomly generate board or get input from view
  /**
   * set the initial board, just for test
   * @param s row by row
   * @return
   */
  public void init(String s){
    if(s.length() != 81){
      throw new IllegalArgumentException("Invalid board string length: " + s.length());
    }
    for(int i = 0; i < 9; i++){
      for(int j = 0; j < 9; j++){
        int strpos = i * 9 + j;
        char str = s.charAt(strpos);
        int y = str - '0';
        this.replaceNumber(i,j,y);
      }
    }
  }

  /**
   * check the size of grid
   * @return true if the size is correct
   */
  public boolean validSize(){
    boolean rowSize = (grid.size() == 9);
    boolean columnSize = true;
    for(int i = 0; i < grid.size(); i++){
      columnSize = columnSize && (grid.get(i).size() == 9);
    }
    return rowSize && columnSize;
  }

  public int getNumber(int i, int j){
    return this.grid.get(i).get(j);
  }

  /**
   * replace the number on the given index with given number
   * @param i row index
   * @param j column index
   * @param num replaced number
   */
  public void replaceNumber(int i, int j, int num){
    this.grid.get(i).set(j, num);
  }

  public String printRow(int i){
    String result = "";
    for(int j = 0; j < this.grid.get(i).size(); j++){
      result = result + this.grid.get(i).get(j).toString();
    }
    return result + "\n";
  }
//  public String printRow(ArrayList<Integer> al){
//    String result = "";
//    for(int j = 0; j < al.size(); j++){
//      result = result + al.get(j).toString();
//    }
//    return result + "\n";
//  }
  /**
   * get the row
   * @param i row index
   * @return the row
   */
  ArrayList<Integer> getRow(int i){
    return this.grid.get(i);
  }

  /**
   * check if all of the spot are filled in number
   * @return true if all full
   */
  Boolean isAllFull(){
    Boolean result = true;
    for(int i = 0; i < 9; i++){
      for(int j = 0; j < 9; j++){
        result = result && (this.getNumber(i,j) != 0);
      }
    }
    return result;
  }


}
