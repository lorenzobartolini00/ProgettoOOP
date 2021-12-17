package it.univpm.DropboxAnalyzer.configuration;

import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.json.JSONObject;

import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;

public class GetMetadataBody extends Configuration {

	@Override
	public void setDefault(Map<String, Object> parameters) throws BadFormatException {
		parameters.putIfAbsent("url", "https://api.dropboxapi.com/2/files/get_metadata");
		parameters.putIfAbsent("type", "POST");
		Map<String, Object> info = (Map<String, Object>) parameters.get("info");
		info.putIfAbsent("include_media_info", true);
		info.putIfAbsent("include_deleted", true);
		info.putIfAbsent("include_has_explicit_shared_members", true);
	}

	@Override
	public void checkFormat(Map<String, Object> parameters) throws BadFormatException {
		Vector<Property> properties = new Vector<Property>();
		properties.add(new Property("path", true, 0));
		
		Map<String, String> errors = null;
		if((errors = getErrors(parameters, properties)) != null)
		{
			throw new BadFormatException(errors.get("errorContext"), errors.get("errorCause"), errors.get("errorType"));
		}
	}
	
	
	
	
	
	

}
