package it.univpm.DropboxAnalyzer.Model;

public class User {

	//attributi
	private String accountId;
	private String email;
	private String displayName;
	
	//costruttore
	public User(String accountId, String email, String displayName) {
		this.accountId = accountId;
		this.email = email;
		this.displayName = displayName;
	}

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
