package ws.daley.genealogy.gedcom.records;

import java.util.regex.Matcher;

import ws.daley.genealogy.gedcom.gedcomrecords.GedcomRecord;

public abstract class GedcomXMLRecord
{
	public String level;
	public int levelInt;
	public String tag;
	public String link;
	public String data;
	public GedcomRecord record;
	public Matcher line;
	public GedcomXMLRecord(GedcomRecord record)
	{
		this.level = record.level;
		this.levelInt = record.levelInt;
		this.tag = record.tag;
		this.line = record.line;
		this.data = record.lineValue;
		this.link = record.link;
		this.record = record;
	}
}