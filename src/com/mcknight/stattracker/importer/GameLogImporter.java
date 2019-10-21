package com.mcknight.stattracker.importer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jdbc.core.JdbcTemplate;

public class GameLogImporter extends CsvImporter {

	@Override
	public void importRecord(String[] fields) {
		jdbcTemplate jdbcTemplate = getJdbcTemplate();
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

}
