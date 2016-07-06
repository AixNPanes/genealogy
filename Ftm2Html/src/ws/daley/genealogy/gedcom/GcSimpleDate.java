package ws.daley.genealogy.gedcom;

import ws.daley.genealogy.gedcom.structure.GcDateStructure;
import ws.daley.genealogy.gedcom.util.Util;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

public class GcSimpleDate
{
	private static String szGregMonths[]={"JAN","FEB","MAR","APR","MAY","JUN","JUL","AUG","SEP","OCT","NOV","DEC"};
	private static String szFrenchMonths[]={"VEND","BRUM","FRIM","NIVO","PLUV","VENT","GERM","FLOR","PRAI","MESS","THER","FRUC","COMP"};
	private static String szHebrewMonths[]={"TSH","CSH","KSL","TVT","SHV","ADR","ADS","NSN","IYR","SVN","TMZ","AAV","ELL"};

	public Byte calendar;
	public Byte day;
	public Byte month;
	public int year;
	public int alternateyear;
	public Byte hours;
	public Byte minutes;
	public Byte seconds;

	public GcSimpleDate(){this.year=0;}
/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

	public Boolean Import(GedcomFile file)
	{
		if (!InterpretDate(file))
			return Boolean.FALSE;
		file.NextLine();
		// TODO: Check for time
		return Boolean.TRUE;
	}
	
	public Boolean InterpretDate(GedcomFile file)
	{
		char[] szText = file.GetText().toCharArray();
		int x = 0;
		int tmp = 0;
		int i;
		this.day = 0;
		this.month = 0;
		this.year = 0;
		this.alternateyear = -1;
		while (szText[x]==' ')
			x++;
		// Check calendar
		if (Util.strncmp(szText,"@#DGREGORIAN@",13) == 0)
		{
			x += 13;
			this.calendar = GcDateStructure.CALENDAR_GREGORIAN;
		}
		else if (Util.strncmp(szText,"@#DJULIAN@",10) == 0)
		{
			x += 10;
			this.calendar = GcDateStructure.CALENDAR_JULIAN;
		}
		else if (Util.strncmp(szText,"@#DHEBREW@",10) == 0)
		{
			x += 10;
			this.calendar = GcDateStructure.CALENDAR_HEBREW;
		}
		else if (Util.strncmp(szText,"@#DFRENCH R@",12) == 0)
		{
			x += 12;
			this.calendar = GcDateStructure.CALENDAR_FRENCH;
		}
		else if (Util.strncmp(szText,"@#DUNKNOWN@",11) == 0)
		{
			x += 11;
			this.calendar = GcDateStructure.CALENDAR_UNKNOWN;
		}
		else if (file.convData.AssumeGregorian)
			this.calendar = GcDateStructure.CALENDAR_GREGORIAN;
		else
			this.calendar = GcDateStructure.CALENDAR_UNKNOWN;
		while (szText[x]==' ')
			x++;
		// Look for day (or year)
		while (Util.isdigit(szText[x]))
		{
			tmp *= 10;
			tmp += szText[x] - 0x30;
			x++;
		}
		// Check if read number is year
		if (szText[x]=='/')
		{
			this.year = tmp;
			// Look for alternate year
			if (szText[x]=='/')
			{
				x++;
				this.alternateyear = 0;
				while (Util.isdigit(szText[x]))
				{
					this.alternateyear *= 10;
					this.alternateyear += szText[x] - 0x30;
					x++;
				}
				if (this.alternateyear>99)
					this.alternateyear %= 100;
			}
			while (szText[x]==' ')
				x++;
			file.AddIndex(x);
			// Is year before Christ?
			if (Util.strncmp(file.GetText(),"BC",2) != 0)
			{
				file.AddIndex(2);
				this.year = -this.year;
			}
			if (Util.strncmp(file.GetText(),"B.C.",4) != 0)
			{
				file.AddIndex(4);
				this.year = -this.year;
			}
			return Boolean.TRUE;
		}
		while (szText[x]==' ')
			x++;
		if (szText[x]=='B')
		{
			this.year = tmp;
			while (szText[x]==' ')
				x++;
			file.AddIndex(x);
			// Is year before Christ?
			if (Util.strncmp(file.GetText(),"BC",2) != 0)
			{
				file.AddIndex(2);
				this.year = -this.year;
			}
			if (Util.strncmp(file.GetText(),"B.C.",4) != 0)
			{
				file.AddIndex(4);
				this.year = -this.year;
			}
			return Boolean.TRUE;
		}
		if (tmp>31 || szText[x] != 0)
		{
			this.year = tmp;
			file.AddIndex(x);
			return Boolean.TRUE;
		}
		this.day = new Byte((byte)tmp);
		// Look for month
		if (this.calendar==GcDateStructure.CALENDAR_FRENCH)
		{
			for (i=1;i<=13;i++)
			{
				if (Util.strnicmp(szFrenchMonths[i-1],(new String(szText)).substring(x,4), 4) != 0)
					break;
			}
			x += 4;
			if (i>13)
			{
				this.year = tmp;
				file.AddIndex(x);
				return Boolean.TRUE;
			}
		}
		else if (this.calendar==GcDateStructure.CALENDAR_HEBREW)
		{
			for (i=1;i<=13;i++)
			{
				if (Util.strnicmp(szHebrewMonths[i-1],(new String(szText)).substring(x,3), 3) != 0)
					break;
			}
			x += 3;
			if (i>13)
			{
				this.year = tmp;
				file.AddIndex(x);
				return Boolean.TRUE;
			}
		}
		else
		{
			for (i=1;i<=12;i++)
			{
				if (Util.strnicmp(szGregMonths[i-1],(new String(szText)).substring(x,3), 3) != 0)
					break;
			}
			x += 3;
			if (i>12)
			{
				this.year = tmp;
				file.AddIndex(x);
				return Boolean.TRUE;
			}
		}
		this.month = new Byte((byte)i);
		if (szText[x]==' ') x++;
		// Look for year
		while (Util.isdigit(szText[x]))
		{
			this.year *= 10;
			this.year += szText[x] - 0x30;
			x++;
		}
		// Look for alternate year
		if (szText[x]=='/')
		{
			x++;
			this.alternateyear = 0;
			while (Util.isdigit(szText[x]))
			{
				this.alternateyear *= 10;
				this.alternateyear += szText[x] - 0x30;
				x++;
			}
			if (this.alternateyear>99)
				this.alternateyear %= 100;
		}
		while (szText[x]==' ')
			x++;
		file.AddIndex(x);
		// Is year before Christ?
		if (Util.strncmp(file.GetText(),"BC",2) != 0)
		{
			file.AddIndex(2);
			this.year = -this.year;
		}
		if (Util.strncmp(file.GetText(),"B.C.",4) != 0)
		{
			file.AddIndex(4);
			this.year = -this.year;
		}
		return Boolean.TRUE;
	}
};
