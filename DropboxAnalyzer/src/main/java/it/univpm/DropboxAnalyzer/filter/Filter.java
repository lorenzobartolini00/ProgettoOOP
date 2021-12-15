package it.univpm.DropboxAnalyzer.filter;

import java.util.Vector;

import it.univpm.DropboxAnalyzer.Model.Revision;

public interface Filter {
	public Vector<Revision> filter();
}
