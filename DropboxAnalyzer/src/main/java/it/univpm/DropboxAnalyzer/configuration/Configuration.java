package it.univpm.DropboxAnalyzer.configuration;

import java.util.Map;

import org.json.JSONObject;

import it.univpm.DropboxAnalyzer.exceptions.BadFormatException;

public interface Configuration {
	public void setDefault(Map<String, Object> parameters) throws BadFormatException;
	public void checkFormat(Map<String, Object> parameters) throws BadFormatException;
}
