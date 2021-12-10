package it.univpm.DropboxAnalyzer.configuration;

import java.net.URL;

public class Configuration {
	private URL url;
	private Body body;
	
	public String getStringBody()
	{
		return body.toString();
	}
}
