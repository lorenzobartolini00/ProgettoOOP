package it.univpm.DropboxAnalyzer.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univpm.DropboxAnalyzer.configuration.ListRevisionsConfiguration;
import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;
import it.univpm.DropboxAnalyzer.model.Content;
import it.univpm.DropboxAnalyzer.model.File;
import it.univpm.DropboxAnalyzer.model.Folder;
import it.univpm.DropboxAnalyzer.model.Revision;

/**Classe che implementa l'interfaccia {@link FileService}
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 */
@Service
public class FileServiceImpl implements FileService {

	@Autowired
	HTTPSRequest httpsReq;
	
	@Override
	public Vector<Revision> getRevisionList(JSONObject jsonObjectRevisions) {
		Vector<Revision> revisionList = new Vector<Revision>();
		
		//Ottengo il jsonArray che contiene la lista delle revisioni per il file di interesse
        JSONArray jsonArrayRevisions = (JSONArray) jsonObjectRevisions.get("entries");
        
        //Per ogni JSONObject dentro il jsonArray estraggo i 3 valori che mi interessano e li uso per inizializzare una nuova
        //istanza della classe Revision
        for(Object jsonObjectRevision : jsonArrayRevisions)
        {
        	String lastClientModify = ((JSONObject) jsonObjectRevision).getString("client_modified");
        	String lastServerModify = ((JSONObject) jsonObjectRevision).getString("server_modified");
        	String revisionId = ((JSONObject) jsonObjectRevision).getString("rev");
        	Long size = ((JSONObject) jsonObjectRevision).getLong("size");
        	Boolean isDownloadable = ((JSONObject) jsonObjectRevision).getBoolean("is_downloadable");

        	Revision revision = new Revision(lastClientModify, lastServerModify, revisionId, size, isDownloadable);
        	
        	revisionList.add(revision);
        }
		return revisionList;
		
			
		}

	@Override
	public Vector<Content> getContentList(JSONObject jsonObjectFolders, Map<String, Object> parameters) {
		Vector<Content> contentList = new Vector<Content>();
		
		
		//Ottengo il jsonArray che contiene la lista delle revisioni per il file di interesse
        JSONArray jsonArrayFolders = (JSONArray) jsonObjectFolders.get("entries");
        
        for(Object jsonObjectContent : jsonArrayFolders) {
        	String name=((JSONObject) jsonObjectContent).getString("name");
        	String pathLower=((JSONObject) jsonObjectContent).getString("path_lower");
        	String pathDisplay=((JSONObject) jsonObjectContent).getString("path_display");
        	String id=((JSONObject) jsonObjectContent).getString("id");
        	
        	Content content = null;
        	
        	//In base all'attributo tag, decido se si tratta di un File o un Folder
        	if(((JSONObject) jsonObjectContent).getString(".tag").equals("file"))
        	{
        		//TODO: Gestire eccezioni
        		Long size = ((JSONObject) jsonObjectContent).getLong("size");
        		boolean isDownloadable = ((JSONObject) jsonObjectContent).getBoolean("is_downloadable");
        		Vector<Revision> revisions = getRevisionsFromFile(pathDisplay, id, parameters);
        		content = new File(name, pathLower, pathDisplay, id, size, isDownloadable, revisions);
        	}
        	else if(((JSONObject) jsonObjectContent).getString(".tag").equals("folder"))
        	{
        		content = new Folder(name, pathLower, pathDisplay, id);
        	}
        	
        	if(content != null) contentList.add(content);
        }
        
		return contentList;
	}

	
	@Override
	public Content getMetadata(JSONObject jsonObjectContent, Map<String, Object> parameters) {
		
		String name=((JSONObject) jsonObjectContent).getString("name");
		String pathLower=((JSONObject) jsonObjectContent).getString("path_lower");
    	String pathDisplay=((JSONObject) jsonObjectContent).getString("path_display");
    	String id=((JSONObject) jsonObjectContent).getString("id");
    	
    	Content content = null;
    	if(((JSONObject) jsonObjectContent).getString(".tag").equals("file"))
    	{
    		//TODO: Gestire eccezioni
    		Long size = ((JSONObject) jsonObjectContent).getLong("size");
    		boolean isDownloadable = ((JSONObject) jsonObjectContent).getBoolean("is_downloadable");
    		Vector<Revision> revisions = getRevisionsFromFile(pathDisplay, id, parameters);
    		content = new File(name, pathLower, pathDisplay, id, size, isDownloadable, revisions);
    	}
    	else if(((JSONObject) jsonObjectContent).getString(".tag").equals("folder"))
    	{
    		content = new Folder(name, pathLower, pathDisplay, id);
    	}
    	
    	return content;
	}
	
	//Per ottenere l'attributo numberOfRevision della classe File, è necessario conoscere la lista delle revisioni del file stesso
	//e per farlo, fare una richiesta http a dropbox, passando l'id del file
	private Vector<Revision> getRevisionsFromFile(String path, String id, Map<String, Object> parameters)
	{
		HTTPSRequest httpRequest = new HTTPSRequest();
		ListRevisionsConfiguration revisionConfig = new ListRevisionsConfiguration();
		
		@SuppressWarnings("unchecked")
		String mode = (String)parameters.get("mode");
		String token = (String) parameters.get("token");
		
		//Ripulisco la lista di parametri per prepararla alla nuova chiamata api
		parameters = new HashMap<String, Object>();
		Map<String, Object> info = new HashMap<String, Object>();
		
		info.put("mode", mode);
		
		//A seconda della scelta dell'utente, si farà riferimento ad un file in base al suo percorso
		//oppure in base al suo. La differenza sta nel fatto che, mentre l'id è univoco per quel file e vale per tutta la "vita" del 
		//del file stesso, dal momento dell'upload su DropBox in poi, il percorso fa riferimento solamente alla posizione in cui si trova il file 
		//in quel preciso momento. Quindi le revisioni recuperate a partire dall'id tengono conto di tutte le modifiche fatte al
		//file dalla data di creazione alla data odierna, mentre quelle recuperate a partire dal percorso tengono conto delle modifiche fatte 
		//al file soltanto mentre si trovava in quella specifica posizione.
		if(mode.equals("path"))
		{
			info.put("path", path);
		}
		else if(mode.equals("id"))
		{
			info.put("path", id);
		}
		parameters.put("info", info);
		parameters.put("token", token);
		
		//Setto i parametri per la chiamata all'API /list_revisions
		revisionConfig.setDefault(parameters);
		
		//Ritorno la lista di revisioni associate al file
		return getRevisionList(httpRequest.rootCall(parameters));
	}

	

	

	
}
