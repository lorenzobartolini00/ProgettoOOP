package it.univpm.DropboxAnalyzer.statistics;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;
import it.univpm.DropboxAnalyzer.model.Revision;

/**
 * Classe che estende {@link RevisionStatistics}
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 */
public class RevisionSizeStatistics extends RevisionStatistics implements Statistics{
	private long averageSizeIncrementPerRevision;
	private double averageSizePercentageIncrementPerRevision;
	private long absoluteSizeIncrement;
	private double absoluteSizePercentageIncrement;
	
	/**
	 * Costruttore
	 * @param revisions Vettore delle revisioni
	 */
	public RevisionSizeStatistics(Vector<Revision> revisions) {
		super(revisions);
		updateStatistics();
	}
	
	@Override
	public void updateStatistics() {
		setAverageSizeIncrementPerRevision(getAverageSizeIncrementPerRevision());
		setAverageSizePercentageIncrementPerRevision(getAverageSizePercentageIncrementPerRevision());
		setAbsoluteSizeIncrement(getAbsoluteSizeIncrement());
		setAbsoluteSizePercentageIncrement(getAbsoluteSizePercentageIncrement());
	}
	
	@Override
	public Map<String, Object> addStatistic(Map<String, Object> data) {
		Map<String, Object> sizeData = new HashMap<String, Object>();
			
		sizeData.put("average_size_increment_per_revision", Parser.humanReadableBytes(this.averageSizeIncrementPerRevision));
		sizeData.put("average_size_percentage_increment_per_revision", Parser.toPercentage(Parser.round(averageSizePercentageIncrementPerRevision, 2)));
		sizeData.put("absolute_size_increment", Parser.humanReadableBytes(absoluteSizeIncrement));
		sizeData.put("absolute_size_percentage_increment", Parser.toPercentage(Parser.round(absoluteSizePercentageIncrement, 2)));
		data.put("size_statistics", sizeData);
			
		return data;
	}
	
	public long getAverageSizeIncrementPerRevision() {
		return (long) getAverage(revisions, "getSize", false);
	}

	public void setAverageSizeIncrementPerRevision(long sizeIncrementalAbsolute) {
		this.averageSizeIncrementPerRevision = sizeIncrementalAbsolute;
	}

	public double getAverageSizePercentageIncrementPerRevision() {
		return getAverage(revisions, "getSize", true);
	}

	public void setAverageSizePercentageIncrementPerRevision(double sizeIncrementalPercentage) {
		this.averageSizePercentageIncrementPerRevision = sizeIncrementalPercentage;
	}

	public long getAbsoluteSizeIncrement() {
		int index = revisions.size();
		if(index != 0)
		{
			return revisions.get(0).getSize() - revisions.get(index-1).getSize();
		}
		else
		{
			return 0;
		}
		
	}

	public void setAbsoluteSizeIncrement(long absoluteSizeIncrement) {
		this.absoluteSizeIncrement = absoluteSizeIncrement;
	}

	public double getAbsoluteSizePercentageIncrement() {
		int index = revisions.size();
		if(index != 0)
		{
			double delta=revisions.get(0).getSize() - revisions.get(index-1).getSize(); //dimensione finale meno iniziale
			return delta/revisions.get(index-1).getSize()*100; //incremento percentuale
		}
		else
		{
			return 0;
		}
		
	}

	public void setAbsoluteSizePercentageIncrement(double totalSizeIncrementalPercentage) {
		this.absoluteSizePercentageIncrement = totalSizeIncrementalPercentage;
	}

}
