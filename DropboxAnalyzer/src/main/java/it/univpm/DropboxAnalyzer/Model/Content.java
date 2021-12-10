package it.univpm.DropboxAnalyzer.Model;

public class Content {

	/*Inizializzo le variabili che, usando in 
	 * Postaman GetMetadata, mi vengono restituite */

	//attributi
	private String name;
	private String pathLower;
	private String pathDisplay;
	private String id;
	
	//costruttore

	public Content(String name, String pathLower, String pathDisplay, String id) {
		super();
		this.name = name;
		this.pathLower = pathLower;
		this.pathDisplay = pathDisplay;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPathLower() {
		return pathLower;
	}

	public void setPathLower(String pathLower) {
		this.pathLower = pathLower;
	}

	public String getPathDisplay() {
		return pathDisplay;
	}

	public void setPathDisplay(String pathDisplay) {
		this.pathDisplay = pathDisplay;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}
