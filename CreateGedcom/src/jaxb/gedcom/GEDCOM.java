package jaxb.gedcom;

import java.io.FileOutputStream;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import jaxb.gedcom.ContactRecElement;
import jaxb.gedcom.EventRecElement;
import jaxb.gedcom.FamilyRecElement;
import jaxb.gedcom.GEDCOMElement;
import jaxb.gedcom.GroupRecElement;
import jaxb.gedcom.HeaderRecElement;
import jaxb.gedcom.IndividualRecElement;
import jaxb.gedcom.LinkElement;
import jaxb.gedcom.ObjectFactory;
import jaxb.gedcom.SourceRecElement;
import jaxb.gedcom.SubmitterElement;

/**
 * A GEDCOM file that will be written out as a GEDCOM 6.0 xml file.
 * See the <a href="http://www.familysearch.org/Eng/Home/FAQ/frameset_faq.asp?FAQ=faq_gedcom.asp">Gedcom 6.0 specification</A> for more details.
 * 
 * @author Ken Stevens
 * @version 25-Feb-2006
 *
 */
public class GEDCOM {

	private GEDCOMElement ged;

	/**
	 * Instantiate a new GEDCOM to which records will be added, and which
	 * will eventually be written out as a file.
	 */
	public GEDCOM() {
		ged = (new ObjectFactory()).createGEDCOMElement();
	}

	/**
	 * Add a contact record to the GEDCOM
	 * @param contact
	 */
	public void addContactRec(ContactRec contact) {
		ged.getContactRec().add((ContactRecElement) contact);
	}

	/**
	 * Add an individual record to the GEDCOM
	 * @param indi
	 */
	public void addIndividualRec(IndividualRec indi) {
		ged.getIndividualRec().add((IndividualRecElement) indi);
	}

	/**
	 * Add a group record to the GEDCOM
	 * @param group 
	 */
	public void addGroupRec(GroupRec group) {
		ged.getGroupRec().add((GroupRecElement) group);
	}

	/**
	 * Add a source record to the GEDCOM
	 * @param source
	 */
	public void addSourceRec(SourceRec source) {
		ged.getSourceRec().add((SourceRecElement) source);
	}

	/**
	 * Add an event record to the GEDCOM
	 * @param event 
	 */
	public void addEventRec(EventRec event) {
		ged.getEventRec().add((EventRecElement) event);
	}

	/**
	 *  Add a family record to the GEDCOM
	 * @param family
	 */
	public void addFamilyRec(FamilyRec family) {
		ged.getFamilyRec().add((FamilyRecElement) family);
	}

	/**
	 * Write the GEDCOM out as a file.  For example:
	 * <PRE>
	 * 	private void print(GEDCOM ged, String filename) throws Exception {
	 *    JAXBContext jc = JAXBContext.newInstance("jaxb.gedcom");
	 *    // create a Marshaller and marshal to filename
	 *	  Marshaller m = jc.createMarshaller();
	 *	  m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	 *	  FileOutputStream out = new FileOutputStream(filename);
	 *	  ged.marshal(m, out);
	 *	  out.close();	
	 *  }
	 * </PRE>
	 * 
	 * @param m JAXB Marshaller
	 * @param out Output stream to write XML to
	 * @throws JAXBException
	 */
	public void marshal(Marshaller m, FileOutputStream out)
			throws JAXBException {
		m.marshal(ged, out);
	}

	/**
	 * Set the Header record of the GEDCOM
	 * @param header
	 */
	public void setHeader(HeaderRec header) {
		ged.setHeaderRec(header);
	}

	/**
	 * This is a convenience method for HeaderRec.setSubmitter(ContactRec).
	 * Set the reference to the contact record that contains the
	 * contact information for the contact who created
	 * the GEDCOM.
	 * <p>
	 * <i>IMPORTANT</i> This ContactRec must have already been added to the
 	 * GEDCOM earlier via the GEDCOM.addContactRec(ContactRec) method.
 	 *  
	 * @see HeaderRec#setSubmitter(ContactRec)
	 * @see #addContactRec(ContactRec)
	 * 
	 * @param contact
	 */
	public void setSubmitter(ContactRec contact) {
		HeaderRecElement header = ged.getHeaderRec();
		SubmitterElement submitter = new SubmitterElement();
		LinkElement link = new LinkElement();
		link.setTarget("ContactRec");
		link.setRef(contact);
		submitter.setLink(link);
		header.setSubmitter(submitter);
	}
}
