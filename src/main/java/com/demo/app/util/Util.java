package com.demo.app.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.app.domain.Entity;

public class Util {

	public static <K> Map<K,? extends Entity<K>> ListToMap (List<? extends Entity<K>> list){
		Map<K, Entity <K>> map = new HashMap<>();
		for (Entity<K> entity : list) {
			map.put(entity.getId(), entity);
		}
		return map;
	}
}
