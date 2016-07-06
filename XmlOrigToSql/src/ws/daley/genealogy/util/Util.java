package ws.daley.genealogy.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ws.daley.genealogy.db.places.abstrct.PlaceStructure;
import ws.daley.genealogy.db.places.abstrct.PlaceType.TYPE;

public class Util
{
	public static String join(String[] elements, String sep)
	{
		String ret = "";
		for(String e:elements)
			if (e != null && e.length() > 0)
			{
				if (ret.length() > 0)
					ret += sep;
				ret += e;
			}
		return ret;
	}

	public static File getInFile(String filename)
	{
		File file = new File(filename);
		if (!file.exists())
			throw new RuntimeException("file "+filename+" does not exist");
		return file;
	}
	
	public static String getString(File inFile) throws Exception
	{
		long fileLen = inFile.length();
		byte[] bytes = new byte[(int)fileLen];
		FileInputStream fis = null;
        fis = new FileInputStream(inFile);
		fis.read(bytes);
		return new String(bytes, "UTF8");
	}
	
	public static String getNodeValue(Node contNode)
	{
		NamedNodeMap map = contNode.getAttributes();
		Node item = map.getNamedItem("value");
		return item==null?"":item.getNodeValue();
	}
	
	public static String addContToValue(String value, Node contNode, boolean nl)
	{
		String val = value==null?"":value;
		return val + (nl?"\n":"") + getNodeValue(contNode);
	}
	
	public static String getNodeValueWithChild(Class<?> parent, Node node)
	{
		NamedNodeMap map = node.getAttributes();
		Node item = map.getNamedItem("value");
		String value = item==null?"":item.getNodeValue();
		NodeList list = node.getChildNodes();
		for(int i = 0;i < list.getLength();i++)
		{
			Node child = list.item(i);
			if ("CONT".equals(child.getNodeName()))
				value += "\n" + child.getNodeValue();
			else if ("CONC".equals(child.getNodeName()))
				value += child.getNodeValue();
			else if ("TIME".equals(child.getNodeName()))
				value += ", " + child.getNodeValue();
			else if ("#text".equals(child.getNodeName()))
				;
			else
				throw new RuntimeException(child.getNodeName()+" not processed in "+parent.getSimpleName());
		}
		return value;
	}
	
	private static Object getAttribute(NamedNodeMap attributes, String attributeName)
	{
		Node node = attributes.getNamedItem(attributeName);
		if (node == null)
			return null;
		return node.getNodeValue();
	}
	
	public static String getStringAttribute(NamedNodeMap attributes, String attributeName)
	{
		Object o = getAttribute(attributes, attributeName);
		if (o == null)
			return null;
		return (String) o;
	}
	
	public static Integer getIntegerAttribute(NamedNodeMap attributes, String attributeName)
	{
		Object o = getAttribute(attributes, attributeName);
		if (o == null)
			return null;
		return Integer.decode((String)o);
	}
	
	public static Long getLongAttribute(NamedNodeMap attributes, String attributeName)
	{
		Object o = getAttribute(attributes, attributeName);
		if (o == null)
			return null;
		return Long.decode((String)o);
	}
	
	public static String[] trim(String[] names)
	{
		try{
		List<String> ary = new ArrayList<String>();
		for(String name:names)
			ary.add(name);
		for(int i = ary.size() - 1; i >= 0; i--)
			if (ary.get(i) == null)
				ary.remove(i);
			else
				break;
		for(int i = 0; i < ary.size(); i++)
			if (ary.get(i) != null)
			{
				for(int j = i-1; j >= 0; j--)
					ary.remove(j);
				break;
			}
		return ary.toArray(new String[]{});
		}
		catch(Throwable e)
		{
			return null;
		}
	}
	
	public static Object[] subElement(Object[] o, int start, int end)
	{
		List<Object> ary = new ArrayList<Object>();
		for(int i = start; i < end; i++)
			ary.add(o[i]);
		return ary.toArray();
	}
	
	public static String[] subElement(String[] o, int start, int end)
	{
		List<String> ary = new ArrayList<String>();
		for(int i = start; i < end; i++)
			ary.add(o[i]);
		return ary.toArray(new String[]{});
	}

	public static ArrayList<PlaceStructure> arrayList(PlaceStructure[] places)
	{
		ArrayList<PlaceStructure> list = new ArrayList<PlaceStructure> ();
		for(PlaceStructure place:places)
			list.add(place);
		return list;
	}

	public static ArrayList<PlaceStructure> arrayList(ArrayList<PlaceStructure> list1, ArrayList<PlaceStructure> list2)
	{
		ArrayList<PlaceStructure> list = new ArrayList<PlaceStructure> ();
		for(PlaceStructure item:list1)
			list.add(item);
		for(PlaceStructure item:list2)
			list.add(item);
		return list;
	}
	
	public static String[] reverse(String[] name)
	{
		String[] trimmed = trim(name);
		String[] element = new String[trimmed.length];
		for(int i = 0, j = trimmed.length-1; i < trimmed.length; i++, j--)
			element[j] = name[i];
		return element;
	}
}
