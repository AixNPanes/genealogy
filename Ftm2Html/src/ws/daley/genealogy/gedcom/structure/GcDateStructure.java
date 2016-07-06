package ws.daley.genealogy.gedcom.structure;

import java.util.Vector;

import ws.daley.genealogy.Ged2XML;
import ws.daley.genealogy.gedcom.GcSimpleDate;
import ws.daley.genealogy.gedcom.GedcomFile;
import ws.daley.genealogy.gedcom.STRING;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptor;
import ws.daley.genealogy.gedcom.attribute.AttributeDescriptorMap;
import ws.daley.genealogy.gedcom.attribute.GcTransmissionDateAttribute;
import ws.daley.genealogy.gedcom.line.GcTimeLine;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.object.GcTags;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptor;
import ws.daley.genealogy.gedcom.structure.util.TagDescriptorMap;
import ws.daley.genealogy.gedcom.util.Util;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

/**
 * used in HEAD
 *
 * n DATE <TRANSMISSION_DATE>						{0:1}	
 *     +1 TIME <TIME_VALUE>							{0:1}	
 */

public class GcDateStructure extends Gc_Structure
{
	public static final int CALENDAR_HEBREW		= 0;
	public static final int CALENDAR_ROMAN		= 1;
	public static final int CALENDAR_FRENCH		= 2;
	public static final int CALENDAR_GREGORIAN	= 3;
	public static final int CALENDAR_JULIAN		= 4;
	public static final int CALENDAR_UNKNOWN	= 5;
	public static final int DATETYPE_NONE		= 0;
	public static final int DATETYPE_TEXT		= 1;
	public static final int DATETYPE_EXACT		= 2;
	public static final int DATETYPE_ABT		= 3;
	public static final int DATETYPE_EST		= 4;
	public static final int DATETYPE_FROM		= 5;
	public static final int DATETYPE_TO			= 6;
	public static final int DATETYPE_FROMTO		= 7;
	public static final int DATETYPE_BEF		= 8;
	public static final int DATETYPE_AFT		= 9;
	public static final int DATETYPE_BET		= 10;

	public char DateType;
	public GcSimpleDate Date1 = new GcSimpleDate();
	public GcSimpleDate Date2 = new GcSimpleDate();
	public STRING Text = new STRING();
	
	public GcTimeLine gcTime = new GcTimeLine();
	@SuppressWarnings("hiding")
	private static TagDescriptorMap tagDescriptorMap = new TagDescriptorMap();
	@SuppressWarnings("hiding")
	private static AttributeDescriptorMap attributeDescriptorMap = new AttributeDescriptorMap();
	
