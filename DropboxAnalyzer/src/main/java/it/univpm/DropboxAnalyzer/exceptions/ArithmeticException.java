package it.univpm.DropboxAnalyzer.exceptions;

/**
* @author Francesco Pio Cecca
* @author Lorenzo Bartolini
*/

public class ArithmeticException {

private String message;
	
	/**
	 * Costruttore
	 * @param message è il messaggio che mi viene restituito quando viene effettuata un'operazione matematicamente errata
	 */
	public ArithmeticException() {
		super();
		this.message="Operazione non consentita";
	}

	/**
	 * Restituisce il massaggio d'errore presente nel costruttore
	 * @return messaggio d'errore sotto forma di stringa
	 */
	public String getMessage() {
		return message;
	}

	
}
