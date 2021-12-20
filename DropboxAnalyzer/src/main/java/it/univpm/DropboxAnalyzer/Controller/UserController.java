package it.univpm.DropboxAnalyzer.Controller;

import java.net.MalformedURLException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import it.univpm.DropboxAnalyzer.Model.Content;
import it.univpm.DropboxAnalyzer.Model.User;
import it.univpm.DropboxAnalyzer.Service.HTTPSRequest;
import it.univpm.DropboxAnalyzer.Service.UserService;
import it.univpm.DropboxAnalyzer.configuration.ListFileMembersConfiguration;
import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;


/**
 * Gestisce le chiamate delle rotte relative relative agli {@link User}
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 */
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private HTTPSRequest httpsReq;
	@Autowired
	private ListFileMembersConfiguration membersConfig;
	
	/**
	 * Risponde alla chiamata HTTP restituendo la lista degli utenti che possono accedere ad un determinato file
	 * @param parametersMap con all'interno i parametri di configurazione
	 * @param token Codice d'accesso per l'autenticazione DropBox
	 * @return Ritorna un ResponseEntity di tipo Object
	 * @throws MalformedURLException Generato per indicare che si è verificato un URL non valido
	 */
	//list-file-member API call
	@GetMapping("/list_file_members")
	public ResponseEntity<Object> POSTListFileMember(@RequestBody Map<String, Object> parameters, @RequestParam(name="token") String token) throws MalformedURLException{
		
		parameters.put("token", token);
		
		try {
		membersConfig.checkFormat(parameters);
		membersConfig.setDefault(parameters);
		}
		catch (BadFormatException e){
			//Nel caso in cui venga lanciata l'eccezione, oltre al messaggio di errore, viene
			//dichiarato che lo stato Http 400 BAD REQUEST
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST );
		}
		
		//Ottengo la lista di utenti che possono accedere ad un file 
		Vector<User> users;
		
		try {
			users = userService.getUserList(httpsReq.rootCall(parameters));
		}
		catch (NullPointerException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		catch(Exception e)
		{
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST );
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
}
