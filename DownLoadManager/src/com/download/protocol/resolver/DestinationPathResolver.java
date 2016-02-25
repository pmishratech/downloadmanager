package com.download.protocol.resolver;

import java.io.IOException;
import java.net.URL;

import com.download.utilities.PropertyLoader;

public class DestinationPathResolver {
	public static String getDestinationPath(URL url) throws IOException {
		return PropertyLoader.getConfigProperty(url.getProtocol());
	}
}
