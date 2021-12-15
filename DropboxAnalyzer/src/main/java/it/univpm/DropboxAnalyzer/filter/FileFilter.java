package it.univpm.DropboxAnalyzer.filter;

import java.util.Vector;

import it.univpm.DropboxAnalyzer.Model.File;

public class FileFilter implements Filter{
	
	private String fileExtension;
	private boolean onlyDownloadable;

	private Vector<File> files;
	private Vector<File> filteredFiles;
	
	//questo metodo mi deve restituire un lista di oggetti filtrati
	
	@Override
	public Vector<File> filter() {
		
		
		for(File file: files) {
			
		//vado a vedere se onlyDownloadable mi viene richiesto come filtro
		
			if(onlyDownloadable) {
			if(file.getIsDownloadable()) {
				filteredFiles.add(file);
			}
		}
	}
		
		return filteredFiles;
	}
}
