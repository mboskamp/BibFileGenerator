package model;

import java.util.HashMap;
import java.util.Map;

public class GlobalStorage {

	private static GlobalStorage instance;
	private Map<String, Object> storage;
	
	private GlobalStorage() {
		storage = new HashMap<>();
	}
	
	public static GlobalStorage getInstance(){
		if(instance == null){
			instance = new GlobalStorage();
		}
		return instance;
	}
	
	public void persist(String key, Object value){
		storage.put(key, value);
	}
	
	public Object get(String key){
		return storage.get(key);
	}
}