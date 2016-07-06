package ws.daley.genealogy.gedcom;
/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import ws.daley.genealogy.Ged2XML;

public class Conversion
{
	private ConversionData ConvData = new ConversionData();

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/
	
	public Conversion(ConversionData convdata)
	{
		this.ConvData = convdata;
	}

	public int Run()
	{
		File gedcomFileHandle;
		File genXMLFileHandle;
		GedcomFile gedcomFile = null;
//		GenXML GenXMLFile = null;
//		GedcomData data = new GedcomData();
		Boolean convOK;
//		long iRepositories = 0;
//		long iSources = 0;
//		long iExcerpts = 0;
//		long iEventtypes = 0;
//		long iPersons = 0;
//		long iAssertions = 0;
	
		// Read the Gedcom file
		gedcomFileHandle = new File(new String(this.ConvData.szGedcomFile));
		if (gedcomFileHandle.canRead()) {
			gedcomFile = new GedcomFile(gedcomFileHandle, this.ConvData);
//			try{GedcomFile.gedcom.emitXML(System.out, 0);}
//			catch(Exception e){throw new RuntimeException(e);}
//			Vector<GcBaseElement> elements = GedcomFile.getBaseElements();
//			throw new RuntimeException("Finish");
			convOK = Boolean.TRUE;
		} else {
			gedcomFile.PrintError(Ged2XML.ERROR_GEDCOMFILE,Boolean.FALSE);
			convOK = Boolean.FALSE;
		}
		if (!convOK)
			return Ged2XML.RETURN_GEDCOMFAILURE;
		
		GedcomXml gedcomXml = new GedcomXml(gedcomFile.getGedcom());
		
	
		// Write the GenXML file
		try {
	        gedcomXml.print(this.ConvData.szGenXMLFile);
        }
        catch (Exception e) {e.printStackTrace();}
		
//		try {
////			GenXMLFile = new GenXML(new FileOutputStream(GenXMLFileHandle),ConvData);
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
////		GenXMLFile.Header(data.header,data.submitters);
////		iRepositories = GenXMLFile.Repository(data.repositories,data.notes,data.noteIDs);
////		iSources = GenXMLFile.Source(data.sources,data.sourcerefs,data.repositoryIDs,data.notes,data.noteIDs);
////		iExcerpts = GenXMLFile.Excerpt(data.sourcerefs,data.sourceIDs,data.notes,data.noteIDs);
////		iEventtypes = GenXMLFile.Eventtype(data.eventtypes);
////		iPersons = GenXMLFile.Person(data.persons);
////		iAssertions = GenXMLFile.Assertion(data.persons,data.families,data.personIDs,data.notes,data.noteIDs);
//
//		//TODO
//		//GenXMLFile.Total(iRepositories,iSources,iExcerpts,iEventtypes,iPersons,iAssertions);
		return Ged2XML.RETURN_OK;
	}
};