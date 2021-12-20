package it.univpm.DropboxAnalyzer.service;


import java.util.Vector;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import it.univpm.DropboxAnalyzer.model.*;

/**Le classi che implementano questa interfaccia si occupano di costruire oggetti({@link Content} e {@link Revision}) sulla base
 * delle informazioni contenute nel file Json passato 
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 */
@Service
public interface FileService {
	
	/**
	 * Metodo che a partire da un JSONObject costruisce un vettore di {@link Revision}
	 * @param jsonObjectRevisions JSONObject delle revisioni
	 * @return Vettore di revisioni
	 */
	public Vector<Revision> getRevisionList(JSONObject jsonObjectRevisions);
	/**
	 * Metodo che a partire da un JSONObject costruisce un vettore di {@link Content}({@link File} o {@link Folder})
	 * @param jsonObjectFolders JSONObject di contenuti
	 * @return Vettore di contenuti 
	 */
	public Vector<Content> getContentList(JSONObject jsonObjectFolders);
	public Content getMetadata(JSONObject jsonObjectContent);
}
