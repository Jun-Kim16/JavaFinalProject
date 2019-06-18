package edu.handong.excel.merging.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import edu.handong.excel.merging.data.DataPool;

public class ExcelReader {

	
	public ArrayList<DataPool> getData(InputStream is, int type) {
		 ArrayList<DataPool> rows = new ArrayList<DataPool>();
		
		int i;
		try (InputStream inp = is) {
		  
		    
		        Workbook wb = WorkbookFactory.create(inp);
		        Sheet sheet = wb.getSheetAt(0);
		        int rowNum = sheet.getPhysicalNumberOfRows();
		        int columns = sheet.getRow(0).getLastCellNum();
		        if(type==1) {
			        for(i = 0; i< rowNum; i++) {
			        	DataPool data = new DataPool(sheet.getRow(i));
			        		rows.add(data);
			        	}
				    
		        }else if(type==2) {
		        	 for(i = 0; i< rowNum; i++) {
		        		 DataPool data = new DataPool(sheet.getRow(i), true);
				         rows.add(data);
		        	 }
		        }
		    } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return rows;
	}
	
}
