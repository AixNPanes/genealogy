package ws.daley.genealogy.gedcom;

import java.util.TreeMap;

public class Location
{
	public static TreeMap<String, Location> locations = new TreeMap<String, Location>();	// lcplace to location
	public static TreeMap<String, Location> places = new TreeMap<String, Location>();		// place id to location
	private static int idno = -1;
	private GedPlaceRecord placeRecord = null;

	private String locationId = null;
	private String place = null;
	private String placePiece = null;

	private Location parent = null;
	
	private Location(String place)
	{
		this.place = place;
		idno++;
		this.locationId = String.format("@L%05d@", idno);
	}

	public static void generateLocation(GedPlaceRecord place)
	{
		if (place.getId() != null)
		{
			Location location = generateLocation(place.getValue());
			location.placeRecord = place;
			places.put(place.getId(), location);
		}
	}
	
	private static Location generateLocation(String place)
	{
		String lcPlace = place.toLowerCase().trim();
		Location location = locations.get(lcPlace);
		if (location != null)
			return location;
		location = new Location(place.trim());
		locations.put(lcPlace, location);
		generateLocation(location);
		return location;
	}
	
	private static Location generateLocation(Location location)
	{
		int lc = location.place.indexOf(",");
		if (lc >= 0)
		{
			location.placePiece = location.place.substring(0, lc);
			location.parent = generateLocation(location.place.substring(lc + 1).trim());
			return location.parent;
		}
		location.placePiece = location.place;
		return null;
	}
	
	public static void reorg()
	{
		
	}

	/**
     * @return the placePiece
     */
    public String getPlacePiece() {return this.placePiece;}
	
	/**
     * @return the locations
     */
    public static TreeMap<String, Location> getLocations() {return locations;}
	
	/**
     * @return the locations
     */
    public static TreeMap<String, Location> getPlaces() {return places;}

	/**
     * @return the id
     */
    public String getId() {return this.locationId;}

	/**
     * @return the place
     */
    public String getPlace() {return this.place;}

	/**
     * @return the parent
     */
    public Location getParent() {return this.parent;}

    /**
     * @return the placeRecord
     */
    public GedPlaceRecord getPlaceRecord() {return this.placeRecord;}
}
