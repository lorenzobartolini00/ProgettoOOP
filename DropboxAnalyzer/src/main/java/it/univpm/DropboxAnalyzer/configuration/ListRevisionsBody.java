package it.univpm.DropboxAnalyzer.configuration;

import org.json.JSONObject;

public class ListRevisionsBody implements Body {
	private String path;
	private String mode;
	private int limit;
	
	@Override
	public String toString() {
		return toJSONObject().toString();
	}
	
	@Override
	public JSONObject toJSONObject() {
		JSONObject object = new JSONObject();
		
		object.put("path", path);
		object.put("mode", mode);
		object.put("limit", limit);
		
		return object;
	}
	
	
	//Overloading del costruttore con i parametri più importanti
	public ListRevisionsBody(String path, int limit) {
		super();
		this.path = path;
		this.mode = "path";
		this.limit = limit;
	}

	public ListRevisionsBody(String path, String mode, int limit) {
		super();
		this.path = path;
		this.mode = mode;
		this.limit = limit;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	


}
