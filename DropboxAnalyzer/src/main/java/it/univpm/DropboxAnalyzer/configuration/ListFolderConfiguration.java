package it.univpm.DropboxAnalyzer.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;

/**
 * Classe che estende {@link Configuration} e si occupa della configurazione della chiamata "/list_files"
 * @author Lorenzo Bartolini
 * @author Francesc Pio Cecca
 */
@Service
public class ListFolderConfiguration extends Configuration {

	@Override
	public void setDefault(Map<String, Object> parameters) {
		parameters.putIfAbsent("url", "https://api.dropboxapi.com/2/files/list_folder");
		parameters.putIfAbsent("type", "POST");
		@SuppressWarnings("unchecked")
		Map<String, Object> info = (Map<String, Object>) parameters.get("info");
		
		//Devo aggiungere un altro parametro "mode" necessario per ottenere il numero di revisioni relative a quel file.
		//Infatti per ogni file dovrà essere fatta una chiamata API a DropBox(/list_revisions) per avere accesso alle revisioni di quel file.
		//Il file può essere identificato tramite id oppure tramite path. Tramite il parametro mode l'utente esplicita la modalità di scelta del file.
		if(info.containsKey("mode"))
		{
			parameters.put("mode", (String) info.get("mode"));
		}
		else
		{
			parameters.put("mode", "path");
		}
		
		//E' necessario rimuovere mode da dentro "info", dato che i parametri in info servono per fare la chiamata /list_folder,
		//che non prevede la presenza di un parametro "mode". Questo parametro verrà usato, infatti, solo per la chiamata /list_revision
		info.remove("mode");
		
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
		properties.add(new Property("mode", false, 0));
		properties.add(new Property("recursive", false, 1));
		
		Map<String, String> errors = null;
		if((errors = getErrors(parameters, properties)) != null)
		{
			throw new BadFormatException(errors.get("errorContext"), errors.get("errorCause"), errors.get("errorType"));
		}
		
	}

	
	
	

}
