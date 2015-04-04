/**
 * 
 */
package mathsquared.pointtrack;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single event, or category of points. Read from {@code events} on program start using the {@link #load(BufferedReader)} method.
 * 
 * @author alex
 *
 */
public class CompetitiveEvent {
	String code;
	String name;
	
	public CompetitiveEvent (String code, String name) {
		this.code = code;
		this.name = name;
	}
	
	/**
	 * Creates a list of events from a stream of characters.
	 * 
	 * The format of this stream is a series of lines, each one being either
	 * <ul>
	 * <li>blank,</li>
	 * <li>a comment (beginning with the # sign), or</li>
	 * <li>an event line containing optional whitespace, the event code, mandatory whitespace, the event name, and then optional whitespace.</li>
	 * </ul>
	 * 
	 * The events will be returned in the list in the same order as they appear in the file. Blank and comment lines are ignored.
	 * 
	 * @param spec the buffered reader from which to obtain this file
	 * @return a list of {@linkplain CompetitiveEvent competitive events} generated from the file
	 * @throws IOException if an I/O exception occurs when reading from {@code spec}
	 * @throws IllegalArgumentException if the text from {@code spec} is formatted incorrectly
	 */
	public static List<CompetitiveEvent> load (BufferedReader spec) throws IOException, IllegalArgumentException {
		// Create the list
		List<CompetitiveEvent> ret = new ArrayList<>();
		
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
			
			String[] arr = line.split("\\s+", 2); // split into at most two fields
			
			// handle an invalid config file (no spaces)
			if (arr.length < 2) {
				throw new IllegalArgumentException("Space(s) must separate code and name on line "+ lineNo);
			}
			
			// else, create a new event
			ret.add(new CompetitiveEvent(arr[0], arr[1]));
		}
		
		return ret;
	}

	/**
	 * Returns the short code for the event (a few characters).
	 * 
	 * @return the code as a string
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Returns the long-form nickname for this event.
	 * 
	 * @return the long name as a string
	 */
	public String getName() {
		return name;
	}
}
