package edu.handong.excel.merging.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

public class ZipReader {

	public InputStream[] readFileInZip(String path) {
		ZipFile zipFile;
		InputStream[] streamArr = new InputStream[2];
		int i = 0;
		try {
			zipFile = new ZipFile(path);
			Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();

			while (entries.hasMoreElements()) {
				ZipArchiveEntry entry = entries.nextElement();
				InputStream stream = zipFile.getInputStream(entry);
				streamArr[i++] = stream;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return streamArr;
	}
}
