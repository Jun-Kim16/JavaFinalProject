package edu.handong.excel.merging;

import java.io.File;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import edu.handong.excel.merging.utils.dataErrorException;

public class CliOptions {

	private String inputPath = null;
	private String outputPath1= null;
	private String outputPath2= null;
	private boolean help = false;
	
	public boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {
			CommandLine cmd = parser.parse(options, args);
			
			inputPath = cmd.getOptionValue("i");
			outputPath1 = cmd.getOptionValue("o1");
			outputPath2 = cmd.getOptionValue("o2");
			help = cmd.hasOption("h");
		}catch (Exception e) {
			System.out.println(e.getMessage());
			printHelp(options);
			return false;
		}
		return true;
	}
	
	public Options createOptions() {
		Options options = new Options();

		options.addOption(Option.builder("i").longOpt("input")
				.desc("Set an input file path")
				.hasArg()
				.argName("Input path")
				.required()
				.build());
		
		options.addOption(Option.builder("o1").longOpt("output")
				.desc("Set an output file path")
				.hasArg()
				.argName("Output path")
				.required()
				.build());
		
		options.addOption(Option.builder("o2").longOpt("output")
				.desc("Set an output file path")
				.hasArg()
				.argName("Output path")
				.required()
				.build());
		
		options.addOption(Option.builder("h").longOpt("help")
		        .desc("Show a Help page")
		        .argName("Help")
		        .build());
		return options;
	}
	
	public void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "excelmerger";
		String footer ="";
		formatter.printHelp("JKexcelmerger", header, options, footer, true);
	}
	
	public String getInputPath() {
		return inputPath;
	}

	public String getOutputPath1() {
		return outputPath1;
	}
	public String getOutputPath2() {
		return outputPath2;
	}

	public boolean isHelp() {
		return help;
	}
}
