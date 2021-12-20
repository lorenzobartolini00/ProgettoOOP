package it.univpm.DropboxAnalyzer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.DropboxAnalyzer.configuration.ListRevisionsConfiguration;
import it.univpm.DropboxAnalyzer.model.Revision;
import it.univpm.DropboxAnalyzer.service.FileService;
import it.univpm.DropboxAnalyzer.service.FileServiceImpl;
import it.univpm.DropboxAnalyzer.service.HTTPSRequest;

class TestStatistics {
	
	private Map<String, Object> parameters;
	private ListRevisionsConfiguration revisionConfig;
	private HTTPSRequest httpsRequest;
	private JSONObject jsonObjectRevisionList;
	private Vector<Revision> revisions;
	private 

	@BeforeEach
	void setUp() throws Exception {
		parameters = new HashMap<String, Object>();
		revisionConfig = new ListRevisionsConfiguration();
		
		
		parameters.put("token", "G4J8eRdP9roAAAAAAAAAAbvWRhutuOx6QkF7rz2VDCjVr5tQMhM3InqV16_tajQB");
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("path", "/Uni/Generali.docx");
		info.put("limit", 100);
		parameters.put("info", info);
		revisionConfig.setDefault(parameters);	
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	//Test per verificare il funzionamento del metodo setDefault() della classe di configurazione
	@Test
	void testSetDefault() {
		assertEquals("https://api.dropboxapi.com/2/files/list_revisions", parameters.get("url"));
	}
	
	//Test per verificare che la funzione rootCall() ritorni un jsonObject che contenga la lista di revisioni
	//e che siano presenti gli attributi fondamentali
		@Test
		void testRootCall() {
			httpsRequest = new HTTPSRequest();
			
			JSONArray jsonRevisionList = null;
			
			try {
				jsonObjectRevisionList = httpsRequest.rootCall(parameters);
				jsonRevisionList = jsonObjectRevisionList.getJSONArray("entries");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			JSONObject jsonRevision = null;
			try {
				jsonRevision = jsonRevisionList.getJSONObject(0);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			assertTrue(jsonRevision.has("name"));
			assertTrue(jsonRevision.has("path_lower"));
			assertTrue(jsonRevision.has("path_display"));
			assertTrue(jsonRevision.has("id"));
			assertTrue(jsonRevision.has("client_modified"));
			assertTrue(jsonRevision.has("server_modified"));
			assertTrue(jsonRevision.has("path_display"));
			assertTrue(jsonRevision.has("rev"));
		}
		
		//Test allo scopo di verificare il corretto funzionamento del metodo che converte da
		//jsonObject a classe Revision nella classe FileServiceImpl
		@Test
		void testService() {
			//Lista di revisioni di prova
			String jsonRevisions = "{\r\n"
					+ "    \"is_deleted\": false,\r\n"
					+ "    \"entries\": [\r\n"
					+ "        {\r\n"
					+ "            \"name\": \"Generali.docx\",\r\n"
					+ "            \"path_lower\": \"/uni/generali.docx\",\r\n"
					+ "            \"path_display\": \"/Uni/Generali.docx\",\r\n"
					+ "            \"id\": \"id:xjKzQLUfUpAAAAAAAAAARQ\",\r\n"
					+ "            \"client_modified\": \"2021-12-17T18:44:29Z\",\r\n"
					+ "            \"server_modified\": \"2021-12-17T18:44:30Z\",\r\n"
					+ "            \"rev\": \"5d35bedefdfb189398f81\",\r\n"
					+ "            \"size\": 1055630,\r\n"
					+ "            \"is_downloadable\": true,\r\n"
					+ "            \"content_hash\": \"31eac7ad513f9afc61c8b85cced4bff30d58686de6391b39594a771a15fd1f44\"\r\n"
					+ "        },\r\n"
					+ "        {\r\n"
					+ "            \"name\": \"Generali.docx\",\r\n"
					+ "            \"path_lower\": \"/uni/generali.docx\",\r\n"
					+ "            \"path_display\": \"/Uni/Generali.docx\",\r\n"
					+ "            \"id\": \"id:xjKzQLUfUpAAAAAAAAAARQ\",\r\n"
					+ "            \"client_modified\": \"2021-12-15T10:57:29Z\",\r\n"
					+ "            \"server_modified\": \"2021-12-15T10:57:31Z\",\r\n"
					+ "            \"rev\": \"5d32d2c227dfd89398f81\",\r\n"
					+ "            \"size\": 1055536,\r\n"
					+ "            \"is_downloadable\": true,\r\n"
					+ "            \"content_hash\": \"921478ab998348f32ee282e4f836d5d917fdb70b764138a81092be6d8cc5a82c\"\r\n"
					+ "        },\r\n"
					+ "        {\r\n"
					+ "            \"name\": \"Generali.docx\",\r\n"
					+ "            \"path_lower\": \"/uni/generali.docx\",\r\n"
					+ "            \"path_display\": \"/Uni/Generali.docx\",\r\n"
					+ "            \"id\": \"id:xjKzQLUfUpAAAAAAAAAARQ\",\r\n"
					+ "            \"client_modified\": \"2021-12-14T17:36:57Z\",\r\n"
					+ "            \"server_modified\": \"2021-12-14T17:36:57Z\",\r\n"
					+ "            \"rev\": \"5d31ea2cd798b89398f81\",\r\n"
					+ "            \"size\": 602569,\r\n"
					+ "            \"is_downloadable\": true,\r\n"
					+ "            \"content_hash\": \"ad77bbdb1ed26ca620026ad03adf7adc9d5f6dba02fe2900f7fd598aea4709da\"\r\n"
					+ "        },\r\n"
					+ "        {\r\n"
					+ "            \"name\": \"Generali.docx\",\r\n"
					+ "            \"path_lower\": \"/uni/generali.docx\",\r\n"
					+ "            \"path_display\": \"/Uni/Generali.docx\",\r\n"
					+ "            \"id\": \"id:xjKzQLUfUpAAAAAAAAAARQ\",\r\n"
					+ "            \"client_modified\": \"2021-12-14T17:36:30Z\",\r\n"
					+ "            \"server_modified\": \"2021-12-14T17:36:30Z\",\r\n"
					+ "            \"rev\": \"5d31ea13476b389398f81\",\r\n"
					+ "            \"size\": 12163,\r\n"
					+ "            \"is_downloadable\": true,\r\n"
					+ "            \"content_hash\": \"f250055389428e8ad6df8aae56f284258a95ab3a93fcbca488b9099846541ff0\"\r\n"
					+ "        },\r\n"
					+ "        {\r\n"
					+ "            \"name\": \"Generali.docx\",\r\n"
					+ "            \"path_lower\": \"/uni/generali.docx\",\r\n"
					+ "            \"path_display\": \"/Uni/Generali.docx\",\r\n"
					+ "            \"id\": \"id:xjKzQLUfUpAAAAAAAAAARQ\",\r\n"
					+ "            \"client_modified\": \"2021-12-14T17:31:51Z\",\r\n"
					+ "            \"server_modified\": \"2021-12-14T17:34:09Z\",\r\n"
					+ "            \"rev\": \"5d31e98cc8bba89398f81\",\r\n"
					+ "            \"size\": 12094,\r\n"
					+ "            \"is_downloadable\": true,\r\n"
					+ "            \"content_hash\": \"6dcc7beb7a071524e88730d01776197d720a2a0a746218f78aba5a07c06e2973\"\r\n"
					+ "        }\r\n"
					+ "    ]\r\n"
					+ "}";
			
			FileService fileService = new FileServiceImpl();
			
			try {
				revisions = fileService.getRevisionList(new JSONObject(jsonRevisions));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Controlla che l'id della prima revisione, preso dall'istanza dell'oggetto
			//java, corrisponda a quello presente nel json
			assertEquals("5d35bedefdfb189398f81", revisions.get(0).getRevisionId());			
		}
		

}
