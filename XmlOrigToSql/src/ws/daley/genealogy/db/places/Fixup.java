package ws.daley.genealogy.db.places;

import java.util.HashMap;

public class Fixup
{
	private static HashMap<String, String> map = new HashMap<String, String>();
    public static HashMap<String, String> getMap() {return map;}
    public static void setMap(HashMap<String, String> map) {Fixup.map = map;}
    public static String getMapEntry(String name) {return map.get(name);}

	private String name;
    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}

	private String fixup;
    public String getFixup() {return this.fixup;}
    public void setFixup(String fixup) {this.fixup = fixup;}
	
	public Fixup() {}
	
	public Fixup(String name, String fixup)
	{
		this.name = name;
		this.fixup = fixup;
		map.put(name, fixup);
	}
	
	public static void load()
	{
		for(Fixup fix:new Fixup[]{
				new Fixup("Huntsville, Madison, Alabama, USA", "Huntsville, Madison County, Alabama, USA"),
				new Fixup("Troy, Pike, Alabama, USA", "Troy, Pike County, Alabama, USA"),

				new Fixup("Fort Yuma, Imperial County, Arizona", "Fort Yuma, Imperial County, Arizona, USA"),
				new Fixup("Chloride, Mohave, Arizona, USA", "Chloride, Mohave County, Arizona, USA"),
				new Fixup("Kingman, Mohave, Arizona", "Kingman, Mohave County, Arizona, USA"),
				new Fixup("Kingman, Mohave, Arizona, USA", "Kingman, Mohave County, Arizona, USA"),
				new Fixup("Oatman, Mohave, Arizona", "Oatman, Mohave County, Arizona, USA"),
				new Fixup("Oatman, Mohave, Arizona, USA", "Oatman, Mohave County, Arizona, USA"),
				new Fixup("Fort Huachuca, Pima County, Arizona", "Fort Huachuca, Pima County, Arizona, USA"),

				new Fixup("Sharp, Independence, Arkansas, USA", "Sharp, Independence County, Arkansas, USA"),
				new Fixup("Sharp, Independence County, Arkansas", "Sharp, Independence County, Arkansas, USA"),
				new Fixup("Monroe, Monroe, Arkansas, USA", "Monroe, Monroe County, Arkansas, USA"),
				new Fixup("Richland, Newton, Arkansas, USA", "Richland, Newton County, Arkansas, USA"),
				new Fixup("Center, Pope, Arkansas, USA", "Center, Pope County, Arkansas, USA"),
				new Fixup("Dover, Pope, Arkansas, USA", "Dover, Pope County, Arkansas, USA"),
				new Fixup("Gum Log, Pope, Arkansas, USA", "Gum Log, Pope County, Arkansas, USA"),
				new Fixup("Illinois, Pope, Arkansas, USA", "Illinois, Pope County, Arkansas, USA"),
				new Fixup("Jackson, Pope, Arkansas, USA", "Jackson, Pope County, Arkansas, USA"),
				new Fixup("Jackson Twp, Pope, Arkansas", "Jackson Twp, Pope County, Arkansas, USA"),
				new Fixup("Liberty, Pope, Arkansas, USA", "Liberty, Pope County, Arkansas, USA"),
				new Fixup("Valley, Pope, Arkansas, USA", "Valley, Pope County, Arkansas, USA"),
				new Fixup("Benton, Arkansas, USA", "Benton, Saline County, Arkansas, USA"),
				new Fixup("Benton, Saline, Arkansas, USA", "Benton, Saline County, Arkansas, USA"),

				new Fixup("Alameda, Alameda, California, USA", "Alameda, Alameda County, California, USA"),
				new Fixup("Berkeley, Alameda, California, USA", "Berkeley, Alameda County, California, USA"),
				new Fixup("Oakland, Alameda, California, USA", "Oakland, Alameda County, California, USA"),
				
				new Fixup("Ione, Amador, California, USA", "Ione, Amador County, California, USA"),
				
				new Fixup("Biggs, Butte, California", "Biggs, Butte County, California, USA"),
				new Fixup("Biggs, Butte, California, USA", "Biggs, Butte County, California, USA"),
				
				new Fixup("West Point, Calaveras, California", "West Point, Calaveras County, California, USA"),
				new Fixup("West Point, Calaveras, California, USA", "West Point, Calaveras County, California, USA"),
				
				new Fixup("Contra Costa, California", "Contra Costa County, California, USA"),
				new Fixup("Martinez, Contra Costa, California", "Martinez, Contra Costa County, California, USA"),
				new Fixup("Martinez, Contra Costa, California, USA", "Martinez, Contra Costa County, California, USA"),
				new Fixup("Walnut Creek, Contra Costa, California, USA", "Walnut Creek, Contra Costa County, California, USA"),
				
				new Fixup("Coalinga, Fresno, California, USA", "Coalinga, Fresno County, California, USA"),
				new Fixup("Fowler, Fresno, California, USA", "Fowler, Fresno County, California, USA"),
				new Fixup("Fresno, California", "Fresno, Fresno County, California, USA"),
				new Fixup("Fresno, CA", "Fresno, Fresno County, California, USA"),
				new Fixup("Fresno, California, USA", "Fresno, Fresno County, California, USA"),
				new Fixup("Fresno County, California, USA", "Fresno County, California, USA"),
				new Fixup("Fresno, Fresno, California, USA", "Fresno, Fresno County, California, USA"),
				new Fixup("3-Wd Fresno, Fresno, California, USA", "Ward 3, Fresno, Fresno County, California, USA"),
				new Fixup("3-Wd Fresno, Fresno, California", "Ward 3, Fresno, Fresno County, California, USA"),
				new Fixup("Township 9, Fresno, California", "Township 9, Fresno, Fresno County, California, USA"),

				new Fixup("Humboldt, California", "Humboldt County, California, USA"),

				new Fixup("Inyo, California, USA", "Inyo County, California, USA"),
				new Fixup("Inyo National Forest reserve, Inyo, California, USA", "Inyo National Forest reserve, Inyo County, California, USA"),
				new Fixup("Inyo National Forest reserve, Inyo, California", "Inyo National Forest reserve, Inyo County, California, USA"),
				new Fixup("Bishop, Inyo, California, USA", "Bishop, Inyo County, California, USA"),

				new Fixup("Arvin, Kern, California, USA", "Arvin, Kern County, California, USA"),
				new Fixup("Bakersfield, Kern, California, USA", "Bakersfield, Kern County, California, USA"),
				new Fixup("Bakersfield Ward 1, Kern, California", "Ward 1, Bakersfield, Kern County, California, USA"),
				new Fixup("Caliente, Kern, California", "Caliente, Kern County, California, USA"),
				new Fixup("Caliente, Kern, California, USA", "Caliente, Kern County, California, USA"),
				new Fixup("California City, Kern, California", "California City, Kern County, California, USA"),
				new Fixup("California City, Kern, California, USA", "California City, Kern County, California, USA"),
				new Fixup("Canfield, Kern, California", "Canfield, Kern County, California, USA"),
				new Fixup("Taft, Kern, California", "Taft, Kern County, California, USA"),
				new Fixup("Taft, Kern, California, USA", "Taft, Kern County, California, USA"),
				new Fixup("West Side District Cemetery, Taft, Kern, California", "West Side District Cemetery, Taft, Kern County, California, USA"),
				new Fixup("Tehachapi, Kern, California", "Tehachapi, Kern County, California, USA"),
				new Fixup("Tehachipa, Kern, California", "Tehachapi, Kern County, California, USA"),
				new Fixup("Tehachapi, Kern, California, USA", "Tehachapi, Kern County, California, USA"),
				new Fixup("Township 1, Kern, California", "Township 1, Kern County, California, USA"),
				new Fixup("Township 2, Kern, California", "Township 2, Kern County, California, USA"),
				new Fixup("Township 3, Kern, California", "Township 3, Kern County, California, USA"),
				new Fixup("Township 4, Kern, California", "Township 4, Kern County, California, USA"),
				new Fixup("Township 7, Kern, California", "Township 7, Kern County, California, USA"),
				new Fixup("Township 8, Kern, California", "Township 8, Kern County, California, USA"),
				new Fixup("Township 9, Kern, California", "Township 9, Kern County, California, USA"),
				new Fixup("Township 12, Kern, California", "Township 12, Kern County, California, USA"),
				new Fixup("Township 14, Kern, California", "Township 14, Kern County, California, USA"),
				new Fixup("Township 19, Kern, California", "Township 19, Kern County, California, USA"),
				new Fixup("Wasco, Kern, California, USA", "Wasco, Kern County, California, USA"),
				
				new Fixup("Kings, California, USA", "Kings County, California, USA"),
				new Fixup("Hanford, Kings, California, USA", "Hanford, Kings County, California, USA"),
				new Fixup("Lemoore, Kings, California, USA", "Lemoore, Kings County, California, USA"),

				new Fixup("Susanville, Lassen, California, USA", "Susanville, Lassen County, California, USA"),

				new Fixup("Los Angeles, California", "Los Angeles, Los Angeles County, California, USA"),
				new Fixup("Los Angeles, California, USA", "Los Angeles, Los Angeles County, California, USA"),
				new Fixup("Bell, Los Angeles, California, USA", "Bell, Los Angeles County, California, USA"),
				new Fixup("Glendale, Los Angeles, California, USA", "Glendale, Los Angeles County, California, USA"),
				new Fixup("Lancaster, Los Angeles, California, USA", "Lancaster, Los Angeles County, California, USA"),
				new Fixup("La Verne, Los Angeles, California, USA", "La Verne, Los Angeles County, California, USA"),
				new Fixup("Los Angeles, Los Angeles, California, USA", "Los Angeles, Los Angeles County, California, USA"),
				new Fixup("Los Angeles Assembly District 61, Los Angeles, California", "Los Angeles Assembly District 61, Los Angeles County, California, USA"),
				new Fixup("Pomona, Los Angeles, California, USA", "Pomona, Los Angeles County, California, USA"),
				new Fixup("San Pedro, Los Angeles, California, USA", "San Pedro, Los Angeles County, California, USA"),
				new Fixup("Redondo Beach, Los Angeles, California, USA", "Redondo Beach, Los Angeles County, California, USA"),
				new Fixup("Tejon, Los Angeles, California, USA", "Tejon, Los Angeles County, California, USA"),
				new Fixup("Tejon, Los Angeles, California", "Tejon, Los Angeles County, California, USA"),
				new Fixup("Torrance, Los Angeles, California, USA", "Torrance, Los Angeles County, California, USA"),
				new Fixup("Whittier, Los Angeles, California, USA", "Whittier, Los Angeles County, California, USA"),

				new Fixup("Madera, California, USA", "Madera County, California, USA"),
				new Fixup("Chowchilla, Madera, California, USA", "Chowchilla, Madera County, California, USA"),
				new Fixup("Madera, Madera, California, USA", "Madera, Madera County, California, USA"),

				new Fixup("San Rafael, Marin, California, USA", "San Rafael, Marin County, California, USA"),

				new Fixup("Mariposa, Mariposa, California, USA", "Mariposa, Mariposa County, California, USA"),

				new Fixup("Atwater, Merced, California, USA", "Atwater, Merced County, California, USA"),
				new Fixup("Merced, Merced, California, USA", "Merced, Merced County, California, USA"),
				new Fixup("Winton Cemetery, Winton, Merced, California, USA", "Winton Cemetery, Winton, Merced County, California, USA"),
				new Fixup("Winton Cemetery, Winton, Merced, California", "Winton Cemetery, Winton, Merced County, California, USA"),

				new Fixup("Huntington Beach, Orange, California, USA", "Huntington Beach, Orange County, California, USA"),
				new Fixup("Newport Beach, Orange, California, USA", "Newport Beach, Orange County, California, USA"),

				new Fixup("Calimesa, Riverside, California, USA", "Calimesa, Riverside County, California, USA"),
				new Fixup("Riverside, Riverside, California, USA", "Riverside, Riverside County, California, USA"),
				new Fixup("Riverside, Riverside, California", "Riverside, Riverside County, California, USA"),
				new Fixup("Riverside, San Bernardino County, California", "Riverside, Riverside County, California, USA"),
				new Fixup("Kaiser Hospital, Riverside, Riverside Count, California, US", "Kaiser Hospital, Riverside, Riverside County, California, USA"),

				new Fixup("Carmichael, Sacramento, California, USA", "Carmichael, Sacramento County, California, USA"),
				new Fixup("Galt, Sacramento, California, USA", "Galt, Sacramento County, California, USA"),
				new Fixup("Sacramento, Sacramento, California, USA", "Sacramento, Sacramento County, California, USA"),

				new Fixup("Loma Linda, San Bernardino, California, USA", "Loma Linda, San Bernardino County, California, USA"),
				new Fixup("Loma Linda, San Bernardino County, California", "Loma Linda, San Bernardino County, California, USA"),
				new Fixup("Ontario, San Bernardino, California, USA", "Ontario, San Bernardino County, California, USA"),
				new Fixup("Rancho Cucamonga, San Bernardino, California, USA", "Rancho Cucamonga, San Bernardino County, California, USA"),
				new Fixup("San Bernardino, California, USA", "San Bernardino, San Bernardino County, California, USA"),
				new Fixup("San Bernardino, San Bernardino, California, USA", "San Bernardino, San Bernardino County, California, USA"),

				new Fixup("National City, San Diego, California, USA", "National City, San Diego County, California, USA"),
				new Fixup("San Diego, San Diego, California", "San Diego, San Diego County, California, USA"),
				
				new Fixup("San Francisco, San Francisco, California", "San Francisco, San Francisco County, California, USA"),
				new Fixup("San Francisco, San Francisco, California, USA", "San Francisco, San Francisco County, California, USA"),
				new Fixup("San Francisco Assembly District 29, San Francisco, California", "San Francisco Assembly District 29, San Francisco, San Francisco County, California, USA"),
				new Fixup("San Francisco Assembly District 29, San Francisco, California, USA", "San Francisco Assembly District 29, San Francisco, San Francisco County, California, USA"),

				new Fixup("Acampo, San Joaquin, California, USA", "Acampo, San Joaquin County, California, USA"),
				new Fixup("Escalon, San Joaquin, California, USA", "Escalon, San Joaquin County, California, USA"),
				new Fixup("Lodi, San Joaquin, California, USA", "Lodi, San Joaquin County, California, USA"),
				new Fixup("Manteca, San Joaquin, California, USA", "Manteca, San Joaquin County, California, USA"),
				new Fixup("Ripon, San Joaquin, California, USA", "Ripon, San Joaquin County, California, USA"),
				new Fixup("Stockton, San Joaquin, California, USA", "Stockton, San Joaquin County, California, USA"),
				new Fixup("Woodbridge, San Joaquin, California, USA", "Woodbridge, San Joaquin County, California, USA"),

				new Fixup("Los Osos, San Luis Obispo, California", "Los Osos, San Luis Obispo County, California, USA"),
				new Fixup("San Luis Obispo, San Luis Obispo, California, USA", "San Luis Obispo, San Luis Obispo County, California, USA"),
				new Fixup("Los Osos Valley Memorial Park, Los Osos, San Luis Obispo, California", "Los Osos Valley Memorial Park, Los Osos, San Luis Obispo County, California, USA"),
				new Fixup("Los Osos Valley Memorial Park, Los Osos, San Luis Obispo County, California", "Los Osos Valley Memorial Park, Los Osos, San Luis Obispo County, California, USA"),
				new Fixup("Morro Bay, San Luis Obispo, California, USA", "Morro Bay, San Luis Obispo County, California, USA"),

				new Fixup("Santa Barbara, California", "Santa Barbara, Santa Barbara County, California, USA"),
				new Fixup("Santa Maria, Santa Barbara, California, USA", "Santa Maria, Santa Barbara County, California, USA"),
				
				new Fixup("Mountain View, Santa Clara, California, USA", "Mountain View, Santa Clara County, California, USA"),
				new Fixup("Campbell, Santa Clara, California, USA", "Campbell, Santa Clara County, California, USA"),
				new Fixup("San Jose, Santa Clara, California, USA", "San Jose, Santa Clara County, California, USA"),
				new Fixup("San Jose, California", "San Jose, Santa Clara County, California, USA"),
				new Fixup("Good Samaritan H, San Jose, Santa Clara County, California", "Good Samaritan Hospital, San Jose, Santa Clara County, California, USA"),

				new Fixup("Santa Cruz, Santa Cruz, California", "Santa Cruz, Santa Cruz County, California, USA"),
				new Fixup("Santa Cruz, Santa Cruz, California, USA", "Santa Cruz, Santa Cruz County, California, USA"),

				new Fixup("Scott Bar, Siskiyou, CA, USA", "Scott Bar, Siskiyou County, California, USA"),
				new Fixup("Scott Bar, Siskiyou, California, USA", "Scott Bar, Siskiyou County, California, USA"),
				new Fixup("Scott River, Siskiyou, CA, USA", "Scott River, Siskiyou County, California, USA"),
				new Fixup("Scott River, Siskiyou, California, USA", "Scott River, Siskiyou County, California, USA"),

				new Fixup("Santa Rosa, Sonoma, California, USA", "Santa Rosa, Sonoma County, California, USA"),

				new Fixup("Stanislaus, California", "Stanislaus County, California, USA"),
				new Fixup("Ceres, Stanislaus, California, USA", "Ceres, Stanislaus County, California, USA"),
				new Fixup("Hills Ferry, Stanislaus County, California", "Hill's Ferry, Stanislaus County, California, USA"),
				new Fixup("Hill&#39;s Ferry, Stanislaus County, California", "Hill's Ferry, Stanislaus County, California, USA"),
				new Fixup("Modesto, Stanislaus, California, USA", "Modesto, Stanislaus County, California, USA"),
				new Fixup("Modesto Acacia Memorial Cemetery, Modesto, Stanislaus County, California", "Modesto Acacia Memorial Cemetery, Modesto, Stanislaus County, California, USA"),
				new Fixup("Modesto Cemetery Association, Modesto, Stanislaus County, California", "Modesto Cemetery Association, Modesto, Stanislaus County, California, USA"),
				new Fixup("Riverbank, Stanislaus, California, USA", "Riverbank, Stanislaus County, California, USA"),
				new Fixup("Turlock, Stanislaus, California, USA", "Turlock, Stanislaus County, California, USA"),
				new Fixup("Oristimba, Stanislaus, California", "Oristimba, Stanislaus County, California, USA"),

				new Fixup("Yuba City, Sutter, California, USA", "Yuba City, Sutter County, California, USA"),

				new Fixup("Trinity, California", "Trinity County, California, USA"),
				new Fixup("Trinity, California, USA", "Trinity County, California, USA"),
				new Fixup("Trinity, CA", "Trinity County, California, USA"),
				new Fixup("Trinity, CA, USA", "Trinity County, California, USA"),
				new Fixup("Douglas City, Trinity, California, USA", "Douglas City, Trinity County, California, USA"),
				new Fixup("Junction City, Trinity, California, USA", "Junction City, Trinity County, California, USA"),
				new Fixup("Junction City Twp, Trinity, California", "Junction City Twp, Trinity County, California, USA"),
				new Fixup("Junction City Twp, Trinity, California, USA", "Junction City Twp, Trinity County, California, USA"),
				new Fixup("Red Hill, Trinity, California, USA", "Red Hill, Trinity County, California, USA"),
				new Fixup("Red Hill, Trinity, CA", "Red Hill, Trinity County, California, USA"),
				new Fixup("Red Hill, Trinity, CA, USA", "Red Hill, Trinity County, California, USA"),
				
				new Fixup("Cutler, Tulare, California, USA", "Cutler, Tulare County, California, USA"),
				new Fixup("Dinuba, Tulare, California, USA", "Dinuba, Tulare County, California, USA"),
				new Fixup("Exeter, Tulare, California, USA", "Exeter, Tulare County, California, USA"),
				new Fixup("Exeter, Tulliallan, CA", "Exeter, Tulare County, California, USA"),
				new Fixup("Exeter Cemetery, Exeter, Tulare County, California", "Exeter Cemetery, Exeter, Tulare County, California, USA"),
				new Fixup("Exeter Cemetery, Exeter, Tulare, California, USA", "Exeter Cemetery, Exeter, Tulare County, California, USA"),
				new Fixup("Exeter Cemetery, Exeter, Tulare County, California, USA", "Exeter Cemetery, Exeter, Tulare County, California, USA"),
				new Fixup("Exeter Twp, Tulare, California, USA", "Exeter Twp, Tulare County, California, USA"),
				new Fixup("Exeter Twp, Tulare, California", "Exeter Twp, Tulare County, California, USA"),
				new Fixup("Farmersville, Tulare, California, USA", "Farmersville, Tulare County, California, USA"),
				new Fixup("Lindsay, Tulare County, California", "Lindsay, Tulare County, California, USA"),
				new Fixup("Lindsay, Tulare, California, USA", "Lindsay, Tulare County, California, USA"),
				new Fixup("Near Lindsay, Tulare County, California", "Near Lindsay, Tulare County, California, USA"),
				new Fixup("Orosi, Tulare, California, USA", "Orosi, Tulare County, California, USA"),
				new Fixup("Plano, Tulare, California, USA", "Plano, Tulare County, California, USA"),
				new Fixup("Plano, Tulare, California", "Plano, Tulare County, California, USA"),
				new Fixup("Porterville, Tulare, California, USA", "Porterville, Tulare County, California, USA"),
				new Fixup("Porterville, Tulliallan, CA", "Porterville, Tulare County, California, USA"),
				new Fixup("Porterville, Tulliallan, CA, USA", "Porterville, Tulare County, California, USA"),
				new Fixup("First United Methodist Church, Porterville, Tulare County, California", "First United Methodist Church, Porterville, Tulare County, California, USA"),
				new Fixup("First United Methodist Church, Porterville, Tulare County, California, USA", "First United Methodist Church, Porterville, Tulare County, California, USA"),
				new Fixup("First Methodist, Porterville, Tulare County, California", "First United Methodist Church, Porterville, Tulare County, California, USA"),
				new Fixup("Methodist Church, Porterville, Tulare County, California", "First United Methodist Church, Porterville, Tulare County, California, USA"),
				new Fixup("Hillcrest Memorial Park, Porterville Cem., Porterville, Tulare County, California", "Hillcrest Memorial Park, Porterville Cemetery, Porterville, Tulare County, California, USA"),
				new Fixup("Hillcrest Memorial Park, Porterville Cem., Porterville, Tulare County, California, USA", "Hillcrest Memorial Park, Porterville Cemetery, Porterville, Tulare County, California, USA"),
				new Fixup("Hillcrest Memorial Park, Porterville Cemetery, Porterville, Tulare County, California", "Hillcrest Memorial Park, Porterville Cemetery, Porterville, Tulare County, California, USA"),
				new Fixup("Hillcrest Memorial Park, Porterville, Tulare County, California", "Hillcrest Memorial Park, Porterville Cemetery, Porterville, Tulare County, California, USA"),
				new Fixup("Nazarene Church, Porterville, Tulare County, California", "Nazarene Church, Porterville, Tulare County, California, USA"),
				new Fixup("Old Porterville Cemetery, Tulare County, California, USA", "Old Porterville Cemetery, Porterville, Tulare County, California, USA"),
				new Fixup("Old Porterville Cemetary, Tulare County, California, USA", "Old Porterville Cemetery, Porterville, Tulare County, California, USA"),
				new Fixup("Old Porterville Cemetary, Porterville, Tulare County, California", "Old Porterville Cemetery, Porterville, Tulare County, California, USA"),
				new Fixup("Porterville Cem., Porterville, Tulare County, California, USA", "Porterville Cemetery, Porterville, Tulare County, California, USA"),
				new Fixup("Porterville Hosp, Porterville, Tulare County, California, USA", "Porterville Hospital, Porterville, Tulare County, California, USA"),
				new Fixup("Porterville Hospital, Porterville, Tulare County, California", "Porterville Hospital, Porterville, Tulare County, California, USA"),
				new Fixup("3-Wd Porterville, Tulare, California", "Ward 3, Porterville, Tulare County, California, USA"),
				new Fixup("3-Wd Porterville, Tulare, California, USA", "Ward 3, Porterville, Tulare County, California, USA"),
				new Fixup("3-Wd Porterville, Tulare County, California, USA", "Ward 3, Porterville, Tulare County, California, USA"),
				new Fixup("Porterville Ward 3, Tulare, California, USA", "Ward 3, Porterville, Tulare County, California, USA"),
				new Fixup("Porterville Ward 3, Tulare, California", "Ward 3, Porterville, Tulare County, California, USA"),
				new Fixup("Porterville Ward 4, Tulare, California, USA", "Ward 4, Porterville, Tulare County, California, USA"),
				new Fixup("Porterville Ward 4, Tulare, California", "Ward 4, Porterville, Tulare County, California, USA"),
				new Fixup("Porterville Ward 5, Tulare, California, USA", "Ward 5, Porterville, Tulare County, California, USA"),
				new Fixup("Porterville Ward 5, Tulare, California", "Ward 5, Porterville, Tulare County, California, USA"),
				new Fixup("Towhship 2, Tulare, California, USA", "Township 2, Tulare County, California, USA"),
				new Fixup("Township 2, Tulare, California", "Township 2, Tulare, Tulare County, California, USA"),
				new Fixup("TULARE, California", "Tulare, California, USA"),
				new Fixup("Tulare, Tulare, California, USA", "Tulare, Tulare County, California, USA"),
				new Fixup("Tule River, Tulare, California", "Tule River, Tulare County, California, USA"),
				new Fixup("Tule River, Tulare County, California", "Tule River, Tulare County, California, USA"),
				new Fixup("Tule River, Tulare, California, USA", "Tule River, Tulare County, California, USA"),
				new Fixup("Tule River Twp, Tulare, California, USA", "Tule River Twp, Tulare County, California, USA"),
				new Fixup("Tule Rvr Twp, Tulare, California", "Tule River Twp, Tulare County, California, USA"),
				new Fixup("Visalia, Tulare, California, USA", "Visalia, Tulare County, California, USA"),
				new Fixup("Visalia Twp, Tulare, California, USA", "Visalia Twp, Tulare County, California, USA"),
				new Fixup("Visalia Twp, Tulare, California", "Visalia Twp, Tulare County, California, USA"),
				new Fixup("6-Wd Visalia, Tulare, California, USA", "Ward 6, Visalia, Tulare County, California, USA"),
				new Fixup("6-Wd Visalia, Tulare, California", "Ward 6, Visalia, Tulare County, California, USA"),

				new Fixup("Sonora, Tuolumne, California, USA", "Sonora, Tuolumne County, California, USA"),

				new Fixup("Boulder, Boulder, Colorado, USA", "Boulder, Boulder County, Colorado, USA"),
				new Fixup("Denver, Denver, Colorado, USA", "Denver, Denver County, Colorado, USA"),
				new Fixup("Greeley, Weld, Colorado, USA", "Greeley, Weld County, Colorado, USA"),

				new Fixup("East Hartford, Hartford, Connecticut, USA", "East Hartford, Hartford County, Connecticut, USA"),
				new Fixup("Enfield, Hartford, Connecticut, USA", "Enfield, Hartford County, Connecticut, USA"),

				new Fixup("Orlando, Orange, Florida, USA", "Orlando, Orange County, Florida, USA"),
				
				new Fixup("Jo Davies County, IL", "Jo Davies County, Illinois, USA"),
				new Fixup("Jo Davies, IL, USA", "Jo Davies County, Illinois, USA"),
				new Fixup("Elizabeth, Jo Daviess, Illinois, USA", "Elizabeth, Jo Davies County, Illinois, USA"),
				
				new Fixup("La Porte, La Porte, Indiana, USA", "LaPorte, La Porte County, Indiana, USA"),
				new Fixup("Michigan City, La Porte County, Indiana 161st Regiment Indiana Infantry", "161st Regiment Indiana Infantry, Michigan City, LaPorte County, Indiana, USA"),
				new Fixup("Lawrence, Marion, Indiana, USA", "Lawrence, Marion County, Indiana, USA"),

				new Fixup("Coon Rapids, Carroll, Iowa, USA", "Coon Rapids, Carroll County, Iowa, USA"),
				new Fixup("Center Grove, Dickinson, Iowa, USA", "Center Grove, Dickinson County, Iowa, USA"),
				new Fixup("Fayette, Fayette, Iowa, USA", "Fayette, Fayette County, Iowa, USA"),
				new Fixup("West Union, Fayette, Iowa, USA", "West Union, Fayette County, Iowa, USA"),
				new Fixup("Beaman, Grundy, Iowa, USA", "Beaman, Grundy County, Iowa, USA"),
				new Fixup("Eldora, Hardin, Iowa, USA", "Eldora, Hardin County, Iowa, USA"),
				new Fixup("Ocheyedan, Osceola, Iowa, USA", "Ocheyedan, Osceola County, Iowa, USA"),
				new Fixup("Sibley, Osceola, Iowa, USA", "Sibley, Osceola County, Iowa, USA"),
				new Fixup("Des Moines, Polk, Iowa, USA", "Des Moines, Polk County, Iowa, USA"),
				new Fixup("Dixon, Scott, Iowa, USA", "Dixon, Scott County, Iowa, USA"),
				new Fixup("Nevada, Story, Iowa, USA", "Nevada, Story County, Iowa, USA"),
				new Fixup("Zearing, Story, Iowa, USA", "Zearing, Story County, Iowa, USA"),
				new Fixup("Elk Horn, Shelby, Iowa, USA", "Elk Horn, Shelby County, Iowa, USA"),
				new Fixup("Indianola, Warren, Iowa, USA", "Indianola, Warren County, Iowa, USA"),

				new Fixup("Kentucky County, Kentucky, USA", "Marshall County, Kentucky, USA"),
				new Fixup("Hardin, Marshall County, Kentucky, USA", "Hardin, Marshall County, Kentucky, USA"),
				new Fixup("Hardin, Kentucky County, Kentucky, USA", "Hardin, Marshall County, Kentucky, USA"),
				new Fixup("Hardin, Kentucky, USA", "Hardin, Marshall County, Kentucky, USA"),

				new Fixup("Frederick, Maryland, USA", "Frederick County, Maryland, USA"),
				new Fixup("Frederick, Frederick, Maryland, USA", "Frederick, Frederick County, Maryland, USA"),

				new Fixup("Egremont, Berkshire, Massachusetts, USA", "Egremont, Berkshire County, Massachusetts, USA"),
				new Fixup("Taunton, Bristol, Massachusetts, USA", "Taunton, Bristol County, Massachusetts, USA"),
				new Fixup("Deerfield, Franklin, Massachusetts, USA", "Deerfield, Franklin County, Massachusetts, USA"),
				new Fixup("Northfield, Franklin, Massachusetts, USA", "Northfield, Franklin County, Massachusetts, USA"),
				new Fixup("Springfield, Hampden, Massachusetts, USA", "Springfield, Hampden County, Massachusetts, USA"),
				new Fixup("Northampton, Hampshire, Massachusetts, USA", "Northampton, Hampshire County, Massachusetts, USA"),
				new Fixup("Boston, Suffolk, Massachusetts, USA", "Boston, Suffolk County, Massachusetts, USA"),
				
				new Fixup("Bay City, Bay, Michigan, USA", "Bay City, Bay County, Michigan, USA"),
				new Fixup("Flint, Genesee, Michigan, USA", "Flint, Genesee County, Michigan, USA"),

				new Fixup("Sleepy Eye, Brown, Minnesota, USA", "Sleepy Eye, Brown County, Minnesota, USA"),
				new Fixup("Rochester, Olmsted, Minnesota, USA", "Rochester, Olmsted County, Minnesota, USA"),
				new Fixup("St Paul, Ramsey, Minnesota, USA", "St Paul, Ramsey County, Minnesota, USA"),

				new Fixup("Missouri", "Missouri, USA"),
				new Fixup("Cass, Missouri, USA", "Cass County, Missouri, USA"),
				new Fixup("District 16, Cass, Missouri", "District 16, Cass County, Missouri, USA"),
				new Fixup("Independence, Jackson, Missouri, USA", "Independence, Jackson County, Missouri, USA"),
				new Fixup("Carterville, Jasper, Missouri, USA", "Carterville, Jasper County, Missouri, USA"),
				new Fixup("Conway, Laclede, Missouri, USA", "Conway, Laclede County, Missouri, USA"),
				new Fixup("Piney, Texas, Missouri, USA", "Piney, Texas County, Missouri, USA"),
				new Fixup("Piney Ward 3, Texas, Missouri", "Ward 3, Piney, Texas County, Missouri, USA"),
				new Fixup("St Louis, St Louis, Missouri, USA", "St Louis, St Louis County, Missouri, USA"),
				new Fixup("St Louis Ward 6, St Louis (Independent City), Missouri", "Ward 6, St Louis, St Louis County, Missouri, USA"),
				new Fixup("Cedar Creek, Wayne, Missouri, USA", "Cedar Creek, Wayne County, Missouri, USA"),
				new Fixup("Niangua, Webster, Missouri, USA", "Niangua, Webster County, Missouri, USA"),

				new Fixup("North Platte, Lincoln, Nebraska, USA", "North Platte, Lincoln County, Nebraska, USA"),

				new Fixup("Henderson, Clark, Nevada, USA", "Henderson, Clark County, Nevada, USA"),
				new Fixup("Las Vegas, Clark, Nevada, USA", "Las Vegas, Clark County, Nevada, USA"),
				new Fixup("Reno, Washoe, Nevada, USA", "Reno, Washoe County, Nevada, USA"),

				new Fixup("Clinton, New York, USA", "Clinton County, New York XlsState, USA"),
				new Fixup("Champlain, Clinton, New York, USA", "Champlain, Clinton County, New York XlsState, USA"),
				new Fixup("Ellenburghy, Clinton County, NewYork XlsState, USA", "Ellenburg, Clinton County, NewY ork XlsState, USA"),
				new Fixup("Ellenburgh, Clinton, NY", "Ellenburgh, Clinton County, New York XlsState, USA"),
				new Fixup("Ellenburg, Clinton, New York, USA", "Ellenburgh, Clinton County, New York XlsState, USA"),
				new Fixup("Hutchin&#39;s Cem., Ellenburg, Clinton County, New York XlsState", "Hutchin's Cemetery, Ellenburgh, Clinton County, New York XlsState, USA"),
				new Fixup("Warren County, NY", "Warren County, New York XlsState, USA"),
				new Fixup("Warren, NY, USA", "Warren County, New York XlsState, USA"),
				new Fixup("Brooklyn, Kings, New York, USA", "Brooklyn, Kings County, New York XlsState, USA"),
				new Fixup("Greenpoint Hospital, Brooklyn, Kings County, New York XlsState", "Greenpoint Hospital, Brooklyn, Kings County, New York XlsState, USA"),
				new Fixup("Greenpoint Hospital, Brooklyn, Kings County, New York XlsState, USA", "Greenpoint Hospital, Brooklyn, Kings County, New York XlsState, USA"),
				new Fixup("Elmhurst, Queens, New York", "Elmhurst, Queens County, New York XlsState, USA"),
				new Fixup("Elmhurst, Queens, New York, USA", "Elmhurst, Queens County, New York XlsState, USA"),
				new Fixup("Woodhaven, Queens, New York, USA", "Woodhaven, Queens County, New York XlsState, USA"),
				new Fixup("Woodside, Queens, New York XlsState", "Woodside, Queens County, New York XlsState, USA"),
				new Fixup("Woodside, Queens, New York XlsState, USA", "Woodside, Queens County, New York XlsState, USA"),
				new Fixup("Woodside, Queens, New York, USA", "Woodside, Queens County, New York XlsState, USA"),
				new Fixup("Lake George, Warren, New York, USA", "Lake George, Warren County, New York XlsState, USA"),
				new Fixup("New York, New York, USA", "New York, New York XlsState, USA"),

				new Fixup("Lawton, Comanche, Oklahoma, USA", "Lawton, Comanche County, Oklahoma, USA"),
				new Fixup("Oklahoma City, Canadian, Oklahoma, USA", "Oklahoma City, Canadian County, Oklahoma, USA"),
				new Fixup("Chickasha, Grady, Oklahoma, USA", "Chickasha, Grady County, Oklahoma, USA"),
				new Fixup("Chandler, Lincoln, Oklahoma, USA", "Chandler, Lincoln County, Oklahoma, USA"),
				new Fixup("McIntosh, Oklahoma, USA", "McIntosh County, Oklahoma, USA"),
				new Fixup("Mcintosh County, Oklahoma, USA", "McIntosh County, Oklahoma, USA"),
				new Fixup("Boynton, Mcintosh County, Oklahoma, USA", "Boynton, McIntosh County, Oklahoma, USA"),
				new Fixup("Checotah, McIntosh, Oklahoma, USA", "Checotah, McIntosh County, Oklahoma, USA"),
				new Fixup("Council Hill, Muskogee, Oklahoma, USA", "Council Hill, Muskogee County, Oklahoma, USA"),
				new Fixup("Broken Arrow, Tulsa, Oklahoma, USA", "Broken Arrow, Tulsa County, Oklahoma, USA"),
				new Fixup("Sand Springs, Tulsa, Oklahoma, USA", "Sand Springs, Tulsa County, Oklahoma, USA"),
				new Fixup("Wekiwa, Tulsa, Oklahoma, USA", "Wekiwa, Tulsa County, Oklahoma, USA"),
				new Fixup("Woodward, Woodward, Oklahoma, USA", "Woodward, Woodward County, Oklahoma, USA"),

				new Fixup("Oregon City, Clackamas, Oregon, USA", "Oregon City, Clackamas County, Oregon, USA"),
				new Fixup("Jackson, Oregon, USA", "Jackson County, Oregon, USA"),
				new Fixup("Ashland, Jackson, Oregon, USA", "Ashland, Jackson County, Oregon, USA"),
				new Fixup("Jacksonville, Jackson, Oregon, USA", "Jacksonville, Jackson County, Oregon, USA"),

				new Fixup("Cumberland, Pennsylvania, USA", "Cumberland County, Pennsylvania, USA"),
				new Fixup("Johnston, Providence, Rhode Island, USA", "Johnston, Providence County, Rhode Island, USA"),

				new Fixup("East Ridge, Hamilton, Tennessee, USA", "East Ridge, Hamilton County, Tennessee, USA"),

				new Fixup("Lamar, Texas, USA", "Lamar, Arkansas County, Texas, USA"),
				new Fixup("Lamar, Arkansas, Texas, USA", "Lamar, Arkansas County, Texas, USA"),
				new Fixup("San Antonio, Bexar, Texas, USA", "San Antonio, Bexar County, Texas, USA"),
				new Fixup("Charco, Goliad, Texas, USA", "Charco, Goliad County, Texas, USA"),
				new Fixup("Grayson, Texas, USA", "Grayson County, Texas, USA"),
				new Fixup("Runge, Karnes, Texas, USA", "Runge, Carnes County, Texas, USA"),

				new Fixup("Chipping Ongar, Essex, Vermont, USA", "Chipping Ongar, Essex County, Vermont, USA"),
				new Fixup("Guildhall, Essex, Vermont, USA", "Guildhall, Essex County, Vermont, USA"),

				new Fixup("Randolph, Charlotte, Virginia, USA", "Randolph, Charlotte County, Virginia, USA"),
				new Fixup("Suffolk, Nansemond, Virginia, USA", "Suffolk, Nansemond County, Virginia, USA"),

				new Fixup("Seattle, King, Washington, USA", "Seattle, King County, Washington, USA"),
				new Fixup("Bremerton, Kitsap, Washington, USA", "Bremerton, Kitsap County, Washington, USA"),
				new Fixup("Lynnwood, Snohomish, Washington, USA", "Lynnwood, Snohomish County, Washington, USA"),

				new Fixup("Fon Du Lac, Wisconsin", "Fon du Lac County, Wisconsin, USA"),
				new Fixup("Fond Du Lac, Wisconsin", "Fon du Lac County, Wisconsin, USA"),
				new Fixup("Fond du Lac, Wisconsin, USA", "Fon du Lac County, Wisconsin, USA"),
				new Fixup("Fon Du Lac County, Wisconsin, USA", "Fon du Lac County, Wisconsin, USA"),
				new Fixup("Avoca, Iowa, Wisconsin, USA", "Avoca, Iowa County, Wisconsin, USA"),

				new Fixup("Parkman, Sheridan, Wyoming, USA", "Parkman, Sheridan County, Wyoming, USA"),

				new Fixup("Washington City, District Of Columbia, District of Columbia, USA", "Washington City, Washington, District of Columbia, USA"),

				new Fixup("Tyrone, Ireland", "Tyrone County, Northern Ireland"),
				new Fixup("Tyrone County, Ireland", "Tyrone County, Northern Ireland"),
				new Fixup("Odessa, Russia", "Odessa, Ukraine")
		})
			/*entityManager.persist(fix)*/;
	}
}
