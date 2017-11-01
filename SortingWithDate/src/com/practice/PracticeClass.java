package com.practice;

import java.util.ArrayList;
import java.util.Collections;

public class PracticeClass {

	public static void main(String[] args){
		
		ArrayList<TableRecords> tableRecordsList = new ArrayList<TableRecords>();
		
		for(int i=0 ;i<20;i++){
			TableRecords tableRecords = new TableRecords();
			tableRecords.setSno(i);
			tableRecords.setName("a"+i);
			tableRecords.setCompany("Pyro");
			tableRecords.setProject("CTOPUP");
			
			tableRecordsList.add(tableRecords);
		}
		MyComparator comparator = new MyComparator();
		Collections.sort(tableRecordsList,comparator);
		
		for(int i=0;i<10;i++){
			System.out.println(tableRecordsList.get(i).toString());
		}
	}
}
