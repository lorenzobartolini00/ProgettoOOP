package it.univpm.DropboxAnalyzer.configuration;

import java.util.Map;

import org.json.JSONObject;

import it.univpm.DropboxAnalyzer.Service.BadFormatException;

public class ListFileMembersConfiguration implements Configuration{

	@Override
	public void setDefault(Map<String, Object> parameters) throws BadFormatException
	{
		parameters.putIfAbsent("url", "https://api.dropboxapi.com/2/files/list_file_members");
		parameters.putIfAbsent("type", "POST");
		
		if(parameters.containsKey("info"))
		{
			Map<String, Object> info = (Map<String, Object>) parameters.get("info");
			info.putIfAbsent("include_inherited", true);
		}
		else
		{
			throw new BadFormatException("No info found");
		}
		
	}
	
	
}
