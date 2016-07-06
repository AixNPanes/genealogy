package ws.daley.genealogy.db.xls;


public class XlsCounty
{
	public static void addPlaceLists()
	{
		String[][][] elements1 = XlsCountyUS.getNames();
		String[][][] elements2 = XlsCountyCanada.getNames();
		String[][][] elements3 = XlsCountyMexico.getNames();
		String[][][] elements4 = XlsCountyFrance.getNames();
		String[][][] elements5 = XlsCountyUK.getNames();
		String[][][] elements = new String[elements1.length+elements2.length+elements3.length+elements4.length+elements5.length][][];
		int j = 0;
		for(int i = 0; i < elements1.length; i++)
			elements[j+i] = elements1[i];
		j += elements1.length;
		for(int i = 0; i < elements2.length; i++)
			elements[j+i] = elements2[i];
		j += elements2.length;
		for(int i = 0; i < elements3.length; i++)
			elements[j+i] = elements3[i];
		j += elements3.length;
		for(int i = 0; i < elements4.length; i++)
			elements[j+i] = elements4[i];
		j += elements4.length;
		for(int i = 0; i < elements5.length; i++)
			elements[j+i] = elements5[i];
		XlsPlaceName.addNameList(elements);
	}
    
//    private static void addNameList(String[][][] elements)
//    {
//    	String[][][] els = new String[elements.length][][];
//    	for(int i = 0; i < elements.length; i++)
//    	{
//    		String[][] element = elements[i];
//    		String name = Util.join(element[0], ", ");
//    		ArrayList<String> countyNames = new ArrayList<String>();
//    		countyNames.add(element[0][0]);
//    		for(String alias:element[1])
//    			countyNames.add(alias);
//    		ArrayList<String> stateNames = XlsState.getStateMap().get(element[0][1]);
//    		String sn = element[0][1]+", "+element[0][2];
//    		if (!stateNames.contains(sn))
//    			stateNames.add(sn);
//    		ArrayList<String> countyAliases = new ArrayList<String>();
//    		for(String countyName:countyNames)
//    			for(String countryName:stateNames)
//    			{
//    				String[] entry = new String[3];
//    				entry[0] = countyName;
//    				entry[1] = stateName;
//    				entry[2] = countryName;
//    				String entryName = Util.join(entry, ", ");
//    				if (!name.equals(entryName))
//    					countyAliases.add(entryName);
//    			}
//    		els[i][0] = elements[i][0];
//    		els[i][1] = countyAliases.toArray(new String[]{});
//    	}
//		XlsPlaceName.addNameList(els);
//    }
}
