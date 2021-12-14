package it.univpm.DropboxAnalyzer.Statistics;

import java.util.Calendar;
import java.util.Vector;

import org.json.JSONObject;

import it.univpm.DropboxAnalyzer.Model.File;
import it.univpm.DropboxAnalyzer.Model.Revision;

public class RevisionStatistics implements Statistics{
	private Vector<Revision> revisions;
	
	
	public RevisionStatistics(Vector<Revision> revisions)
	{
		this.revisions = revisions;
	}
	
	
	
	private double getHourPerRevision() {
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
		
		return somma/revisions.size();
	}
	
	//mi ritorna ogni quante ore, in media, viene effettuata una revisione di questo file

	private double getSizeAverage(){
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
			 prevSize = thisSize;
		 }
		 return totalSize/revisions.size();
		}

	
	@Override
	public JSONObject getStatistics() {
		JSONObject statistics=new JSONObject();
		
		statistics.put("average_time_between_each_revision", getHourPerRevision());
		statistics.put("average_size_increment", getSizeAverage());
		
		return statistics;
	}
	
}
