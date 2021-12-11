package it.univpm.DropboxAnalyzer.configuration;

public class Configuration {
	private String url;
	private Body body;
	private String token;
	public Configuration(String url, Body body, String token) {
		this.url = url;
		this.body = body;
		this.token = token;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Body getBody() {
		return body;
	}
	public void setBody(Body body) {
		this.body = body;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
	
	
	
}
