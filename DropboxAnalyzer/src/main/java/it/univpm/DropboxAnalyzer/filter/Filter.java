package it.univpm.DropboxAnalyzer.filter;

import java.util.Vector;

import org.json.JSONObject;

import it.univpm.DropboxAnalyzer.Model.Revision;

public interface Filter {
	public void applyFilters();
	public void setFilters(JSONObject jsonFilters);
}
