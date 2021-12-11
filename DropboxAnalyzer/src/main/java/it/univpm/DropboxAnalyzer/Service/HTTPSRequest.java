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


import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univpm.DropboxAnalyzer.configuration.Configuration;
import org.json.JSONObject;



@Service
public class HTTPSRequest{
	public JSONObject rootCall(Configuration config)
	{
		return getJson(connectionSetUp(config));
	}
	
	//Metodo per fare chimata HTTP
	private HttpURLConnection connectionSetUp(Configuration config)
	{
		String url = config.getUrl();
		String jsonBody = config.getBody().toString();
		String type = config.getType();
		String token = config.getToken();
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
	
}
	
	
