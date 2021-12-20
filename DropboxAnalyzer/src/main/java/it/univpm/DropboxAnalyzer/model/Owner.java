package it.univpm.DropboxAnalyzer.model;

/**
 * Questa classe estende {@link User} e modella il proprietario di un {@link Content}
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 */
public class Owner extends User{

	/**
	 * Costruttore
	 * @param accountId Id dell'utente
	 * @param email email dell'utente
	 * @param displayName nome visualizzato dell'utente
	 */
	public Owner(String accountId, String email, String displayName) {
		super(accountId, email, displayName);
	}

	
}
