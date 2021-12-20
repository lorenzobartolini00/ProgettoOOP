package it.univpm.DropboxAnalyzer.statistics;

import java.util.Map;

import org.json.JSONObject;

import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;
import it.univpm.DropboxAnalyzer.filter.RevisionFilter;

/**
 * Le classi che implementano questa interfaccia si occupano di generare statistiche
 * sotto forma di mappa, in cui ogni chiave rappresenta il tipo di statistica e come valore 
 * una o più statistiche
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 */
public interface Statistics {

	/**
	 * Metodo che calcola ogni statistica prevista dalla classe e ne salva il valore negli attributi
	 * della classe stessa
	 */
	public void updateStatistics();
	/**
	 * Aggiunge alla mappa passata una nuova chiave contenente la statistica desiderata
	 * @param data Mappa
	 * @return Mappa aggiornata con la nuova statistica
	 */
	public Map<String, Object> addStatistic(Map<String, Object> data);
}
