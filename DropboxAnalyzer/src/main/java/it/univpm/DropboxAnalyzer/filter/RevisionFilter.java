package it.univpm.DropboxAnalyzer.filter;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Vector;
import java.util.function.Predicate;

import org.json.JSONObject;

import it.univpm.DropboxAnalyzer.Model.Revision;

public class RevisionFilter implements Filter{
	private Long periodOfTime;
	private Integer revisionsThreshold;
	
	private Vector<Revision> revisions;
	
	
	//questo metodo mi deve restituire un lista di revisioni filtrate
	
	@Override
	public Vector<Revision> filter() {
		if(periodOfTime != null) revisions.removeIf(notInRange());
		if(revisionsThreshold != null) revisions.removeIf(aboveThreshold());
				
		return revisions;		
		}
	
	//Metodi che restituiscono il filtro da passare come parametro al metodo RemoveIf()
	private Predicate<Revision> notInRange() {
		//vedo qual è la data attuale
		 LocalDate todaysDate = LocalDate.now();
		 
		 //vado a prendere la data attuale in millisecondi
		 Long todaysDateinMillis=todaysDate.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
		 
		 //L'elemento viene rimosso se se la seguente condizione è verificata, ovvero se la distanza temporale tra la data odierna
		 //e quella della modifica è maggiore di un certo range.
        return p -> (( todaysDateinMillis - p.getLastClientModifyInMilliseconds() ) > periodOfTime) && periodOfTime!=null;
    }
	
	private Predicate<Revision> aboveThreshold() {
		//L'elemento viene rimosso se se la seguente condizione è verificata,
		//ovvero se la dimensione dell'elemento è maggiore o uguale alla soglia
        return p -> (p.getSize() - revisionsThreshold >= 0) && revisionsThreshold!=null;
    }
	
	
	public RevisionFilter(Vector<Revision> revisions, long periodOfTime, int revisionsThreshold) {
		this.revisions = revisions;
		this.periodOfTime = periodOfTime;
		this.revisionsThreshold = revisionsThreshold;
	}
	
	public RevisionFilter(Vector<Revision> revisions, long periodOfTime) {
		this.revisions = revisions;
		this.periodOfTime = periodOfTime;
		this.revisionsThreshold = null;
	}
	
	public RevisionFilter(Vector<Revision> revisions, int revisionsThreshold) {
		this.revisions = revisions;
		this.periodOfTime = null;
		this.revisionsThreshold = revisionsThreshold;
	}
	
	public RevisionFilter(Vector<Revision> revisions) {
		this.revisions = revisions;
		this.periodOfTime = null;
		this.revisionsThreshold = null;
	}
	
	public long getPeriodOfTime() {
		return periodOfTime;
	}
	public void setPeriodOfTime(long periodOfTime) {
		this.periodOfTime = periodOfTime;
	}
	public int getRevisionsThreshold() {
		return revisionsThreshold;
	}
	public void setRevisionsThreshold(int revisionsThreshold) {
		this.revisionsThreshold = revisionsThreshold;
	}
	
	/*
	 * for(Revision revision: revisions) {
				
				//vado a vedere se mi viene richiesto un periodo temporale
				if(periodOfTime!=null) {
					
					//vedo qual è la data attuale
					 LocalDate todaysDate = LocalDate.now();
					 
					 //vado a prendere la data attuale in millisecondi
					 Long todaysDateinMillis=todaysDate.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
					
					 //se la differenza tra la data attuale e la data dell'ultima modifica è minore del filtro, allora aggiungo la revisione al vettore filteredRevision
					 if(todaysDateinMillis-revision.getLastClientModify().getTimeInMillis()<periodOfTime) {
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
	 */
	
}
