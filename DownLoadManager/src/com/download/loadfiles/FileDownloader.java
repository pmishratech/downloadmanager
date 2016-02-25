package com.download.loadfiles;

import java.io.IOException;

public interface FileDownloader {
	void download(String source) throws IOException;
}
