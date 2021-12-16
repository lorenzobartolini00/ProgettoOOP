package it.univpm.DropboxAnalyzer.configuration;

import java.util.Map;

import org.json.JSONObject;

public class ListRevisionsConfiguration implements Configuration {
	
	public void setDefault(Map<String, Object> parameters)
	{
		parameters.putIfAbsent("url", "https://api.dropboxapi.com/2/files/list_revisions");
		parameters.putIfAbsent("type", "POST");
	}

}
