package example;

import java.util.Date;

import jaxb.gedcom.ContactRec;
import jaxb.gedcom.FileCreation;
import jaxb.gedcom.GEDCOM;
import jaxb.gedcom.HeaderRec;
import jaxb.gedcom.MailAddress;
import jaxb.gedcom.Product;
import jaxb.gedcom.Supplier;


public class MyGEDCOM {
	static final String TOCCATA_ID = "CB001";

	public MyGEDCOM() {
		super();
	}

	public GEDCOM create() {
		// GEDCOM
		GEDCOM ged = new GEDCOM();
		
		// Supplier Contact
		ContactRec toccata = createToccata();
		// Add toccata contact to ged
		ged.addContactRec(toccata);
		
		HeaderRec header = createHeaderRec(toccata);
		ged.setHeader(header);
		
		return ged;
	}

	private ContactRec createToccata() {
		// Toccata ContactRec
		ContactRec toccata = new ContactRec(TOCCATA_ID);
		toccata.setType("business");
		toccata.setName("Toccata Software Inc.");
		toccata.addEmail("ken.stevens@sympatico.ca");
		toccata.addURI("http://www.toccatagames.com/");

		// Mailing Address
		MailAddress address = new MailAddress();
		address.addStreet("29 Marmaduke St.");
		address.addCity("Toronto");
		address.addProvince("Ontario");
		address.addCountry("Canada");
		address.addPostalCode("M6R 1T1");
		toccata.addMailAddress(address);
		
		// Phone numbers
		toccata.addPhone("416 555 1212", "home");

		return toccata;
	}
	
	private HeaderRec createHeaderRec(ContactRec toccata) {
		// FileCreation
		FileCreation fc = new FileCreation();
		fc.setDate(new Date());
		fc.setCopyright("2006");
		
		// Product
		Product prod = new Product();
		prod.setName("SourceForge createGedcom");
		prod.setProductId("createGedcom");
		prod.setVersion("1.0");

		// Supplier
		Supplier supplier = new Supplier();
		supplier.setLink(toccata);
		prod.setSupplier(supplier);
		fc.setProduct(prod);

		// HeaderRec
		HeaderRec header = new HeaderRec();
		header.setSubmitter(toccata);
		header.setFileCreation(fc);
		
		return header;
	}
}
