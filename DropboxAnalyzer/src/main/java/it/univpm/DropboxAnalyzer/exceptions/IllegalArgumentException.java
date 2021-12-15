package it.univpm.DropboxAnalyzer.exceptions;

/**
 * @author Francesco Pio Cecca
 * @author Lorenzo Bartolini
 */

public class IllegalArgumentException {

	private String message;
		
		/**
		 * Costruttore
		 * @param message è il messaggio che mi viene restituito quando viene inserito un valore che non soddisfa le aspattative previste
		 */
		public IllegalArgumentException() {
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
