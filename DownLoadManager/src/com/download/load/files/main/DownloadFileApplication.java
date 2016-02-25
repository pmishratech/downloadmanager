package com.download.load.files.main;

import java.io.IOException;
import java.util.List;

import com.download.load.processor.FileProcessor;
import com.download.utilities.PropertyLoader;

public class DownloadFileApplication {
	public static void main(String[] args) {
		List<String> sources = null;
		try {
			sources = PropertyLoader.loadInputs();
		} catch (IOException e) {
			System.out.println("Input file not found ");
		}
		new FileProcessor().process(sources);
	}
}
