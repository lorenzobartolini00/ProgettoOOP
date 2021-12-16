package it.univpm.DropboxAnalyzer.exceptions;

public class BadFormatException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BadFormatException(String context, String cause)
	{
		super("Invalid data in " + context + ": "+ cause + " is missing"); 
	}
}
