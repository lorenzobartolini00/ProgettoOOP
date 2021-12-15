package it.univpm.DropboxAnalyzer.Model;

import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import org.joda.time.DateTime;
import org.json.JSONObject;

import it.univpm.DropboxAnalyzer.filter.RevisionFilter;

public class Revision {
	private Calendar lastClientModify;
	private Calendar lastServerModify;
	private String revisionId;
	private Long size;
	private Boolean isDownloadable;
	

	public Revision(Calendar lastClientModify, Calendar lastServerModify, String revisionId, Long size,
			Boolean isDownloadable) {
		this.lastClientModify = lastClientModify;
		this.lastServerModify = lastServerModify;
		this.revisionId = revisionId;
		this.size = size;
		this.isDownloadable = isDownloadable;
	}
	
	
	public Revision(String lastClientModify, String lastServerModify, String revisionId, Long size,
			Boolean isDownloadable) {
		this.lastClientModify = toCalendar(lastClientModify);
		this.lastServerModify = toCalendar(lastServerModify);
		this.revisionId = revisionId;
		this.size = size;
		this.isDownloadable = isDownloadable;
	}
	
	//Metodo che converte da stringa a calendar
	private Calendar toCalendar(String stringDate)
	{
		DateTime dateTime = new DateTime(stringDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateTime.toDate());
		return calendar;
	}
	
	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Calendar getLastClientModify() {
		return lastClientModify;
	}
	
	public Long getLastClientModifyInMilliseconds() {
		return lastClientModify.getTimeInMillis();
	}
	
	public void setLastClientModify(Calendar lastClientModify) {
		this.lastClientModify = lastClientModify;
	}
	public Calendar getLastServerModify() {
		return lastServerModify;
	}
	public Long getLastServerModifyInMilliseconds() {
		return lastServerModify.getTimeInMillis();
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

	public Boolean getIsDownloadable() {
		return isDownloadable;
	}

	public void setIsDownloadable(Boolean isDownloadable) {
		this.isDownloadable = isDownloadable;
	}
	
	
}
