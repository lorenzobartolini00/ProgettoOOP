package it.univpm.DropboxAnalyzer.Service;

import java.util.Collection;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import it.univpm.DropboxAnalyzer.Model.Content;
import it.univpm.DropboxAnalyzer.Model.Revision;



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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Content getMetadata(JSONObject jsonObj) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

	
}
