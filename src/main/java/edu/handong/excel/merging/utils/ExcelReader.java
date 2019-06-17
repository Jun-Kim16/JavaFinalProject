package edu.handong.excel.merging.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	
	public HashMap<Integer,ArrayList<String>> getData(InputStream is) {
		HashMap<Integer,ArrayList<String>> excelData = new HashMap<Integer,ArrayList<String>>();
		ArrayList<String> rowdata = null;
		int i = 1;
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(is);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				rowdata = new ArrayList<String>();
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					 if (cell.getCellType() == CellType.STRING) {
	                        rowdata.add(cell.getStringCellValue());
	                    } else if (cell.getCellType() == CellType.NUMERIC) {
	                        rowdata.add(Double.toString(cell.getNumericCellValue()));
	                    }					
				}
			excelData.put(i++, rowdata);
			}
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return excelData;
	}
}
