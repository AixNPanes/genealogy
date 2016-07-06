package jaxb.gedcom;

import java.util.HashMap;

import jaxb.gedcom.GroupRecElement;
import jaxb.gedcom.LinkElement;
import jaxb.gedcom.MemberElement;
import jaxb.gedcom.NameElement;

/**
 * A GEDCOM Group record.
 * @author Ken Stevens
 * @version 25-Feb-2006
 *
 */
public class GroupRec extends GroupRecElement {
	private static HashMap<String, GroupRec> records = new HashMap<String, GroupRec>();

	/**
	 * Create a new GroupRec and record it in the in-memory database.
	 * @param key the id of the GroupRec being created.
	 * @see #getRecord(String)
	 */
	public GroupRec(String key) {
		super();
		super.setId(key);
		records.put(key, this);
	}

	/**
	 * Retrieve the GroupRec identified by key from the in-memory database.
	 * @param key the id of the GroupRec being retrieved
	 * @return the instance of GroupRec 
	 */
	public static GroupRec getRecord(String key) {
		return records.get(key);
	}

	/**
	 * @param value the name of the group
	 */
	public void setName(String value) {
		if (value != null) {
			NameElement name = new NameElement();
			name.setValue(value);
			super.setName(name);
		}
	}

	/**
	 * Add an individual to this group.
	 * @param indi
	 */
	public void addIndi(IndividualRec indi) {
		MemberElement member = new MemberElement();
		LinkElement link = new LinkElement();
		link.setTarget("IndividualRec");
		link.setRef(indi);
		member.setLink(link);
		super.getMember().add(member);
	}
}
