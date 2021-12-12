package it.univpm.DropboxAnalyzer.Statistics;

import java.util.Calendar;
import java.util.Vector;

import it.univpm.DropboxAnalyzer.Model.Revision;

public class Statistics {
		
	//mi ritorna quante ore, in media, viene effettuata una revisione di questo file
	public double getHourPerRevision (Vector<Revision> revisions) {
		
		Long somma=(long) 0;
		Long delta=(long) 0;
		Long deltaHour=(long) 0;
		Calendar thisDate=null;
		Calendar prevDate=null;
		
		for (Revision revision : revisions) {
			thisDate=revision.getLastClientModify();
			if(revision != 0) {
				//calcolo delta in millisecondi
				delta= thisDate.getTimeInMillis()-prevDate.getTimeInMillis(); 
				//conversione in ore
				deltaHour=delta/1000/60/60; 
				somma += deltaHour;
			}
			prevDate = thisDate;
		}
		return somma/revisions.size();;
		
	}
}
