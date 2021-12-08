package it.univpm.DropboxAnalyzer.Service;


import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

/*La utilizzerò per gestire i dati del package Model
  Essa comunica con le API, che sono al livello superiore.  */

@Service
public interface FileService {
	
	public JSONObject GetJsonFromRequest(String type, String token, String url, String jsonBody);
}
