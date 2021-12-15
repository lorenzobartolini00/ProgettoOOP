package it.univpm.DropboxAnalyzer.filter;

import java.util.Vector;

import it.univpm.DropboxAnalyzer.Model.File;

public class FileFilter implements Filter{
	
	private String fileExtension;
	private boolean onlyDownloadable;

	private Vector<File> files;
	private Vector<File> filteredFiles;
	
	//questo metodo mi deve restituire un lista di file filtrati
	
	@Override
	public Vector<File> filter() {
		
		
		for(File file: files) {
			
		//vado a vedere se onlyDownloadable mi viene richiesto come filtro
		
			if(onlyDownloadable) {
				if(file.getIsDownloadable()) filteredFiles.add(file);
			}
			
		//vado a filtrare per estensione
			
			if(fileExtension!=null) {
				if(file.getExtension().equals(fileExtension)) filteredFiles.add(file);
			}
	}
		
		return filteredFiles;
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

	public Vector<File> getFilteredFiles() {
		return filteredFiles;
	}

	public void setFilteredFiles(Vector<File> filteredFiles) {
		this.filteredFiles = filteredFiles;
	}
	
	
}
