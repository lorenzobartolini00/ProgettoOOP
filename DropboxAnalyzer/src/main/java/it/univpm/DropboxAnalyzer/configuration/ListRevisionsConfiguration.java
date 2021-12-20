package it.univpm.DropboxAnalyzer.configuration;

import java.util.Map;
import java.util.Vector;

import org.springframework.stereotype.Service;

import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;

/**
 * Si occupa della configurazione delle chiamate "/revision_statistics/{statistic_type}" e "/get_list_revisions"
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 */
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
		properties.add(new Property("path", true, 0));
		properties.add(new Property("mode", false, 0));
		properties.add(new Property("limit", false, 2));
		
		Map<String, String> errors = null;
		if((errors = getErrors(parameters, properties)) != null)
		{
			throw new BadFormatException(errors.get("errorContext"), errors.get("errorCause"), errors.get("errorType"));
		}
		
	}

}
