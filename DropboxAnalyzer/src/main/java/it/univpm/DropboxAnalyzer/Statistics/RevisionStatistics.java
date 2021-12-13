package it.univpm.DropboxAnalyzer.Statistics;

import java.util.Calendar;
import java.util.Vector;
import it.univpm.DropboxAnalyzer.Model.Revision;

public class RevisionStatistics{
	private double hourPerRevision;
	private Long sizeEverage;
	private Vector<Revision> revisions;
	
	public RevisionStatistics(Vector<Revision> revisions)
	{
		this.revisions = revisions;
		setHourPerRevision();
	}
	
	public double getHourPerRevision()
	{
		return this.hourPerRevision;
	}
	
	//mi ritorna ogni quante ore, in media, viene effettuata una revisione di questo file
	public void setHourPerRevision () {
		Long somma=(long) 0;
		Long delta=(long) 0;
		Long deltaHour=(long) 0;
		Calendar thisDate=null;
		Calendar prevDate=null;
			
		for (Revision revision : revisions) {
			thisDate = revision.getLastClientModify();
			if(revisions.indexOf(revision) != 0)
			{
				//calcolo delta in millisecondi
				delta =  prevDate.getTimeInMillis() - thisDate.getTimeInMillis() ; 
				//conversione in ore
				deltaHour=delta/1000/60/60; 
				somma += deltaHour;
			}
			prevDate = thisDate;
		}
		
		this.hourPerRevision = somma/revisions.size();
	}
	
	public String toString()
	{
		return String.valueOf(this.hourPerRevision);
	}
	
	 
	 //metodo che mi dice di quanto aumenta, in media, la dimensione dalla prima revisione
	 public void sizePerRevision(){
		 Long thisSize=(long) 0;
		 Long prevSize=(long) 0;
		 Long totalSize= (long) 0;
		 Long delta=(long) 0;
		 for(Revision revision:revisions) {
			 thisSize=revision.getSize();
			 if(revisions.indexOf(revision)!=0) {
				 delta = thisSize - prevSize;
				 totalSize += delta;
			 }
			 thisSize=prevSize;
		 }
		 this.sizeEverage=totalSize/revisions.size();
		}
	 
	/*
		//metodo che mi dice quanti utenti ci sono in media per ogni cartella/file nel dropbox
		public void usersPerFile(){
		
			//userò l'id che mi viene fornito per ogni file
			 * 
		}
		
		//metodo che mi calcola quanti file in media sono scaricabili
		 * 
		 * public void mediaIsDownloadable(){
		 * 
		 * }
	 */
	
	
}
