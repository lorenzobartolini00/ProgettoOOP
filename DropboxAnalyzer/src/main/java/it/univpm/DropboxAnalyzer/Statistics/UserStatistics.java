package it.univpm.DropboxAnalyzer.Statistics;

import java.util.Map;
import java.util.Vector;

import it.univpm.DropboxAnalyzer.Model.File;
import it.univpm.DropboxAnalyzer.Model.User;
import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;

public class UserStatistics implements Statistics{

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

	@Override
	public void updateStatistics() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Object> addStatistic(Map<String, Object> data) {
		// TODO Auto-generated method stub
		return null;
	}
}
