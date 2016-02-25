package com.download.load.processor;

import java.io.IOException;

import com.download.load.constants.DownloadFileConstants;
import com.download.loadfiles.FileDownloader;
import com.download.loadfiles.FileDownloaderImpl;
import com.download.utilities.PropertyLoader;

public class LoadProcessorThread implements Runnable {
	private String source;

	public LoadProcessorThread(String source) {
		this.source = source;
	}

	@Override
	public void run() {
		FileDownloader downloader = new FileDownloaderImpl();
		boolean isSuccessful = false;
		int retryAttempt = 1;
		int retries = 4; // default
		try {
			retries = Integer.parseInt(PropertyLoader.getConfigProperty(DownloadFileConstants.RETRIES));
		} catch (Exception e) {
			System.out.println("setting default retries of 4 due to Exception:" + e.getMessage());
		}

		/**
		 * Logic written to retries a URL for a resource/file, This will take
		 * care of following points: 1-some sources might very big (more than
		 * memory) 2-some sources might be very slow, while others might be fast
		 * 3-some sources might fail in the middle of download 4-we don't want
		 * to have partial data in the final location in any case.
		 */

		while (!isSuccessful && retryAttempt <= retries) {
			try {
				downloader.download(source);
				isSuccessful = true;
			} catch (IOException e) {
				System.out.println("Failure of Downloading [" + source + "] for attempt : " + retryAttempt);
				System.out.println("Exception : " + e.getMessage());
				retryAttempt++;
			}
		}
	}
}
