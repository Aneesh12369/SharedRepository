package com.me.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.me.data.DataStore;
import com.me.dto.StudentDto;

public class CacheUtil {

	public static Map<String, Object> storeCache(List<StudentDto> lt) {
		Map<String, Object> map = null;
		map = new HashMap<String, Object>();
		for (StudentDto dto : lt) {

			Map<String, Object> sub_map = new HashMap<String, Object>();
			sub_map.put("id", dto.getId());
			sub_map.put("name", dto.getName());
			sub_map.put("address", dto.getAddress());
			sub_map.put("age", dto.getAge());
			map.put(String.valueOf(sub_map.get("id")), sub_map);

		}
		return map;

	}

	public static void setCache(List<StudentDto> list) {
		DataStore.setMap(storeCache(list));
	}

	public static Map<String, Object> getCache() {
		return DataStore.getMap();

	}
	
	public static void setListCache(List<StudentDto> list) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("StudentsList", list);
		DataStore.setListOfObjetcs(map);
	}
	
	public static Map<String, Object> getListCache() {
		return DataStore.getListOfObjetcs();

	}
	

}
