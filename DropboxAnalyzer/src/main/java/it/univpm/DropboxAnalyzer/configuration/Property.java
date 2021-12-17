package it.univpm.DropboxAnalyzer.configuration;

public class Property {
	private String propertyName;
	private boolean isRequired;
	private int type;
	public Property(String propertyName, boolean isRequired, int type) {
		super();
		this.propertyName = propertyName;
		this.isRequired = isRequired;
		this.type = type;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	public boolean isRequired() {
		return isRequired;
	}
	public void setRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
}
