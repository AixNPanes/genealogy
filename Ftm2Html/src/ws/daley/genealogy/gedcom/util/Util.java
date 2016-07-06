package ws.daley.genealogy.gedcom.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Locale;

/****************************************************************
 *                                                              *
 *  Copyright (C) 2003,2005 Christoffer Owe                     *
 *  For conditions of distribution and use, see License.txt     *
 *                                                              *
 ****************************************************************/

//#include <windows.h>
//#include <crtdbg.h>
public class Util
{
	public static final int XREFSIZE	= 20;
	
	public static boolean isdigit(char c)
	{
		return c >= '0' && c <= '9';
	}
	
	public static int strnicmp(char[] s, char[] t, int len)
	{
		return strnicmp(new String(s), new String(t), len);
	}
	
	public static int strnicmp(String s, char[] t, int len)
	{
		return strnicmp(s, new String(t), len);
	}
	
	public static int strnicmp(char[] s, String t, int len)
	{
		return strnicmp(new String(s), t, len);
	}
	
	public static int strnicmp(String s, String t, int len)
	{
		if (s == t)
			return 0;
		if (s == null)
			return 1;
		if (t == null)
			return -1;
		return stricmp(s.substring(0, len), t.substring(0, len));
	}
	
	public static int stricmp(String s, String t)
	{
		if (s == t)
			return 0;
		if (s == null)
			return 1;
		if (t == null)
			return -1;
		if (s.equals(t))
			return 0;
		String si = s.toLowerCase();
		String ti = t.toLowerCase();
		int sl = si.length();
		int tl = ti.length();
		int l = sl <= tl?sl:tl;
		for(int i = 0; i < l; i++)
		{
			if (si.charAt(i) < ti.charAt(i))
				return -1;
			if (si.charAt(i) > ti.charAt(i))
				return 1;
		}
		if (sl == tl)
			return 0;
		if (sl < tl)
			return -1;
		return 1;
	}
	
	public static int stricmp(char[] s, char[] t)
	{
		return stricmp(new String(s), new String(t));
	}
	
	public static int stricmp(String s, char[] t)
	{
		return stricmp(s, new String(t));
	}
	
	public static int stricmp(char[] s, String t)
	{
		return stricmp(new String(s), t);
	}
	
	public static int strncmp(char[] s, String t, int len)
	{
		return strncmp(new String(s), t, len);
	}
	
	public static int strncmp(String s, char[] t, int len)
	{
		return strncmp(s, new String(t), len);
	}
	
	public static int strncmp(char[] s, char[] t, int len)
	{
		return strncmp(new String(s), new String(t), len);
	}
	
	public static int strncmp(String s, String t, int len)
	{
		if (s == t)
			return 0;
		if (s == null)
			return 1;
		if (t == null)
			return -1;
		int l = s.length()<len?s.length():len;
		l = t.length()<l?t.length():l;
		int r = strcmp(s.substring(0, l), t.substring(0, l));
		if (r != 0)
			return r;
		if (s.length() == t.length())
			return 0;
		if (s.length() < t.length())
			return -1;
		return 1;
	}
	
	public static int strcmp(char[] s, String t)
	{
		return strcmp(new String(s), t);
	}
	
	public static int strcmp(String s, char[] t)
	{
		return strcmp(s, new String(t));
	}
	
	public static int strcmp(char[] s, char[] t)
	{
		return strcmp(new String(s), new String(t));
	}
	
	public static int strcmp(String s, String t)
	{
		if (s == t)
			return 0;
		if (s == null)
			return 1;
		if (t == null)
			return -1;
		if (s.equals(t))
			return 0;
		int sl = s.length();
		int tl = t.length();
		int l = sl <= tl?sl:tl;
		for(int i = 0; i < l; i++)
		{
			if (s.charAt(i) < t.charAt(i))
				return -1;
			if (s.charAt(i) > t.charAt(i))
				return 1;
		}
		if (sl == tl)
			return 0;
		if (sl < tl)
			return -1;
		return 1;
	}
	
