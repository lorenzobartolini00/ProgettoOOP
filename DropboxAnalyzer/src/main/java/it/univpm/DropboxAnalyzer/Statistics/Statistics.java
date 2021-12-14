package it.univpm.DropboxAnalyzer.Statistics;

import org.json.JSONObject;

import it.univpm.DropboxAnalyzer.filter.Filter;

public interface Statistics {

	public JSONObject getStatistics(Filter filter);
}
