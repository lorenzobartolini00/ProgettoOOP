package it.univpm.DropboxAnalyzer.Controller;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.univpm.DropboxAnalyzer.Service.FileService;

import org.json.simple.JSONObject; 

@Controller
public class ContentController {
	@Autowired
	private FileService fileService;
	//"list-folder API call
	@GetMapping("/list")
	public @ResponseBody JSONObject POSTListFolder(@RequestParam(name="token") String token) throws MalformedURLException
	{
		String url = "https://api.dropboxapi.com/2/files/list_folder";
		String jsonBody = "{\r\n"
				+ "    \"path\": \"/Uni\",\r\n"
				+ "    \"recursive\": false,\r\n"
				+ "    \"include_media_info\": false,\r\n"
				+ "    \"include_deleted\": false,\r\n"
				+ "    \"include_has_explicit_shared_members\": false,\r\n"
				+ "    \"include_mounted_folders\": true,\r\n"
				+ "    \"include_non_downloadable_files\": true\r\n"
				+ "}";
		JSONObject jsonObj = fileService.GetJsonFromRequest("POST", token, url, jsonBody);
		//TODO: Proper convert from json to list of files. (Now only raw conversion from json to string).
		return jsonObj;
	}
	
	@GetMapping("/get-metadata")
	public @ResponseBody JSONObject POSTGetMetadata(@RequestParam(name="token") String token) throws MalformedURLException
	{
		String url = "https://api.dropboxapi.com/2/files/get_metadata";
		String jsonBody = "{\r\n" + 
				"    \"path\": \"/Uni\",\r\n" + 
				"    \"include_media_info\": true,\r\n" + 
				"    \"include_deleted\": false,\r\n" + 
				"    \"include_has_explicit_shared_members\": false\r\n" + 
				"}";
		JSONObject jsonObj = fileService.GetJsonFromRequest("POST", token, url, jsonBody);
		//TODO: Proper convert from json to metadata file. (Now only raw conversion from json to string).
		return jsonObj;
	}
	
	
}