	static{
		tagDescriptorMap = TagDescriptorMap.newFromArray(new TagDescriptor[]{
				new TagDescriptor("TIME", 0, 1, GcTimeLine.class)
		});
		attributeDescriptorMap = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("TRANSMISSION_DATE", 0, 1, GcTransmissionDateAttribute.class)
		});
	}

	public GcDateStructure(GcBaseElement e, AttributeDescriptorMap _attributeDescriptorMap, TagDescriptorMap _tagDescriptorMap, Vector<GcBaseElement> _vector)
	{
		super(e, "DATE", AttributeDescriptorMap.newFromArray(attributeDescriptorMap, _attributeDescriptorMap), TagDescriptorMap.newFromArray(tagDescriptorMap, _tagDescriptorMap), _vector);
//		throw new RuntimeException("Implementation?");
	}
	
	public GcDateStructure()
	{
		this.DateType = DATETYPE_NONE;
		throw new RuntimeException("Implementation?");
	}
	
	public Boolean Import(GedcomFile file)
	{
		char[] szText;
		char[] szStart;
		int i,level;
	
		level = file.GetLevel();
		// INT type date?
		szText = file.GetText().toCharArray();
		szStart = file.GetText().toCharArray();
		if (Util.strncmp(szText,"INT",3) != 0)
		{
			i = 0;
			while (szText[i] != 0 && szText[i]!='(')
				i++;
			if (szText[i] != 0)
			{
				file.PrintError(Ged2XML.ERROR_DATEFORMAT,Boolean.TRUE);
				file.NextLine();
				return Boolean.TRUE;
			}
			file.AddIndex(i);
		}
		// Text type date?
		String text = file.GetText();
		if (text.startsWith("("))
		{
			int n = text.lastIndexOf(')');
			if (n >= 0)
			{
				this.Text.Set(text.substring(1, n).toCharArray());
				file.AddIndex(n+1);
			}
			else
			{
				this.Text.Set(text.toCharArray());
				file.AddIndex(text.length()+1);
			}
			this.DateType = DATETYPE_TEXT;
		}
		// Approximated type date?
		else if (Util.strncmp(szText,"ABT ",4) == 0 || Util.strncmp(szText,"CIR ",4) == 0 || Util.strncmp(szText,"CA. ",4) == 0)
		{
			if (Util.strncmp(szText,"ABT ",4)!=0 && file.convData.MessageLevel==Ged2XML.MESSAGELEVEL_ALL)
				file.PrintError(Ged2XML.ERROR_CIRCA,Boolean.TRUE);
			file.AddIndex(4);
			this.DateType = DATETYPE_ABT;
			this.Date1.InterpretDate(file);
		}
		else if (Util.strncmp(szText,"CA ",3)==0)
		{
			if (file.convData.MessageLevel==Ged2XML.MESSAGELEVEL_ALL)
				file.PrintError(Ged2XML.ERROR_CIRCA,Boolean.TRUE);
			file.AddIndex(3);
			this.DateType = DATETYPE_ABT;
			this.Date1.InterpretDate(file);
		}
		else if (Util.strncmp(szText,"CAL ",4) == 0 || Util.strncmp(szText,"EST ",4) == 0)
		{
			file.AddIndex(4);
			this.DateType = DATETYPE_EST;
			this.Date1.InterpretDate(file);
		}
		// FROM or FROM ... TO type date?
		else if (Util.strncmp(szText,"FROM ",5) == 0)
		{
			this.DateType = DATETYPE_FROM;
			file.AddIndex(5);
			this.Date1.InterpretDate(file);
			szText = file.GetText().toCharArray();
			if (Util.strncmp(file.GetText(),"TO ",3) == 0)
			{
				this.DateType = DATETYPE_FROMTO;
				file.AddIndex(3);
				this.Date2.InterpretDate(file);
			}
		}
		// TO type date?
		else if (Util.strncmp(szText,"TO ",3) == 0)
		{
			this.DateType = DATETYPE_TO;
			file.AddIndex(3);
			this.Date1.InterpretDate(file);
		}
		// BEF type date?
		else if (Util.strncmp(szText,"BEF ",4) == 0)
		{
			this.DateType = DATETYPE_BEF;
			file.AddIndex(4);
			this.Date1.InterpretDate(file);
		}
		// AFT type date?
		else if (Util.strncmp(szText,"AFT ",4) == 0)
		{
			this.DateType = DATETYPE_AFT;
			file.AddIndex(4);
			this.Date1.InterpretDate(file);
		}
		// BET type date?
		else if (Util.strncmp(szText,"BET ",4) == 0)
		{
			this.DateType = DATETYPE_BET;
			file.AddIndex(4);
			this.Date1.InterpretDate(file);
			szText = file.GetText().toCharArray();
			if (Util.strncmp(szText,"AND ",4) != 0)
				file.PrintError(Ged2XML.ERROR_DATEFORMAT,Boolean.TRUE);
			else
			{
				file.AddIndex(4);
				this.Date2.InterpretDate(file);
			}
		}
		// BETWEEN = BET (typical TMG error)
		else if (Util.strncmp(szText,"BETWEEN ",8) == 0)
		{
			if (file.convData.MessageLevel==Ged2XML.MESSAGELEVEL_ALL)
				file.PrintError(Ged2XML.ERROR_BETWEEN,Boolean.TRUE);
			this.DateType = DATETYPE_BET;
			file.AddIndex(8);
			this.Date1.InterpretDate(file);
			szText = file.GetText().toCharArray();
			if (Util.strncmp(szText,"AND ",4) != 0)
				file.PrintError(Ged2XML.ERROR_DATEFORMAT,Boolean.TRUE);
			else
			{
				file.AddIndex(4);
				this.Date2.InterpretDate(file);
			}
		}
		// Exact type date?
		else
		{
			this.DateType = DATETYPE_EXACT;
			this.Date1.InterpretDate(file);
		}
		// Unknown characters?
		while (file.GetText().toCharArray()[0]==' ')
			file.AddIndex(1);
		if (file.GetText().charAt(0) != 0)
		{
			if (file.convData.TextDates)
			{
				this.Text = new STRING(szStart);
				this.DateType = DATETYPE_TEXT;
			}
			else
				file.PrintError(Ged2XML.ERROR_DATEFORMAT,Boolean.TRUE);
		}
		file.NextLine();
		// Check if next line is TIME on same or higher level
		if (this.DateType==DATETYPE_EXACT && file.GetTag()==GcTags.TAG.TAG_TIME && file.GetLevel()>=level)
		{
			if (file.convData.MessageLevel==Ged2XML.MESSAGELEVEL_ALL)
				file.PrintError(Ged2XML.ERROR_TIME,Boolean.TRUE);
			String[] token = file.GetText().split("[:.]");
			for(int j = 0; j < token.length; j++)
			{
				if (token[j].length() > 0)
				{
					byte b = Byte.valueOf(token[j]).byteValue();
					switch(j)
					{
					case 0:
						this.Date1.hours = b;
						break;
					case 1:
						this.Date1.minutes = b;
						break;
					default:
						this.Date1.seconds = b;
						break;
					}
				}
			}
			file.NextLine();
		}
		return Boolean.TRUE;
	}
	
	public GcTimeLine getTime() {return (GcTimeLine)getElementForKey("TIME");}
};
