package it.univpm.DropboxAnalyzer.Statistics;

import java.util.Vector;

import it.univpm.DropboxAnalyzer.Model.File;
import it.univpm.DropboxAnalyzer.Model.User;

public class UserStatistics {

	private double usersEverage;
	private Vector<User> users;
	private Vector<File> files;
	
	
	
	public UserStatistics(Vector<User> users) {
		this.users = users;
	}

	public double getUsersEverage() {
		return usersEverage;
	}

	//implementare un setter che mi dice quanti utenti in media hanno accesso ai file di dropbox
	public void setUsersEverage() {
		
		String id=null;
		
		for(User user:users) {
			
		}
	}
}
