package it.univpm.DropboxAnalyzer.Statistics;

import org.json.JSONObject;

import it.univpm.DropboxAnalyzer.filter.RevisionFilter;

public interface Statistics {

	public JSONObject getStatistics(RevisionFilter filter);
}
