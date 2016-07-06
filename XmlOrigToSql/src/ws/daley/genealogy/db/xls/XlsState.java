package ws.daley.genealogy.db.xls;

import java.util.ArrayList;
import java.util.TreeMap;

import ws.daley.genealogy.util.Util;

public class XlsState
{
	private static TreeMap<String, ArrayList<String>> stateMap = new TreeMap<String, ArrayList<String>>();
	public static TreeMap<String, ArrayList<String>> getStateMap() {return stateMap;}
	
    public static void addPlaceLists()
    {
    	String[][][] elements = new String[][][]{
    		{{"Alabama", "United States"}, {"AL"}},
			{{"Alaska", "United States"}, {"AK"}},
			{{"American Samoa", "United States"}, {"AS"}},
			{{"Arizona", "United States"}, {"AZ"}},
			{{"Arkansas", "United States"}, {"AR"}},
			{{"California", "United States"}, {"CA"}},
			{{"Colorado", "United States"}, {"CO"}},
			{{"Connecticut", "United States"}, {"CT"}},
			{{"Delaware", "United States"}, {"DE"}},
			{{"Washington, District of Columbia", "United States"}, {"DC"}},
			{{"Federated States of Micronesia", "United States"}, {"FM"}},
			{{"Florida", "United States"}, {"FL"}},
			{{"Georgia", "United States"}, {"GA"}},
			{{"Guam", "United States"}, {"GU"}},
			{{"Hawaii", "United States"}, {"HI"}},
			{{"Idaho", "United States"}, {"ID"}},
			{{"Illinois", "United States"}, {"IL"}},
			{{"Indiana", "United States"}, {"IN"}},
			{{"Iowa", "United States"}, {"IA"}},
			{{"Kansas", "United States"}, {"KS"}},
			{{"Kentucky", "United States"}, {"KY"}},
			{{"Louisiana", "United States"}, {"LA"}},
			{{"Maine", "United States"}, {"ME"}},
			{{"Marshall Islands", "United States"}, {"MH"}},
			{{"Maryland", "United States"}, {"MD"}},
			{{"Massachusetts", "United States"}, {"MA"}},
			{{"Michigan", "United States"}, {"MI"}},
			{{"Minnesota", "United States"}, {"MN"}},
			{{"Mississippi", "United States"}, {"MS"}},
			{{"Missouri", "United States"}, {"MO"}},
			{{"Montana", "United States"}, {"MT"}},
			{{"Nebraska", "United States"}, {"NE"}},
			{{"Nevada", "United States"}, {"NV"}},
			{{"New Hampshire", "United States"}, {"NH"}},
			{{"New Jersey", "United States"}, {"NJ"}},
			{{"New Mexico", "United States"}, {"NM"}},
			{{"New York State", "United States"}, {"NY", "New York"}},
			{{"North Carolina", "United States"}, {"NC"}},
			{{"North Dakota", "United States"}, {"ND"}},
			{{"Northern Mariana Islands", "United States"}, {"MP"}},
			{{"Ohio", "United States"}, {"OH"}},
			{{"Oklahoma", "United States"}, {"OK"}},
			{{"Oregon", "United States"}, {"OR"}},
			{{"Palau", "United States"}, {"PW"}},
			{{"Pennsylvania", "United States"}, {"PA"}},
			{{"Puerto Rico", "United States"}, {"PR"}},
			{{"Rhode Island", "United States"}, {"RI"}},
			{{"South Carolina", "United States"}, {"SC"}},
			{{"South Dakota", "United States"}, {"SD"}},
			{{"Tennessee", "United States"}, {"TN"}},
			{{"Texas", "United States"}, {"TX"}},
			{{"Utah", "United States"}, {"UT"}},
			{{"Vermont", "United States"}, {"VT"}},
			{{"Virgin Islands", "United States"}, {"VI"}},
			{{"Virginia", "United States"}, {"VA"}},
			{{"Washington", "United States"}, {"WA"}},
			{{"West Virginia", "United States"}, {"WV"}},
			{{"Wisconsin", "United States"}, {"WI"}},
			{{"Wyoming", "United States"}, {"WY"}},
			{{"Armed Forces Africa", "United States"}, {"AE"}},
			{{"Armed Forces Americas", "United States"}, {"AA"}},
			{{"Armed Forces Canada", "United States"}, {"AE"}},
			{{"Armed Forces Europe", "United States"}, {"AE"}},
			{{"Armed Forces Middle East", "United States"}, {"AE"}},
			{{"Armed Forces Pacific", "United States"}, {"AP"}},
			{{"Alberta", "Canada"}, {"AB"}},
			{{"British Columbia", "Canada"}, {"BC"}},
			{{"Manitoba", "Canada"}, {"MB"}},
			{{"New Brunswick", "Canada"}, {"NB"}},
			{{"Newfoundland and Labrador", "Canada"}, {"NL"}},
			{{"Northwest Territories", "Canada"}, {"NT"}},
			{{"Nova Scotia", "Canada"}, {"NS"}},
			{{"Nunavut", "Canada"}, {"NU"}},
			{{"Ontario", "Canada"}, {"ON"}},
			{{"Prince Edward Island", "Canada"}, {"PE"}},
			{{"Quebec", "Canada"}, {"QC"}},
			{{"Saskatchewan", "Canada"}, {"SK"}},
			{{"Yukon", "Canada"}, {"YT"}},
			{{"Aguascalientes", "Mexico"}, {"AG"}},
			{{"Baja California", "Mexico"}, {"BN"}},
			{{"Baja California Sur", "Mexico"}, {"BS"}},
			{{"Campeche", "Mexico"}, {"CM"}},
			{{"Chiapas", "Mexico"}, {"CP"}},
			{{"Chihuahua", "Mexico"}, {"CH"}},
			{{"Coahuila", "Mexico"}, {"CO"}},
			{{"Colima", "Mexico"}, {"CL"}},
			{{"Distrito Federal", "Mexico"}, {"DF"}},
			{{"Durango", "Mexico"}, {"DU"}},
			{{"Guanajuato", "Mexico"}, {"GJ"}},
			{{"Guerrero", "Mexico"}, {"GR"}},
			{{"Hidalgo", "Mexico"}, {"HG"}},
			{{"Jalisco", "Mexico"}, {"JA"}},
			{{"México", "Mexico"}, {"MX"}},
			{{"Michoacán", "Mexico"}, {"MC"}},
			{{"Morelos", "Mexico"}, {"MR"}},
			{{"Nayarit", "Mexico"}, {"NA"}},
			{{"Nuevo León", "Mexico"}, {"NL"}},
			{{"Oaxaca", "Mexico"}, {"OA"}},
			{{"Puebla", "Mexico"}, {"PU"}},
			{{"Querétaro", "Mexico"}, {"AE"}},
			{{"Quintana Roo", "Mexico"}, {"QR"}},
			{{"San Luis Potosí", "Mexico"}, {"SL"}},
			{{"Sinaloa", "Mexico"}, {"SI"}},
			{{"Sonora", "Mexico"}, {"SO"}},
			{{"Tabasco", "Mexico"}, {"TB"}},
			{{"Tamaulipas", "Mexico"}, {"TM"}},
			{{"Tlaxcala", "Mexico"}, {"TL"}},
			{{"Veracruz", "Mexico"}, {"VE"}},
			{{"Yucatán", "Mexico"}, {"YU"}},
			{{"Zacatecas", "Mexico"}, {"ZA"}},
			{{"Alsace-Lorraine", "France"}, {"Alsace"}},
			{{"Aquitane", "France"}, {}},
			{{"Auvergne", "France"}, {}},
			{{"Bourgogne", "France"}, {}},
			{{"Brittany", "France"}, {}},
			{{"Centre", "France"}, {}},
			{{"Champagne-Ardenne", "France"}, {}},
			{{"Corsica", "France"}, {}},
			{{"Franche-Comté", "France"}, {}},
			{{"Île-de-France", "France"}, {}},
			{{"Languedoc-Roussillon", "France"}, {}},
			{{"Limousin", "France"}, {}},
			{{"Lorraine", "France"}, {}},
			{{"Midi-Pyrénées", "France"}, {}},
			{{"Nord-Pas-de-Calais", "France"}, {}},
			{{"Basse-Normandie", "France"}, {}},
			{{"Haute-Normandie", "France"}, {}},
			{{"Pays de la Loire", "France"}, {}},
			{{"Picardy", "France"}, {}},
			{{"Poitou-Charentes", "France"}, {}},
			{{"Provence-Alpes-Côte d''Azur", "France"}, {}},
			{{"Rhône-Alpes", "France"}, {}},
			{{"French Guiana", "France"}, {}},
			{{"Guadeloupe", "France"}, {}},
			{{"Martinique", "France"}, {}},
			{{"Réunion", "France"}, {}},
			{{"Baden-Württemberg", "Germany"}, {}},
			{{"Bayern", "Germany"}, {"Bavaria"}},
			{{"Berlin", "Germany"}, {}},
			{{"Brandenburg", "Germany"}, {}},
			{{"Bremen", "Germany"}, {}},
			{{"Hamburg", "Germany"}, {}},
			{{"Hessen", "Germany"}, {"Hesse"}},
			{{"Mecklenburg-Vorpommern", "Germany"}, {"Mecklenburg-Western Pomerania"}},
			{{"Niedersachsen", "Germany"}, {"Lower Saxony"}},
			{{"Nordrhein-Westfalen", "Germany"}, {"North Rhine-Westphalia"}},
			{{"Rheinland-Pfalz", "Germany"}, {"Rhineland-Palatinate"}},
			{{"Saarland", "Germany"}, {}},
			{{"Sachsen", "Germany"}, {"Saxony"}},
			{{"Sachsen-Anhalt", "Germany"}, {"Saxony-Anhalt"}},
			{{"Schleswig-Holstein", "Germany"}, {}},
			{{"Thuringia", "Germany"}, {"Thüringen", "Thuringen"}},
			{{"Drenthe", "Netherlands"}, {}},
			{{"Flevoland", "Netherlands"}, {}},
			{{"Friesland", "Netherlands"}, {}},
			{{"Gelderland", "Netherlands"}, {}},
			{{"Groningen", "Netherlands"}, {}},
			{{"Limburg", "Netherlands"}, {}},
			{{"North Brabant", "Netherlands"}, {}},
			{{"North Holland", "Netherlands"}, {"Noord Brabant", "Noord Holland"}},
			{{"Overijssel", "Netherlands"}, {}},
			{{"Utrecht", "Netherlands"}, {}},
			{{"Zeeland", "Netherlands"}, {"Zealand"}},
			{{"South Holland", "Netherlands"}, {"Zuid Holland", "Zuid-Holland"}},
			{{"Channel Islands", "United Kingdom"}, {"CHI"}},
			{{"England", "United Kingdom"}, {"ENG"}},
			{{"Isle of Man", "United Kingdom"}, {"IOM"}},
			{{"Ireland", "United Kingdom"}, {"IRL"}},
			{{"Northern Ireland", "United Kingdom"}, {"NIR"}},
			{{"Scotland", "United Kingdom"}, {"SCT"}},
			{{"Wales", "United Kingdom"}, {"WAL"}}};
		addNameList(elements);
    }
    
