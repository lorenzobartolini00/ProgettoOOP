package it.univpm.DropboxAnalyzer.configuration;

public class Property {
	private String property;
	private boolean isRequired;
	public Property(String property, boolean isRequired) {
		super();
		this.property = property;
		this.isRequired = isRequired;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public boolean isRequired() {
		return isRequired;
	}
	public void setRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}
	
	
}
