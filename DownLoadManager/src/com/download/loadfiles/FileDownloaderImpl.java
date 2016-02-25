package com.download.loadfiles;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

import com.download.load.constants.DownloadFileConstants;
import com.download.load.logging.FileDownloadLogger;
import com.download.protocol.resolver.DestinationPathResolver;

public class FileDownloaderImpl implements FileDownloader {
	private static final int BUFFER_SIZE = 4096;
	private static final Logger LOGGER = FileDownloadLogger.getInstance().getLogger(FileDownloaderImpl.class.getName());

	@Override
	public void download(String source) throws IOException {

		LOGGER.info("File[" + source + "] Download Started");
		InputStream is = null;
		FileOutputStream os = null;
		try {
			URL url = new URL(source);
			URLConnection uc = url.openConnection();
			uc.connect();
			uc = url.openConnection();
			uc.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
			is = uc.getInputStream();
			os = new FileOutputStream(getFileToStore(getFileName(source), getDestinationLocation(url)));
			int bytesRead = -1;
			byte[] buffer = new byte[BUFFER_SIZE];
			while ((bytesRead = is.read(buffer)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
		} finally {
			if (is != null) {
				is.close();
			}
			if (os != null) {
				os.close();
			}
		}
		LOGGER.info("File[" + source + "] Download Completed");
	}

	/**
	 * This method take care of use case,
	 * "The program should extensible to support different protocols" as
	 * DestinationPathResolver would handle all protocols
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 * @author pankajmishra
	 */
	private String getDestinationLocation(URL url) throws IOException {
		return DestinationPathResolver.getDestinationPath(url);
	}

	private String getFileName(String source) {
		return source.substring(source.lastIndexOf(DownloadFileConstants.BACKSLASH) + 1, source.length());
	}

	private File getFileToStore(String fileName, String location) {
		File f = new File(location + fileName);
		int counter = 1;
		while (f.exists()) {
			f = new File(location
					+ (fileName.contains(DownloadFileConstants.DOT) ? fileName.substring(0,
							fileName.lastIndexOf(DownloadFileConstants.DOT)) : fileName)
					+ DownloadFileConstants.UNDERSCORE
					+ counter++
					+ (fileName.contains(DownloadFileConstants.DOT) ? fileName.substring(
							fileName.lastIndexOf(DownloadFileConstants.DOT), fileName.length()) : ""));
		}
		return f;
	}

}
