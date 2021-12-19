package it.univpm.DropboxAnalyzer.Statistics;

import java.util.Map;

import org.json.JSONObject;

import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;
import it.univpm.DropboxAnalyzer.filter.RevisionFilter;

public interface Statistics {

	public void updateStatistics();
	public Map<String, Object> addStatistic(Map<String, Object> data);
}
