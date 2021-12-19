package it.univpm.DropboxAnalyzer.filter;

import java.util.Map;
import java.util.Vector;

import org.json.JSONObject;

import it.univpm.DropboxAnalyzer.Model.Revision;
import it.univpm.DropboxAnalyzer.configuration.Configuration;
import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;

/**
 * Interfaccia dei filtri
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 */
public interface Filter {
	
	/**
	 * Metodo che si occupa di appicari i filtri
	 */
	public void applyFilters();
	/**
	 * Metodo che si occupa di settare i filtri
	 * @param parameters Map con all'interno i parametri di configurazione
	 */
	public void setFilters(Map<String,  Object> parameters);
}
