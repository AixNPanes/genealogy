package ws.daley.genealogy.gedcom;

import ws.daley.genealogy.gedcom.gedcomrecords.GedcomRecord;

@SuppressWarnings("serial")
public class SyntaxException extends RuntimeException
{
	@SuppressWarnings("unused")
	private SyntaxException() {}
	public SyntaxException(GedcomRecord record, GedcomRecord subRecord)
	{
		super("Syntax Exception\n\trecord=\"" + record.line.group(0) + "\"\n\tsubRecord=\""+subRecord.line.group(0) + "\"");
	}
}