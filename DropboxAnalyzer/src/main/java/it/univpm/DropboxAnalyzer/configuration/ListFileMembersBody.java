package it.univpm.DropboxAnalyzer.configuration;

import org.json.JSONObject;

public class ListFileMembersBody implements Body{
	private String file;
	private boolean includeInherited;
	private int limit;
	
	@Override
	public String toString() {
		return toJSONObject().toString();
	}
	
	@Override
	public JSONObject toJSONObject() {
		JSONObject object = new JSONObject();
		
		object.put("file", file);
		object.put("include_inherited", includeInherited);
		object.put("limit", limit);
		
		return object;
	}
	
	
	//Overloading del costruttore con i parametri più importanti
	public ListFileMembersBody(String file, int limit) {
		super();
		this.file = file;
		this.includeInherited = true;
		this.limit = limit;
	}

	public ListFileMembersBody(String file, boolean includeInherited, int limit) {
		super();
		this.file = file;
		this.includeInherited = includeInherited;
		this.limit = limit;
	}
	
	
}
