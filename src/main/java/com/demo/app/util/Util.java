package com.demo.app.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.app.domain.Entity;

public class Util {

	public static Map<String, ? extends Entity> ListToMap(List<? extends Entity> list) {
		Map<String, Entity> map = new HashMap<>();
		for (Entity entity : list) {
			map.put(entity.getId(), entity);
		}
		return map;
	}
}
