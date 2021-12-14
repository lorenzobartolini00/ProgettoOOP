package it.univpm.DropboxAnalyzer.Statistics;

import java.util.Calendar;
import java.util.Vector;

import org.json.JSONObject;

import it.univpm.DropboxAnalyzer.Model.File;
import it.univpm.DropboxAnalyzer.Model.Revision;
import it.univpm.DropboxAnalyzer.filter.RevisionFilter;

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
	
	//percentuale
	
	public double getPercentSize() {
		 Long thisSize=(long) 0;
		 Long prevSize=(long) 0;
		 double somma= 0;
		 Long delta=(long) 0;
		 double incrRelativo=0;
		 double incrPercentuale=0;
		 for(Revision revision: revisions) {
			 thisSize=revision.getSize();
			 if(revisions.indexOf(revision)!=0) {
				 delta=prevSize-thisSize;
				 incrRelativo= delta/thisSize;
				 incrPercentuale= incrRelativo*100;
				 somma += incrPercentuale;
			 }
			 prevSize=thisSize;
		 }
		 return somma/(revisions.size()-1);
	}
	

	//incremento percentuale della dimensione dalla creazione all'ultima revisione
	
	public double getTotalPercent() {
	int indice=revisions.size();
	double delta=revisions.get(0).getSize()-revisions.get(indice-1).getSize(); //dimensione finale meno iniziale
	return delta/revisions.get(indice-1).getSize()*100; //incremento percentuale
	}
	
	
	//numero totale di revisioni
	
	public int getNumberOfRevision() {
		return revisions.size();
	}
	
	@Override
	public JSONObject getStatistics(RevisionFilter filter) {
		JSONObject statistics=new JSONObject();
		
		//Chiamata del metodo filter.filter() 
		
		statistics.put("average_time_between_each_revision", getHourPerRevision());
		statistics.put("average_size_increment", getSizeAverage());
		statistics.put("totale_percentage_size", getTotalPercent());
		statistics.put("percentage_size", getPercentSize());
		statistics.put("number_of_revisions", getNumberOfRevision());
		
		return statistics;
	}
	
}
