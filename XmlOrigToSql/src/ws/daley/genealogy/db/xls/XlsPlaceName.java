package ws.daley.genealogy.db.xls;

import java.util.TreeMap;

import ws.daley.genealogy.util.Util;

public class XlsPlaceName
{
	private static TreeMap<String, XlsPlaceName> nameMap = new TreeMap<String, XlsPlaceName>();
	public static TreeMap<String, XlsPlaceName> getNameMap() {return nameMap;}
	public static void setNameMap(TreeMap<String, XlsPlaceName> newUrlMap) {nameMap = newUrlMap;}
	public static void addName(String newName, XlsPlaceName newUrl)
	{
		if (nameMap.get(newName) != null)
			throw new RuntimeException("nameMap already has key "+newName);
		nameMap.put(newName, newUrl);
	}

	private String name;
    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}

    private XlsPlace place;
    public XlsPlace getPlace() {return this.place;}
    public void setPlace(XlsPlace place) {this.place = place;}
    
    public XlsPlaceName() {}
    
    public XlsPlaceName(XlsPlace place, String alias)
    {
    	this.setPlace(place);
    	this.setName(alias);
    }
    
    public XlsPlaceName(XlsPlace place)
    {
    	this.setPlace(place);
    	String newName = place.getCountry();
    	if (place.getState() != null && !"".equals(place.getState()))
    	{
    		newName += ", " + place.getState();
        	if (place.getCounty() != null && !"".equals(place.getCounty()))
        	{
        		newName += ", " + place.getCounty();
            	if (place.getCity() != null && !"".equals(place.getCity()))
            	{
            		newName += ", " + place.getCity();
                	if (place.getLocale1() != null && !"".equals(place.getLocale1()))
                	{
                		newName += ", " + place.getLocale1();
                    	if (place.getLocale2() != null && !"".equals(place.getLocale2()))
                    	{
                    		newName += ", " + place.getLocale2();
                        	if (place.getLocale3() != null && !"".equals(place.getLocale3()))
                        	{
                        		newName += ", " + place.getLocale3();
                        	}
                    	}
                	}
            	}
        	}
    	}
    	this.setName(newName);
    }
	
	public static void addNameList(String[][][] elements)
	{
		for(String[][] element:elements)
		{
			if (element.length != 2 || element[0].length < 1)
				throw new RuntimeException("Parameter error in place list");
			String name = Util.join(element[0], ", ");
			XlsPlace xlsPlace;
			XlsPlaceName placeName = nameMap.get(name);
			if (placeName == null)
			{
				xlsPlace = new XlsPlace(element[0]);
				XlsPlace.addPlace(xlsPlace.getId(), xlsPlace);
			}
			else
				xlsPlace = placeName.getPlace();
			if (element[1] != null)
				for(String alias: element[1])
				{
					if (nameMap.get(alias) == null)
						XlsPlaceName.addName(alias, new XlsPlaceName(xlsPlace, alias));
					else if (!"AE, US".equalsIgnoreCase(alias) && !"AE, USA".equalsIgnoreCase(alias) && !"AE, United States".equalsIgnoreCase(alias))
						throw new RuntimeException("place name already exists for key "+alias);
				}
		}
	}
}
