package com.mcknight.stattracker.importer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {
	private static Logger LOGGER = LogManager.getLogger();
	public static void main(String[] args) {
		ImporterFactory factory = null;
		
		if(args.length == 0) {
			showUsageText();
			return;
		}
		
		ApplicationContext context;
		
		LOGGER.info("Loading Spring Config...");
		try {
			context = new FileSystemXmlApplicationContext("spring-config.xml");
			factory = context.getBean(ImporterFactory.class);
		} catch(Exception ex) {
			LOGGER.error("Error loading Spring Config.  Details below:");
			ex.printStackTrace();
			return;
		}
		
		
		String data = args[0];
		String file = args[1];
		System.out.println("Getting logger...");
		Importer importer = factory.getImporter(data);
		
		if(importer == null) {			
			System.out.println("Unknown dataType: " + data);
			showUsageText();
		}
		try {
			System.out.println("Using " + importer.getClass().getName());
			importer.importFile(file);
		} catch(Exception ex) {
			LOGGER.error("Error importing file.  Stack trace below.");
			ex.printStackTrace();	
			System.exit(-1);
		} 
		
	}
	
	private static void showUsageText() {
		System.out.println("Usage:");
		System.out.println("st_import <data-type> <filename> <options>");
		System.out.println("<datetype>:    type of data you are importing.  Options are:");
		System.out.println("               person");
		System.out.println("               franchise");
		System.out.println("               park");
		System.out.println("               schedule");
		System.out.println("               event");
		System.out.println("<options>:");
		System.out.println("               -i   ignore headers");
		System.out.println("               -v   verbose logging");
	}

}
