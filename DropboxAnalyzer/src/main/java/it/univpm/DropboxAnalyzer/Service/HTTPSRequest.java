package it.univpm.DropboxAnalyzer.Service;

import it.univpm.DropboxAnalyzer.configuration.*;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class HTTPSRequest{
	public JSONObject rootCall(int request, Configuration config, String token)
	{
		HttpURLConnection openConnection = connectionSetUp(getConfigurationsProperties(request, config, token));
		return getJson(openConnection);
	}
	
	//Metodo per fare chimata HTTP
	private HttpURLConnection connectionSetUp(Vector<String> properties)
	{
		String url = properties.get(0);
		String jsonBody = properties.get(1);
		String type = properties.get(2);
		String token = properties.get(3);
		HttpURLConnection openConnection = null;
		try 
		{
			openConnection = (HttpURLConnection) new URL(url).openConnection();
			openConnection.setRequestMethod(type);
			openConnection.setRequestProperty("Authorization", "Bearer " + token);
			openConnection.setRequestProperty("Content-Type", "application/json");
			openConnection.setRequestProperty("Accept", "application/json");
			openConnection.setDoOutput(true);
			try(OutputStream os = openConnection.getOutputStream())
			{
				byte[] input = jsonBody.getBytes("utf-8");
				os.write(input, 0, input.length);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return openConnection;
	}
	
	//Salva su un JSONObject la risposta alla chiamata HTTP
	private JSONObject getJson(HttpURLConnection openConnection)
	{
		JSONObject jsonObject = null;
		try {
			InputStream in = openConnection.getInputStream();
			JSONParser jsonParser = new JSONParser();
			jsonObject = (JSONObject)jsonParser.parse(new InputStreamReader(in, "UTF-8"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	//Ottiene i parametri giusti a seconda della richiesta e del tipo
	private Vector<String> getConfigurationsProperties(int request, Configuration config, String token)
	{
		Vector<String> properties = new Vector<String>();
		String url = null;
		String jsonBody = null;
		String type = null;
		switch(request)
		{
		case 0:
		{
			url = "https://api.dropboxapi.com/2/files/list_folder";
			jsonBody = "{\r\n"
					+ "    \"path\": \""
					+ config
					+ "\",\r\n"
					+ "    \"recursive\": false,\r\n"
					+ "    \"include_media_info\": false,\r\n"
					+ "    \"include_deleted\": false,\r\n"
					+ "    \"include_has_explicit_shared_members\": false,\r\n"
					+ "    \"include_mounted_folders\": true,\r\n"
					+ "    \"include_non_downloadable_files\": true\r\n"
					+ "}";
			type = "POST";
			break;
		}
		case 1:
		{
			url = "https://api.dropboxapi.com/2/files/get_metadata";
			jsonBody = "{\r\n" + 
					"    \"path\": \""
					+ config
					+ "\",\r\n" + 
					"    \"include_media_info\": true,\r\n" + 
					"    \"include_deleted\": false,\r\n" + 
					"    \"include_has_explicit_shared_members\": false\r\n" + 
					"}";
			type = "POST";
			break;
		}
		}
		properties.add(url);
		properties.add(jsonBody);
		properties.add(type);
		properties.add(token);
		return properties;
	}
	
	
}
	
	
