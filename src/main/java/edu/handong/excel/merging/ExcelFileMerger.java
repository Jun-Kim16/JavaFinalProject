package edu.handong.excel.merging;
	
import java.util.ArrayList;
import java.util.TreeMap;

import org.apache.commons.cli.Options;

import edu.handong.excel.merging.data.DataPool;
import edu.handong.excel.merging.threads.WritingThread;
import edu.handong.excel.merging.utils.ZipReader;

public class ExcelFileMerger {
	private static TreeMap<String ,ArrayList<DataPool>> totalData = new TreeMap<String ,ArrayList<DataPool>>();
	
	public void run(String[] args) {
		CliOptions cliOptions = new CliOptions();
		Options options = cliOptions.createOptions();
		if (cliOptions.parseOptions(options, args)) {
			String inputPath = cliOptions.getInputPath();
			String outputPath1 = cliOptions.getOutputPath().substring(0, cliOptions.getOutputPath().indexOf("."))+"1"+
					cliOptions.getOutputPath().substring(cliOptions.getOutputPath().indexOf("."), cliOptions.getOutputPath().length());
			String outputPath2 = cliOptions.getOutputPath().substring(0, cliOptions.getOutputPath().indexOf("."))+"2"+
					cliOptions.getOutputPath().substring(cliOptions.getOutputPath().indexOf("."), cliOptions.getOutputPath().length());

			if (cliOptions.isHelp()) {
				cliOptions.printHelp(options);
				return;
			}
			
			ZipReader.readFileInZip(inputPath+"\\0001.zip", totalData);
			ZipReader.readFileInZip(inputPath+"\\0002.zip", totalData);
			ZipReader.readFileInZip(inputPath+"\\0003.zip", totalData);
			ZipReader.readFileInZip(inputPath+"\\0004.zip", totalData);
			ZipReader.readFileInZip(inputPath+"\\0005.zip", totalData);
			
			WritingThread it1 = new WritingThread(outputPath1, 1, totalData); //쓰는 동시에 읽어야하므로 InputThread생성
			it1.start();
			WritingThread it2 = new WritingThread(outputPath2, 2, totalData);
			it2.start();
			
			
			try {
				it1.join();
				it2.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Fin");
		}
	}
}