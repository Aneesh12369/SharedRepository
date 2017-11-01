package com.practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PracticeClass {

	public static void main(String[] args) {
		ArrayList<TableRecords> tableRecordsList = new ArrayList<TableRecords>();
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
			TableRecords tableRecords = new TableRecords();
			tableRecords.setSno(i);
			tableRecords.setName("a" + i);
			tableRecords.setCompany("Pyro");
			tableRecords.setProject("CTOPUP");
			tableRecords.setDateString("2014/10/29 18:10:4" + random.nextInt(9));
			tableRecordsList.add(tableRecords);
		}
		
		System.out.println(tableRecordsList);
		Collections.sort(tableRecordsList, new MyComparator());
		System.out.println("******************************");
		for (TableRecords record:tableRecordsList) {
			System.out.println(record);
		}
	}
}
