package it.univpm.DropboxAnalyzer.Model;

/**
 * Questa classe descrive le proprietà di ogni contentuto
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 */
public class Content {

	//attributi
	private String name;
	private String pathLower;
	private String pathDisplay;
	private String id;
	

	/**
	 * Costruttore
	 * @param name Nome del contentuto
	 * @param pathLower Percorso completo in minuscolo
	 * @param pathDisplay Percorso con maiuscole e minuscole da utilizzare solo a scopo di visualizzazione
	 * @param id Id del contenuto
	 */
	public Content(String name, String pathLower, String pathDisplay, String id) {
		super();
		this.name = name;
		this.pathLower = pathLower;
		this.pathDisplay = pathDisplay;
		this.id = id;
	}

	/**
	 * metodo che restituisce il nome del contenuto
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * metodo che setta il nome del contenuto
	 * @param String name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * metodo che restituisce il pathLower del contenuto
	 * @return pathLower
	 */
	public String getPathLower() {
		return pathLower;
	}

	/**
	 * metodo che setta il pathLower del contenuto
	 * @param String pathLower
	 */
	public void setPathLower(String pathLower) {
		this.pathLower = pathLower;
	}

	/**
	 * metodo che restituisce il pathDisplay del contenuto
	 * @return pathDisplay
	 */
	public String getPathDisplay() {
		return pathDisplay;
	}

	/**
	 * metodo che setta il pathDisplay del contenuto
	 * @param String pathDisplay
	 */
	public void setPathDisplay(String pathDisplay) {
		this.pathDisplay = pathDisplay;
	}

	/**
	 * metodo che restituisce l'id del contenuto
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * metodo che setta l'id del contenuto
	 * @param String id
	 */
	public void setId(String id) {
		this.id = id;
	}

	
}
