package it.univpm.DropboxAnalyzer.configuration;

import java.net.URL;

public class Configuration {
	private URL url;
	private Body body;
	
	
	
	public Configuration(URL url, Body body) {
		super();
		this.url = url;
		this.body = body;
	}



	public String getStringBody()
	{
		return body.toString();
	}
}
