package it.univpm.DropboxAnalyzer.Model;

public class File extends Content{

	//attributi
	private Long size;
	private Boolean isDownloadable;
	
	//costruttore
	public File(String name, String pathLower, String pathDisplay, String id, Long size, Boolean isDownloadable) {
		super(name, pathLower, pathDisplay, id);
		this.size = size;
		this.isDownloadable = isDownloadable;
	}

	//getter e setter
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
