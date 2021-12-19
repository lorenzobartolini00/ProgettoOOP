package it.univpm.DropboxAnalyzer.Service;

import java.util.*;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import it.univpm.DropboxAnalyzer.Model.*;

/**Interfaccia che gestisce i dati del package Model. Essa comunica con le API, che sono al livello superiore.
 * Questa classe descrive le proprietà di ogni utente.
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 */
@Service
public interface UserService {

	/**
	 * Metodo che da un JSONObject mi restituisce un vettore di utenti
	 * @param jsonObjectRevisions JSONObject di utenti
	 * @return vettore di utenti
	 */
	public Vector<User> getUserList(JSONObject jsonObj);
}
