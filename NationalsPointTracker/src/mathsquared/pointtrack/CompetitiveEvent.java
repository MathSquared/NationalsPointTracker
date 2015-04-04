/**
 * 
 */
package mathsquared.pointtrack;

/**
 * Represents a single event, or category of points. Read from {@code events} on program start.
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
