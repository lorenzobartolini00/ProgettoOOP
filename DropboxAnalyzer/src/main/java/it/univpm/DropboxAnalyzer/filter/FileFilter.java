package it.univpm.DropboxAnalyzer.filter;

import java.util.Map;
import java.util.Vector;
import java.util.function.Predicate;

import org.json.JSONObject;

import it.univpm.DropboxAnalyzer.configuration.Configuration;
import it.univpm.DropboxAnalyzer.model.Content;
import it.univpm.DropboxAnalyzer.model.File;
import it.univpm.DropboxAnalyzer.model.Revision;

/**
 * Classe che estende {@link FilterImpl} e contiene i metodi per il filtraggio dei file
 * @author Lorenzo Bartolini
 * @author Francesco Pio Cecca
 */
public class FileFilter extends FilterImpl{
	
	private Integer minNumberOfRevision;
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
		if(minNumberOfRevision != null) contents.removeIf(belowNumberOfRevisionThreshold());
		if(maxSize != null) contents.removeIf(aboveSizeThreshold());
		if(minSize != null) contents.removeIf(belowSizeThreshold());
		if(fileExtension != null) contents.removeIf(notRightExtension());
		if(onlyDownloadable != false) contents.removeIf(isNotDownloadable());
	}
	
	@Override
	public void setFilters(Map<String, Object> parameters) {
		if(parameters.containsKey("filters"))
		{
			super.setFilters(parameters);
			@SuppressWarnings("unchecked")
			Map<String, Object> filters = (Map<String, Object>) parameters.get("filters");
			
			if(filters.containsKey("min_number_of_revisions"))
			{
				this.setMinNumberOfRevision((Integer)filters.get("min_number_of_revisions"));
			}
			
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
	
	Predicate<Content> belowNumberOfRevisionThreshold() {
		
        return p -> (((File) p).getNumberOfRevisions() < minNumberOfRevision);
    }
	
	//Metodo che restituisce il filtro da passare come parametro al metodo RemoveIf()
	//Elimino il file se non ha la stessa estensione del filtro
	private Predicate<Content> notRightExtension(){
		
		//se l'estensione del file non è la stessa del filtro, la elimino
		return p -> (!((File) p).getExtension().equals(fileExtension));
	}

	// Metodo che restituisce il filtro da passare come parametro al metodo RemoveIf()
	// Elimino il file se getIsDownloadable ritorna falso
	private Predicate<Content> isNotDownloadable(){
		
		return p -> (!((File) p).getIsDownloadable());
	}
	
	private Predicate<Content> aboveSizeThreshold() {
		
        return p -> (((File) p).getSize() > maxSize);
    }
	
	//Metodo che restituisce il filtro da passare come parametro al metodo RemoveIf()
	//L'elemento viene rimosso se la dimensione dell'elemento è minore o uguale alla soglia
	Predicate<Content> belowSizeThreshold() {
		
        return p -> (((File) p).getSize() <= minSize);
    }
	
	
	
	public int getMinNumberOfRevision() {
		return minNumberOfRevision;
	}

	public void setMinNumberOfRevision(int minNumberOfRevision) {
		this.minNumberOfRevision = minNumberOfRevision;
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
