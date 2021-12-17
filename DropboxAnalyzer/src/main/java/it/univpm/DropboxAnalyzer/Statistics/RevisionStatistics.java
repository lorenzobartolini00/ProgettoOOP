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

public class RevisionStatistics implements Statistics{
	private double hourPerRevision;
	private long averageSizeIncrementPerRevision;
	private double averageSizePercentageIncrementPerRevision;
	private double absoluteSizeIncrement;
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
	
	
	/**
	 * Metodo che mi permette di convertire parametri in stringa
	 * @param numero da convertire in stringa
	 * @return numero convertito in stringa
	 */
	 private String doubleToString(double numero){
	  	String s=String.valueOf(numero); 
	  	return s;
	 }
	 
	
	/**
	 * Metodo che mi permette di prendere solo le prime due cifre dopo la virgola del parametro double inserito
	 * @param numero double da arrotondare
	 */
	private double roudDouble(double numero){ 
	  	numero = Math.round(numero*100.0)/100.0;
	  	return numero;
	  }
	
	
	
	private String toHours(double ore) {
		
		int oreInt = (int) ore;
		double minDouble = (ore-oreInt)*60;
		int minInt = (int) minDouble;
		return oreInt+"h,"+minInt+"m";
		
	}

	
	/**
	 * Metodo che mi permette di convertire i bytes in un formato leggibile dall'uomo
	 * @param bytes da convertire
	 * @return bytes convertiti sotto forma di stringa
	 */
	private static String humanReadableByteCountBin(long bytes) {
    long absB = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
    if (absB < 1024) {
        return bytes + " B";
    }
    long value = absB;
    CharacterIterator ci = new StringCharacterIterator("KMGTPE");
    for (int i = 40; i >= 0 && absB > 0xfffccccccccccccL >> i; i -= 10) {
        value >>= 10;
        ci.next();
    }
    value *= Long.signum(bytes);
    return String.format("%.1f %ciB", value / 1024.0, ci.current());
}
	 
	public Map<String, Object> toMap() {
		
		JSONObject jo=new JSONObject();
		
		String hpr=toHours(hourPerRevision);
		jo.put("hour_per_revision", hpr);
		
		String asipr =humanReadableByteCountBin(averageSizeIncrementPerRevision);
		jo.put("average_size_increment_per_revision", asipr);
		
		String aspipr= doubleToString(roudDouble(averageSizePercentageIncrementPerRevision));
		jo.put("average_size_percentage_increment_per_revision", aspipr);
		
		jo.put("absolute_size_increment", this.absoluteSizeIncrement);
		
		String aspi = doubleToString(roudDouble(absoluteSizePercentageIncrement));
		jo.put("ablolute_size_percentage_increment", aspi);
		
		jo.put("numbers_of_revisions", this.numberOfRevisons);
		
		Map<String, Object> result=null;
		
		try {
			result= new ObjectMapper().readValue(jo.toString(), HashMap.class);
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		return result;
	}
	
}
