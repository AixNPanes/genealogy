package example;

import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import jaxb.gedcom.GEDCOM;


public class CreateGedcomExample {
	static final String OUTPUT_FILENAME = "output.filename";

	private CreateGedcomExample() {
	};

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String filename = System.getProperty(OUTPUT_FILENAME);
		if (filename == null) {
			throw new IllegalArgumentException(OUTPUT_FILENAME+" not defined");
		}
		CreateGedcomExample createGed = new CreateGedcomExample();
		try {
			System.out.println("Creating "+filename);
			MyGEDCOM myGed = new MyGEDCOM();
			GEDCOM ged = myGed.create();
			createGed.print(ged, filename);
			System.out.println("done.");
		} catch (Exception e) {
			System.err.println("Current working directory: "+System.getProperty("user.dir"));
			e.printStackTrace();
		}
	}

	
	private void print(GEDCOM ged, String filename) throws Exception {
		JAXBContext jc = JAXBContext.newInstance("jaxb.gedcom");

		// create a Marshaller and marshal to filename
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		FileOutputStream out = new FileOutputStream(filename);
		ged.marshal(m, out);
		out.close();		
	}
	

}
	
	
