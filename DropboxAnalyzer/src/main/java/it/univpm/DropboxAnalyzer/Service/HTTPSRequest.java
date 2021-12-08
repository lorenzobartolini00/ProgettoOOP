package it.univpm.DropboxAnalyzer.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

@Service
public class HTTPSRequest implements FileService {

	public JSONObject GetJsonFromRequest(String type, String token, String url, String jsonBody)
	{
		JSONObject jsonObject = null;
		try {

			HttpURLConnection openConnection = (HttpURLConnection) new URL(url).openConnection();
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
			
			InputStream in = openConnection.getInputStream();
			JSONParser jsonParser = new JSONParser();
			jsonObject = (JSONObject)jsonParser.parse(new InputStreamReader(in, "UTF-8"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	
}
