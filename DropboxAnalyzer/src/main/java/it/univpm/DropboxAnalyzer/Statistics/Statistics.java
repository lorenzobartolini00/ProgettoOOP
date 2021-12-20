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
	 * Aggiunge alla mappa passata una nuova chiave contenente la statistica desiderata
	 * @param data Mappa
	 * @return Mappa aggiornata con la nuova statistica
	 */
	public Map<String, Object> addStatistic(Map<String, Object> data);
}
