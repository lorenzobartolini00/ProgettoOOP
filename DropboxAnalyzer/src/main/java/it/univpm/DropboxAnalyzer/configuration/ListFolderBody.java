package it.univpm.DropboxAnalyzer.configuration;

public class ListFolderBody implements Body {
	private String path;
	private boolean recursive;
	private boolean includeMediaInfo;
	private boolean includeDeleted;
	private boolean includeHasExplicitMembers;
	private boolean includeMountedFoulders;
	private boolean includeNonDownloadableFiles;

	public String toString()
	{
		return "{\r\n"
				+ "    \"path\": \""
				+ this.path
				+ "\",\r\n"
				+ "    \"recursive\": "
				+ String.valueOf(this.recursive)
				+ ",\r\n"
				+ "    \"include_media_info\": "
				+ String.valueOf(this.includeMediaInfo)
				+ ",\r\n"
				+ "    \"include_deleted\": "
				+ String.valueOf(this.includeDeleted)
				+ ",\r\n"
				+ "    \"include_has_explicit_shared_members\": "
				+ String.valueOf(this.includeHasExplicitMembers)
				+ ",\r\n"
				+ "    \"include_mounted_folders\": "
				+ String.valueOf(this.includeMountedFoulders)
				+ ",\r\n"
				+ "    \"include_non_downloadable_files\": "
				+ String.valueOf(this.includeNonDownloadableFiles)
				+ "\r\n"
				+ "}";
	}
	
	public ListFolderBody(String path, boolean recursive) {
		this.path = path;
		this.recursive = recursive;
		this.includeMediaInfo = true;
		this.includeDeleted = true;
		this.includeHasExplicitMembers = true;
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
