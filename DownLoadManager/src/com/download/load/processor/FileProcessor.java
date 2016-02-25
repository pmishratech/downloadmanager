package com.download.load.processor;

import java.util.Iterator;
import java.util.List;

import com.download.utilities.Utility;

public class FileProcessor {
	FileDownloadExecutorPool fileDownloadExecutorPool;

	public void process(List<String> sources) {
		fileDownloadExecutorPool = FileDownloadExecutorPool.getInstance();
		if (Utility.isNotEmpty(sources)) {
			for (Iterator<String> iterator = sources.iterator(); iterator.hasNext();) {
				String source = iterator.next();
				fileDownloadExecutorPool.submitTask(new LoadProcessorThread(source));
			}
			fileDownloadExecutorPool.close();
		}
	}
}
