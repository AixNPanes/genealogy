package jaxb.gedcom;

import java.text.SimpleDateFormat;
import java.util.Date;

import jaxb.gedcom.FileCreationElement;

/**
 * GEDCOM File Creation details
 * @author Ken Stevens
 * @version 25-Feb-2006
 *
 */
public class FileCreation extends FileCreationElement {

	/**
	 * constructor
	 */
	public FileCreation() {
		super();
	}

	/**
	 * @param date the date this GEDCOM was created
	 */
	public void setDate(Date date) {
		super.setDate(new SimpleDateFormat("dd MMM yyyy").format(date));
		super.setTime(new SimpleDateFormat("HH:mm:ss").format(date));
	}

}
