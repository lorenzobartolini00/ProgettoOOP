package it.univpm.DropboxAnalyzer.configuration;

/**
 * Classe che modella una generica proprietà tra quelle che può inserire l'utente in fase
 * di configurazione nel body della richiesta http
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 *
 */

public class Property {
	private String propertyName;
	private boolean isRequired;
	private int type;
	
	/**
	 * Costruttore
	 * @param propertyName Nome della proprietà
	 * @param isRequired Se il parametro è obbligatorio
	 * @param type Rappresenta il tipo della proprietà(0=String, 1=Boolean, 2=Integer)
	 */
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
