package com.mcknight.stattracker.importer;

import java.util.Map;

public class ImporterFactory {	
	private Map<String, Importer> importerMap;
	
	public void setImporterMap(Map<String, Importer> importerMap) {
		this.importerMap = importerMap;
	}
	
	public Importer getImporter(String dataSet) {
		return importerMap.get(dataSet);
	}
}
