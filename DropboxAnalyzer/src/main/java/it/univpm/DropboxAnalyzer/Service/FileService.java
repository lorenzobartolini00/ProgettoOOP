package it.univpm.DropboxAnalyzer.Service;


import java.util.Vector;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import it.univpm.DropboxAnalyzer.Model.*;

/**Interfaccia che gestisce i dati del package Model. Essa comunica con le API, che sono al livello superiore.
 * Questa classe descrive le proprietà di ogni file
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 */
@Service
public interface FileService {
	
	/**
	 * Metodo che da un JSONObject mi restituisce un vettore di revisioni
	 * @param jsonObjectRevisions JSONObject delle revisioni
	 * @return vettore di revisioni
	 */
	public Vector<Revision> getRevisionList(JSONObject jsonObjectRevisions);
	/**
	 * Metodo che da un JSONObject mi restituisce un vettore di contenuti(file, folder)
	 * @param jsonObjectFolders JSONObject di contenti
	 * @return vettore di contenuti 
	 */
	public Vector<Content> getContentList(JSONObject jsonObjectFolders);
	public Content getMetadata(JSONObject jsonObjectContent); //non è un vettore perchè mi restituisce ciò che riguarda un solo file
}
