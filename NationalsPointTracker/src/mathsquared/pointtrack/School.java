/**
 * 
 */
package mathsquared.pointtrack;

/**
 * Represents a single school competing at the tournament.
 * 
 * @author alex
 *
 */
public class School {
	String code;
	String abbrev;
	String name;
	String note;
	
	public School (String code, String abbrev, String name, String note) {
		this.code = code;
		this.abbrev = abbrev;
		this.name = name;
		this.note = note;
	}

	/**
	 * Returns the postings code or code prefix for students from this school.
	 * 
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Returns the UI abbreviation for the school.
	 * 
	 * @return
	 */
	public String getAbbrev() {
		return abbrev;
	}

	/**
	 * Returns the long name of the school.
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns user-specified notes about the school.
	 * 
	 * @return
	 */
	public String getNote() {
		return note;
	}
}
