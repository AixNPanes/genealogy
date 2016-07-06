package ws.daley.genealogy.db.places;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import ws.daley.genealogy.XmlOrigToSQL;
import ws.daley.genealogy.db.places.abstrct.PlaceStructure;
import ws.daley.genealogy.util.Util;

public class Place
{
	private static TreeMap<String, String[]> nameMap = new TreeMap<String, String[]>();

	private static TreeMap<String, Place> placeMap = new TreeMap<String, Place>();
    public static TreeMap<String, Place> getPlaceMap() {return placeMap;}
    public static void setPlaceMap(TreeMap<String, Place> placeMap) {Place.placeMap = placeMap;}
    public static void addPlaceMap(Place place)
    {
    	Place thisPlace = place;
    	while(thisPlace != null)
    	{
    		String name = Util.join(thisPlace.getElement(), ", ");
    		Place gotten = placeMap.get(name);
    		if (gotten == null)
    			placeMap.put(name, thisPlace);
    	}
    }
    
    private static String[] titles;
    public static String[] getTitles() {return titles;}
    public static void setTitles(String[] titles) {Place.titles = titles;}

	private String[] element;
    public String[] getElement() {return this.element;}
    public void setElement(String[] element) {this.element = element;}

	private String[] parentElement;
    public String[] getParentElement() {return this.parentElement;}
    public void setParentElement(String[] parentElement) {this.parentElement = parentElement;}
	
	private boolean persisted = false;
    public boolean isPersisted() {return this.persisted;}
    public void setPersisted(boolean persisted) {this.persisted = persisted;}

	private String name;
    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}
    
    private Place parent;
    public Place getParent() {return this.parent;}
    public void setParent(Place parent) {this.parent = parent;}
    
    private List<URL> urls = new ArrayList<URL>();
    public List<URL> getUrls() {return this.urls;}
    public void setUrls(List<URL> urls) {this.urls = urls;}

	private String parentName;
    public String getParentName() {return this.parentName;}
    public void setParentName(String parentName) {this.parentName = parentName;}
	
	public Place() {}
	
	private Place(String[] nameList) throws IOException
	{
		this.element = trim(nameList);
		this.name = join(this.element, ", ");
		PlaceStructure placeStructure = null;
		if (nameList != null && nameList.length > 0 && nameList[0].startsWith("Michigan"))
		{
			int x = 0;
		}
		switch(this.element.length)
		{
			case 1:
				placeStructure = PlaceStructure.getPlace(this.element);
				if (placeStructure == null)
					placeStructure = new Country(nameList, "");
				break;
			case 2:
				placeStructure = PlaceStructure.getPlace(this.element);
				if (placeStructure == null)
					placeStructure = new State(nameList, "");
				break;
			case 3:
				placeStructure = PlaceStructure.getPlace(this.element);
				if (placeStructure == null)
					placeStructure = new County(nameList, "");
				break;
			case 4:
				placeStructure = PlaceStructure.getPlace(this.element);
				if (placeStructure == null)
					placeStructure = new City(nameList, "");
				break;
			case 5:
			case 6:
			case 7:
				placeStructure = PlaceStructure.getPlace(this.element);
				if (nameList != null && nameList.length > 0 && nameList[0].startsWith("161"))
				{
					int x = 0;
				}
				if (placeStructure == null)
					placeStructure = new Locale(nameList, "");
				break;
			default:
				break;
		}
		XmlOrigToSQL.getEntityManager().persist(placeStructure);
		this.parentElement = shorten(this.element);
		this.parentName = join(this.parentElement, ", ");
		if (this.parentName.length() > 0)
		{
			this.parent = placeMap.get(this.parentName);
			if (this.parent == null)
			{
				String[][] newParent = new String[1][];
				newParent[0] = this.parentElement;
				this.parent = Place.getPlace(newParent);
			}
		}
		nameMap.put(this.name, nameList);
		placeMap.put(this.name, this);
	}
	
	public static Place getPlace(String[][] nameArray) throws IOException
	{
		if (nameArray == null || nameArray.length != 2 || nameArray[0] == null || nameArray[0].length == 0)
			return null;
		Place place = placeMap.get(nameArray[0].length==1?nameArray[0][0]:join(nameArray[0], ", "));
		if (place != null)
			return place;
		place =  new Place(nameArray[0]);
		for(int i = 0; i < nameArray[1].length; i++)
			if (nameArray[1][i] != null)
				place.urls.add(new URL(titles[i], nameArray[1][i]));
		return place;
	}
	
	public static Place getPlace(String[] nameArray) throws IOException
	{
		Place place = placeMap.get(join(nameArray, ", "));
		if (place != null)
			return place;
		place =  new Place(nameArray);
		return place;
	}
	
	private static String join(String[] elements, String sep)
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
	
	private String[] trim(String[] elements)
	{
		ArrayList<String> list = new ArrayList<String>();
		for(String e:elements)
			if (e != null && e.length() > 0)
				list.add(e.trim());
		String[] ret = new String[list.size()];
		ret = list.toArray(ret);
		return ret;
	}
	
	private String[] shorten(String[] elements)
	{
		String[] ret = new String[elements.length-1];
		for(int i = 0; i < ret.length; i++)
			ret[i] = elements[i+1];
		return ret;
	}
}
