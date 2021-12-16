package it.univpm.DropboxAnalyzer.configuration;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import it.univpm.DropboxAnalyzer.Service.BadFormatException;

@Service
public class ListRevisionsConfiguration implements Configuration {
	
	public void setDefault(Map<String, Object> parameters) throws BadFormatException
	{
		parameters.putIfAbsent("url", "https://api.dropboxapi.com/2/files/list_revisions");
		parameters.putIfAbsent("type", "POST");
		if(parameters.containsKey("info"))
		{
			Map<String, Object> info = (Map<String, Object>) parameters.get("info");
			info.putIfAbsent("mode", "path");
		}
		else
		{
			throw new BadFormatException("No info found");
		}
	}

}
