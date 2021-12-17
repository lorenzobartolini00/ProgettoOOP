package it.univpm.DropboxAnalyzer.configuration;

import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.json.JSONObject;

import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;

public class ListFileMembersConfiguration extends Configuration{

	@Override
	public void setDefault(Map<String, Object> parameters) throws BadFormatException
	{
		parameters.putIfAbsent("url", "https://api.dropboxapi.com/2/files/list_file_members");
		parameters.putIfAbsent("type", "POST");
		Map<String, Object> info = (Map<String, Object>) parameters.get("info");
		info.putIfAbsent("include_inherited", true);
		
	}

	@Override
	public void checkFormat(Map<String, Object> parameters) throws BadFormatException {
		Vector<Property> properties = new Vector<Property>();
		properties.add(new Property("file", true, 0));
		properties.add(new Property("limit", false, 2));
		
		Map<String, String> errors = null;
		if((errors = getErrors(parameters, properties)) != null)
		{
			throw new BadFormatException(errors.get("errorContext"), errors.get("errorCause"), errors.get("errorType"));
		}
	}
	
	
}
