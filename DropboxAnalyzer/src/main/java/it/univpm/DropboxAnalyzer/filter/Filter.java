package it.univpm.DropboxAnalyzer.filter;

public class Filter {
	private long periodOfTime;
	private String fileExtension;
	private boolean onlyDownloadable;
	private int revisionsThreshold;
	
	public Filter(long periodOfTime, String fileExtension, boolean onlyDownloadable, int revisionsThreshold) {
		this.periodOfTime = periodOfTime;
		this.fileExtension = fileExtension;
		this.onlyDownloadable = onlyDownloadable;
		this.revisionsThreshold = revisionsThreshold;
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
