package model;

import java.util.HashMap;
import java.util.Map;

/**
 * GlobalStorage is used to store data objects in a {@link HashMap}. The data
 * can be accessed from any class by the key given when stored. The key must be
 * a {@link String}. Access to the GlobalStorage can be gained by calling the
 * static {@link #getInstance()} method. As GlobalStorage implements the
 * singleton pattern only one instance exists at the time.
 * 
 * @author Miklas Boskamp
 */
public class GlobalStorage {

	private static GlobalStorage instance;
	private Map<String, Object> storage;

	/**
	 * The singleton pattern does not allow a public constructor.
	 */
	private GlobalStorage() {
		storage = new HashMap<>();
	}

	/**
	 * Returns the current instance of this class. There can only be one
	 * instance at a time. If there is already one instance of this class, this
	 * instance will be returned.
	 * 
	 * @return The current instance of this class.
	 */
	public static GlobalStorage getInstance() {
		if (instance == null) {
			instance = new GlobalStorage();
		}
		return instance;
	}

	/**
	 * Store a value referenced by a {@link String} key. The stored data can be
	 * accessed from any class by the given key.
	 * 
	 * @param key A string value that references the data.
	 * @param value The data object.
	 */
	public void persist(String key, Object value) {
		storage.put(key, value);
	}

	/**
	 * Retrieves the previously stored data object referenced by the given key string.
	 * 
	 * @param key The key string that references the data object. (The key the data was stored with)
	 * @return The data object or <code>null</code> if there was no such data object found.
	 */
	public Object get(String key) {
		return storage.get(key);
	}
}