package it.univpm.DropboxAnalyzer.exceptions;

/**
 * @author Francesco Pio Cecca
 * @author Lorenzo Bartolini
 */


public class WrongParamException extends Exception{

	private String message;
	
	/**
	 * Costruttore
	 * @param message è il messaggio che mi viene restituito quando viene inserito un parametro errato
	 */
	public WrongParamException() {
		super();
		this.message="Il parametro che hai inserito non è corretto";
	}

	/**
	 * Restituisce il massaggio d'errore presente nel costruttore
	 * @return messaggio d'errore sotto forma di stringa
	 */
	public String getMessage() {
		return message;
	}

	
	
}
