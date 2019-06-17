package edu.handong.excel.merging;
	
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import org.apache.commons.cli.Options;

import edu.handong.excel.merging.threads.ReadingThread;
import edu.handong.excel.merging.threads.WritingThread;

public class ExcelFileMerger {
	private static TreeMap<String, HashMap<Integer,ArrayList<String>>> mapFromStuToExcel
					= new TreeMap<String, HashMap<Integer,ArrayList<String>>>();
	
	public void run(String[] args) {
		CliOptions cliOptions = new CliOptions();
		Options options = cliOptions.createOptions();

		if (cliOptions.parseOptions(options, args)) {
			String inputPath = cliOptions.getInputPath();
			String outputPath = cliOptions.getOutputPath();

			if (cliOptions.isHelp()) {
				cliOptions.printHelp(options);
				return;
			}

			
			ReadingThread in1 = new ReadingThread(inputPath, 1, mapFromStuToExcel);
			in1.start();
			ReadingThread in2 = new ReadingThread(inputPath, 2, mapFromStuToExcel);
			in2.start();
			ReadingThread in3 = new ReadingThread(inputPath, 3, mapFromStuToExcel);
			in3.start();
			ReadingThread in4 = new ReadingThread(inputPath, 4, mapFromStuToExcel);
			in4.start();
			ReadingThread in5 = new ReadingThread(inputPath, 5, mapFromStuToExcel);
			in5.start();
			
			try {	
				in1.join();in2.join();in3.join();in4.join();in5.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			WritingThread out0 = new WritingThread(outputPath, 1, mapFromStuToExcel);
			out0.start();
			
			WritingThread out1 = new WritingThread(outputPath, 2, mapFromStuToExcel);
			out1.start();
			
		}
	}
}