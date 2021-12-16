package it.univpm.DropboxAnalyzer.Controller;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.univpm.DropboxAnalyzer.Model.Content;
import it.univpm.DropboxAnalyzer.Model.Revision;
import it.univpm.DropboxAnalyzer.Service.FileService;
import it.univpm.DropboxAnalyzer.Service.HTTPSRequest;
import it.univpm.DropboxAnalyzer.Statistics.RevisionStatistics;
import it.univpm.DropboxAnalyzer.Statistics.Statistics;
import it.univpm.DropboxAnalyzer.configuration.Body;
import it.univpm.DropboxAnalyzer.configuration.Configuration;
import it.univpm.DropboxAnalyzer.configuration.GetMetadataBody;
import it.univpm.DropboxAnalyzer.configuration.ListFolderBody;
import it.univpm.DropboxAnalyzer.configuration.ListRevisionsConfiguration;
import it.univpm.DropboxAnalyzer.filter.FileFilter;
import it.univpm.DropboxAnalyzer.filter.RevisionFilter;

@Controller
public class ContentController {
	@Autowired
	private FileService fileService;
	@Autowired
	private HTTPSRequest httpsReq;
	@Autowired
	private ListRevisionsConfiguration config;
	
	@GetMapping("/revision_statistics")
	public @ResponseBody RevisionStatistics POSTRevisionStatistics(@RequestBody Map<String, Object> parameters, @RequestParam(name="token") String token) throws MalformedURLException
	{
		parameters.put("token", token);
		config.setDefault(parameters);
		
		//Ottengo la lista di revisioni su cui fare statistiche
		Vector<Revision> revisions = fileService.getRevisionList(httpsReq.rootCall(parameters));
		
		//Imposto i filtri tramite classe Filter e li applico alla lista di revisioni
		RevisionFilter revisionFilter = new RevisionFilter(revisions);
		revisionFilter.setFilters(parameters);
		revisionFilter.applyFilters();
		
		//Ottengo la lista filtrata di revisioni
		Vector<Revision> filteredRevisions = revisionFilter.getRevisions();
		
		//Eseguo statistiche sulla lista filtrata
		return new RevisionStatistics(filteredRevisions);
	}
	
	/*
	
	@GetMapping("/test_config")
	public @ResponseBody String test(@RequestBody Map<String, Object> parameters)
	{
		parameters.putIfAbsent("url", "https://api.dropboxapi.com/2/files/list_revisions");
		parameters.putIfAbsent("type", "POST");
		
		@SuppressWarnings("unchecked")
		Map<String, String> jsonBody =  (Map<String, String>) parameters.get("body");
		
		
	}
	
	
	/*
	//"list-folder API call
	@GetMapping("/list_folder")
	public @ResponseBody Vector<Content> POSTListFolder(@RequestParam(name="filter_type", required = false, defaultValue = "none") String filterType, @RequestParam(name="token") String token) throws MalformedURLException
	{
		Configuration config = new Configuration("https://api.dropboxapi.com/2/files/list_folder", new ListFolderBody("/Uni", true), "POST", token);
		Vector<Content> contents = fileService.getContentList(httpsReq.rootCall(config));
		FileFilter fileFilter = new FileFilter(contents);
		fileFilter.applyFilters();
		Vector<Content> filteredRevisions = fileFilter.getContents();
				
		
		return contents;
	}
	
	//get-metadata API call
	@GetMapping("/get_metadata")
	public @ResponseBody Content POSTGetMetadata(@RequestParam(name="token") String token) throws MalformedURLException
	{
		
		Configuration config = new Configuration("https://api.dropboxapi.com/2/files/get_metadata", new GetMetadataBody("/Uni",true,true,true), "POST", token);
		Content content = fileService.getMetadata(httpsReq.rootCall(config));
		return content;
	}
	
	//list-revision API call
	@GetMapping("/get_list_revisions")
	public @ResponseBody Revision POSTGetListRevision(@RequestParam(name="token") String token) throws MalformedURLException
	{
		Configuration config = new Configuration("https://api.dropboxapi.com/2/files/list_revisions", new ListRevisionsConfiguration("/Uni/Appunti.paper",10), "POST", token);
		Vector<Revision> revisions = fileService.getRevisionList(httpsReq.rootCall(config));
		return revisions.get(1);
		//Stringa per prova
	}
	*/
	
}
