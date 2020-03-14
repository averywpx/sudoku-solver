package test_model;
import model.Grid;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;


public class GridTest {
  @Test
  public void testGetNumberEx1(){
    Grid g = new Grid();
    assertEquals(0, g.getNumber(2,2));

  }

  @Test
  public void testReplaceNumber(){
    Grid g = new Grid();
    g.replaceNumber(2,3,2);
    assertEquals(2, g.getNumber(2,3));
  }

  @Test
  public void testPrintRow(){
    Grid g = new Grid();
    g.replaceNumber(2,3,7);
    ArrayList<Integer> nl = new ArrayList<>(Arrays.asList(0,0,0,0,0));
    assertEquals("000700000\n", g.printRow(2));


  }

//  @Test
//  public void testInit(){
//    Grid g = new Grid();
//    String s = "007500816"
//            + "002160490"
//            + "196804000"
//            + "573601000"
//            + "260040105"
//            + "000025638"
//            + "349008060"
//            + "000473509"
//            + "720010084";
//    g.init(s);
//    assertEquals(0, g.grid);
//  }









}