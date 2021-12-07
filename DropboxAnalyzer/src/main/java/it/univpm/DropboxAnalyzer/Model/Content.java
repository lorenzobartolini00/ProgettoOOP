package it.univpm.DropboxAnalyzer.Model;

public class Content {

	/*Inizializzo le variabili che, usando in 
	 * Postaman GetMetadata, mi vengono restituite */

	//attributi
	private String name;
	private String path;
	private String id;
	
	//costruttore
	public Content(String name, String path, String id) {
		this.name=name;
		this.path=path;
		this.id=id;
	}

	//getter e setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
