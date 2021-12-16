package it.univpm.DropboxAnalyzer.configuration;

import java.util.Map;

import org.json.JSONObject;

import it.univpm.DropboxAnalyzer.Service.BadFormatException;

public interface Configuration {
	public void setDefault(Map<String, Object> parameters) throws BadFormatException;
}
