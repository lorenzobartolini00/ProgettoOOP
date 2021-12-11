package it.univpm.DropboxAnalyzer.configuration;

public class Configuration {
	private String url;
	private Body body;
	private String type;
	private String token;
	public Configuration(String url, Body body, String type, String token) {
		this.url = url;
		this.body = body;
		this.type = type;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
	
	
	
}
