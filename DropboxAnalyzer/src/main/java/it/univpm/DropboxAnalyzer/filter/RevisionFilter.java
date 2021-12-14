package it.univpm.DropboxAnalyzer.filter;

public class RevisionFilter implements Filter{
	private Long periodOfTime;
	private String fileExtension;
	private boolean onlyDownloadable;
	private Integer revisionsThreshold;
	
	@Override
	public Object filter() {
		// TODO Implementare metodo che restituisce la lista di oggetti filtrati
		return null;
	}
	
	public RevisionFilter(long periodOfTime, String fileExtension, boolean onlyDownloadable, int revisionsThreshold) {
		this.periodOfTime = periodOfTime;
		this.fileExtension = fileExtension;
		this.onlyDownloadable = onlyDownloadable;
		this.revisionsThreshold = revisionsThreshold;
	}
	public RevisionFilter(long periodOfTime, String fileExtension, boolean onlyDownloadable) {
		this.periodOfTime = periodOfTime;
		this.fileExtension = fileExtension;
		this.onlyDownloadable = onlyDownloadable;
		this.revisionsThreshold = null;
	}
	public RevisionFilter(long periodOfTime, String fileExtension) {
		this.periodOfTime = periodOfTime;
		this.fileExtension = fileExtension;
		this.onlyDownloadable = false;
		this.revisionsThreshold = null;
	}
	public RevisionFilter(long periodOfTime) {
		this.periodOfTime = periodOfTime;
		this.fileExtension = ".all";
		this.onlyDownloadable = false;
		this.revisionsThreshold = null;
	}
	public RevisionFilter() {
		this.periodOfTime = null;
		this.fileExtension = ".all";
		this.onlyDownloadable = false;
		this.revisionsThreshold = null;
	}
	public long getPeriodOfTime() {
		return periodOfTime;
	}
	public void setPeriodOfTime(long periodOfTime) {
		this.periodOfTime = periodOfTime;
	}
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
	public int getRevisionsThreshold() {
		return revisionsThreshold;
	}
	public void setRevisionsThreshold(int revisionsThreshold) {
		this.revisionsThreshold = revisionsThreshold;
	}
	
}
