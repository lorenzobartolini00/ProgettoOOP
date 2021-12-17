package it.univpm.DropboxAnalyzer.Controller;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import it.univpm.DropboxAnalyzer.configuration.Configuration;
import it.univpm.DropboxAnalyzer.configuration.GetMetadataBody;
import it.univpm.DropboxAnalyzer.configuration.ListFolderConfiguration;
import it.univpm.DropboxAnalyzer.configuration.ListRevisionsConfiguration;
import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;
import it.univpm.DropboxAnalyzer.filter.FileFilter;
import it.univpm.DropboxAnalyzer.filter.RevisionFilter;

/**
 * Gestisce le chiamate delle rotte
 * 
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 *
 */

@Controller
public class ContentController {
	@Autowired
	private FileService fileService;
	@Autowired
	private HTTPSRequest httpsReq;
	@Autowired
	private ListRevisionsConfiguration revisionConfig;
	@Autowired
	private ListFolderConfiguration folderConfig;
	
	/**
	 * Risponde alla chiamata HTTP restituendo statistiche, opportunamente filtrate, sulle revisioni 
	 * @param parameters Map con all'interno i parametri di configurazione
	 * @param token Codice d'accesso per l'autenticazione DropBox
	 * @return Ritorna un ResponseEntity di tipo Object
	 * @throws MalformedURLException Generato per indicare che si è verificato un URL non valido
	 */
	@GetMapping("/revision_statistics")
	public ResponseEntity<Object> POSTRevisionStatistics(@RequestBody Map<String, Object> parameters, @RequestParam(name="token") String token) throws MalformedURLException
	{
		parameters.put("token", token);
		
		try {
			revisionConfig.checkFormat(parameters);
			revisionConfig.setDefault(parameters);
		} catch (BadFormatException e) {
			//Nel caso in cui venga lanciata l'eccezione, oltre al messaggio di errore, viene
			//dichiarato che lo stato Http 400 BAD REQUEST
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST );
		}
		
		//Ottengo la lista di revisioni su cui fare statistiche
		Vector<Revision> revisions = fileService.getRevisionList(httpsReq.rootCall(parameters));
		
		//Imposto i filtri tramite classe Filter e li applico alla lista di revisioni
		RevisionFilter revisionFilter = new RevisionFilter(revisions);
		revisionFilter.setFilters(parameters);
		revisionFilter.applyFilters();
		
		//Eseguo statistiche sulla lista filtrata
		RevisionStatistics stats = new RevisionStatistics(revisions);
		return new ResponseEntity<>(stats.toMap(), HttpStatus.OK);
	}
	
	
	
	/**
	 * Risponde alla chiamata HTTP restituendo una lista, opportunamente filtrata, di file
	 * @param parameters Map con all'interno i parametri di configurazione
	 * @param token Codice d'accesso per l'autenticazione DropBox
	 * @return Ritorna un ResponseEntity di tipo Object
	 * @throws MalformedURLException Generato per indicare che si è verificato un URL non valido
	 */
	@GetMapping("/list_files")
	public ResponseEntity<Object> POSTListFolder(@RequestBody Map<String, Object> parameters, @RequestParam(name="token") String token) throws MalformedURLException
	{
		parameters.put("token", token);
		try {
			folderConfig.checkFormat(parameters);
			folderConfig.setDefault(parameters);
		} catch (BadFormatException e) {
			//Nel caso in cui venga lanciata l'eccezione, oltre al messaggio di errore, viene
			//dichiarato che lo stato Http 400 BAD REQUEST
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST );
		}
		
		
		//Ottengo la lista di revisioni su cui fare statistiche
		Vector<Content> contents = fileService.getContentList(httpsReq.rootCall(parameters));
		
		//Imposto i filtri tramite classe Filter e li applico alla lista di revisioni
		FileFilter fileFilter = new FileFilter(contents);
		fileFilter.setFilters(parameters);
		fileFilter.applyFilters();
		
		//Ritorno lista di file filtrata
		return new ResponseEntity<>(contents, HttpStatus.OK);
	}
	
	/*
	
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
