package com.me.data;

import java.util.HashMap;
import java.util.Map;

public class DataStore {

	private static Map<String, Object> map = new HashMap<String, Object>();
	private static Map<String, Object> listOfObjetcs = new HashMap<String, Object>();

	public static Map<String, Object> getMap() {
		return map;
	}

	public static void setMap(Map<String, Object> map) {
		DataStore.map = map;
	}

	public static Map<String, Object> getListOfObjetcs() {
		return listOfObjetcs;
	}

	public static void setListOfObjetcs(Map<String, Object> listOfObjetcs) {
		DataStore.listOfObjetcs = listOfObjetcs;
	}

}
