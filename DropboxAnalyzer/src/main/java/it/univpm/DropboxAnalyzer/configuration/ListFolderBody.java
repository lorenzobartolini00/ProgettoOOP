package it.univpm.DropboxAnalyzer.configuration;

import org.json.JSONObject;

public class ListFolderBody implements Body {
	private String path;
	private boolean recursive;
	private boolean includeMediaInfo;
	private boolean includeDeleted;
	private boolean includeHasExplicitMembers;
	private boolean includeMountedFoulders;
	private boolean includeNonDownloadableFiles;

	@Override
	public String toString()
	{
		return toJSONObject().toString();
	}
	
	@Override
	public JSONObject toJSONObject()
	{
		JSONObject object = new JSONObject();
		
		object.put("path", path);
		object.put("recursive", recursive);
		object.put("include_media_info", includeMediaInfo);
		object.put("include_deleted", includeDeleted);
		object.put("include_has_explicit_shared_members", includeHasExplicitMembers);
		object.put("include_mounted_folders", includeMountedFoulders);
		object.put("include_non_downloadable_files", includeNonDownloadableFiles);
		
		return object;
	}
	
	public ListFolderBody(String path, boolean recursive) {
		this.path = path;
		this.recursive = recursive;
		this.includeMediaInfo = false;
		this.includeDeleted = false;
		this.includeHasExplicitMembers = false;
		this.includeMountedFoulders = true;
		this.includeNonDownloadableFiles = true;
	}

	public ListFolderBody(String path, boolean recursive, boolean includeMediaInfo, boolean includeDeleted,
			boolean includeHasExplicitMembers, boolean includeMountedFoulders, boolean includeNonDownloadableFiles) {
		super();
		this.path = path;
		this.recursive = recursive;
		this.includeMediaInfo = includeMediaInfo;
		this.includeDeleted = includeDeleted;
		this.includeHasExplicitMembers = includeHasExplicitMembers;
		this.includeMountedFoulders = includeMountedFoulders;
		this.includeNonDownloadableFiles = includeNonDownloadableFiles;
	}



	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRecursive() {
		return recursive;
	}

	public void setRecursive(boolean recursive) {
		this.recursive = recursive;
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

	public boolean isIncludeHasExplicitMembers() {
		return includeHasExplicitMembers;
	}

	public void setIncludeHasExplicitMembers(boolean includeHasExplicitMembers) {
		this.includeHasExplicitMembers = includeHasExplicitMembers;
	}

	public boolean isIncludeMountedFoulders() {
		return includeMountedFoulders;
	}

	public void setIncludeMountedFoulders(boolean includeMountedFoulders) {
		this.includeMountedFoulders = includeMountedFoulders;
	}

	public boolean isIncludeNonDownloadableFiles() {
		return includeNonDownloadableFiles;
	}

	public void setIncludeNonDownloadableFiles(boolean includeNonDownloadableFiles) {
		this.includeNonDownloadableFiles = includeNonDownloadableFiles;
	}

	
	
	

}
