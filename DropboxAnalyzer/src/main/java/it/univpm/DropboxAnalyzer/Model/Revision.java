package it.univpm.DropboxAnalyzer.Model;

import java.util.Calendar;

public class Revision {
	private String lastClientModify;
	private String lastServerModify;
	private String revisionId;
	
	public Revision(String lastClientModify, String lastServerModify, String revisionId) {
		this.lastClientModify = lastClientModify;
		this.lastServerModify = lastServerModify;
		this.revisionId = revisionId;
	}
	public String getLastClientModify() {
		return lastClientModify;
	}
	public void setLastClientModify(String lastClientModify) {
		this.lastClientModify = lastClientModify;
	}
	public String getLastServerModify() {
		return lastServerModify;
	}
	public void setLastServerModify(String lastServerModify) {
		this.lastServerModify = lastServerModify;
	}
	public String getRevisionId() {
		return revisionId;
	}
	public void setRevisionId(String revisionId) {
		this.revisionId = revisionId;
	}
	
	
}
