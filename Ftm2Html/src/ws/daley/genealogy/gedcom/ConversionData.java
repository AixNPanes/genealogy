package ws.daley.genealogy.gedcom;

import java.io.OutputStream;

public class ConversionData
{
	public static final int MESSAGELEVEL_ALL		= 0;	// Print all messages, including warnings
	public static final int MESSAGELEVEL_SKIPIGNORE	= 1;	// Don't print error messages for ignored lines
	public static final int MESSAGELEVEL_SKIPERRORS	= 2;	// Neither print error messages for known errors
	
	
	// Path to GEDCOM file.
	public String		szGedcomFile = new String();			
	// Path to new GenXML file. If the file exist, it will be overwritten.
	public String		szGenXMLFile = new String();			
	// Handle to log file. May for example be standard out.
	public OutputStream	hLogFile;				
	// See values above
	public int			MessageLevel;			
	// Function that will provide texts for error messages and event types.
	public MsgProvider	MsgProvider;			
	// Assume the name preciding the surname is a given name
	public Boolean		AssumeGivenName = Boolean.FALSE;		
	// Use structured names instead of the value of the NAME tag
	public Boolean		UseStructuredNames = Boolean.FALSE;		
	// Assume dates to be Gregorian when calendar is unspecified
	public Boolean		AssumeGregorian = Boolean.FALSE;		
	// Treat erroneous dates as text
	public Boolean		TextDates = Boolean.FALSE;				
	// Read alias as NAME
	public Boolean		AliasNames = Boolean.FALSE;				
}
