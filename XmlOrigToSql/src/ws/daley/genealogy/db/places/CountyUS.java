package ws.daley.genealogy.db.places;

import java.util.ArrayList;

import ws.daley.genealogy.db.places.abstrct.PlaceStructure;
import ws.daley.genealogy.util.Util;

public class CountyUS
{
	public static ArrayList<PlaceStructure> us()
	{
		ArrayList<PlaceStructure> list = new ArrayList<PlaceStructure>();
		list = Util.arrayList(list, CountyUSAToK.us());
		list = Util.arrayList(list, CountyUSLToN.us());
		list = Util.arrayList(list, CountyUSOToZ.us());
		return list;
	}
}
