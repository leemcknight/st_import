package com.mcknight.stattracker.importer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class PersonImporter extends CsvImporter {	
	private static Logger logger = LogManager.getLogger();
	private static String SQL = "insert into public.person (person_id, first_name, last_name, debut) values (?, ?, ?, ?)";
	@Override
	public void importRecord(String[] fields) {
		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		try {			
			DateFormat df = new SimpleDateFormat("MM/DD/yyyy");
			Date date = df.parse(fields[3]);
			jdbcTemplate.update(SQL, fields[2], fields[1], fields[0], date);
		} catch(DataAccessException ex) {
			System.out.println("Error inserting " + fields[0] + ": " + ex.getMessage());
			logger.warn("Error inserting " + fields[0] + ": " + ex.getMessage());
		} catch(ParseException ex) {
			System.out.println("Invalid data format: " + ex.getMessage());
			logger.error("Invalid data format: " + ex.getMessage());
		}
	}
}
