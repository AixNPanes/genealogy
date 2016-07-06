package ws.daley.genealogy;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import ws.daley.genealogy.gedcom.Conversion;
import ws.daley.genealogy.gedcom.ConversionData;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

public class Ged2XML
{
	// Return values
	public static final int RETURN_OK				= 0;
	public static final int RETURN_GEDCOMFAILURE	= 1;
	public static final int RETURN_GENXMLFAILURE	= 2;
	
	// Error messages
	public static final int ERROR_OK				= 0;	// No error
	public static final int ERROR_EOF				= 1;	// End of file reached (without TRLR)
	public static final int ERROR_USERDEFINED		= 2;	// The line is ignored because of a user defined tag
	public static final int ERROR_UNKNOWNTAG		= 3;	// The tag is unknown (not standard)
	public static final int ERROR_HEADERMISSING		= 4;	// The header record is missing
	public static final int ERROR_ILLEGALTAG		= 5;	// Unexpected tag
	public static final int ERROR_LINE				= 6;	// The line does not conform to the Gedcom standard
	public static final int ERROR_GEDCOMFILE		= 7;	// The Gedcom file could not be opened
	public static final int ERROR_XREFMISSING		= 8;	// An XREF was expected on this line.
	public static final int ERROR_XREFTOOLONG		= 9;	// An XREF is limited to 20 characters according to the GEDCOM standard.
	public static final int ERROR_DATEFORMAT		= 10;	// Erroneous date
	public static final int ERROR_ABORTED			= 11;	// The conversion was aborted
	public static final int ERROR_UNKNOWNCHARSET	= 12;	// An unknown character set was detected, ANSEL is assumed
	public static final int ERROR_BETWEEN			= 13;	// Warning: BETWEEN detected in date, treated as BET
	public static final int ERROR_CIRCA				= 14;	// Warning: CA, CA. or CIR detected in date, treated as ABT
	public static final int ERROR_TIME				= 15;	// Warning: TIME detected after date, accepted but not standard GEDCOM
	public static final int ERROR_POINTERMISSING	= 16;	// A pointer was expected after the tag
	public static final int ERROR_IGNORE			= 100;	// The line will be ignored.
	// Default descriptions for event types
	public static final int EVENTTYPE_BIRT		= 1000;
	public static final int EVENTTYPE_CHR		= 1001;
	public static final int EVENTTYPE_DEAT		= 1002;
	public static final int EVENTTYPE_BURI		= 1003;
	public static final int EVENTTYPE_CREM		= 1004;
	public static final int EVENTTYPE_ADOP		= 1005;
	public static final int EVENTTYPE_BAPM		= 1006;
	public static final int EVENTTYPE_BARM		= 1007;
	public static final int EVENTTYPE_BASM		= 1008;
	public static final int EVENTTYPE_BLES		= 1009;
	public static final int EVENTTYPE_CHRA		= 1010;
	public static final int EVENTTYPE_CONF		= 1011;
	public static final int EVENTTYPE_FCOM		= 1012;
	public static final int EVENTTYPE_ORDN		= 1013;
	public static final int EVENTTYPE_NATU		= 1014;
	public static final int EVENTTYPE_EMIG		= 1015;
	public static final int EVENTTYPE_IMMI		= 1016;
	public static final int EVENTTYPE_CENS		= 1017;
	public static final int EVENTTYPE_PROB		= 1018;
	public static final int EVENTTYPE_WILL		= 1019;
	public static final int EVENTTYPE_GRAD		= 1020;
	public static final int EVENTTYPE_RETI		= 1021;
	public static final int EVENTTYPE_RESI		= 1022;
	// Attributes
	public static final int EVENTTYPE_CAST		= 1023;
	public static final int EVENTTYPE_DSCR		= 1024;
	public static final int EVENTTYPE_EDUC		= 1025;
	public static final int EVENTTYPE_IDNO		= 1026;
	public static final int EVENTTYPE_NATI		= 1027;
	public static final int EVENTTYPE_NCHI		= 1028;
	public static final int EVENTTYPE_NMR		= 1029;
	public static final int EVENTTYPE_OCCU		= 1030;
	public static final int EVENTTYPE_PROP		= 1031;
	public static final int EVENTTYPE_RELI		= 1032;
	public static final int EVENTTYPE_SSN		= 1033;
	public static final int EVENTTYPE_TITL		= 1034;
	// Family events
	public static final int EVENTTYPE_ANUL		= 1035;
	public static final int EVENTTYPE_DIV		= 1036;
	public static final int EVENTTYPE_DIVF		= 1037;
	public static final int EVENTTYPE_ENGA		= 1038;
	public static final int EVENTTYPE_MARR		= 1039;
	public static final int EVENTTYPE_MARB		= 1040;
	public static final int EVENTTYPE_MARC		= 1041;
	public static final int EVENTTYPE_MARL		= 1042;
	public static final int EVENTTYPE_MARS		= 1043;
	
	// Values for MessageLevel
	public static final int MESSAGELEVEL_ALL		= 0;	// Print all messages, including warnings
	public static final int MESSAGELEVEL_SKIPIGNORE	= 1;	// Don't print error messages for ignored lines
	public static final int MESSAGELEVEL_SKIPERRORS	= 2;	// Neither print error messages for known errors
	
	public static final int WM_USER				= 1024;
	public static final int WM_CONVPROGRESS		= (WM_USER+7000);
	public static final int WM_SAVEPROGRESS		= (WM_USER+7001);
	
	/****************************************************************
	 *                                                              *
	 *  Copyright (C) 2003,2005 Christoffer Owe                     *
	 *  For conditions of distribution and use, see License.txt     *
	 *                                                              *
	 ****************************************************************/

	public static int convert(ConversionData convdata)
	{
		Conversion conv = new Conversion(convdata);
		return conv.Run();
	}
	
	public static void main(String[] args) throws FileNotFoundException
	{
		ConversionData conversionData = new ConversionData();
		conversionData.szGedcomFile = "daley.ged";
		conversionData.szGenXMLFile = "daley.xml";
		conversionData.MessageLevel = ConversionData.MESSAGELEVEL_ALL;
		conversionData.hLogFile = new FileOutputStream("daley.err");
		conversionData.MsgProvider = new Ged2XMLMsgProvider();
		convert(conversionData);
	}
};