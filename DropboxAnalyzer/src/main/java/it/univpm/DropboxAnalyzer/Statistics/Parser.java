package it.univpm.DropboxAnalyzer.Statistics;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class Parser {
	/**
	 * Metodo che converte un long di bytes in una stringa formattata in un formato leggibile dall'uomo
	 * @param bytes da convertire
	 * @return bytes convertiti sotto forma di stringa
	 */
	public static String humanReadableBytes(long bytes) 
	{
		long absB = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
		if (absB < 1024) {
			return bytes + " B";
		}
		long value = absB;
		CharacterIterator ci = new StringCharacterIterator("KMGTPE");
		for (int i = 40; i >= 0 && absB > 0xfffccccccccccccL >> i; i -= 10) {
			value >>= 10;
			ci.next();
		}
		value *= Long.signum(bytes);
		return String.format("%.1f %ciB", value / 1024.0, ci.current());
	}
	
	/**
	 * Metodo che converte un double di ore in una stringa formattata in un formato leggibile dall'uomo
	 * @param ore da convertire
	 * @return tempo convertito sotto forma di stringa
	 */
	public static String humanReadableTime(double time) {
		int hours = (int) time;
		double decimals = (time-hours)*60;
		int mins = (int) decimals;
		return hours + "h," + mins + "m";
		
	}
	
	/**
	 * Metodo che permette di convertire un numero in una stringa, aggiungendo in coda il simbolo %
	 * @param numero da convertire in stringa
	 * @return numero convertito in stringa
	 */
	public static String toPercentage(double numero){
	  	return String.valueOf(numero) + "%";
	 }
	 
	
	/**
	 * Metodo che arrotonda @param value a @param places numeri dopo la virgola
	 * @param value double da arrotondare
	 * @param places int numero di cifre dopo la virgola da mantenere
	 */
	 public static double round(double value, int places) 
	 {
		 BigDecimal bd = BigDecimal.valueOf(value);
		 bd = bd.setScale(places, RoundingMode.HALF_UP);
		 return bd.doubleValue();
	 }

}
