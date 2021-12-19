package it.univpm.DropboxAnalyzer.filter;

import java.util.Map;
import java.util.Vector;
import java.util.function.Predicate;

import org.json.JSONObject;

import it.univpm.DropboxAnalyzer.Model.Content;
import it.univpm.DropboxAnalyzer.Model.File;
import it.univpm.DropboxAnalyzer.Model.Revision;
import it.univpm.DropboxAnalyzer.configuration.Configuration;

/**
 * Classe che estende {@link FilterImpl} e contiene i metodi per il filtraggio dei file
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 */
public class FileFilter extends FilterImpl implements Filter{
	
	private String fileExtension;
	private boolean onlyDownloadable;
	private Vector<Content> contents;
	
	/**
	 * Costruttore
	 * @param contents Vettore di contenuti (file, folder)
	 */
	public FileFilter(Vector<Content> contents) {
		super();
		this.contents=contents;
	}
	
	@Override
	public void applyFilters() {
		//Dato che in contents ci sono sia folder che files,
		//rimuovo prima tutti gli elementi di contents che non sono istanza
		//della classe File
		contents.removeIf(p -> !(p instanceof File));
		if(maxSize != null) contents.removeIf(aboveThreshold());
		if(minSize != null) contents.removeIf(belowThreshold());
		if(fileExtension != null) contents.removeIf(notRightExtension());
		if(onlyDownloadable != false) contents.removeIf(isNotDownloadable());
	}
	
	@Override
	public void setFilters(Map<String, Object> parameters) {
		if(parameters.containsKey("filters"))
		{
			super.setFilters(parameters);
			Map<String, Object> filters = (Map<String, Object>) parameters.get("filters");
			
			if(filters.containsKey("file_extensions"))
			{
				this.setFileExtension((String)filters.get("file_extensions"));
			}
			
			if(filters.containsKey("only_downloadable"))
			{
				this.setOnlyDownloadable((Boolean)filters.get("only_downloadable")) ;
			}
			else
			{
				this.setOnlyDownloadable(false);
			}
		}
		
	}
	
	/**
	 * Metodo che restituisce il filtro da passare come parametro al metodo RemoveIf()
	 * Elimino il file se non ha la stessa estensione del filtro
	 * @return Funzione a valore booleana p
	 */
	private Predicate<Content> notRightExtension(){
		
		//se l'estensione del file non è la stessa del filtro, la elimino
		return p -> (!((File) p).getExtension().equals(fileExtension));
	}

	/**
	 * Metodo che restituisce il filtro da passare come parametro al metodo RemoveIf()
	 * Elimino il file se getIsDownloadable mi ritorna falso
	 * @return Funzione a valore booleana p
	 */
	private Predicate<Content> isNotDownloadable(){
		
		return p -> (!((File) p).getIsDownloadable());
	}
	
	private Predicate<Content> aboveThreshold() {
		
        return p -> (((File) p).getSize() > maxSize);
    }
	
	/**
	 * Metodo che restituisce il filtro da passare come parametro al metodo RemoveIf()
	 * L'elemento viene rimosso se la dimensione dell'elemento è minore o uguale alla soglia
	 * @return Funzione a valore booleana p
	 */
	private Predicate<Content> belowThreshold() {
		
        return p -> (((File) p).getSize() <= minSize);
    }
	
	//getter e setter
	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public boolean isOnlyDownloadable() {
		return onlyDownloadable;
	}

	public void setOnlyDownloadable(boolean onlyDownloadable) {
		this.onlyDownloadable = onlyDownloadable;
	}

	public Vector<Content> getContents() {
		return contents;
	}

	public void setContents(Vector<Content> contents) {
		this.contents = contents;
	}
	
	
}
