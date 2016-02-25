package com.download.load.processor;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.download.load.constants.DownloadFileConstants;
import com.download.utilities.PropertyLoader;

public class FileDownloadExecutorPool {
	private ExecutorService executorService;
	private static FileDownloadExecutorPool fileDownloadExecutorPool;

	private FileDownloadExecutorPool(int noOfThreads) {
		executorService = Executors.newFixedThreadPool(noOfThreads);
	}

	public static synchronized FileDownloadExecutorPool getInstance() {
		if (fileDownloadExecutorPool != null) {
			return fileDownloadExecutorPool;
		}
		int noOfThreads;
		try {
			noOfThreads = Integer.parseInt(PropertyLoader.getConfigProperty(DownloadFileConstants.NO_OF_THREADS));
		} catch (NumberFormatException | IOException e) {
			System.out.println("Propery not found. setting default value of 5");
			noOfThreads = 5;
		}
		fileDownloadExecutorPool = new FileDownloadExecutorPool(noOfThreads);
		return fileDownloadExecutorPool;
	}

	public void submitTask(Runnable task) {
		executorService.execute(task);
	}

	public void close() {
		executorService.shutdown();
	}
}
