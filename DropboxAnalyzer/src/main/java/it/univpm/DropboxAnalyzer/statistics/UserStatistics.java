package it.univpm.DropboxAnalyzer.statistics;

import java.util.Map;
import java.util.Vector;

import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;
import it.univpm.DropboxAnalyzer.model.File;
import it.univpm.DropboxAnalyzer.model.Revision;
import it.univpm.DropboxAnalyzer.model.User;

/**
 * Classe che implementa l'interfaccia {@link Statistics} , specifica per gli oggetti di tipo {@link User}
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
