package it.univpm.DropboxAnalyzer.Statistics;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.TransformerUtils;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.univpm.DropboxAnalyzer.Model.Revision;
import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;

public class RevisionStatistics implements Statistics{
	private double hourPerRevision;
	private long averageSizeIncrementPerRevision;
	private double averageSizePercentageIncrementPerRevision;
	private long absoluteSizeIncrement;
	private double absoluteSizePercentageIncrement;
	private int numberOfRevisons;
	
	private Vector<Revision> revisions;
	
	public RevisionStatistics(Vector<Revision> revisions)
	{
		this.revisions = revisions;
		updateStatistics();
	}

	@Override
	public void updateStatistics() {
		setHourPerRevision(getHourPerRevision());
		setAverageSizeIncrementPerRevision(getAverageSizeIncrementPerRevision());
		setAverageSizePercentageIncrementPerRevision(getAverageSizePercentageIncrementPerRevision());
		setAbsoluteSizeIncrement(getAbsoluteSizeIncrement());
		setAbsoluteSizePercentageIncrement(getAbsoluteSizePercentageIncrement());
		setNumberOfRevisons(getNumberOfRevisons());
	}
	
	//Getters e setters
	
	public double getHourPerRevision() {
		double timeInMilliseconds = getAverage(revisions, "getLastClientModifyInMilliseconds", false);
		return toHour(timeInMilliseconds);
	}

	public void setHourPerRevision(double hourPerRevision) {
		this.hourPerRevision = hourPerRevision;
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

	public int getNumberOfRevisons() {
		return revisions.size();
	}

	public void setNumberOfRevisons(int numberOfRevisons) {
		this.numberOfRevisons = numberOfRevisons;
	}
	
	//Metodi ausiliari
	private double getAverage(Vector<Revision> args, String methodName, boolean isPercentage)
	{
		if(args.size() != 0)
		{
			//Salva dentro il vettore values tutti i valori restituiti dal metodo methodName
			Vector<Long> values = new Vector<Long>(CollectionUtils.collect(args, TransformerUtils.invokerTransformer(methodName)) );
			
			//Poichè serve calcolare la media di questi valori, uso un for each per sommare le differenze tra tutti i valori
			double sum = 0;
			double delta = 0;
			for(Long value : values) 
			{
				if(values.indexOf(value) != 0)
				{
					//Il primo elemento, essendo il più recente, è quello che ha dimensione maggiore. In questo modo sum è positivo
					delta = values.get(values.indexOf(value) - 1) - value;
					if(isPercentage)
					{
						delta = (delta / value) * 100;
					}
					sum += delta;
				}
			}
			return sum/values.size();
		}
		else
		{
			return 0;
		}
			
	}
	/**
	 * Metodo che da millisecondi mi restituisce le ore
	 * @param timeInMilliseconds
	 * @return tempo convertito in ore
	 */
	private double toHour(double timeInMilliseconds) {
		return timeInMilliseconds/1000/60/60;
	}

	
	public Map<String, Object> formatData(String statisticType) throws BadFormatException {
		if((statisticType.equals("time")) || (statisticType.equals("size")) || (statisticType.equals("both")))
		{
			Map<String, Object> data = new HashMap<String, Object>();
			Map<String, Object> sizeData = new HashMap<String, Object>();
			Map<String, Object> timeData = new HashMap<String, Object>();
			Map<String, Object> generalData = new HashMap<String, Object>();
			
			generalData.put("numbers_of_revisions", this.numberOfRevisons);
			
			switch(statisticType)
			{
			case "time":
			{
				timeData.put("hour_per_revision", Parser.humanReadableTime(this.hourPerRevision));
				data.put("time_statistics", timeData);
				break;
			}
			case "size":
			{
				sizeData.put("average_size_increment_per_revision", Parser.humanReadableBytes(this.averageSizeIncrementPerRevision));
				sizeData.put("average_size_percentage_increment_per_revision", Parser.toPercentage(Parser.round(averageSizePercentageIncrementPerRevision, 2)));
				sizeData.put("absolute_size_increment", Parser.humanReadableBytes(absoluteSizeIncrement));
				sizeData.put("absolute_size_percentage_increment", Parser.toPercentage(Parser.round(absoluteSizePercentageIncrement, 2)));
				data.put("size_statistics", sizeData);
				break;
			}
			case "both":
			{
				timeData.put("hour_per_revision", Parser.humanReadableTime(this.hourPerRevision));
				sizeData.put("average_size_increment_per_revision", Parser.humanReadableBytes(this.averageSizeIncrementPerRevision));
				sizeData.put("average_size_percentage_increment_per_revision", Parser.toPercentage(Parser.round(averageSizePercentageIncrementPerRevision, 2)));
				sizeData.put("absolute_size_increment", Parser.humanReadableBytes(absoluteSizeIncrement));
				sizeData.put("absolute_size_percentage_increment", Parser.toPercentage(Parser.round(absoluteSizePercentageIncrement, 2)));
				data.put("size_statistics", sizeData);
				data.put("time_statistics", timeData);
				break;
			}
			}
			
			data.put("generic", generalData);
			
			return data;
		}
		else
		{
			throw new BadFormatException("url", statisticType, "is not a valid statistic type");
		}
		
		
	}
	
}
