package it.univpm.DropboxAnalyzer.filter;

import java.util.Map;
import java.util.Vector;

import org.json.JSONObject;

import it.univpm.DropboxAnalyzer.Model.Revision;
import it.univpm.DropboxAnalyzer.configuration.Configuration;
import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;

/**
 * Le classi che implementano questa interfaccia devono prevedere un attributo contenente
 * una lista di oggetti da filtrare, un metodo per settare i parametri in base ai quali
 * verrà filtrata la lista e un altro che esegue la rimozione dalla lista degli elementi
 * non desiderati.
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 */
public interface Filter {
	/**
	 * Questo metodo si occupa di settare gli attributi della classe, in base
	 * ai quali verrà filtrata la lista
	 * @param parameters Map con all'interno i parametri di configurazione
	 */
	public void setFilters(Map<String,  Object> parameters);
	
	/**
	 * Questo metodo si occupa di rimuovere gli elementi indesiderati dalla lista
	 */
	public void applyFilters();
	
}
