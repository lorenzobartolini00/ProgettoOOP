package it.univpm.DropboxAnalyzer.filter;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Vector;
import java.util.function.Predicate;

import it.univpm.DropboxAnalyzer.Model.Revision;
import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;

/**
 * Classe che estende FilterImpl e contiene i metodi per il filtraggio delle revisioni
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 */
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
	
	/**
	 * Metodo che restituisce il filtro da passare come parametro al metodo RemoveIf()
	 * L'elemento viene rimosso se la distanza temporale tra la data odierna
	 * e quella della modifica è maggiore di un certo range.
	 * @return Funzione a valore booleana p
	 */
	private Predicate<Revision> notInRange() {
		//vedo qual è la data attuale
		 LocalDate todaysDate = LocalDate.now();
		 
		 //vado a prendere la data attuale in millisecondi
		 Long todaysDateinMillis=todaysDate.atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli();
		 
        return p -> (( todaysDateinMillis - p.getLastClientModifyInMilliseconds() ) > periodOfTime);
    }
	
	/**
	 * Metodo che restituisce il filtro da passare come parametro al metodo RemoveIf()
	 * L'elemento viene rimosso se la dimensione dell'elemento è maggiore alla soglia
	 * @return Funzione a valore booleana p
	 */
	private Predicate<Revision> aboveThreshold() {
		
        return p -> (p.getSize() > maxSize);
    }
	
	/**
	 * Metodo che restituisce il filtro da passare come parametro al metodo RemoveIf()
	 * L'elemento viene rimosso la dimensione dell'elemento è minore o uguale alla soglia
	 * @return Funzione a valore booleana p
	 */
	private Predicate<Revision> belowThreshold() {
		
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
