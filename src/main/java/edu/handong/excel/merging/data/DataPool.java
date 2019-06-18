package edu.handong.excel.merging.data;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class DataPool{
	

	private Cell c0;
	private Cell c1;
	private Cell c2;
	private Cell c3;
	private Cell c4;
	private Cell c5;
	private Cell c6;
	
	
	public DataPool(Row line){

		c0 = line.getCell(0);
		c1 = line.getCell(1);
		c2 = line.getCell(2);
		c3 = line.getCell(3);
		c4 = line.getCell(4);
		c5 = line.getCell(5);
		c6 = line.getCell(6);
	}
	
	public DataPool(Row line, boolean a){

		c0 = line.getCell(0);
		c1 = line.getCell(1);
		c2 = line.getCell(2);
		c3 = line.getCell(3);
		c4 = line.getCell(4);
		
	}






	public Cell getC0() {
		return c0;
	}


	public Cell getC1() {
		return c1;
	}


	public Cell getC2() {
		return c2;
	}


	public Cell getC3() {
		return c3;
	}


	public Cell getC4() {
		return c4;
	}


	public Cell getC5() {
		return c5;
	}


	public Cell getC6() {
		return c6;
	}
		
}
