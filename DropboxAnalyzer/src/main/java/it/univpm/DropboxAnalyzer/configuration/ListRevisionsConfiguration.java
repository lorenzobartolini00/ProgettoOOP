package it.univpm.DropboxAnalyzer.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;

@Service
public class ListRevisionsConfiguration extends Configuration {
	
	public void setDefault(Map<String, Object> parameters) throws BadFormatException
	{
		parameters.putIfAbsent("url", "https://api.dropboxapi.com/2/files/list_revisions");
		parameters.putIfAbsent("type", "POST");
		Map<String, Object> info = (Map<String, Object>) parameters.get("info");
		info.putIfAbsent("mode", "path");
	}

	@Override
	public void checkFormat(Map<String, Object> parameters) throws BadFormatException {
		Vector<Property> properties = new Vector<Property>();
		properties.add(new Property("path", true));
		properties.add(new Property("limit", false));
		
		Map<String, String> errors = null;
		if((errors = getErrors(parameters, properties)) != null)
		{
			throw new BadFormatException(errors.get("errorContext"), errors.get("errorCause"), errors.get("errorType"));
		}
		
	}

}
