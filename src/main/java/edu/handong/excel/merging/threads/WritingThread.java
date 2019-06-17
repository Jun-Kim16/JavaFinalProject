package edu.handong.excel.merging.threads;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WritingThread extends Thread {

	XSSFWorkbook workbook = new XSSFWorkbook();
	XSSFSheet sheet = workbook.createSheet("Datatypes in Java");
	String outputPath = null;
	int version = -1;
	int rowNum = 0;
	TreeMap<String, HashMap<Integer, ArrayList<String>>> mapFromStuToExcel = null;

	public WritingThread(String path, int i, TreeMap<String, HashMap<Integer, ArrayList<String>>> hm) {
		outputPath = path;
		version = i;
		mapFromStuToExcel = hm;
	}
	
	public void run() {
		
		for (String stu_ver : mapFromStuToExcel.keySet()) {
			if (stu_ver.contains("_" + Integer.toString(version))) {
				rowNum = 0;
				for (Integer rowLine : mapFromStuToExcel.get(stu_ver).keySet()) {
					Row row = sheet.createRow(rowNum++);
					int colNum = 0;
					for (String col : mapFromStuToExcel.get(stu_ver).get(rowLine)) {
						Cell cell = row.createCell(colNum++);
						if (col instanceof String) {
							cell.setCellValue((String) col);
						} 
					}
				}
			}
		}
		
		try {
			FileOutputStream outputStream = new FileOutputStream(outputPath.substring(0, outputPath.length()-4)+"_"+Integer.toString(version)+".xls");
			workbook.write(outputStream);
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Done");
	}
}
