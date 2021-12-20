package it.univpm.DropboxAnalyzer.Model;

/**
 * Questa classe modella un generico Utente di DropBox 
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 */
public class User {

	private String accountId;
	private String email;
	private String displayName;
	
	/**
	 * Costruttore
	 * @param accountId Id dell'utente
	 * @param email email dell'utente
	 * @param displayName nome visualizzato dell'utente
	 */
	public User(String accountId, String email, String displayName) {
		this.accountId = accountId;
		this.email = email;
		this.displayName = displayName;
	}

	//getter e setter
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	
}
