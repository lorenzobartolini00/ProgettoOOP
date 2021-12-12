package it.univpm.DropboxAnalyzer.Service;

import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import it.univpm.DropboxAnalyzer.Model.Editor;
import it.univpm.DropboxAnalyzer.Model.File;
import it.univpm.DropboxAnalyzer.Model.Folder;
import it.univpm.DropboxAnalyzer.Model.Owner;
import it.univpm.DropboxAnalyzer.Model.Revision;
import it.univpm.DropboxAnalyzer.Model.User;

@Service
public class UserServiceImpl implements UserService{

	@Override
	public Vector<User> getUserList(JSONObject jsonObjectUsers) {
		
			Vector<User> userList = new Vector<User>();
			
			//Ottengo il jsonArray che contiene la lista delle revisioni per il file di interesse
	        JSONArray jsonArrayUsers = (JSONArray) jsonObjectUsers.get("users");
	        
	        //Per ogni JSONObject dentro il jsonArray estraggo i 3 valori che mi interessano e li uso per inizializzare una nuova
	        //istanza della classe Revision
	        for(Object jsonObjectUser : jsonArrayUsers)
	        {
	        	JSONObject jsonAccesType = ((JSONObject) jsonObjectUser).getJSONObject("access_type");
	        	JSONObject jsonUser = ((JSONObject) jsonObjectUser).getJSONObject("user");
	        	String accountId = ((JSONObject) jsonUser).getString("account_id");
	        	String email = ((JSONObject) jsonUser).getString("email");
	        	String displayName = ((JSONObject) jsonUser).getString("display_name");
	        	
	        	User user = null;
	        	//In base all'attributo tag, decido se si tratta di un Editor o un Owner
	        	if(((JSONObject) jsonAccesType).getString(".tag").equals("editor"))
	        	{
	        		//TODO: Gestire eccezioni
	        		user = new Editor(accountId, email, displayName);
	        	}
	        	else if(((JSONObject) jsonObjectUser).getString(".tag").equals("owner"))
	        	{
	        		user = new Owner(accountId, email, displayName);
	        	}
	        	
	        	if(user != null) userList.add(user);
	        	userList.add(user);
	        }
			return userList;
		
	}



}
