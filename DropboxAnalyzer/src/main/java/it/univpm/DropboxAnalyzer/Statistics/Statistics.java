package it.univpm.DropboxAnalyzer.Statistics;

import java.util.Calendar;
import java.util.Vector;

import it.univpm.DropboxAnalyzer.Model.Revision;

public class Statistics {

	//devo implementare un metodo che restituisce una lista di revisioni per il Content desiderato
	
	public Vector<Revision> getRevisionFromContent(Content content){
		
	}
	
	public double getHourPerRevision (Vector<Revision> revisions) {
		
		Long somma=(long) 0;
		Long delta=(long) 0;
		Long deltaHour=(long) 0;
		Calendar thisDate=null;
		Calendar prevDate=null;
		
		for (Revision revision : revisions) {
			thisDate=revision.getLastClientModify();
			if(revision.get() != 0) {
				//calcolo delta in millisecondi
				delta= thisDate.getTimeInMillis()-prevDate.getTimeInMillis(); 
				//conversione in ore
				deltaHour=delta/1000/60/60; 
				somma += deltaHour;
			}
		}
		return 0;
		
	}
}
