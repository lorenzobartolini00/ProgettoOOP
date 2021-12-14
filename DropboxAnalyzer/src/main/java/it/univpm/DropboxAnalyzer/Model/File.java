package it.univpm.DropboxAnalyzer.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class File extends Content{

	//attributi
	private Long size;
	private Boolean isDownloadable;
	private String extension;
	
	//costruttore
	public File(String name, String pathLower, String pathDisplay, String id, Long size, Boolean isDownloadable) {
		super(name, pathLower, pathDisplay, id);
		this.size = size;
		this.isDownloadable = isDownloadable;
		this.extension = toExtension(pathLower);
	}

	private String toExtension(String pathLower) {
		ArrayList<String> strings = new ArrayList<String>(Arrays.asList(pathLower.split(".")));
		return "." + strings.get(strings.size() - 1);
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

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	
	
}
