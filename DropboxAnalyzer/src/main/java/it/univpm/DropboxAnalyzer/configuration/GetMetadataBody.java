package it.univpm.DropboxAnalyzer.configuration;

import org.json.JSONObject;

public class GetMetadataBody implements Body {
	private String path;
	private boolean includeMediaInfo;
	private boolean includeDeleted;
	private boolean inlcudeHasExplicitSharedMembers;

	
	@Override
	public String toString() {
		return toJSONObject().toString();
	}
	
	@Override
	public JSONObject toJSONObject()
	{
		JSONObject object = new JSONObject();
		
		object.put("path", path);
		object.put("include_media_info", includeMediaInfo);
		object.put("include_deleted", includeDeleted);
		object.put("include_has_explicit_shared_members", inlcudeHasExplicitSharedMembers);
		
		return object;
	}

	//Overloading del costruttore con i parametri più importanti
	public GetMetadataBody(String path) {
		this.path = path;
		this.includeMediaInfo = true;
		this.includeDeleted = true;
		this.inlcudeHasExplicitSharedMembers = true;
	}



	public GetMetadataBody(String path, boolean includeMediaInfo, boolean includeDeleted,
			boolean inlcudeHasExplicitSharedMembers) {
		this.path = path;
		this.includeMediaInfo = includeMediaInfo;
		this.includeDeleted = includeDeleted;
		this.inlcudeHasExplicitSharedMembers = inlcudeHasExplicitSharedMembers;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isIncludeMediaInfo() {
		return includeMediaInfo;
	}

	public void setIncludeMediaInfo(boolean includeMediaInfo) {
		this.includeMediaInfo = includeMediaInfo;
	}

	public boolean isIncludeDeleted() {
		return includeDeleted;
	}

	public void setIncludeDeleted(boolean includeDeleted) {
		this.includeDeleted = includeDeleted;
	}

	public boolean isInlcudeHasExplicitSharedMembers() {
		return inlcudeHasExplicitSharedMembers;
	}

	public void setInlcudeHasExplicitSharedMembers(boolean inlcudeHasExplicitSharedMembers) {
		this.inlcudeHasExplicitSharedMembers = inlcudeHasExplicitSharedMembers;
	}
	
	
	
	
	
	

}