    private static void addNameList(String[][][] elements)
    {
    	String[][][] els = new String[elements.length][][];
    	for(int i = 0; i < elements.length; i++)
    	{
    		String[][] element = elements[i];
    		ArrayList<String> list = new ArrayList<String>();
    		for(String alias:element[1])
    			list.add(alias);
    		stateMap.put(element[0][0], list);
    		String name = Util.join(element[0], ", ");
    		ArrayList<String> stateNames = new ArrayList<String>();
    		stateNames.add(element[0][0]);
    		for(String alias:element[1])
    			stateNames.add(alias);
    		TreeMap<String, ArrayList<String>> countryMap = XlsCountry.getCountryMap();
    		ArrayList<String> countryNames = countryMap.get(element[0][1]);
    		if (!countryNames.contains(element[0][1]))
    			countryNames.add(element[0][1]);
    		ArrayList<String> stateAliases = new ArrayList<String>();
    		for(String stateName:stateNames)
    			for(String countryName:countryNames)
    			{
    				String[] entry = new String[2];
    				entry[0] = stateName;
    				entry[1] = countryName;
    				String entryName = Util.join(entry, ", ");
    				if (!name.equals(entryName))
    					stateAliases.add(entryName);
    			}
    		els[i] = new String[2][];
    		els[i][0] = elements[i][0];
    		els[i][1] = stateAliases.toArray(new String[]{});
    	}
		XlsPlaceName.addNameList(els);
    }
}
