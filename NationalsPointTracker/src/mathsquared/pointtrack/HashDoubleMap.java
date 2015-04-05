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
}
