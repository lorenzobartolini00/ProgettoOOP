package it.univpm.DropboxAnalyzer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.univpm.DropboxAnalyzer.configuration.ListRevisionsConfiguration;
import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;

class TestBadFormatException {
	
	private BadFormatException e;
	private ListRevisionsConfiguration revisionConfig;
	Map<String, Object> info;
	
	private Map<String, Object> parameters;

	@BeforeEach
	void setUp() throws Exception {
		parameters = new HashMap<String, Object>();
		parameters.put("token", "G4J8eRdP9roAAAAAAAAAAbvWRhutuOx6QkF7rz2VDCjVr5tQMhM3InqV16_tajQB");
		info = new HashMap<String, Object>();
		
		revisionConfig = new ListRevisionsConfiguration();
		
		//Inserisco appositamente un campo errato, per verificare 
		//che venga effettivmente sollevata un'eccezione
		
		info.put("limit", 100);
		parameters.put("info", info);
		revisionConfig.setDefault(parameters);	
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Test gestione eccezione nome parametro errato")
	void test1() {
		//Al posto di "path", inserisco un'altro campo errato
		info.put("not_path", "/Uni/Generali.docx");
		
		e = assertThrows(BadFormatException.class, () -> {
			revisionConfig.checkFormat(parameters);
		}) ;
		
		assertEquals("Invalid data in body/info: 'path' is missing", e.getMessage());
	}
	
	@Test
	@DisplayName("Test gestione eccezione tipo parametro errato")
	void test2() {
		//Al parametro "path" associo un tipo errato, ad esempio un boolean, al posto di una tringa
		info.put("path", true);
		
		e = assertThrows(BadFormatException.class, () -> {
			revisionConfig.checkFormat(parameters);
		}) ;
		
		assertEquals("Invalid data in body/info: 'path' has wrong type", e.getMessage());
	}

}
