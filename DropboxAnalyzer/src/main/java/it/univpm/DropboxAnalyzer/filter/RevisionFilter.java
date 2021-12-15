package it.univpm.DropboxAnalyzer.filter;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Vector;

import org.json.JSONObject;

import it.univpm.DropboxAnalyzer.Model.Revision;

public class RevisionFilter implements Filter{
	private Long periodOfTime;
	private String fileExtension;
	private boolean onlyDownloadable;
	private Integer revisionsThreshold;
	
	private Vector<Revision> revisions;
	private Vector<Revision> filteredRevisions;
	
	
	//questo metodo mi deve restituire un lista di oggetti filtrati
	@Override
	public Vector<Revision> filter() {
		
		JSONObject jo= new JSONObject();
		
			for(Revision revision: revisions) {
				
				//vado a vedere se mi viene richiesto un periodo temporale
				if(periodOfTime!=null) {
					
					//vedo qual'è la data attuale
					 LocalDate todaysDate = LocalDate.now();
					 
					 //vado a prendere la data attuale in millisecondi
					 Long todaysDateinMillis=todaysDate.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
					
					 //se la differenza tra la data attuale e la data dell'ultima modifica è minore del filtro, allora aggiungo la revisione al vettore filteredRevision
					 if(todaysDateinMillis-revision.getLastClientModify().getTimeInMillis()<periodOfTime) {
						 filteredRevisions.add(revision);
					 }
					 
				}
				
				
				//vado a vedere se onlyDownloadable mi viene richiesto come filtro
				if(onlyDownloadable) {
					if(revision.getIsDownloadable()) {
						filteredRevisions.add(revision);
					}
				}
				
				//vado a filtrare gli elementi per dimensione (piazzo una soglia)
				if (revisionsThreshold!=null) {
					
					//controllo che il file non superi la soglia richiesta
					if(revision.getSize()-revisionsThreshold>=0) {
						filteredRevisions.add(revision);
					}
				}
			}
			
			return filteredRevisions;		
		}
			
	
	
	public RevisionFilter(long periodOfTime, String fileExtension, boolean onlyDownloadable, int revisionsThreshold) {
		this.periodOfTime = periodOfTime;
		this.fileExtension = fileExtension;
		this.onlyDownloadable = onlyDownloadable;
		this.revisionsThreshold = revisionsThreshold;
	}
	public RevisionFilter(long periodOfTime, String fileExtension, boolean onlyDownloadable) {
		this.periodOfTime = periodOfTime;
		this.fileExtension = fileExtension;
		this.onlyDownloadable = onlyDownloadable;
		this.revisionsThreshold = null;
	}
	public RevisionFilter(long periodOfTime, String fileExtension) {
		this.periodOfTime = periodOfTime;
		this.fileExtension = fileExtension;
		this.onlyDownloadable = false;
		this.revisionsThreshold = null;
	}
	public RevisionFilter(long periodOfTime) {
		this.periodOfTime = periodOfTime;
		this.fileExtension = ".all";
		this.onlyDownloadable = false;
		this.revisionsThreshold = null;
	}
	public RevisionFilter() {
		this.periodOfTime = null;
		this.fileExtension = ".all";
		this.onlyDownloadable = false;
		this.revisionsThreshold = null;
	}
	public long getPeriodOfTime() {
		return periodOfTime;
	}
	public void setPeriodOfTime(long periodOfTime) {
		this.periodOfTime = periodOfTime;
	}
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
	public int getRevisionsThreshold() {
		return revisionsThreshold;
	}
	public void setRevisionsThreshold(int revisionsThreshold) {
		this.revisionsThreshold = revisionsThreshold;
	}
	
	
	
}
