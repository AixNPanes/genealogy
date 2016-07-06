package jaxb.gedcom;

import java.util.HashMap;

import jaxb.gedcom.BasedOnElement;
import jaxb.gedcom.ChildElement;
import jaxb.gedcom.FamilyRecElement;
import jaxb.gedcom.HusbFathElement;
import jaxb.gedcom.LinkElement;
import jaxb.gedcom.WifeMothElement;

/**
 * A GEDCOM Family record.
 * @author Ken Stevens
 * @version 25-Feb-2006
 *
 */
public class FamilyRec extends FamilyRecElement {
	private static HashMap<String, FamilyRec> records = new HashMap<String, FamilyRec>();
	/**
	 * Create a new FamilyRec and record it in the in-memory database.
	 * @param key the id of the FamilyRec being created.
	 * @see #getRecord(String)
	 */
	public FamilyRec(String key) {
		super();
		super.setId(key);
		records.put(key, this);
	}
	/**
	 * Retrieve the FamilyRec identified by key from the in-memory database.
	 * @param key the id of the FamilyRec being retrieved
	 * @return the instance of FamilyRec 
	 */
	static public FamilyRec getRecord(String key) {
		return records.get(key);
	}
	
	/**
	 * @param indi the husband/father of the family
	 */
	public void setHusband(IndividualRec indi) {
		if (indi != null) {
			if (indi.getGender() != null && indi.getGender().equals(IndividualRec.FEMALE)) {
				System.err.println("WARNING: FEMALE Individual "+indi.getId()+" is being set as a husband or father.");
			}
			HusbFathElement husband = new HusbFathElement();
			LinkElement link = new LinkElement();
			link.setTarget("IndividualRec");
			link.setRef(indi);
			husband.setLink(link);
			super.setHusbFath(husband);
		}
	}
	/**
	 * @param indi the wife/mother of the family
	 */
	public void setWife(IndividualRec indi) {
		if (indi != null) {
			if (indi.getGender() != null && indi.getGender().equals(IndividualRec.MALE)) {
				System.err.println("WARNING: MALE Individual "+indi.getId()+" is being set as a wife or mother.");
			}
			WifeMothElement wife = new WifeMothElement();
			LinkElement link = new LinkElement();
			link.setTarget("IndividualRec");
			link.setRef(indi);
			wife.setLink(link);
			super.setWifeMoth(wife);
		}
	}
	/**
	 * Add a child to a family
	 * @param indi
	 */
	public void addChild(IndividualRec indi) {
		ChildElement child = new ChildElement();
		LinkElement link = new LinkElement();
		link.setTarget("IndividualRec");
		link.setRef(indi);
		child.setLink(link);
		super.getChild().add(child);
	}
	/**
	 * 
	 * @param eventRec the marriage event that created this family
	 */
	public void setBasedOn(EventRec eventRec) {
		BasedOnElement basedOn = new BasedOnElement();
		LinkElement link = new LinkElement();
		link.setTarget("EventRec");
		link.setRef(eventRec);
		basedOn.getLink().add(link);
		super.setBasedOn(basedOn);
	}
}
