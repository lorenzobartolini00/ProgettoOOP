package it.univpm.DropboxAnalyzer.Service;

import it.univpm.DropboxAnalyzer.configuration.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Vector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univpm.DropboxAnalyzer.configuration.Configuration;
import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;

import org.json.JSONObject;

/**
 * Classe che gestisce le chiamate HTTP
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 */
@Service
public class HTTPSRequest{
	/**
	 * Si occupa di aprire la connessione sulla base dei parametri forniti e restituire
	 * in formato Json i dati letti dallo stream di bit provenienti dal server di DropBox
	 * @param parameters Mappa contenente i parametri necessari per la richiesta HTTP(url, type, token,...)
	 * @return JSONObject contenente i dati
	 */
	public JSONObject rootCall(Map<String, Object> parameters)
	{
		return getJson(connectionSetUp(parameters));
	}
	
	
	private HttpURLConnection connectionSetUp(Map<String, Object> parameters)
	{
		String url = (String) parameters.get("url");
		String type = (String) parameters.get("type");
		String token = (String) parameters.get("token");
		@SuppressWarnings("unchecked")
		String info = this.getParamString((Map<String, Object>) parameters.get("info"));
		
		HttpURLConnection openConnection = null;
		try 
		{
			//Apro la connessione
			openConnection = (HttpURLConnection) new URL(url).openConnection();
			openConnection.setRequestMethod(type);
			openConnection.setRequestProperty("Authorization", "Bearer " + token);
			openConnection.setRequestProperty("Content-Type", "application/json");
			openConnection.setRequestProperty("Accept", "application/json");
			openConnection.setDoOutput(true);
			//Passo attraverso il DataOutputStream il body per poter utilizzare l'API messa a disposizione da Dropbox
			try( OutputStream os = openConnection.getOutputStream())
			{
				byte[] input = info.getBytes("utf-8");
				os.write(input, 0, input.length);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return openConnection;
	}
	
	
	private JSONObject getJson(HttpURLConnection openConnection)
	{
		JSONObject jsonObject = null;
		try {
			InputStream in = openConnection.getInputStream();
			String data = "";
			String line = "";
			try {
				InputStreamReader inR = new InputStreamReader(in);
				BufferedReader buf = new BufferedReader(inR);

				while ((line = buf.readLine()) != null) {
					data += line;
				}
			} finally {
				in.close();
			}
			jsonObject = (JSONObject) new JSONObject(data);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	

	private String getParamString(Map<String, Object> bodyParams)
	{
        return new JSONObject(bodyParams).toString();
	}
	
}
	
	
