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
	@GetMapping("/list_folder")
	public @ResponseBody JSONObject POSTListFolder(@RequestParam(name="token") String token) throws MalformedURLException
	{
		JSONObject jsonObj = fileService.rootCall(0,"/Uni", token);
		//TODO: Proper convert from json to list of files. (Now only raw conversion from json to string).
		return jsonObj;
	}
	
	@GetMapping("/get_metadata")
	public @ResponseBody JSONObject POSTGetMetadata(@RequestParam(name="token") String token) throws MalformedURLException
	{
		JSONObject jsonObj = fileService.rootCall(1,"/Uni", token);
		//TODO: Proper convert from json to metadata file. (Now only raw conversion from json to string).
		return jsonObj;
	}
	
	
}
