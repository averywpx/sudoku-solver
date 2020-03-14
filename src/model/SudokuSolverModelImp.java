package model;

import java.util.*;

public class SudokuSolverModelImp implements SudokuSolverModel {
  public Grid board;
  public HashMap<Integer, ArrayList<Integer>> rows;
  public HashMap<Integer, ArrayList<Integer>> columns;
  public HashMap<ArrayList<Integer>, ArrayList<Integer>> subsection;

  /**
   * have the initial board
   * @param s initial board
   */
  public SudokuSolverModelImp(String s){
    this.board = new Grid();
    this.rows = new HashMap<>();
    this.columns = new HashMap<>();
    for(int i = 0; i < 9; i++){
      ArrayList<Integer> newl = new ArrayList<>();
      this.rows.put(i,newl);
      this.columns.put(i,newl);
    }
    this.subsection = new HashMap<>();
    for(int i = 0; i < 9; i += 3){
      for(int j = 0; j < 9; j += 3){
        ArrayList<Integer> newl = new ArrayList<>();
        ArrayList<Integer> pos = new ArrayList<Integer>(Arrays.asList(i, j));
        this.subsection.put(pos, newl);
      }
    }

    this.board.init(s);
    this.isValid();

  }

  /**
   * constructor with only board
   * @param board
   */
  public SudokuSolverModelImp(Grid board){
    this.board = board;
    this.rows = new HashMap<>();
    this.columns = new HashMap<>();
    for(int i = 0; i < 9; i++){
      ArrayList<Integer> newl = new ArrayList<>();
      this.rows.put(i,newl);
      this.columns.put(i,newl);
    }
    this.subsection = new HashMap<>();
    for(int i = 0; i < 9; i += 3){
      for(int j = 0; j < 9; j += 3){
        ArrayList<Integer> newl = new ArrayList<>();
        ArrayList<Integer> pos = new ArrayList<Integer>(Arrays.asList(i, j));
        this.subsection.put(pos, newl);
      }
    }

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
      ArrayList<Integer> valideNum = validSet(this.board.getRow(i));
      row = row && (valideNum.size() != 10);
      this.rows.put(i, valideNum);
    }
    for(int j = 0; j< 9; j++){
      ArrayList<Integer> valideNum = validSet(this.collectColumn(j));
      column = column && (valideNum.size() != 10);
      this.columns.put(j, valideNum);
    }
    for(int i = 0; i < 9; i += 3){
      for(int j = 0; j < 9; j += 3){
        ArrayList<Integer> coord = new ArrayList<>(Arrays.asList(i, j));
        ArrayList<Integer> valideNum = validSet(this.collectSubsection(i,j));
        subsection = subsection && (valideNum.size() != 10);
        this.subsection.put(coord, valideNum);
      }
    }
    return row && column && subsection;
  }

  //easiest algorithm
  //todo: case that the size of possible solution for each remain spots is larger than 1
  @Override
  public void fillBoard() {
    //skip if the board have a number
    while(!this.board.isAllFull()) {
      for (int i = 0; i < 9; i++) {
        for (int j = 0; j < 9; j++) {
          if (this.board.getNumber(i, j) == 0) {
            this.fillANum(i, j);
            System.out.print("Filling in i: " + i + ", j: " + j + "\n");
          }
        }
      }
    }
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
      //System.out.print(column);
      return column;
    }else{
      throw new IllegalArgumentException("collectColumn: invalid column size: " + column.size());
      //return new ArrayList<>();
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
  public ArrayList<Integer> validSet(ArrayList<Integer> set){
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
        ArrayList<Integer> f = new ArrayList<>();
        for(int j = 0; j < 10; j++){
          f.add(0);
        }
        return f;}
    }
    return template;
  }

  /**
   * fill in a number by given position
   * @param i row index
   * @param j column index
   */
  void fillANum(int i, int j){
    //check valid input
    if(i < 0 || i > 8 || j < 0 || j > 8){
      throw new IllegalArgumentException("invalid index  i: " + i + "j: "+ j);
    }
    //check is valid
    this.isValid();

    //find intersection of the three set
    int columnStart = i - i % 3;
    int rowStart = j - j % 3;
    ArrayList<Integer> subcoord = new ArrayList<>(Arrays.asList(columnStart,rowStart));
    ArrayList<Integer> intersection = this.intersection(this.rows.get(i), this.columns.get(j),
            this.subsection.get(subcoord));

    //fill in a number if only one result
    if(intersection.size() == 1){
      int y = intersection.get(0);
      this.board.replaceNumber(i, j, y);
      System.out.print("============Fill " + y + " at i: " + i + ", j: " + j + "\n");
    }
  }

  /**
   * find the intersection of the three ArrayList
   * @param a
   * @param b
   * @param c
   * @return the intersection
   */
  public ArrayList<Integer> intersection(ArrayList<Integer> a, ArrayList<Integer> b,
                                   ArrayList<Integer> c){

    System.out.print(a + "\n");
    System.out.print(b + "\n");
    System.out.print(c + "\n");
    b.retainAll(a);

    c.retainAll(b);
    if(c.size() == 0){
      throw new IllegalArgumentException("No intersection");
    }

    return c;
  }


}