	public static int strlen(String s)
	{
		return s.length();
	}
	
	public static int strlen(char[] s)
	{
		return strlen(new String(s));
	}
	
	public static long WriteFile(OutputStream hFile, String buffer, @SuppressWarnings("unused") int len, @SuppressWarnings("unused") Class<?> clazz)
	{
		WriteFile(hFile, buffer);
		return buffer.length();
	}
	
	public static void WriteFile(OutputStream hFile, String buffer)
	{
		try {
			hFile.write(buffer.getBytes());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static long SendMessage(@SuppressWarnings("unused") Class<?> hWnd, @SuppressWarnings("unused") long Msg, 
			@SuppressWarnings("unused") long wParam, @SuppressWarnings("unused") long lParam)
	{
		return 0;
	}
	
	public static String sprintf(String fmt, String s)
	{
		StringBuffer sb = new StringBuffer();
		Formatter fmtr = new Formatter(sb, Locale.US);
		fmtr.format(fmt, s);
		return sb.toString();
	}
	
	public static String sprintf(String fmt, long l)
	{
		StringBuffer sb = new StringBuffer();
		Formatter fmtr = new Formatter(sb, Locale.US);
		fmtr.format(fmt, l);
		return sb.toString();
	}
	
	public static String sprintf(String fmt, int i)
	{
		StringBuffer sb = new StringBuffer();
		Formatter fmtr = new Formatter(sb, Locale.US);
		fmtr.format(fmt, i);
		return sb.toString();
	}
	
	public static String sprintf(String fmt, int i, Byte b1, Byte b2)
	{
		StringBuffer sb = new StringBuffer();
		Formatter fmtr = new Formatter(sb, Locale.US);
		fmtr.format(fmt, i, b1, b2);
		return sb.toString();
	}
	
	public static String sprintf(String fmt, int i1, int i2, Byte b1, Byte b2)
	{
		StringBuffer sb = new StringBuffer();
		Formatter fmtr = new Formatter(sb, Locale.US);
		fmtr.format(fmt, i1, i2, b1, b2);
		return sb.toString();
	}
	
	private static void assertionFailed()
	{
		throw new RuntimeException("Assertion failed.");
	}
	
	public static void _ASSERT(long l)
	{
		if (l == 0)
			assertionFailed();
	}
	
	public static void _ASSERT(boolean b)
	{
		if (!b)
			assertionFailed();
	}
	
	public static String itoa(int i, String tmp, int base)
	{
		if (tmp != null)
			throw new RuntimeException("itoa parameter error");
		if (base != 10)
			throw new RuntimeException("itoa parameter error");
		return (new Integer(i)).toString();
	}
	
	public static String itoa(long l, String tmp, int base)
	{
		if (tmp != null)
			throw new RuntimeException("itoa parameter error");
		if (base != 10)
			throw new RuntimeException("itoa parameter error");
		return (new Long(l)).toString();
	}
	
	public static HashMap<String, ?> buildHashMap(@SuppressWarnings("unused") Class<?> clazz, String[][] _objects) {
		if (_objects.length > 0 && _objects[0].length == 2) {
			HashMap<String, String> map = new HashMap<String, String>();
			for(String[] object:_objects) {
				map.put(object[0], object[1]);
			}
			return map;
		}
		throw new RuntimeException("invalid parameters");
	}
	
	public static HashMap<String, ?> buildHashMap(@SuppressWarnings("unused") Class<?> clazz, Object[][] _objects) {
		if (_objects.length > 0 && _objects[0].length == 2) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			for(Object[] object:_objects) {
				if (!object[0].getClass().isAssignableFrom(String.class))
					throw new RuntimeException("invalid definition.");
//				if (!object[1].getClass().isAssignableFrom(clazz))
//					throw new RuntimeException("invalid definition."); 
				String key = (String)object[0];
				map.put(key, object[1]);
			}
			return map;
		}
		throw new RuntimeException("invalid parameters");
	}
}
