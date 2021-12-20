package it.univpm.DropboxAnalyzer.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;

/**
 * Classe astratta che si occupa della configurazione delle varie rotte
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 */
public abstract class Configuration {
	/**
	 * Si occupa di settare i parametri al loro valore di default e di aggiungere quelli non required, nel
	 * caso non fossero presenti 
	 * @param parameters Map che contiene i parametri di configurazione
	 */
	public abstract void setDefault(Map<String, Object> parameters);
	
	/**
	 * Si occupa di vedere se il body inserito dall'utente presenta tutti i parametri required
	 * e di vedere se sono hanno il tipo corretto  
	 * @param parameters 
	 */
	public abstract void checkFormat(Map<String, Object> parameters) throws BadFormatException;
	
	/**
	 * Restituisce una mappa contenente le stringhe con cui inizializzare l'eventuale eccezione 
	 * sollevata in caso di errore
	 * @param parameters Map che contiene i parametri di configurazione
	 * @param properties Lista di Property usate come riferimento per controllare se
	 * la lista di parametri inseriti dall'utente è corretta
	 * @return Mappa contenente le stringhe
	 */
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
			//Trovare soluzione migliore
			@SuppressWarnings("unchecked")
			Map<String, Object> info = (Map<String, Object>) parameters.get(header);
			
			//Estraggo prima dal vettore di Property, una collection di stringhe contenente i nomi di tutte le properties
			Vector<String> stringProperties = new Vector<String>();
			properties.forEach(property -> stringProperties.add(property.getPropertyName()));
			
			//Se sono presenti dei parametri superflui, vengono eliminati
			info.keySet().retainAll(stringProperties);
			
			
			for(Property property : properties)
			{
				Object myProperty = null;
				
				//Nel caso in cui dovesse mancare una delle properties e questa fosse anche required, allora 
				//si genera un'eccezione
				if(!info.containsKey(property.getPropertyName()) && property.isRequired() )
				{
					errorContext = "body/" + header;
					errorCause = property.getPropertyName();
					errorType = "is missing";
					errorFound = true;
				}
				else if(info.containsKey(property.getPropertyName()))
				{
					myProperty = info.get(property.getPropertyName());
					
					//Nel caso in cui nella Map fosse presente la key associata a quella property, ma
					//il suo value fosse di un tipo errato, viene generata l'eccezione
					switch(property.getType())
					{
					case 0:
					{
						if(!(myProperty instanceof String))
						{
							errorContext = "body/" + header;
							errorCause = property.getPropertyName();
							errorType = "has wrong type";
							errorFound = true;
						}
						break;
					}
					case 1:
					{
						if(!(myProperty instanceof Boolean))
						{
							errorContext = "body/" + header;
							errorCause = property.getPropertyName();
							errorType = "has wrong type";
							errorFound = true;
						}
						break;
					}
					case 2:
					{
						if(!(myProperty instanceof Integer))
						{
							errorContext = "body/" + header;
							errorCause = property.getPropertyName();
							errorType = "has wrong type";
							errorFound = true;
						}
						break;
					}
					}
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
