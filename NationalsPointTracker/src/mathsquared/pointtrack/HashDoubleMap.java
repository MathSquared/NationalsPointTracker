/**
 * 
 */
package mathsquared.pointtrack;

import java.util.HashMap;
import java.util.Map;

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
		
		HashMap<L, V> comp = getKey1(key);
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
	
	/**
	 * Tests for whether this doublemap contains a mapping with the given objects as first and second keys.
	 * 
	 * @param key1 the first key to check
	 * @param key2 the second key to check
	 * @return true if there is a mapping with the given keys
	 */
	public boolean contains (Object key1, Object key2) {
		if (!containsKey1(key1)) return false;
		return getKey1(key1).containsKey(key2);
	}
	
	/**
	 * Given a first key, returns a map that can be indexed by the second key to return the corresponding value.
	 * 
	 * Modifying this map will in turn modify the backing doublemap.
	 * 
	 * @param key the first key
	 * @return a mapping from the second key to the value given by the first key given here and the second key in that map
	 */
	public HashMap<L, V> getKey1 (Object key) {
		return hash.get(key);
	}
	
	/**
	 * Given a second key, returns a map that can be indexed by the first key to return the corresponding value.
	 * 
	 * This is <strong>slow</strong> since it must iterate over all of the first keys.
	 * 
	 * Modifying this map will <strong>not</strong> modify the backing doublemap.
	 * 
	 * @param key the second key
	 * @return a mapping from the first key to the value given by the first key in that map and the second key given here
	 */
	public HashMap<K, V> getKey2 (Object key) {
		HashMap<K, V> ret = new HashMap<>();
		
		for (Map.Entry<K, HashMap<L, V>> entry : hash.entrySet()) {
			if (entry.getValue().containsKey(key)) {
				ret.put(entry.getKey(), entry.getValue().get(key));
			}
		}
		
		return ret;
	}
	
	public V get (Object key1, Object key2) {
		if (!containsKey1(key1)) return null;
		return getKey1(key1).get(key2);
	}
}
