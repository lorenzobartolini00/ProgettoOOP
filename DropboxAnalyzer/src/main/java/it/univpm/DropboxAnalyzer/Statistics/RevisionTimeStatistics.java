package it.univpm.DropboxAnalyzer.Statistics;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import it.univpm.DropboxAnalyzer.Model.Revision;
import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;

/**
 * Classe che estende {@link RevisionStatistics} 
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 */
public class RevisionTimeStatistics extends RevisionStatistics implements Statistics{
	protected double hourPerRevision;
	
	/**
	 * Costruttore
	 * @param revisions Vettore delle revisioni
	 */
	public RevisionTimeStatistics(Vector<Revision> revisions) {
		super(revisions);
		updateStatistics();
	}
	
	@Override
	public void updateStatistics() {
		setHourPerRevision(getHourPerRevision());
	}
	
	@Override
	public Map<String, Object> addStatistic(Map<String, Object> data) {
		Map<String, Object> timeData = new HashMap<String, Object>();
			
		timeData.put("hour_per_revision", Parser.humanReadableTime(this.hourPerRevision));
		data.put("time_statistics", timeData);
			
		return data;
	}
	
	//getter e setter
	public double getHourPerRevision() {
		double timeInMilliseconds = getAverage(revisions, "getLastClientModifyInMilliseconds", false);
		return toHour(timeInMilliseconds);
	}

	public void setHourPerRevision(double hourPerRevision) {
		this.hourPerRevision = hourPerRevision;
	}

}
