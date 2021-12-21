package it.univpm.DropboxAnalyzer.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

/**
 * Questa classe estende {@link Content} e modella un file di DropBox.
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 */
public class File extends Content{

	//attributi
	private Long size;
	private Boolean isDownloadable;
	private String extension;
	private Vector<Revision> revisions;
	private Integer numberOfRevisions;
	
	/**
	 * Costruttore
	 * @param name Nome del contentuto
	 * @param pathLower Percorso completo in minuscolo
	 * @param pathDisplay Percorso con maiuscole e minuscole da utilizzare solo a scopo di visualizzazione
	 * @param id Id del contenuto
	 * @param size Dimensione del contenuto
	 * @param isDownloadable Boolean che mi dice se il contenuto è scaricabile
	 */
	public File(String name, String pathLower, String pathDisplay, String id, Long size, Boolean isDownloadable, Vector<Revision> revisions) {
		super(name, pathLower, pathDisplay, id);
		this.size = size;
		this.isDownloadable = isDownloadable;
		this.extension = getExtension();
		this.revisions = revisions;
		this.numberOfRevisions = revisions.size();
	}
	

	/**
	 * metodo che restituisce la dimensione del file
	 * @return size
	 */
	public Long getSize() {
		return size;
	}

	/**
	 * metodo che setta la dimensione del file
	 * @param size
	 */
	public void setSize(Long size) {
		this.size = size;
	}

	/**
	 * metodo che restituisce un boolean "isDownloadable"
	 * @return isDownloadable
	 */
	public Boolean getIsDownloadable() {
		return isDownloadable;
	}

	/**
	 * metodo che setta un boolean "isDownloadable"
	 * @param  isDownloadable
	 */
	public void setIsDownloadable(Boolean isDownloadable) {
		this.isDownloadable = isDownloadable;
	}

	/**
	 * metodo che restituisce l'estensione del file
	 * @return extension
	 */
	public String getExtension() {
		String[] s = pathLower.split("\\.");
		ArrayList<String> strings = new ArrayList<String>(Arrays.asList(s));
		return strings.get(strings.size() - 1);
	}

	/**
	 * metodo che setta l'estensione del file
	 * @param extension
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}


	public Integer getNumberOfRevisions() {
		return numberOfRevisions;
	}


	public void setNumberOfRevisions(Integer numberOfRevisions) {
		this.numberOfRevisions = numberOfRevisions;
	}


	public Vector<Revision> getRevisions() {
		return revisions;
	}


	public void setRevisions(Vector<Revision> revisions) {
		this.revisions = revisions;
	}
	
	
	
	
	
	
	
}
