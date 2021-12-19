package it.univpm.DropboxAnalyzer.Statistics;

import java.util.Map;

import org.json.JSONObject;

import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;
import it.univpm.DropboxAnalyzer.filter.RevisionFilter;

/**
 * Interfaccia che si occupa di fare statistiche
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 */
public interface Statistics {

	/**
	 * 
	 */
	public void updateStatistics();
	/**
	 * 
	 * @param data
	 * @return
	 */
	public Map<String, Object> addStatistic(Map<String, Object> data);
}
