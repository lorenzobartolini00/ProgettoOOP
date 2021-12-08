package it.univpm.DropboxAnalyzer.Controller;

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

public class HTTPRequests {
	public static JSONObject ListFolderRequest(String token)
	{
		JSONObject jsonObject = null;
		String url = "https://api.dropboxapi.com/2/files/list_folder";
		try {

			HttpURLConnection openConnection = (HttpURLConnection) new URL(url).openConnection();
			openConnection.setRequestMethod("POST");
			openConnection.setRequestProperty("Authorization", token);
			openConnection.setRequestProperty("Content-Type", "application/json");
			openConnection.setRequestProperty("Accept", "*/*");
			openConnection.setDoOutput(true);
			
			String jsonBody = "{\r\n"
					+ "    \"path\": \"/Uni\",\r\n"
					+ "    \"recursive\": false,\r\n"
					+ "    \"include_media_info\": false,\r\n"
					+ "    \"include_deleted\": false,\r\n"
					+ "    \"include_has_explicit_shared_members\": false,\r\n"
					+ "    \"include_mounted_folders\": true,\r\n"
					+ "    \"include_non_downloadable_files\": true\r\n"
					+ "}";
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
