package it.univpm.DropboxAnalyzer.Service;

import java.util.Collection;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.univpm.DropboxAnalyzer.Model.Content;
import it.univpm.DropboxAnalyzer.Model.File;
import it.univpm.DropboxAnalyzer.Model.Folder;
import it.univpm.DropboxAnalyzer.Model.Revision;


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

        	Revision revision = new Revision(lastClientModify, lastServerModify, revisionId);
        	
        	revisionList.add(revision);
        }
		return revisionList;
		
			
		}

	@Override
	public Vector<Content> getContentList(JSONObject jsonObjectFolders) {
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
        		Long size = Long.valueOf( ((JSONObject) jsonObjectContent).getString("size") );
        		boolean isDownloadable = Boolean.parseBoolean(((JSONObject) jsonObjectContent).getString("is_downloadable"));
        		content = new File(name, pathLower, pathDisplay, id, size, isDownloadable);
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
	public Content getMetadata(JSONObject jsonObjectContent) {
		
		String name=((JSONObject) jsonObjectContent).getString("name");
		String pathLower=((JSONObject) jsonObjectContent).getString("path_lower");
    	String pathDisplay=((JSONObject) jsonObjectContent).getString("path_display");
    	String id=((JSONObject) jsonObjectContent).getString("id");
    	
    	Content content = null;
    	if(((JSONObject) jsonObjectContent).getString(".tag").equals("file"))
    	{
    		//TODO: Gestire eccezioni
    		Long size = Long.valueOf( ((JSONObject) jsonObjectContent).getString("size") );
    		boolean isDownloadable = Boolean.parseBoolean(((JSONObject) jsonObjectContent).getString("is_downloadable"));
    		content = new File(name, pathLower, pathDisplay, id, size, isDownloadable);
    	}
    	else if(((JSONObject) jsonObjectContent).getString(".tag").equals("folder"))
    	{
    		content = new Folder(name, pathLower, pathDisplay, id);
    	}
    	
    	return content;
	}

	

	

	
}
