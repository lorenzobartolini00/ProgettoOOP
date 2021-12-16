package it.univpm.DropboxAnalyzer.configuration;

import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import it.univpm.DropboxAnalyzer.Service.BadFormatException;

@Service
public class ListFolderConfiguration implements Configuration {

	@Override
	public void setDefault(Map<String, Object> parameters) throws BadFormatException{
		parameters.putIfAbsent("url", "https://api.dropboxapi.com/2/files/list_folder");
		parameters.putIfAbsent("type", "POST");
		if(parameters.containsKey("info"))
		{
			Map<String, Object> info = (Map<String, Object>) parameters.get("info");
			info.putIfAbsent("include_media_info", false);
			info.putIfAbsent("include_deleted", false);
			info.putIfAbsent("include_has_explicit_shared_members", false);
			info.putIfAbsent("include_mounted_folders", true);
			info.putIfAbsent("include_non_downloadable_files", true);
		}
		else
		{
			throw new BadFormatException("No info found");
		}
	}

	
	
	

}
