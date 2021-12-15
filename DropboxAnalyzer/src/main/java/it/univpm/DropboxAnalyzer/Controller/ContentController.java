package it.univpm.DropboxAnalyzer.Controller;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.util.Vector;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
import it.univpm.DropboxAnalyzer.configuration.ListRevisionsBody;
import it.univpm.DropboxAnalyzer.filter.RevisionFilter;

@Controller
public class ContentController {
	@Autowired
	private FileService fileService;
	@Autowired
	private HTTPSRequest httpsReq;
	
	@GetMapping("/get_revision_statistics")
	public @ResponseBody RevisionStatistics POSTRevisionStatistics(@RequestParam(name="token") String token) throws MalformedURLException
	{
		Configuration config = new Configuration("https://api.dropboxapi.com/2/files/list_revisions", new ListRevisionsBody("/Uni/Generali.docx", 10), "POST", token);
		Vector<Revision> revisions = fileService.getRevisionList(httpsReq.rootCall(config));
		
		RevisionFilter revisionFilter = new RevisionFilter(revisions);
		Vector<Revision> filteredRevisions = revisionFilter.filter();
		return new RevisionStatistics(filteredRevisions);
	}
	
	//"list-folder API call
	@GetMapping("/list_folder")
	public @ResponseBody Content POSTListFolder(@RequestParam(name="token") String token) throws MalformedURLException
	{
		Configuration config = new Configuration("https://api.dropboxapi.com/2/files/list_folder", new ListFolderBody("/Uni", true), "POST", token);
		Vector<Content> contents = fileService.getContentList(httpsReq.rootCall(config));
		
		return contents.get(4);
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
		Configuration config = new Configuration("https://api.dropboxapi.com/2/files/list_revisions", new ListRevisionsBody("/Uni/Appunti.paper",10), "POST", token);
		Vector<Revision> revisions = fileService.getRevisionList(httpsReq.rootCall(config));
		return revisions.get(1);
		//Stringa per prova
	}
	
	
}
