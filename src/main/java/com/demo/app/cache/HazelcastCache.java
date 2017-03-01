package com.demo.app.cache;

import java.util.ArrayList;
import java.util.List;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

public class HazelcastCache {

	private static final String DATA_GRID = "default_grid";
	private static HazelcastInstance hz = null;
	private static List<String> maps = null;
	private String grid;

	public HazelcastCache() {
		this(DATA_GRID);
	}

	public HazelcastCache(String grid) {
		this.grid = grid;
		if (hz == null) {
			synchronized (HazelcastCache.class) {
				if (hz == null) {
					hz = Hazelcast.newHazelcastInstance();
					maps = new ArrayList<String>();
				}
			}
		}
	}

	private IMap<Object, Object> getMap() {
		return getMap(this.grid);
	}

	private IMap<Object, Object> getMap(String grid) {
		return hz.getMap(grid);
	}

	public Object get(String key) {
		return getMap().get(key);
	}

	public void put(String key, Object value) {
		getMap().set(key, value);
	}

	public void setGrid(String grid) {
		this.grid = grid;

		if (!maps.contains(grid)) {
			maps.add(grid);
		}
	}

}
