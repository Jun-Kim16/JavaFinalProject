package edu.handong.excel.merging.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class WriteCsv {
	
	public static void writeErrorFile(ArrayList<String> lines, String targetFileName) {
		
		BufferedWriter bf = null;
		try {
			bf = new BufferedWriter(new FileWriter(targetFileName, true));
			for (String l : lines) {
				bf.write(l+", ");
			}
			bf.flush();
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
 		}
	}
	
	public static void writeAFile(ArrayList<String> lines, String targetFileName){
		
		BufferedWriter bf = null;
		try {
			bf = new BufferedWriter(new FileWriter(targetFileName, false));
			for (String l : lines) {
				bf.write(l);
				bf.newLine();
			}
			bf.flush();
			bf.close();
		} catch (IOException e) {
			e.printStackTrace();
 		}
	}
}
