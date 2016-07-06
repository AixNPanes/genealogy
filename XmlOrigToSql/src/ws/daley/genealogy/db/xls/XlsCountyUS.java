package ws.daley.genealogy.db.xls;

public class XlsCountyUS
{
	public static String[][][] getNames()
	{
		String[][][] elements1 = XlsCountyUSAToK.getNames();
		String[][][] elements2 = XlsCountyUSLToN.getNames();
		String[][][] elements3 = XlsCountyUSOToZ.getNames();
		String[][][] elements = new String[elements1.length+elements2.length+elements3.length][][];
		int j = 0;
		for(int i = 0; i < elements1.length; i++)
			elements[j+i] = elements1[i];
		j += elements1.length;
		for(int i = 0; i < elements2.length; i++)
			elements[j+i] = elements2[i];
		j += elements2.length;
		for(int i = 0; i < elements3.length; i++)
			elements[j+i] = elements3[i];
		return elements;
	}
}
