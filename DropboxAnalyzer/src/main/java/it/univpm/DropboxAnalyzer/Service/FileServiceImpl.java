package it.univpm.DropboxAnalyzer.Service;

import java.util.Collection;
import java.util.Vector;


import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;

import it.univpm.DropboxAnalyzer.Model.Content;
import it.univpm.DropboxAnalyzer.Model.Revision;

public class FileServiceImpl implements FileService{

	@Autowired
	HTTPSRequest httpsReq;
	
	@Override
	public Vector<Revision> getListRevisions(JSONObject pippo) {
		Vector<Revision> v= new Vector<Revision>();
        JSONArray jsonArray= (JSONArray) pippo.get("entries");
        for(int i=0; i<jsonArray.length() ;i++)
        {
        	try {
				v.add(getJSONObject(i).convertToRevision());
				//Parse from jsonObj to Revision
			} catch (JSONException e) {
				e.printStackTrace();
			}
        }
		
		return v;
		
			
		}
	

	@Override
	public Vector<Content> getListFolder(JSONObject jsonObj) {
		
		return null;
	}

	@Override
	public Content getMetadata(JSONObject jsonObj) {
		
		return null;
	}

	
}
