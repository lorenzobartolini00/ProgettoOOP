package it.univpm.DropboxAnalyzer.filter;

import java.util.Map;
import java.util.Vector;

import org.json.JSONObject;

import it.univpm.DropboxAnalyzer.Model.Revision;
import it.univpm.DropboxAnalyzer.configuration.Configuration;
import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;

public interface Filter {
	public void applyFilters();
	public void setFilters(Map<String,  Object> parameters);
}
