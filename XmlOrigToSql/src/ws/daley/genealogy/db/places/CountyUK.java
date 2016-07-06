package ws.daley.genealogy.db.places;

import java.util.ArrayList;

import ws.daley.genealogy.db.places.abstrct.PlaceStructure;
import ws.daley.genealogy.util.Util;

public class CountyUK
{
	public static ArrayList<PlaceStructure> uk()
	{
		ArrayList<PlaceStructure> channelIslands = Util.arrayList(new PlaceStructure[]{
//				new County(new String[]{"UNITED KINGDOM", "Channel Islands"}, ""),
			});
		
		ArrayList<PlaceStructure> england = Util.arrayList(new PlaceStructure[]{
				new County(new String[]{"Bedfordshire", "England", "United Kingdom"}, ""),
				new County(new String[]{"Berkshire", "England", "United Kingdom"}, ""),
				new County(new String[]{"Buckinghamshire", "England", "United Kingdom"}, ""),
				new County(new String[]{"Cambridgeshire", "England", "United Kingdom"}, ""),
				new County(new String[]{"Cheshire", "England", "United Kingdom"}, ""),
				new County(new String[]{"Cornwall", "England", "United Kingdom"}, ""),
				new County(new String[]{"Cumberland", "England", "United Kingdom"}, ""),
				new County(new String[]{"Derbyshire", "England", "United Kingdom"}, ""),
				new County(new String[]{"Devon", "England", "United Kingdom"}, ""),
				new County(new String[]{"Dorset", "England", "United Kingdom"}, ""),
				new County(new String[]{"Durham", "England", "United Kingdom"}, ""),
				new County(new String[]{"Essex", "England", "United Kingdom"}, ""),
				new County(new String[]{"Gloucestershire", "England", "United Kingdom"}, ""),
				new County(new String[]{"Hampshire", "England", "United Kingdom"}, ""),
				new County(new String[]{"Herefordshire", "England", "United Kingdom"}, ""),
				new County(new String[]{"Hertfordshire", "England", "United Kingdom"}, ""),
				new County(new String[]{"Huntingdonshire", "England", "United Kingdom"}, ""),
				new County(new String[]{"Kent", "England", "United Kingdom"}, ""),
				new County(new String[]{"Lancashire", "England", "United Kingdom"}, ""),
				new County(new String[]{"Leicestershire", "England", "United Kingdom"}, ""),
				new County(new String[]{"Lincolnshire", "England", "United Kingdom"}, ""),
				new County(new String[]{"Middlesex", "England", "United Kingdom"}, ""),
				new County(new String[]{"Norfolk", "England", "United Kingdom"}, ""),
				new County(new String[]{"Northamptonshire", "England", "United Kingdom"}, ""),
				new County(new String[]{"Northumberland", "England", "United Kingdom"}, ""),
				new County(new String[]{"Nottinghamshire", "England", "United Kingdom"}, ""),
				new County(new String[]{"Oxfordshire", "England", "United Kingdom"}, ""),
				new County(new String[]{"Rutland", "England", "United Kingdom"}, ""),
				new County(new String[]{"Shropshire", "England", "United Kingdom"}, ""),
				new County(new String[]{"Somerset", "England", "United Kingdom"}, ""),
				new County(new String[]{"Staffordshire", "England", "United Kingdom"}, ""),
				new County(new String[]{"Suffolk", "England", "United Kingdom"}, ""),
				new County(new String[]{"Surrey", "England", "United Kingdom"}, ""),
				new County(new String[]{"Sussex", "England", "United Kingdom"}, ""),
				new County(new String[]{"Warwickshire", "England", "United Kingdom"}, ""),
				new County(new String[]{"Westmorland", "England", "United Kingdom"}, ""),
				new County(new String[]{"Wiltshire", "England", "United Kingdom"}, ""),
				new County(new String[]{"Worcestershire", "England", "United Kingdom"}, ""),
				new County(new String[]{"Yorkshire", "England", "United Kingdom"}, ""),
			});
		
		ArrayList<PlaceStructure> isleOfMan = Util.arrayList(new PlaceStructure[]{
//				new County(new String[]{"Isle of Man", "UNITED KINGDOM"}, ""),
			});
		
		ArrayList<PlaceStructure> ireland = Util.arrayList(new PlaceStructure[]{
				new County(new String[]{"Carlow", "Ireland", "United Kingdom"}, ""),
				new County(new String[]{"Cavan", "Ireland", "United Kingdom"}, ""),
				new County(new String[]{"Clare", "Ireland", "United Kingdom"}, ""),
				new County(new String[]{"Cork", "Ireland", "United Kingdom"}, ""),
				new County(new String[]{"Donegal", "Ireland", "United Kingdom"}, ""),
				new County(new String[]{"Dublin", "Ireland", "United Kingdom"}, ""),
				new County(new String[]{"Galway", "Ireland", "United Kingdom"}, ""),
				new County(new String[]{"Kerry", "Ireland", "United Kingdom"}, ""),
				new County(new String[]{"Kildare", "Ireland", "United Kingdom"}, ""),
				new County(new String[]{"Kilkenny", "Ireland", "United Kingdom"}, ""),
				new County(new String[]{"Laois", "Ireland", "United Kingdom"}, ""),
				new County(new String[]{"Leitrim", "Ireland", "United Kingdom"}, ""),
				new County(new String[]{"Limerick", "Ireland", "United Kingdom"}, ""),
				new County(new String[]{"Longford", "Ireland", "United Kingdom"}, ""),
				new County(new String[]{"Louth", "Ireland", "United Kingdom"}, ""),
				new County(new String[]{"Mayo", "Ireland", "United Kingdom"}, ""),
				new County(new String[]{"Meath", "Ireland", "United Kingdom"}, ""),
				new County(new String[]{"Monaghan", "Ireland", "United Kingdom"}, ""),
				new County(new String[]{"Offaly", "Ireland", "United Kingdom"}, ""),
				new County(new String[]{"Roscommon", "Ireland", "United Kingdom"}, ""),
				new County(new String[]{"Sligo", "Ireland", "United Kingdom"}, ""),
				new County(new String[]{"Tipperary", "Ireland", "United Kingdom"}, ""),
				new County(new String[]{"Waterford", "Ireland", "United Kingdom"}, ""),
				new County(new String[]{"Westmeath", "Ireland", "United Kingdom"}, ""),
				new County(new String[]{"Wexford", "Ireland", "United Kingdom"}, ""),
				new County(new String[]{"Wicklow", "Ireland", "United Kingdom"}, ""),
			});
		
		ArrayList<PlaceStructure> northernIreland = Util.arrayList(new PlaceStructure[]{
				new County(new String[]{"Fermanagh", "Northern Ireland", "United Kingdom"}, ""),
				new County(new String[]{"Tyrone", "Northern Ireland", "United Kingdom"}, ""),
				new County(new String[]{"Londonderry", "Northern Ireland", "United Kingdom"}, ""),
				new County(new String[]{"Antrim", "Northern Ireland", "United Kingdom"}, ""),
				new County(new String[]{"Down", "Northern Ireland", "United Kingdom"}, ""),
				new County(new String[]{"Armagh", "Northern Ireland", "United Kingdom"}, ""),
			});
		
		ArrayList<PlaceStructure> scotland = Util.arrayList(new PlaceStructure[]{
				new County(new String[]{"Aberdeenshire", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"Angus/Forfarshire", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"Argyllshire", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"Ayrshire", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"Banffshire", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"Berwickshire", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"Buteshire", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"Cromartyshire", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"Caithness", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"Clackmannanshire", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"Dumfriesshire", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"Dunbartonshire/Dumbartonshire", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"East Lothian/Haddingtonshire", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"Fife", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"Inverness-shire", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"Kincardineshire", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"Kinross-shire", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"Kirkcudbrightshire", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"Lanarkshire", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"Midlothian/Edinburghshire", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"Morayshire", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"Nairnshire", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"Orkney", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"Peeblesshire", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"Perthshire", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"Renfrewshire", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"Ross-shire", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"Roxburghshire", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"Selkirkshire", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"Shetland", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"Stirlingshire", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"Sutherland", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"West Lothian/Linlithgowshire", "Scotland", "United Kingdom"}, ""),
				new County(new String[]{"Wigtownshire", "Scotland", "United Kingdom"}, ""),
			});
		
		ArrayList<PlaceStructure> wales = Util.arrayList(new PlaceStructure[]{
				new County(new String[]{"Anglesey", "Wales", "United Kingdom"}, ""),
				new County(new String[]{"Brecknockshire", "Wales", "United Kingdom"}, ""),
				new County(new String[]{"Caernarfonshire", "Wales", "United Kingdom"}, ""),
				new County(new String[]{"Carmarthenshire", "Wales", "United Kingdom"}, ""),
				new County(new String[]{"Cardiganshire", "Wales", "United Kingdom"}, ""),
				new County(new String[]{"Denbighshire", "Wales", "United Kingdom"}, ""),
				new County(new String[]{"Flintshire", "Wales", "United Kingdom"}, ""),
				new County(new String[]{"Glamorgan", "Wales", "United Kingdom"}, ""),
				new County(new String[]{"Merioneth", "Wales", "United Kingdom"}, ""),
				new County(new String[]{"Monmouthshire", "Wales", "United Kingdom"}, ""),
				new County(new String[]{"Montgomeryshire", "Wales", "United Kingdom"}, ""),
				new County(new String[]{"Pembrokeshire", "Wales", "United Kingdom"}, ""),
				new County(new String[]{"Radnorshire", "Wales", "United Kingdom"}, "")
			});
		
		ArrayList<PlaceStructure> list = new ArrayList<PlaceStructure>();
		list = Util.arrayList(list, channelIslands);
		list = Util.arrayList(list, england);
		list = Util.arrayList(list, isleOfMan);
		list = Util.arrayList(list, ireland);
		list = Util.arrayList(list, northernIreland);
		list = Util.arrayList(list, scotland);
		list = Util.arrayList(list, wales);
		return list;
	}
}
