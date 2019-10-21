package com.mcknight.stattracker.importer;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ScheduleImporter extends CsvImporter {
	private static Logger logger = LogManager.getLogger();
	private static String SQL = "insert into public.schedule (date, game_number, day_of_week, " + 
			"visiting_team_id, visiting_team_league_code, visitor_game_number, " +
			"home_team_id, home_team_league_code, home_game_number, " + 
			"time_of_day_code, postponement_cancellation_code, makeup_date) " + 
			"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	@Override
	public void importRecord(String[] fields) {
		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		DateFormat df = new SimpleDateFormat("yyyyMMDD");		
		Date date = null;
		Date makeupDate = null;		
		if(fields.length == 1) {
			//end of file.
			return;
		}
		try {
			date = df.parse(fields[0]);						
		} catch(ParseException parseEx) {
			
		}
		
		try {
			makeupDate = df.parse(fields[11]);
		} catch(ParseException parseEx) {
			
		}
		
		jdbcTemplate.update(SQL, date, getShort(fields[1]), fields[2], fields[3], fields[4], getShort(fields[5]), fields[6], fields[7], getShort(fields[8]), fields[9], fields[10], makeupDate);
	}

}
