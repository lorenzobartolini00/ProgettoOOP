package it.univpm.DropboxAnalyzer.Controller;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.util.Vector;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.univpm.DropboxAnalyzer.Model.Content;
import it.univpm.DropboxAnalyzer.Service.FileService;
import it.univpm.DropboxAnalyzer.Service.HTTPSRequest;
import it.univpm.DropboxAnalyzer.configuration.Body;
import it.univpm.DropboxAnalyzer.configuration.Configuration;
import it.univpm.DropboxAnalyzer.configuration.GetMetadataBody;
import it.univpm.DropboxAnalyzer.configuration.ListFolderBody;

@Controller
public class ContentController {
	@Autowired
	private FileService fileService;
	@Autowired
	private HTTPSRequest httpsReq;
	//"list-folder API call
	@GetMapping("/list_folder")
	public @ResponseBody String POSTListFolder(@RequestParam(name="token") String token) throws MalformedURLException
	{
		Configuration config = new Configuration("https://api.dropboxapi.com/2/files/list_folder", new ListFolderBody("/Uni", true), "POST", token);
		Vector<Content> contents = fileService.getListFolder(httpsReq.rootCall(config));
		
		return contents.get(1).getName();
	}
	
	@GetMapping("/get_metadata")
	public @ResponseBody Content POSTGetMetadata(@RequestParam(name="token") String token) throws MalformedURLException
	{
		
		Configuration config = new Configuration("https://api.dropboxapi.com/2/files/get_metadata", new GetMetadataBody("/Uni",true,true,true), "POST", token);
		Content content = fileService.getMetadata(httpsReq.rootCall(config));
		return content;
	}
	
	@GetMapping("/get_list_revisions")
	public @ResponseBody String POSTGetListRevision(@RequestParam(name="token") String token) throws MalformedURLException
	{
		
		return null;
	}
	
	
}
