package it.univpm.DropboxAnalyzer.controller;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.univpm.DropboxAnalyzer.configuration.Configuration;
import it.univpm.DropboxAnalyzer.configuration.GetMetadataBody;
import it.univpm.DropboxAnalyzer.configuration.ListFolderConfiguration;
import it.univpm.DropboxAnalyzer.configuration.ListRevisionsConfiguration;
import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;
import it.univpm.DropboxAnalyzer.filter.FileFilter;
import it.univpm.DropboxAnalyzer.filter.RevisionFilter;
import it.univpm.DropboxAnalyzer.model.Content;
import it.univpm.DropboxAnalyzer.model.Revision;
import it.univpm.DropboxAnalyzer.service.FileService;
import it.univpm.DropboxAnalyzer.service.HTTPSRequest;
import it.univpm.DropboxAnalyzer.statistics.RevisionSizeStatistics;
import it.univpm.DropboxAnalyzer.statistics.RevisionStatistics;
import it.univpm.DropboxAnalyzer.statistics.RevisionTimeStatistics;
import it.univpm.DropboxAnalyzer.statistics.Statistics;

/**
 * Gestisce le chiamate delle rotte relative ai {@link Content}, alle {@link Revision} e alle
 * {@link RevisionStatistics}
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
	 * Risponde alla chiamata HTTP restituendo statistiche sulle revisioni, eventualmente filtrate
	 * @param parameters Map contenente i parametri di configurazione
	 * @param token Codice d'accesso per l'autenticazione DropBox
	 * @return ResponseEntity di tipo Object.
	 * @throws MalformedURLException Generato per indicare che è stato fornito un URL non valido. Non viene mai lanciata, dato
	 */
	@GetMapping( value = {"/revision_statistics/{statistic_type}", "/revision_statistics"} )
	public ResponseEntity<Object> POSTRevisionStatistics(@RequestBody Map<String, Object> parameters, @PathVariable(value="statistic_type", required = false) Optional<String> statisticType, @RequestParam(name="token") String token) throws MalformedURLException
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
		Vector<Revision> revisions = null;
		try {
			revisions = fileService.getRevisionList(httpsReq.rootCall(parameters));
		}
		catch (NullPointerException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		
		//Imposto i filtri tramite classe Filter e li applico alla lista di revisioni
		RevisionFilter revisionFilter = new RevisionFilter(revisions);
		try
		{
			revisionFilter.setFilters(parameters);
		}
		catch(ClassCastException e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST );
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST );
		}
		revisionFilter.applyFilters();
		
		//Eseguo statistiche sulla lista filtrata
		ResponseEntity<Object> output = null;
		Map<String, Object> data = new HashMap<String, Object>();
		try
		{
			//Mi assicuro che l'utente abbia inserito il parametro statistic_type, nel caso contrario, setto
			//un valore di default
			String type = null;
			if(statisticType.isPresent())
			{
				type = statisticType.get();
			}
			else
			{
				//Valore di default per statistic_type
				type = "all";
			}
			
			//l'oggetto "data" è la mappa che contiene le statistiche sulle revisioni, divise per tipologia
			if(type.equals("time") || type.equals("all"))
			{
				RevisionTimeStatistics timeStats = new RevisionTimeStatistics(revisions);
				data = timeStats.addStatistic(data);
			}
			if(type.equals("size") || type.equals("all"))
			{
				RevisionSizeStatistics sizeStats = new RevisionSizeStatistics(revisions);
				data = sizeStats.addStatistic(data);
			}
			RevisionStatistics genericStats = new RevisionStatistics(revisions);
			data = genericStats.addStatistic(data);
			output = new ResponseEntity<>(data, HttpStatus.OK);
		}
		catch(NullPointerException e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST );
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST );
		}
		return output;
	}
	
	
	
	/**
	 * Risponde alla chiamata HTTP restituendo una lista, eventualmente filtrata, di file
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
		
		
		//Ottengo la lista di contenuti su cui fare statistiche
		Vector<Content> contents = null;
		try {
			contents = fileService.getContentList(httpsReq.rootCall(parameters));
		}
		catch (NullPointerException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		
		//Imposto i filtri tramite classe Filter e li applico alla lista di revisioni
		FileFilter fileFilter = new FileFilter(contents);
		try
		{
			fileFilter.setFilters(parameters);
		}
		catch(ClassCastException e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST );
		}
		fileFilter.applyFilters();
		
		//Ritorno lista di file filtrata
		return new ResponseEntity<>(contents, HttpStatus.OK);
	}
	
	
	/**
	 * Risponde alla chiamata HTTP restituendo una lista di revisioni eventualmente filtrate
	 * @param parameters Map contenente i parametri di configurazione
	 * @param token Codice d'accesso per l'autenticazione DropBox
	 * @return Ritorna un ResponseEntity di tipo Object
	 * @throws MalformedURLException Generato per indicare che si è verificato un URL non valido
	 */
	@GetMapping("/list_revisions")
	public ResponseEntity<Object> POSTGetListRevision(@RequestBody Map<String, Object> parameters, @RequestParam(name="token") String token) throws MalformedURLException
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
		Vector<Revision> revisions = null;
		try {
			revisions = fileService.getRevisionList(httpsReq.rootCall(parameters));
		}
		catch (NullPointerException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		
		//Imposto i filtri tramite classe Filter e li applico alla lista di revisioni
		RevisionFilter revisionFilter = new RevisionFilter(revisions);
		try
		{
			revisionFilter.setFilters(parameters);
		}
		catch(ClassCastException e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST );
		}
		revisionFilter.applyFilters();
		Vector<HashMap<String, Object>> jsonRevisions = new Vector<HashMap<String, Object>>();
		revisions.forEach(revision -> jsonRevisions.add((HashMap<String, Object>) revision.toMap()));
		return new ResponseEntity<>(jsonRevisions, HttpStatus.OK);
	}
	
	
}
