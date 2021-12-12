package it.univpm.DropboxAnalyzer.Service;


import java.util.Vector;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import it.univpm.DropboxAnalyzer.Model.*;

/*La utilizzerò per gestire i dati del package Model
  Essa comunica con le API, che sono al livello superiore.  */

@Service
public interface FileService {
	
	public Vector<Revision> getRevisionList(JSONObject jsonObjectRevisions);
	public Vector<Content> getContentList(JSONObject jsonObjectFolders);
	public Content getMetadata(JSONObject jsonObjectContent); //non è un vettore perchè mi restituisce ciò che riguarda un solo file
}
