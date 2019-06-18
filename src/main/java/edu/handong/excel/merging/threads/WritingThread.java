package edu.handong.excel.merging.threads;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.DataFormatter;

import edu.handong.excel.merging.data.DataPool;
import edu.handong.excel.merging.utils.WriteCsv;

public class WritingThread extends Thread {

	private String outputPath = null;
	private int type = -1;
	private int rowNum = 0;
	private TreeMap<String, ArrayList<DataPool>> mapNumToExcel = null;
	private DataFormatter dataForm = new DataFormatter();
	private ArrayList<String> lines = new ArrayList<String>();
	
	public WritingThread(String path, int type, TreeMap<String, ArrayList<DataPool>> hm) {
		outputPath = path;
		this.type = type;
		synchronized(hm) {
			mapNumToExcel = hm;
		}

	}
	
	public void run() {
		for(String key : mapNumToExcel.keySet()) {
			if (type==1) {
				if(key.contains("0001_1"))makeRow1(lines, key.split("_")[0], mapNumToExcel.get(key), true);
				else if(key.contains("_1")) makeRow1(lines, key.split("_")[0], mapNumToExcel.get(key), false);
			}
			else {
				if(key.contains("0001_2"))makeRow2(lines, key.split("_")[0], mapNumToExcel.get(key), true);
				else if(key.contains("_2")) makeRow2(lines, key.split("_")[0], mapNumToExcel.get(key), false);
			}
		}
		WriteCsv.writeAFile(lines, outputPath);
		System.out.println("Done");
	}

	public void makeRow1(ArrayList<String> lines,String fileNum, ArrayList<DataPool> row, boolean isheader){
		
		if(isheader) {
			
			DataPool data = row.get(0);
			lines.add("\"" + "" + "\"" + "," 
					+ "\"" + dataForm.formatCellValue(data.getC0())+ "\"" + "," 
					+ "\"" + dataForm.formatCellValue(data.getC1())+ "\"" + "," 
					+ "\"" + dataForm.formatCellValue(data.getC2())+ "\"" + "," 
					+ "\"" + dataForm.formatCellValue(data.getC3())+ "\"" + "," 
					+ "\"" + dataForm.formatCellValue(data.getC4())+ "\"" + ","
					+ "\"" + dataForm.formatCellValue(data.getC5())+ "\"" + ","
					+ "\"" + dataForm.formatCellValue(data.getC6())+ "\"") ;
			
			for(int i = 1; i < row.size(); i++) {
				data = row.get(i);
				lines.add("\"" + fileNum + "\"" + "," 
						+ "\"" + dataForm.formatCellValue(data.getC0())+ "\"" + "," 
						+ "\"" + dataForm.formatCellValue(data.getC1())+ "\"" + "," 
						+ "\"" + dataForm.formatCellValue(data.getC2())+ "\"" + "," 
						+ "\"" + dataForm.formatCellValue(data.getC3())+ "\"" + "," 
						+ "\"" + dataForm.formatCellValue(data.getC4())+ "\"" + ","
						+ "\"" + dataForm.formatCellValue(data.getC5())+ "\"" + ","
						+ "\"" + dataForm.formatCellValue(data.getC6())+ "\"") ;
			}
		}
		else {
			for(int i = 1; i < row.size(); i++) {
				DataPool data = row.get(i);
				lines.add("\"" + fileNum + "\"" + "," 
						+ "\"" + dataForm.formatCellValue(data.getC0())+ "\"" + "," 
						+ "\"" + dataForm.formatCellValue(data.getC1())+ "\"" + "," 
						+ "\"" + dataForm.formatCellValue(data.getC2())+ "\"" + "," 
						+ "\"" + dataForm.formatCellValue(data.getC3())+ "\"" + "," 
						+ "\"" + dataForm.formatCellValue(data.getC4())+ "\"" + ","
						+ "\"" + dataForm.formatCellValue(data.getC5())+ "\"" + ","
						+ "\"" + dataForm.formatCellValue(data.getC6())+ "\"") ;
			}
		}
		
	}
	
public void makeRow2(ArrayList<String> lines,String fileNum, ArrayList<DataPool> row, boolean isheader){
		
		if(isheader) {
			for(int i = 0; i<2;i++) {
				DataPool data = row.get(i);
				lines.add("\"" + "" + "\"" + "," 
						+ "\"" + dataForm.formatCellValue(data.getC0())+ "\"" + "," 
						+ "\"" + dataForm.formatCellValue(data.getC1())+ "\"" + "," 
						+ "\"" + dataForm.formatCellValue(data.getC2())+ "\"" + "," 
						+ "\"" + dataForm.formatCellValue(data.getC3())+ "\"" + "," 
						+ "\"" + dataForm.formatCellValue(data.getC4())+ "\"") ;
				}	
			
			for(int i = 2; i<row.size();i++) {
				DataPool data = row.get(i);
				lines.add("\"" + fileNum + "\"" + "," 
						+ "\"" + dataForm.formatCellValue(data.getC0())+ "\"" + "," 
						+ "\"" + dataForm.formatCellValue(data.getC1())+ "\"" + "," 
						+ "\"" + dataForm.formatCellValue(data.getC2())+ "\"" + "," 
						+ "\"" + dataForm.formatCellValue(data.getC3())+ "\"" + "," 
						+ "\"" + dataForm.formatCellValue(data.getC4())+ "\"") ;
			}	
		}
		else {
			for(int i = 2; i<row.size();i++) {
				DataPool data = row.get(i);
				lines.add("\"" + fileNum + "\"" + "," 
						+ "\"" + dataForm.formatCellValue(data.getC0())+ "\"" + "," 
						+ "\"" + dataForm.formatCellValue(data.getC1())+ "\"" + "," 
						+ "\"" + dataForm.formatCellValue(data.getC2())+ "\"" + "," 
						+ "\"" + dataForm.formatCellValue(data.getC3())+ "\"" + "," 
						+ "\"" + dataForm.formatCellValue(data.getC4())+ "\"") ;
			}
		}
	}
}
