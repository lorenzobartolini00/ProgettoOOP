package it.univpm.DropboxAnalyzer.Service;

import java.net.*;
import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


public class FileServiceImpl implements FileService {

	
	public JSONObject getListFolder(String Content) {
		
		JSONObject metadata;
		String url= "https://api.dropboxapi.com/2/files/list_folder";
		
		RestTemplate rt=new RestTemplate(); //mi serve per fare richieste http
		
		HashMap<String,String> param =new HashMap<String,String>();
		param.put("path",Content);
		param.put("include_media_info", "true");
		param.put("include_deleted", "true");
		param.put("include_has_explicit_shared_members", "true");
		param.put("include_mounted_folders", "true");
		param.put("include_non_downloadable_files", "true");
		
		metadata=new JSONObject(rt.postForEntity(url, param, String.class));
		
		return metadata;
	}

	
}
