package it.univpm.DropboxAnalyzer.Service;

import java.util.*;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import it.univpm.DropboxAnalyzer.Model.*;

/**Le classi che implementano questa interfaccia si occupano di costruire oggetti({@link User}) sulla base
 * delle informazioni contenute nel file Json passato 
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 */
@Service
public interface UserService {

	/**
	 * Metodo che a partire da un JSONObject restituisce un vettore di {@link User}
	 * @param jsonObjectRevisions JSONObject di utenti
	 * @return vettore di {@link User}
	 */
	public Vector<User> getUserList(JSONObject jsonObj);
}
