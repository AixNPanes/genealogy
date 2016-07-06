package jaxb.gedcom;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import jaxb.gedcom.ChangedElement;
import jaxb.gedcom.CitationElement;
import jaxb.gedcom.EvidenceElement;
import jaxb.gedcom.IndivNameElement;
import jaxb.gedcom.IndividualRecElement;
import jaxb.gedcom.LinkElement;
import jaxb.gedcom.NamePartElement;
import jaxb.gedcom.PersInfoElement;

/**
 * A GEDCOM Individual record
 * @author Ken Stevens
 * @version 25-Feb-2006
 *
 */
public class IndividualRec extends IndividualRecElement {
	private static HashMap<String, IndividualRec> records = new HashMap<String, IndividualRec>();

	private IndivNameElement name = new IndivNameElement();

	private static final String SURNAME_LEVEL = "1";

	private static final String SURNAME_TYPE = "surname";

	private static final String MAIDEN_NAME_LEVEL = "2";

	private static final String MAIDEN_NAME_TYPE = "maiden name";

	private static final String GIVEN_NAME_LEVEL = "3";

	private static final String GIVEN_NAME_TYPE = "given name";

	private enum PersInfoType {
		/**
		 * Used to store occupations
		 */
		occupation
	};

	/**
	 * Used for individual Gender
	 */
	public static final String MALE = "M";
	/**
	 * Used for individual Gender
	 */
	public static final String FEMALE = "F";

	/**
	 * Create a new IndividualRec and record it in the in-memory database.
	 * @param key the id of the IndividualRec being created.
	 * @see #getRecord(String)
	 */
	public IndividualRec(String key) {
		super();
		super.setId(key);
		records.put(key, this);
	}
	/**
	 * Retrieve the IndividualRec identified by key from the in-memory database.
	 * @param key the id of the IndividualRec being retrieved
	 * @return the instance of IndividualRec 
	 */
	static public IndividualRec getRecord(String key) {
		return records.get(key);
	}

	/**
	 * @param value the given name of the person
	 */
	public void addGivenName(String value) {
		if (value != null) {
			addName(GIVEN_NAME_LEVEL, GIVEN_NAME_TYPE, value);
		}
	}

	/**
	 * @param value the surname of the person
	 */
	public void addSurName(String value) {
		if (value != null) {
			addName(SURNAME_LEVEL, SURNAME_TYPE, value);
		}
	}

	/**
	 * @param value the maiden name of the person
	 */
	public void addMaidenName(String value) {
		if (value != null) {
			addName(MAIDEN_NAME_LEVEL, MAIDEN_NAME_TYPE, value);
		}
	}

	private void addName(String level, String type, String value) {
		NamePartElement np = new NamePartElement();
		np.setLevel(level);
		np.setType(type);
		np.setValue(value);
		if (super.getIndivName().isEmpty()) {
			// Assume only one name
			super.getIndivName().add(name);
		}
		name.getContent().add(np);
	}

	/**
	 * Add a note about the person
	 * @param value
	 */
	public void addNote(String value) {
		NoteHelper.addNote(super.getNote(), value);
	}

	/**
	 * Add a reference to the source where this individual was discovered.
	 * @param sourceRec
	 */
	public void addCitation(SourceRec sourceRec) {
		EvidenceElement evidence = new EvidenceElement();
		CitationElement citation = new CitationElement();
		LinkElement link = new LinkElement();
		link.setTarget("SourceRec");
		link.setRef(sourceRec);
		citation.setLink(link);
		evidence.getCitation().add(citation);
		super.getEvidence().add(evidence);
	}

	// public void setSubmitter(IndividualRec indi) {
	// SubmitterElement submitter = new SubmitterElement();
	// LinkElement link = new LinkElement();
	// link.setTarget("IndividualRec");
	// link.setRef(indi);
	// submitter.setLink(link);
	// super.setSubmitter(submitter);
	// }
	/**
	 * @param value the occupation of the person
	 */
	public void setOccupation(String value) {
		if (value != null) {
			PersInfoElement pi = new PersInfoElement();
			pi.setType(PersInfoType.occupation.toString());
			pi.setInformation(value);
			super.getPersInfo().add(pi);
		}
	}


	/**
	 * 
	 * @param date the date this record was last changed.
	 */
	public void addChanged(Date date) {
		ChangedElement changed = new ChangedElement();
		changed.setTime(new SimpleDateFormat("HH:mm:ss").format(date));
		changed.setDate(new SimpleDateFormat("dd MMM yyyy").format(date));
		super.getChanged().add(changed);
	}

}
