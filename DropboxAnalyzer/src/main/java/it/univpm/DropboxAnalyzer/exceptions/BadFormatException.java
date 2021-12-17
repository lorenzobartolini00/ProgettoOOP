package it.univpm.DropboxAnalyzer.exceptions;

/**
 * Questa classe contiene il metodo che genera un'eccezione quando l'utente non inserisce 
 * i parametri richiesti oppure quando questi hanno il tipo errato 
 * 
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 *
 */

public class BadFormatException extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore
	 * @param context Luogo in cui si presenta l'errore
	 * @param cause Parametro che ha generato l'errore
	 * @param type Tipologia di errore
	 */
	public BadFormatException(String context, String cause, String type)
	{
		super("Invalid data in " + context + ": "+ "'"+ cause +"' "+ type); 
	}
}
