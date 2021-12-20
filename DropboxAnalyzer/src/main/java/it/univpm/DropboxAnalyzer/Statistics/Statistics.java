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
	 * Metodo che chiama i setter degli attributi della classe
	 */
	public void updateStatistics();
	/**
	 * Aggiunge alla mappa passata una nuova mappa chiave-valore che associa il nome della statistica ai suoi valori
	 * @param data Mappa
	 * @return Mappa chiave-valore
	 */
	public Map<String, Object> addStatistic(Map<String, Object> data);
}
