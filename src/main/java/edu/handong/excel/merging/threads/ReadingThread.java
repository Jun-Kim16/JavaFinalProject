package edu.handong.excel.merging.threads;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import edu.handong.excel.merging.utils.ExcelReader;
import edu.handong.excel.merging.utils.ZipReader;

public class ReadingThread extends Thread {
	
	String numOfFile = null;
	String path;
	InputStream[] stream;
	TreeMap<String, HashMap<Integer,ArrayList<String>>> mapFromStuToExcel;
	
	public ReadingThread(String path, int num, TreeMap<String, HashMap<Integer,ArrayList<String>>> hm){
		this.path = path;
		numOfFile = Integer.toString(num);
		mapFromStuToExcel=hm;
	}
	
	public void run() {
		stream = new ZipReader().readFileInZip(path + "\\000"+numOfFile+".zip");
		mapFromStuToExcel.put("000"+numOfFile+"_"+"0", new ExcelReader().getData(stream[0]));
		mapFromStuToExcel.put("000"+numOfFile+"_"+"1", new ExcelReader().getData(stream[1]));	
	}
	
}
