package ws.daley.genealogy.gedcom.gedcomrecords;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class GedcomRecord
{
	private static ClassLoader classLoader = ClassLoader.getSystemClassLoader();
	private static String packageName = GedcomRecord.class.getPackage().getName();
	public String level;
	public Integer levelInt;
	public String tag;
	public String lineValue;
	public String link;
	public int numberOfLines;
	public ArrayList<GedcomRecord> records = new ArrayList<GedcomRecord>();
	public Matcher line;

	protected GedcomRecord(ArrayList<Matcher> lines, int start) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		Matcher m = lines.get(start);
		this.line = m;
		this.level = m.group("level");
		this.levelInt = Integer.parseInt(this.level);
		this.tag = m.group("tag");
		this.lineValue = m.group("linevalue");
		if (this.tag == null)
			this.tag = m.group("tag2");
		ArrayList<Matcher> subRecords = new ArrayList<Matcher>();
		String nextLevel = String.format("%d", this.levelInt + 1);
		for(int i = start + 1; i < lines.size(); i++)
		{
			Matcher m1 = lines.get(i);
			if (nextLevel.equals(m1.group("level")))
			{
				if (subRecords.size() > 0)
				{
					this.records.add(getRecord(subRecords, 0));
					subRecords = new ArrayList<Matcher>();
				}
			}
			subRecords.add(m1);
		}
		if (subRecords.size() > 0)
			this.records.add(getRecord(subRecords, 0));
	}
	
	public static GedcomRecord getRecord(ArrayList<Matcher> lines, int start) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		Matcher m = lines.get(start);
		Class<?> clazz = classLoader.loadClass(packageName + "." + m.group("tag"));
		Constructor<?> constructor = clazz.getConstructor(ArrayList.class, Integer.class);
		return (GedcomRecord)constructor.newInstance(lines, start);
	}
}