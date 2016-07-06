package ws.daley.genealogy.gedcom;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ReadGEDCOM
{
	public static void main(String[] args) throws IOException
	{
		File gedcomFile = new File("Daley-FTM2014_2016-03-17.ged");
		InputStream gedcomFileInputStream = new FileInputStream(gedcomFile);

		GEDCOMInputStream reader = new GEDCOMInputStream(gedcomFileInputStream);
//		StringBuffer stringBuffer = new StringBuffer();
//		String line;
//		while ( (line = reader.readLine()) != null)
//		{
//			System.out.println(line);
//			stringBuffer.append(line).append("\n");
//		}
		reader.close();
		reader = null;
//		String buffer = stringBuffer.toString();
//		stringBuffer = null;
//		int l = buffer.length();
//		int i = 0;
	}
}
