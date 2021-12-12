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
	public Vector<Revision> getListRevisions(JSONObject JSONrevisions) {
		Vector<Revision> revisionList = new Vector<Revision>();
		
		//Ottengo il jsonArray che contiene la lista delle revisioni per il file di interesse
        JSONArray jsonArray= (JSONArray) JSONrevisions.get("entries");
        
        //Per ogni JSONObject dentro il jsonArray estraggo i 3 valori che mi interessano e li uso per inizializzare una nuova
        //istanza della classe Revision
        for(Object jsonObject : jsonArray)
        {
        	String lastClientModify = ((JSONObject) jsonObject).getString("client_modified");
        	String lastServerModify = ((JSONObject) jsonObject).getString("server_modified");
        	String revisionId = ((JSONObject) jsonObject).getString("rev");

        	Revision revision = new Revision(lastClientModify, lastServerModify, revisionId);
        	
        	revisionList.add(revision);
        }
		return revisionList;
		
			
		}

	@Override
	public Vector<Content> getListFolder(JSONObject jsonObj) {
		Vector<Content> listFolder = new Vector<Content>();
		
		//Ottengo il jsonArray che contiene la lista delle revisioni per il file di interesse
        JSONArray jsonArray= (JSONArray) jsonObj.get("entries");
        
        for(Object jsonObject : jsonArray) {
        	String name=((JSONObject) jsonObject).getString("name");
        	String pathLower=((JSONObject) jsonObject).getString("path_lower");
        	String pathDisplay=((JSONObject) jsonObject).getString("path_display");
        	String id=((JSONObject) jsonObject).getString("id");
        	
        	Content content = null;
        	if(((JSONObject) jsonObject).getString(".tag").equals("file"))
        	{
        		//TODO: Gestire eccezioni
        		Long size = Long.valueOf( ((JSONObject) jsonObject).getString("size") );
        		boolean isDownloadable = Boolean.parseBoolean(((JSONObject) jsonObject).getString("is_downloadable"));
        		content = new File(name, pathLower, pathDisplay, id, size, isDownloadable);
        	}
        	else if(((JSONObject) jsonObject).getString(".tag").equals("folder"))
        	{
        		content = new Folder(name,pathLower,pathDisplay,id);
        	}
        	
        	if(content != null) listFolder.add(content);
        }
        
		return listFolder;
	}

	@Override
	public Content getMetadata(JSONObject JSONContent) {
		
		String name=((JSONObject) JSONContent).getString("name");
		String pathLower=((JSONObject) JSONContent).getString("path_lower");
    	String pathDisplay=((JSONObject) JSONContent).getString("path_display");
    	String id=((JSONObject) JSONContent).getString("id");
    	
    	Content content = null;
    	if(((JSONObject) JSONContent).getString(".tag").equals("file"))
    	{
    		//TODO: Gestire eccezioni
    		Long size = Long.valueOf( ((JSONObject) JSONContent).getString("size") );
    		boolean isDownloadable = Boolean.parseBoolean(((JSONObject) JSONContent).getString("is_downloadable"));
    		content = new File(name, pathLower, pathDisplay, id, size, isDownloadable);
    	}
    	else if(((JSONObject) JSONContent).getString(".tag").equals("folder"))
    	{
    		content = new Folder(name,pathLower,pathDisplay,id);
    	}
    	
    	return content;
	}

	

	

	
}
