/**
 * 
 */
package mathsquared.pointtrack;

import java.util.HashMap;

/**
 * A data structure based on {@link HashMap} that allows for two indices at a time. This is implemented as a hash map of hash maps.
 * 
 * @author alex
 *
 */
public class HashDoubleMap <K, L, V> {
	private HashMap<K, HashMap<L, V>> hash;
	
	public HashDoubleMap () {
		hash = new HashMap<>();
	}
	
	public void clear () {
		hash.clear();
	}
	
	/**
	 * Tests for whether this doublemap contains a mapping with the given object as its first key.
	 * 
	 * This doesn't just check if there is a sub-map corresponding to {@code key}, since that sub-map might be empty. This only returns true if that sub-map exists and is not empty.
	 * 
	 * @param key the key to check
	 * @return true if there is at least one mapping with {@code key} as the first key
	 */
	public boolean containsKey1 (Object key) {
		// Can't naively test the parent map in case of empty sub-maps
		
		HashMap<L, V> comp = hash.get(key);
		return (comp != null && !comp.isEmpty());
	}
	
	/**
	 * Tests for whether this doublemap contains a mapping with the given object as its second key.
	 * 
	 * This is <strong>slow</strong> since it must iterate over all of the first keys.
	 * 
	 * @param key the key to check
	 * @return true if there is at least one mapping with {@code key} as the second key
	 */
	public boolean containsKey2 (Object key) {
		for (HashMap<L, V> comp : hash.values()) {
			if (comp.containsKey(key)) return true;
		}
		
		return false;
	}
	
	public V get (Object key1, Object key2) {
		if (!containsKey1(key1)) return null;
		return hash.get(key1).get(key2);
	}
}
