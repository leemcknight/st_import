package com.mcknight.stattracker.importer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Path;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class CsvImporter implements Importer {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private static Logger LOGGER = LogManager.getLogger();
	@Override
	public void importFile(String fileName) throws Exception {
		
		LOGGER.info("Loading " + fileName);
		System.out.println("Loading " + fileName);		
		try {			
			File file = new File(fileName);
			if(file.isFile()) {
				readFile(fileName);
			} else if(file.isDirectory()) {
				File[] files = file.listFiles();
				for(File f : files) {
					readFile(f.getAbsolutePath());
				}
			}
			
		} catch(Exception e) {
			LOGGER.error("Error importing file:");
			e.printStackTrace();
		} 
	}
	
	private void readFile(String fileName) throws FileNotFoundException, IOException {
		FileReader fileReader = null;
		BufferedReader reader = null;
		fileReader = new FileReader(fileName);
		try {
			reader = new BufferedReader(fileReader);
			String line = null;
			System.out.println("Loaded " + fileName);
			while((line = reader.readLine()) != null) {				
				String[] parts = line.split(",");
				for(int i = 0; i < parts.length; i++) {
					String part = parts[i];
					parts[i] = part.replaceAll("\"", "");
				}
				importRecord(parts);
			}
		} finally {
			if(reader != null) {
				reader.close();
			}
		}
		
	}
	
	public abstract void importRecord(String[] fields);
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public DataSource getDataSource() {
		return this.dataSource;
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}
	
	public short getShort(String str) {
		str.replace('"', ' ');
		return Short.parseShort(str);
	}

}
