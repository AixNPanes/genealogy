package ws.daley.genealogy.util;

import java.io.File;
import java.io.FileInputStream;

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
}
