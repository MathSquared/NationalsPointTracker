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
	 * @param ent the number of entries this school submitted for this event; this must be non-negative
	 * @param brk the number of students breaking to each elimination round (copied by the constructor), one by one, including finals; the entries must be not greater than {@code ent}, non-negative, and monotonically non-increasing. For instance, a tournament with quarterfinals would have three entries in this array, one for quarters, one for semis, and one for finals. A null value for this parameter is illegal. A value of -1 indicates that breaks for this round are currently unknown; in this case, if a round is marked as -1, all following rounds must also be -1.
	 * @param plc the places the school earned in this event (cloned by the constructor); this is one-based, bit 0 must be false, and the {@linkplain BitSet#cardinality() cardinality} of the set is treated as the number of finalists and must match the last element of {@code brk}; this may be null if awards haven't happened yet
	 */
	public ResultCell (int ent, int[] brk, BitSet plc) {
		// Consistency checking //
		
		if (ent < 0) {
			throw new IllegalArgumentException("ent must be non-negative: you submitted" + ent);
		}
		
		// check monotonically decreasing
		int prevX = ent; // start at ent because no entry here should be above it; that way, we don't have to compare each entry to ent because it should be decreasing from there
		for (int x : brk) {
			if (x > prevX) {
				throw new IllegalArgumentException("brk must be monotonically non-increasing: illegal transition from " + prevX + " to " + x);
			}
			
			if (x < -1) {
				throw new IllegalArgumentException("breaks in a round must be non-negative or -1 for missing: in other words, not " + x);
			}
			
			prevX = x;
		}
		// now, prevX contains the last entry in the array
		
		if (plc != null) { // allow incomplete data
			// check plc.cardinality against it
			if (plc.cardinality() == prevX) {
				throw new IllegalArgumentException(
						"cardinality of places must match finalists: " + plc.cardinality() + " places for " + prevX + " finalists");
			}
			// ensure !plc.get(0)
			if (plc.get(0)) {
				throw new IllegalArgumentException("this school did better than first place: they got zeroth place!");
			}
		}
		entries = ent;
		breaks = Arrays.copyOf(brk, brk.length);
		places = plc == null ? null : (BitSet) plc.clone();
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
		return places == null ? null : (BitSet) places.clone();
	}
}
