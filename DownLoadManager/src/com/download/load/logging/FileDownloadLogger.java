package com.download.load.logging;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import com.download.load.constants.DownloadFileConstants;
import com.download.utilities.PropertyLoader;

public class FileDownloadLogger {	
	private static FileDownloadLogger instance = null;
	private  Logger logger = null;	
	private static FileHandler fh;
	
	/**
	 * 
	 * @param className
	 * @return
	 * @author pankajmishra
	 */
	public Logger getLogger(String className){
		try { 
			String baseLogPath= PropertyLoader.getConfigProperty(DownloadFileConstants.LOG_PATH);
	        fh = new FileHandler(baseLogPath+"/DownLoadFileLogging_"+System.currentTimeMillis()+ ".log"); 
	        logger= Logger.getLogger("className");
	        logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  
	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  
		return logger;
	}
	
	public static FileDownloadLogger getInstance() {
		if (instance == null) {
			synchronized (FileDownloadLogger.class) {
				// double checking Singleton instance
				if (instance == null) {
					instance = new FileDownloadLogger();
				}
			}
		}
		return instance;
	}
}
