package it.univpm.DropboxAnalyzer.Model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.joda.time.DateTime;
import org.json.JSONObject;

import it.univpm.DropboxAnalyzer.Statistics.Parser;
import it.univpm.DropboxAnalyzer.filter.RevisionFilter;

/**
 * Questa classe modella una generica revisione di un file DropBox.
 * Per revisione si intende la versione di un file in un particolare istante di tempo
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 */
public class Revision {
	private Calendar lastClientModify;
	private Calendar lastServerModify;
	private String revisionId;
	private Long size;
	private Boolean isDownloadable;
	
	/**
	 * Costruttore
	 * @param lastClientModify (Calendar) Tempo di modifica impostato dal client desktop quando il file è stato aggiunto a Dropbox
	 * @param lastServerModify (Calendar) L'ultima volta che il file è stato modificato su Dropbox
	 * @param revisionId Id della revisione
	 * @param size Dimensione del file nella revisione corrente
	 * @param isDownloadable Boolean che mi dice se il contenuto è scaricabile
	 */
	public Revision(Calendar lastClientModify, Calendar lastServerModify, String revisionId, Long size,
			Boolean isDownloadable) {
		this.lastClientModify = lastClientModify;
		this.lastServerModify = lastServerModify;
		this.revisionId = revisionId;
		this.size = size;
		this.isDownloadable = isDownloadable;
	}
	
	/**
	 * Costruttore
	 * @param lastClientModify (String) Tempo di modifica impostato dal client desktop quando il file è stato aggiunto a Dropbox
	 * @param lastServerModify (String) L'ultima volta che il file è stato modificato su Dropbox
	 * @param revisionId Id della revisione
	 * @param size dimensione del file nella revisione corrente
	 * @param isDownloadable Boolean che mi dice se il contenuto è scaricabile
	 */
	public Revision(String lastClientModify, String lastServerModify, String revisionId, Long size,
			Boolean isDownloadable) {
		this.lastClientModify = toCalendar(lastClientModify);
		this.lastServerModify = toCalendar(lastServerModify);
		this.revisionId = revisionId;
		this.size = size;
		this.isDownloadable = isDownloadable;
	}
	
	/**
	 * Metodo che converte da String a Calendar
	 * @param stringDate Data sotto forma di stringa
	 * @return Data sotto forma di Calendar
	 */
	private Calendar toCalendar(String stringDate)
	{
		DateTime dateTime = new DateTime(stringDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateTime.toDate());
		return calendar;
	}
	
	//getter e setter
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

	public String toString() {
		return "Revision [lastClientModify=" + lastClientModify + ", lastServerModify=" + lastServerModify
				+ ", revisionId=" + revisionId + ", size=" + size + ", isDownloadable=" + isDownloadable + "]";
	}
	
	public Map<String, Object> toMap() {
		
		Map<String, Object> map=new HashMap<String, Object>();
	
		String stringSize=Parser.humanReadableBytes(size);

		map.put("last_client_modify", lastClientModify.getTime().toString());
		map.put("last_server_modify", lastServerModify.getTime().toString());
		map.put("revision_id", this.revisionId);
		map.put("size", stringSize);
		
		return map;
	}
	
}
