package com.mcknight.stattracker.importer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class BallparkImporter extends CsvImporter {
	private static Logger logger = LogManager.getLogger();
	private static String SQL = "insert into public.ballpark (ballpark_id, name, secondary_name, city, state, start_date, end_date, league_code, notes) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	@Override
	public void importRecord(String[] fields) {
		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		try {			
			DateFormat df = new SimpleDateFormat("MM/DD/yyyy");			
			Date startDate = fields[5].trim().length() > 0 ?  df.parse(fields[5]) : null;
			Date endDate = fields[6].trim().length() > 0 ?  df.parse(fields[6]) : null;
			jdbcTemplate.update(SQL, fields[0], fields[1], fields[2], fields[3], fields[4], startDate, endDate, fields[7], fields[8]);
		} catch(DataAccessException ex) {
			System.out.println("Error inserting " + fields[0] + ": " + ex.getMessage());
			logger.warn("Error inserting " + fields[0] + ": " + ex.getMessage());
		} catch(ParseException ex) {
			System.out.println("Invalid data format: " + ex.getMessage());
			logger.error("Invalid data format: " + ex.getMessage());
		}
		
	}

}
