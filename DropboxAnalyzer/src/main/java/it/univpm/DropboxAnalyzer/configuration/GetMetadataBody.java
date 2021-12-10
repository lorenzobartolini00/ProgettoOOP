package it.univpm.DropboxAnalyzer.configuration;

public class GetMetadataBody implements Body {
	private String path;
	private boolean includeMediaInfo;
	private boolean includeDeleted;
	private boolean inlcudeHasExplicitSharedMembers;

	@Override
	public String toString() {
		return "{\r\n"
		+ "    \"path\": \""
		+ this.path
		+ "\",\r\n"
		+ "    \"include_media_info\": "
		+ String.valueOf(this.includeMediaInfo)
		+ ",\r\n"
		+ "    \"include_deleted\": "
		+ String.valueOf(this.includeDeleted)
		+ ",\r\n"
		+ "    \"include_has_explicit_shared_members\": "
		+ String.valueOf(this.inlcudeHasExplicitSharedMembers)
		+ "\r\n"
		+ "}";
	}

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
	
	
	
	
	
	

}
