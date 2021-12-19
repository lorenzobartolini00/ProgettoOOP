package it.univpm.DropboxAnalyzer.Model;

/**
 * Questa classe descrive le proprietà di ogni file
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 */
public class Folder extends Content{

	/**
	 * Costruttore
	 * @param name Nome del contentuto
	 * @param pathLower Percorso completo in minuscolo
	 * @param pathDisplay Percorso con maiuscole e minuscole da utilizzare solo a scopo di visualizzazione
	 * @param id Id del contenuto
	 */
	public Folder(String name, String pathLower, String pathDisplay, String id) {
		super(name, pathLower, pathDisplay, id);
	}

}
