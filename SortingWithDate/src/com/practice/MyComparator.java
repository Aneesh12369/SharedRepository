package com.practice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

public class MyComparator implements Comparator<TableRecords> {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	@Override
	public int compare(TableRecords tableRecords1, TableRecords tableRecords2) {
		int status = 0;
		try {
			Date date = sdf.parse(tableRecords1.getDateString());
			Date date1 = sdf.parse(tableRecords2.getDateString());
			status = date.compareTo(date1);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}

		return status;
	}
}
