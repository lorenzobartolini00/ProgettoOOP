package it.univpm.DropboxAnalyzer.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

/**
 * Questa classe estende {@link Content} e descrive le proprietà di ogni file
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 */
public class File extends Content{

	//attributi
	private Long size;
	private Boolean isDownloadable;
	private String extension;
	
	/**
	 * Costruttore
	 * @param name Nome del contentuto
	 * @param pathLower Percorso completo in minuscolo
	 * @param pathDisplay Percorso con maiuscole e minuscole da utilizzare solo a scopo di visualizzazione
	 * @param id Id del contenuto
	 * @param size Dimensione del contenuto
	 * @param isDownloadable Boolean che mi dice se il contenuto è scaricabile
	 */
	public File(String name, String pathLower, String pathDisplay, String id, Long size, Boolean isDownloadable) {
		super(name, pathLower, pathDisplay, id);
		this.size = size;
		this.isDownloadable = isDownloadable;
		this.extension = toExtension(pathLower);
	}

	/**
	 * Mi ritorna l'estensione del file
	 * @param pathLower 
	 * @return String extension
	 */
	private String toExtension(String pathLower) {
		String[] s = pathLower.split("\\.");
		ArrayList<String> strings = new ArrayList<String>(Arrays.asList(s));
		return strings.get(strings.size() - 1);
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
	 * @param Long size
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
	 * @param Boolean isDownloadable
	 */
	public void setIsDownloadable(Boolean isDownloadable) {
		this.isDownloadable = isDownloadable;
	}

	/**
	 * metodo che restituisce l'estensione del file
	 * @return extension
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * metodo che setta l'estensione del file
	 * @param String extension
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}
	
	
	
}
