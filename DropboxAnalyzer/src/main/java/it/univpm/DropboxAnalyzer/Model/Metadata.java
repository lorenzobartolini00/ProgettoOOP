package it.univpm.DropboxAnalyzer.Model;

public class Metadata {

	/*Inizializzo le variabili che, usando in 
	 * Postaman GetMetadata, mi vengono restituite */
	
	//Non inizializzo .tag perchè devo sempre analizzare dei file

	//attributi
	private String name;
	private String path;
	private String id;
	private Long size;
	private Boolean isDownloadable; //in teoria lo sono tutti
	
	//costruttore
	public Metadata(String name, String path, String id, Long size, Boolean isDownloadable) {
		this.name=name;
		this.path=path;
		this.id=id;
		this.size=size;
		this.isDownloadable=isDownloadable;
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

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Boolean getIsDownloadable() {
		return isDownloadable;
	}

	public void setIsDownloadable(Boolean isDownloadable) {
		this.isDownloadable = isDownloadable;
	}
	
}
