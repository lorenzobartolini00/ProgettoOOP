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

/**
 * Classe che implementa l'interfaccia {@link Statistics}
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 */
public class RevisionStatistics implements Statistics{
	protected int numberOfRevisons;
	
	
	protected Vector<Revision> revisions;
	
	public RevisionStatistics(Vector<Revision> revisions)
	{
		this.revisions = revisions;
		updateStatistics();
	}

	@Override
	public void updateStatistics() {
		setNumberOfRevisons(getNumberOfRevisons());
	}
	
	@Override
	public Map<String, Object> addStatistic(Map<String, Object> data) {
		Map<String, Object> generalData = new HashMap<String, Object>();
			
		generalData.put("numbers_of_revisions", this.numberOfRevisons);
			
		data.put("generic_statistics", generalData);
			
		return data;
	}
	
	//Getters e setters
	public int getNumberOfRevisons() {
		return revisions.size();
	}

	public void setNumberOfRevisons(int numberOfRevisons) {
		this.numberOfRevisons = numberOfRevisons;
	}
	
	//Metodi ausiliari
	protected double getAverage(Vector<Revision> args, String methodName, boolean isPercentage)
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
	protected double toHour(double timeInMilliseconds) {
		return timeInMilliseconds/1000/60/60;
	}
	
}
