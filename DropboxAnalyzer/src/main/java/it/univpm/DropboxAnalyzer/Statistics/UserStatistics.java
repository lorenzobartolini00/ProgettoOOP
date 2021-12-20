package it.univpm.DropboxAnalyzer.Statistics;

import java.util.Map;
import java.util.Vector;

import it.univpm.DropboxAnalyzer.Model.File;
import it.univpm.DropboxAnalyzer.Model.User;
import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;

/**
 * Classe che implementa l'interfaccia {@link Statistics}
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 */
public class UserStatistics implements Statistics{

	private Vector<User> users;
		
	public UserStatistics(Vector<User> users) {
		this.users = users;
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
