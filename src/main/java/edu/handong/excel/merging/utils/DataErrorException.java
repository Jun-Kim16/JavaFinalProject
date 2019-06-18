package edu.handong.excel.merging.utils;

import java.util.ArrayList;

public class DataErrorException extends Exception{
	private ArrayList<String> errorlist = new ArrayList<String>();
	
	public DataErrorException(String filename) {
		super("Error in file name.");
		errorlist.add(filename);
	}
	
	public ArrayList<String> geterrorlist(){
		return errorlist;
	}
}
