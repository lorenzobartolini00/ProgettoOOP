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
	public void setFilters(JSONObject jsonFilters) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void applyFilters() {
		if(revisionsThreshold != null) revisions.removeIf(aboveThreshold());
		if(periodOfTime != null) revisions.removeIf(notInRange());	
	}
	
	//Metodi che restituiscono il filtro da passare come parametro al metodo RemoveIf()
	private Predicate<Revision> notInRange() {
		//vedo qual è la data attuale
		 LocalDate todaysDate = LocalDate.now();
		 
		 //vado a prendere la data attuale in millisecondi
		 Long todaysDateinMillis=todaysDate.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
		 
		 //L'elemento viene rimosso se se la seguente condizione è verificata, ovvero se la distanza temporale tra la data odierna
		 //e quella della modifica è maggiore di un certo range.
        return p -> (( todaysDateinMillis - p.getLastClientModifyInMilliseconds() ) > periodOfTime);
    }
	
	private Predicate<Revision> aboveThreshold() {
		//L'elemento viene rimosso se se la seguente condizione è verificata,
		//ovvero se la dimensione dell'elemento è maggiore o uguale alla soglia
        return p -> ((p.getSize() - revisionsThreshold) >= 0);
    }
	
	
	//Getters e setters
	
	public RevisionFilter(Vector<Revision> revisions) {
		this.revisions = revisions;
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
	public Vector<Revision> getRevisions() {
		return revisions;
	}
	public void setRevisions(Vector<Revision> revisions) {
		this.revisions = revisions;
	}
	
	
}
