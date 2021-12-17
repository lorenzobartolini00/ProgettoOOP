package it.univpm.DropboxAnalyzer.configuration;

import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;

@Service
public class ListFolderConfiguration extends Configuration {

	@Override
	public void setDefault(Map<String, Object> parameters) throws BadFormatException{
		parameters.putIfAbsent("url", "https://api.dropboxapi.com/2/files/list_folder");
		parameters.putIfAbsent("type", "POST");
		@SuppressWarnings("unchecked")
		Map<String, Object> info = (Map<String, Object>) parameters.get("info");
		info.putIfAbsent("recursive", false);
		info.putIfAbsent("include_media_info", false);
		info.putIfAbsent("include_deleted", false);
		info.putIfAbsent("include_has_explicit_shared_members", false);
		info.putIfAbsent("include_mounted_folders", true);
		info.putIfAbsent("include_non_downloadable_files", true);
	}

	@Override
	public void checkFormat(Map<String, Object> parameters) throws BadFormatException {
		Vector<Property> properties = new Vector<Property>();
		properties.add(new Property("path", true, 0));
		properties.add(new Property("recursive", false, 1));
		
		Map<String, String> errors = null;
		if((errors = getErrors(parameters, properties)) != null)
		{
			throw new BadFormatException(errors.get("errorContext"), errors.get("errorCause"), errors.get("errorType"));
		}
		
	}

	
	
	

}
