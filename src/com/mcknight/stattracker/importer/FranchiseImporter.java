package com.mcknight.stattracker.importer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class FranchiseImporter extends CsvImporter {

	private static Logger logger = LogManager.getLogger();
	private static String SQL = "insert into public.franchise (franchise_id, current_franchise_id, league_code, division, location, nickname, alternate_nickname, start_date, end_date) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	@Override
	public void importRecord(String[] fields) {
		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		try {			
			DateFormat df = new SimpleDateFormat("MM/DD/yyyy");
			Date start_date = df.parse(fields[7]);
			Date end_date = null;
			try {
				end_date = df.parse(fields[8]);
			} catch(ParseException e) {
				end_date = null;
			}
			jdbcTemplate.update(SQL, fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], start_date, end_date);
		} catch(DataAccessException ex) {
			System.out.println("Error inserting " + fields[0] + ": " + ex.getMessage());
			logger.warn("Error inserting " + fields[0] + ": " + ex.getMessage());
		} catch(ParseException ex) {
			System.out.println("Invalid data format: " + ex.getMessage());
			logger.error("Invalid data format: " + ex.getMessage());
		}
		
	}

}
