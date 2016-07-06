package ws.daley.genealogy.gedcom.gedcomrecords;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class ANCE extends GedcomRecord
{
	public ANCE(ArrayList<Matcher> lines, Integer start) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		super(lines, start);
	}
}
