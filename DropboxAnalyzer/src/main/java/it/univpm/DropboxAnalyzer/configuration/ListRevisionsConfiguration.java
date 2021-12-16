package it.univpm.DropboxAnalyzer.configuration;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;

@Service
public class ListRevisionsConfiguration implements Configuration {
	
	public void setDefault(Map<String, Object> parameters) throws BadFormatException
	{
		parameters.putIfAbsent("url", "https://api.dropboxapi.com/2/files/list_revisions");
		parameters.putIfAbsent("type", "POST");
		Map<String, Object> info = (Map<String, Object>) parameters.get("info");
		info.putIfAbsent("mode", "path");
	}

	@Override
	public void checkFormat(Map<String, Object> parameters) throws BadFormatException {
		
		if( !parameters.containsKey("info") )
		{
			throw new BadFormatException("body", "'info'");
		}
		else
		{
			@SuppressWarnings("unchecked")
			Map<String, Object> info = (Map<String, Object>) parameters.get("info");
			if(!info.containsKey("path") )
			{
				throw new BadFormatException("body/info", "'path'");
			}
			if(!info.containsKey("limit"))
			{
				throw new BadFormatException("body/info", "'recursive'");
			}
		}
	}

}
