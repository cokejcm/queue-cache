package com.demo.app.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.demo.app.domain.Entity;

public class Util {

	private static final Pattern bound = Pattern.compile("\\b(?=\\w)");

	public static Map<String, ? extends Entity> ListToMap(Iterable<? extends Entity> list) {
		Map<String, Entity> map = new HashMap<>();
		for (Entity entity : list) {
			map.put(entity.getId(), entity);
		}
		return map;
	}

	// First letter converted to capital
	private static final String ucFirst(String input) {
		return input.substring(0, 1).toUpperCase() + input.substring(1);
	};

	public static String capitalize(final String input) {
		return bound.splitAsStream(input)
				.map(Util::ucFirst)
				.collect(Collectors.joining());
	}
}
