/**
 * 
 */
package mathsquared.pointtrack;

import java.util.Arrays;
import java.util.BitSet;

/**
 * The results for one {@linkplain School school} in one {@linkplain CompetitiveEvent} event.
 * 
 * @author alex
 *
 */
public class ResultCell {
	int entries;
	int[] breaks;
	BitSet places;
	
	/**
	 * Creates a new ResultsCell for a school.
	 * 
	 * @param ent the number of entries this school submitted for this event
	 * @param brk the number of students breaking to each elimination round (copied by the constructor), one by one, not including finals. For instance, a tournament with quarterfinals would have two entries in this array, one for quarters and one for semis. A null value for this parameter is treated the same as an empty array (i.e. the tournament broke straight to finals).
	 * @param plc the places the school earned in this event (cloned by the constructor); this is one-based, bit 0 must be false, and the {@linkplain BitSet#cardinality() cardinality} of the set is treated as the number of finalists
	 */
	public ResultCell (int ent, int[] brk, BitSet plc) {
		entries = ent;
		breaks = Arrays.copyOf(brk, brk.length);
		places = (BitSet) plc.clone();
	}

	/**
	 * Returns the number of entries this school submitted in this event.
	 * 
	 * @return
	 */
	public int getEntries() {
		return entries;
	}

	/**
	 * Returns, in order, the number of students breaking to each out round (not including finals). The array is copied before returning.
	 * 
	 * @return
	 */
	public int[] getBreaks() {
		return Arrays.copyOf(breaks, breaks.length);
	}

	/**
	 * Returns the places that students from this school earned in this event. The bit set is clone before returning, and the places are one-based in the BitSet (i.e. bit 1 is true if this school won the event).
	 * 
	 * @return
	 */
	public BitSet getPlaces() {
		return (BitSet) places.clone();
	}
}
