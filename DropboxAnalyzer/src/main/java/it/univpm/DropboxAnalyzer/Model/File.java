package it.univpm.DropboxAnalyzer.Model;

public class File extends Content{

	//attributi
	private Long size;
	private Boolean isDownloadable;
	
	public File(String name, String path, String id, Long size, Boolean isDownloadable) {
		super(name, path, id);
		this.size=size;
		this.isDownloadable=isDownloadable;
	}
	
}
