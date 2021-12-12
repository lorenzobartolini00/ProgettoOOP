package it.univpm.DropboxAnalyzer.Statistics;

import java.util.Calendar;
import java.util.Vector;
import it.univpm.DropboxAnalyzer.Model.Revision;

public class Statistics {
	private double hourPerRevision;
	private Vector<Revision> revisions;
	
	public Statistics(Vector<Revision> revisions)
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
	
	
	
}
