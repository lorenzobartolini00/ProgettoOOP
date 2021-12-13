package it.univpm.DropboxAnalyzer.Statistics;

import java.util.Vector;

import it.univpm.DropboxAnalyzer.Model.File;

public class FileStatistics {

	private Vector<File> files;
	private double isDownloadableEverage;
	
	public FileStatistics(Vector<File> files) {
		this.files=files;
		setIsDownloadableEverage();
	}
	
	//setter che mi calcola quanti file in media sono scaricabili
		 public void setIsDownloadableEverage() {
			 
			 //in questo caso userò la chiamata ListFolder
			 
			 int somma=0;
			 for(File file:files) {
				 if(files.indexOf(file)!=0) {
					 if(file.getIsDownloadable().equals("true")) {
						 somma += 1;
					 }
				 }
			 }
			 this.isDownloadableEverage=somma/files.size();
		 }
}
