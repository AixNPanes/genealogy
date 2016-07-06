package jaxb.gedcom;

import java.util.HashMap;

import jaxb.gedcom.SourceRecElement;

/**
 * A GEDCOM Source.
 * @author Ken Stevens
 * @version 25-Feb-2006
 *
 */
public class SourceRec extends SourceRecElement {
	private static HashMap<String, SourceRec> records = new HashMap<String, SourceRec>();

	/**
	 * Create a new SourceRec and record it in the in-memory database.
	 * @param key the id of the SourceRec being created.
	 * @see #getRecord(String)
	 */

	public SourceRec(String key) {
		super();
		super.setId(key);
		records.put(key, this);
	}
	/**
	 * Retrieve the SourceRec identified by key from the in-memory database.
	 * @param key the id of the SourceRec being retrieved
	 * @return the instance of SourceRec 
	 */
	static public SourceRec getRecord(String key) {
		return records.get(key);
	}

	/**
	 * Add a note about the source
	 * @param value
	 */
	public void addNote(String value) {
		NoteHelper.addNote(super.getNote(), value);
	}
}
