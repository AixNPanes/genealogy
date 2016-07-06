package ws.daley.genealogy.gedcom;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ws.daley.genealogy.gedcom.gedcomrecords.GedcomRecord;
import ws.daley.genealogy.gedcom.records.ContactRec;
import ws.daley.genealogy.gedcom.records.IndividualRec;
import ws.daley.genealogy.gedcom.structures.HeaderRec;

public class GEDCOM2XML
{
	public static ArrayList<ContactRec> contacts = new ArrayList<ContactRec>();
	public static int contactIdNo = 0;

	public static int getLevelInt(Matcher m) {return Integer.parseInt(m.group("level"));}
	public static ArrayList<ArrayList<Matcher>> getRecordGroups(ArrayList<Matcher> lines)
	{
		ArrayList<ArrayList<Matcher>> recordGroups = new ArrayList<ArrayList<Matcher>>();
		ArrayList<Matcher> recordGroup = new ArrayList<Matcher>();
		int level = getLevelInt(lines.get(0));
		recordGroup.add(lines.get(0));
		for(int i = 1; i < lines.size(); i++)
		{
			Matcher line = lines.get(i);
			int lev = getLevelInt(line);
			if (lev == level)
			{
				recordGroups.add(recordGroup);
				recordGroup = new ArrayList<Matcher>();
			}
			recordGroup.add(line);
		}
		return recordGroups;
	}

	public ArrayList<GedcomRecord> getRecords(ArrayList<ArrayList<Matcher>> lineGroups) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		ArrayList<GedcomRecord> records = new ArrayList<GedcomRecord>();
		for(ArrayList<Matcher> lineGroup: lineGroups)
			records.add(GedcomRecord.getRecord(lineGroup, 0));
		return records;
	}

	private static final String LEVEL = "(?<level>\\d)";
	private static final String XREF = "(?:\\s+(?<xref>@\\w+@))?";
	private static final String TAG = "(?:\\s+(?<tag>\\w+))";
	private static final String LINE_VALUE = "(?:\\s+(?<linevalue>.*))?";
	private static final Pattern p = Pattern.compile("^" + LEVEL + XREF + TAG + LINE_VALUE + "$");
	@SuppressWarnings("unused")
	private HashMap<Integer, String> levels = new HashMap<Integer, String>();

	private BufferedReader bufferedReader;
	private ArrayList<Matcher> lines = new ArrayList<Matcher>();

	@SuppressWarnings("unused")
	private String xmlTag(String level, String tag) {return getIndent(Integer.parseInt(level) + 1) + "<" + tag;}

	private HeaderRec headerRec;
	private ArrayList<IndividualRec> individualRec = new ArrayList<IndividualRec>();

	public String getIndent(Integer level)
	{
		return new String(new char[level]).replace("\0", " ");
	}
	public GEDCOM2XML(InputStream inputStream) throws IOException, SecurityException, IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException
	{
		System.out.println("<?xml version=\"1.0\"?>");
		System.out.println("<!DOCTYPE GEDCOM SYSTEM \". . .\">");
		System.out.println("<GEDCOM>"); 
		this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String line = null;
		int lineNo = 0;
		while((line = this.bufferedReader.readLine()) != null)
		{
			lineNo++;
			Matcher m = p.matcher(line);
			if (!m.matches())
			{
				throw new RuntimeException("Error line(" + lineNo +"='" + line + "'");
			}
			this.lines.add(m);
		}
		ArrayList<GedcomRecord> recordGroups = getRecords(getRecordGroups(this.lines));
		for(GedcomRecord record: recordGroups)
		{
			switch(record.tag)
			{
				case "HEAD": this.headerRec = new HeaderRec(record); break;
//				case "INDI": this.individualRec.add(new IndividualRec(record)); break;
			}
		}
		System.out.println(this.headerRec.toString());
		for(IndividualRec indiRec:this.individualRec)
			System.out.println(indiRec.toString());
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, ClassNotFoundException, InstantiationException
	{
		FileInputStream fileInputStream = new FileInputStream("/Users/tdaley/Documents/workspace/Genealogy/Genealogy/Daley-FTM2014_2016-03-19.ged");
		GEDCOM2XML gedcom2XML = new GEDCOM2XML(fileInputStream);
	}
}
