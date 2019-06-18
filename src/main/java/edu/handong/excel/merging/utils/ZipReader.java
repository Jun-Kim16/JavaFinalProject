package edu.handong.excel.merging.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TreeMap;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

import edu.handong.excel.merging.data.DataPool;

public class ZipReader {

	public static void readFileInZip(String path, TreeMap<String ,ArrayList<DataPool>> hm) {
		String num = path.substring(path.indexOf("000"), path.indexOf("000")+4);
		ZipFile zipFile;
		try {
			zipFile = new ZipFile(path, "EUC-KR");
			Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();
			ExcelReader reader = new ExcelReader();
			
			while (entries.hasMoreElements()) {
				ZipArchiveEntry entry = entries.nextElement();
				InputStream stream = zipFile.getInputStream(entry);
				
				if(entry.getName().contains("��๮")) {
					ArrayList<DataPool> summaryRows = reader.getData(stream, 1);
					hm.put(num+"_1", summaryRows);
				}
				else if(entry.getName().contains("ǥ.�׸�")) {
					ArrayList<DataPool> tableRows = reader.getData(stream, 2);
					hm.put(num+"_2", tableRows);
				}
					
				else {
					throw new dataErrorException(entry.getName());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}catch (dataErrorException e) {
			WriteCsv.writeErrorFile(e.geterrorlist(), "error.csv");
		}
	}
}
