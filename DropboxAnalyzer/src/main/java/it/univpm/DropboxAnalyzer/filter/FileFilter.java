package it.univpm.DropboxAnalyzer.filter;

import java.util.Vector;
import java.util.function.Predicate;

import it.univpm.DropboxAnalyzer.Model.File;
import it.univpm.DropboxAnalyzer.Model.Revision;

public class FileFilter implements Filter{
	
	private String fileExtension;
	private boolean onlyDownloadable;

	private Vector<File> files;

	
	
	public FileFilter(Vector<File> files) {
		this.files=files;
	}
	
	
	public Vector<File> filter() {
		if(fileExtension != null) files.removeIf(notRightExtension());
		if(onlyDownloadable != false) files.removeIf(isNotDownloadable());
				
		return files;		
		}
	
	private Predicate<File> notRightExtension(){
		//se l'estensione del file non è la stessa del filtro, la elimino
		return p -> (!p.getExtension().equals(fileExtension));
	}

	private Predicate<File> isNotDownloadable(){
		//se getIsDownloadable mi ritorna falso, lo elimino
		return p -> (!p.getIsDownloadable());
	}
	
	

	
	//getter e setter
	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public boolean isOnlyDownloadable() {
		return onlyDownloadable;
	}

	public void setOnlyDownloadable(boolean onlyDownloadable) {
		this.onlyDownloadable = onlyDownloadable;
	}

	public Vector<File> getFiles() {
		return files;
	}

	public void setFiles(Vector<File> files) {
		this.files = files;
	}
	
	
}
