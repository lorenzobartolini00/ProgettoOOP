package it.univpm.DropboxAnalyzer.Model;

/**
 * Questa classe estende {@link User} e modella un particolare tipo di utente
 * che ha accesso ad uno o più {@link Content} solo in modalità di visualizzazione
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 */
public class Viewer extends User{

	/**
	 * Costruttore
	 * @param accountId Id dell'utente
	 * @param email email dell'utente
	 * @param displayName nome visualizzato dell'utente
	 */
	public Viewer(String accountId, String email, String displayName) {
		super(accountId, email, displayName);
	}

}
