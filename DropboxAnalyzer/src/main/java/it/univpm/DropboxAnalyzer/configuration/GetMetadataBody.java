package it.univpm.DropboxAnalyzer.configuration;

import java.util.Map;

import org.json.JSONObject;

import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;

public class GetMetadataBody implements Configuration {

	@Override
	public void setDefault(Map<String, Object> parameters) throws BadFormatException {
		parameters.putIfAbsent("url", "https://api.dropboxapi.com/2/files/get_metadata");
		parameters.putIfAbsent("type", "POST");
		if(parameters.containsKey("info"))
		{
			Map<String, Object> info = (Map<String, Object>) parameters.get("info");
			info.putIfAbsent("include_media_info", true);
			info.putIfAbsent("include_deleted", true);
			info.putIfAbsent("include_has_explicit_shared_members", true);
		}
		else
		{
			throw new BadFormatException("No info found");
		}
	}

	@Override
	public void checkFormat() throws BadFormatException {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	
	

}
