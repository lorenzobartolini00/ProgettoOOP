package it.univpm.DropboxAnalyzer.filter;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Vector;
import java.util.function.Predicate;

import it.univpm.DropboxAnalyzer.Model.Revision;
import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;

public class RevisionFilter extends FilterImpl implements Filter{
	private Long periodOfTime;
	
	private Vector<Revision> revisions;
	
	/**
	 * Metodo che mi deve restituisce una lista di revisioni filtrate
	 */
	@Override
	public void setFilters(Map<String, Object> parameters){
		if(parameters.containsKey("filters"))
		{
			super.setFilters(parameters);
			@SuppressWarnings("unchecked")
			Map<String, Object> filters = (Map<String, Object>) parameters.get("filters");
			
			if(filters.containsKey("time_filter"))
			{
				this.setPeriodOfTime((String)filters.get("time_filter"));
			}
		}
	}
	
	
	/**
	 * Metodo che applica i  filtri
	 */
	@Override
	public void applyFilters() {
		if(maxSize != null) revisions.removeIf(aboveThreshold());
		if(minSize != null) revisions.removeIf(belowThreshold());
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
		//ovvero se la dimensione dell'elemento è maggiore alla soglia
        return p -> (p.getSize() > maxSize);
    }
	
	private Predicate<Revision> belowThreshold() {
		//L'elemento viene rimosso se se la seguente condizione è verificata,
		//ovvero se la dimensione dell'elemento è minore o uguale alla soglia
        return p -> (p.getSize() <= minSize);
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
	public void setPeriodOfTime(String periodOfTime) {
		if(periodOfTime != null)
		{
			switch(periodOfTime)
			{
			case "last_week":
			{
				this.setPeriodOfTime(604800000);
				break;
			}
			case "last_day":
			{
				this.setPeriodOfTime(86400000);
				break;
			}
			case "last_hour":
			{
				this.setPeriodOfTime(3600000);
				break;
			}
			}
		}
	}

	public Vector<Revision> getRevisions() {
		return revisions;
	}
	public void setRevisions(Vector<Revision> revisions) {
		this.revisions = revisions;
	}
	
	
}
