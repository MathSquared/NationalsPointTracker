/**
 * 
 */
package mathsquared.pointtrack;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	 * Creates a list of schools from a stream of characters.
	 * 
	 * The format of this stream is a series of lines, each one being either
	 * <ul>
	 * <li>blank,</li>
	 * <li>a comment (beginning with the # sign), or</li>
	 * <li>a school line containing optional whitespace, the school code, mandatory whitespace, the school abbreviation, mandatory whitespace, the school name (which may contain whitespace), optional whitespace, optionally notes about the school (enclosed in parentheses), and then optional whitespace.</li>
	 * </ul>
	 * 
	 * The schools will be returned in the list in the same order as they appear in the file. Blank and comment lines are ignored.
	 * 
	 * @param spec the buffered reader from which to obtain this file
	 * @return a list of {@linkplain School schools} generated from the file
	 * @throws IOException if an I/O exception occurs when reading from {@code spec}
	 * @throws IllegalArgumentException if the text from {@code spec} is formatted incorrectly
	 */
	public static List<School> load (BufferedReader spec) throws IOException, IllegalArgumentException {
		// Create the list
		List<School> ret = new ArrayList<>();

		// Read each line
		String line;
		int lineNo = 0; // used in exception messages
		while ((line = spec.readLine()) != null) {
			lineNo++;

			// trim the line for manipulation below
			// (we don't do this in the loop header because nulls)
			line = line.trim();

			// skip empty lines and comments
			if (line.length() == 0 || line.charAt(0) == '#') {
				continue;
			}

			String[] arr = line.split("\\s+", 3); // split into at most three fields

			// handle an invalid config file (no spaces)
			if (arr.length < 3) {
				throw new IllegalArgumentException("Space(s) must separate code and name on line "+ lineNo);
			}
			
			// further split last field into name and notes
			String[] nameNotes = arr[2].split("\\s*(", 2);
			
			// create a new school
			if (nameNotes.length == 1) {
				// notes are empty string
				ret.add(new School(arr[0], arr[1], nameNotes[0], ""));
			} else {
				// remove closing paren if present
				if (nameNotes[1].charAt(nameNotes[1].length() - 1) == ')') {
					nameNotes[1] = nameNotes[1].substring(0, nameNotes[1].length() - 1);
				}
				
				ret.add(new School(arr[0], arr[1], nameNotes[0], nameNotes[1]));
			}
		}

		return ret;
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
