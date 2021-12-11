package it.univpm.DropboxAnalyzer.Service;

import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONObject;

import it.univpm.DropboxAnalyzer.Model.Revision;
import it.univpm.DropboxAnalyzer.Model.User;

public class UserServiceImpl implements UserService{

	@Override
	public Vector<User> getListUsers(JSONObject jsonObj) {
		
			Vector<User> listUser = new Vector<User>();
			
			//Ottengo il jsonArray che contiene la lista delle revisioni per il file di interesse
	        JSONArray jsonArray= (JSONArray) jsonObj.get("user");
	        
	        //Per ogni JSONObject dentro il jsonArray estraggo i 3 valori che mi interessano e li uso per inizializzare una nuova
	        //istanza della classe Revision
	        for(Object jsonObject : jsonArray)
	        {
	        	String accountId = ((JSONObject) jsonObject).getString("account_id");
	        	String email = ((JSONObject) jsonObject).getString("email");
	        	String displayName = ((JSONObject) jsonObject).getString("display_name");

	        	User user = new User(accountId, email, displayName);
	        	
	        	listUser.add(user);
	        }
			return listUser;
		
	}



}
