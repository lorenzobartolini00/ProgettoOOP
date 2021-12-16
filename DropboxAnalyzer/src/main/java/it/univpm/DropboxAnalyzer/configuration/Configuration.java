package it.univpm.DropboxAnalyzer.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.json.JSONObject;

import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;

public abstract class Configuration {
	public abstract void setDefault(Map<String, Object> parameters) throws BadFormatException;
	public abstract void checkFormat(Map<String, Object> parameters) throws BadFormatException;
	
	public Map<String, String> getErrors(Map<String, Object> parameters, Vector<Property> properties) 
	{
		
		String header = "info";
		
		Map<String, String> errors = null;
		
		boolean errorFound = false;
		
		String errorContext = null;
		String errorCause  = null;
		String errorType  = null;
		if( !parameters.containsKey(header) )
		{
			errorContext = "body";
			errorCause = header;
			errorType = "is missing";
			errorFound = true;
		}
		else
		{
			@SuppressWarnings("unchecked")
			Map<String, Object> info = (Map<String, Object>) parameters.get("info");
			
			//Se sono presenti dei parametri superflui, vengono eliminati
			Vector<String> stringProperties = new Vector<String>();
			properties.forEach(property -> stringProperties.add(property.getProperty()));
			info.keySet().retainAll(stringProperties);
			
			//Nel caso in cui dovesse mancare una delle properties e questa fosse anche required, allora 
			//si genera un'eccezione
			for(Property property : properties)
			{
				if(!info.containsKey(property.getProperty()) && property.isRequired() )
				{
					errorContext = "body/" + header;
					errorCause = property.getProperty();
					errorType = "is missing";
					errorFound = true;
				}
			}
			
		}
		
		if(errorFound)
		{
			errors = new HashMap<String, String>();
			errors.put("errorContext", errorContext);
			errors.put("errorCause", errorCause);
			errors.put("errorType", errorType);
			return errors;
		}
		else
		{
			return null;
		}
	}
}
