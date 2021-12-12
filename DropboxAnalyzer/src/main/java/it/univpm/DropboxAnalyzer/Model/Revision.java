package it.univpm.DropboxAnalyzer.Model;

import java.util.Calendar;

public class Revision {
	private Calendar lastClientModify;
	private Calendar lastServerModify;
	private String revisionId;
	
	public Revision(Calendar lastClientModify, Calendar lastServerModify, String revisionId) {
		this.lastClientModify = lastClientModify;
		this.lastServerModify = lastServerModify;
		this.revisionId = revisionId;
	}
	
	public Revision(String lastClientModify, String lastServerModify, String revisionId) {
		this.lastClientModify = toCalendar(lastClientModify);
		this.lastServerModify = toCalendar(lastServerModify);
		this.revisionId = revisionId;
	}
	
	//Metodo che converte da stringa a calendar
	private Calendar toCalendar(String date)
	{
		
		return null;
	}
	
	public Calendar getLastClientModify() {
		return lastClientModify;
	}
	public void setLastClientModify(Calendar lastClientModify) {
		this.lastClientModify = lastClientModify;
	}
	public Calendar getLastServerModify() {
		return lastServerModify;
	}
	public void setLastServerModify(Calendar lastServerModify) {
		this.lastServerModify = lastServerModify;
	}
	public String getRevisionId() {
		return revisionId;
	}
	public void setRevisionId(String revisionId) {
		this.revisionId = revisionId;
	}
	
	
}
