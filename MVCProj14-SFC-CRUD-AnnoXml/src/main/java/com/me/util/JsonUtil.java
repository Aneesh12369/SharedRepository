package com.me.util;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {
	
	private static ObjectMapper mapper = new ObjectMapper();

	public static String convertJavaToJson(Object object){
		String jsonString="";
		try {
			jsonString = mapper.writeValueAsString(object);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonString;
	}
	
}
