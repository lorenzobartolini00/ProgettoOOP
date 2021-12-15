package it.univpm.DropboxAnalyzer.Statistics;

import java.util.Vector;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.TransformerUtils;

import it.univpm.DropboxAnalyzer.Model.Revision;

public class RevisionStatistics implements Statistics{
	private double hourPerRevision;
	private double sizeIncrementalAbsolute;
	private double sizeIncrementalPercentage;
	private double totalSizeIncrementalPercentage;
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
		setSizeIncrementalAbsolute(getSizeIncrementalAbsolute());
		setSizeIncrementalPercentage(getSizeIncrementalPercentage());
		setTotalSizeIncrementalPercentage(getTotalSizeIncrementalPercentage());
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

	public double getSizeIncrementalAbsolute() {
		return getAverage(revisions, "getSize", false);
	}

	public void setSizeIncrementalAbsolute(double sizeIncrementalAbsolute) {
		this.sizeIncrementalAbsolute = sizeIncrementalAbsolute;
	}

	public double getSizeIncrementalPercentage() {
		return getAverage(revisions, "getSize", true);
	}

	public void setSizeIncrementalPercentage(double sizeIncrementalPercentage) {
		this.sizeIncrementalPercentage = sizeIncrementalPercentage;
	}

	public double getTotalSizeIncrementalPercentage() {
		int index = revisions.size();
		double delta=revisions.get(0).getSize() - revisions.get(index-1).getSize(); //dimensione finale meno iniziale
		return delta/revisions.get(index-1).getSize()*100; //incremento percentuale
	}

	public void setTotalSizeIncrementalPercentage(double totalSizeIncrementalPercentage) {
		this.totalSizeIncrementalPercentage = totalSizeIncrementalPercentage;
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
			//Salva dentro il vettore values tutti i valori restituiti dal metodo methodName
			Vector<Long> values = new Vector<Long>(CollectionUtils.collect(args, TransformerUtils.invokerTransformer(methodName)) );
			
			//Poichè serve calcolare la media di questi valori, uso un for each per sommare le differenze tra tutti i valori
			long sum = 0;
			long delta = 0;
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



	private double toHour(double timeInMilliseconds) {
		return timeInMilliseconds/1000/60/60;
	}
	
}
