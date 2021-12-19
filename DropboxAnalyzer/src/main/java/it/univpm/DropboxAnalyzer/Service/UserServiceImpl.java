package it.univpm.DropboxAnalyzer.Service;

import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import it.univpm.DropboxAnalyzer.Model.Viewer;
import it.univpm.DropboxAnalyzer.Model.File;
import it.univpm.DropboxAnalyzer.Model.Folder;
import it.univpm.DropboxAnalyzer.Model.Owner;
import it.univpm.DropboxAnalyzer.Model.Revision;
import it.univpm.DropboxAnalyzer.Model.User;

/**
 * Classe che implementa l'interfaccia {@link UserService}
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 */
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
	        	//In base all'attributo tag, decido se si tratta di un Viewer o un Owner
	        	if(((JSONObject) jsonAccesType).getString(".tag").equals("viewer"))
	        	{
	        		//TODO: Gestire eccezioni
	        		user = new Viewer(accountId, email, displayName);
	        	}
	        	else if(((JSONObject) jsonAccesType).getString(".tag").equals("owner"))
	        	{
	        		user = new Owner(accountId, email, displayName);
	        	}
	        	
	        	if(user != null) userList.add(user);
	        }
			return userList;
		
	}



}
