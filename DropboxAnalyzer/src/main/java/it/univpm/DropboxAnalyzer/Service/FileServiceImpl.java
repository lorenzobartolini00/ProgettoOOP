package it.univpm.DropboxAnalyzer.Service;

import java.util.Collection;
import java.util.Vector;


import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import it.univpm.DropboxAnalyzer.Model.Content;
import it.univpm.DropboxAnalyzer.Model.Revision;

public class FileServiceImpl implements FileService{

	@Autowired
	HTTPSRequest httpsReq;
	
	@Override
	public Vector<Revision> getListRevisions(JSONObject pippo) {
		Vector<Revision> v= new Vector<Revision>();
		JSONObject jo=new JSONObject();
        JSONArray jsonArray= (JSONArray) pippo.get("entries");
        for(int i=0; i<jsonArray.length() ;i++)
        {
        	try {
				jo=(JSONObject) jsonArray.get(i);
				for(int k=0; k<(jo).size(); k++) {
				v.add((Revision) jo.get(i)); 
				
				//Posso farlo dato che ogni volta che scorro k, mi verrà restituita una stringa
				
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
        }
		return v;
		
			
		}
	

	@Override
	public Vector<Content> getListFolder(JSONObject jsonObj) {
		Vector<Content> v= new Vector<Content>();
		JSONObject jo=new JSONObject();
        JSONArray jsonArray= (JSONArray) jsonObj.get("entries");
        for(int i=0; i<jsonArray.length() ;i++)
        {
        	try {
				jo=(JSONObject) jsonArray.get(i);
				for(int k=0; k<(jo).size(); k++) {
				v.add((Content) jo.get(i)); 
				
				//Posso farlo dato che ogni volta che scorro k, mi verrà restituita una stringa
				
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
        }
		return v;
	}

	@Override
	public Content getMetadata(JSONObject jsonObj) {
		
		//in questo caso mi viene restituito solo un jo
		
		Vector<String> elem=new Vector<String>();
		for (int i=0;i<jsonObj.size();i++) {
			elem.add((String) jsonObj.get(i));
		}
		Content c=new Content(elem.get(1),elem.get(2),elem.get(3), elem.get(4));
		return c;
	}

	
}
