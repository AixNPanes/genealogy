package ws.daley.genealogy.gedcom.gedcomrecords;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class BAPM extends GedcomRecord
{
	public BAPM(ArrayList<Matcher> lines, Integer start) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		super(lines, start);
	}
}
