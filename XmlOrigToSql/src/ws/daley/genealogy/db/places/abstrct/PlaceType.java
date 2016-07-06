package ws.daley.genealogy.db.places.abstrct;



public abstract class PlaceType extends PlaceName
{
	public enum TYPE{NONE, COUNTRY, STATE, COUNTY, CITY, LOCALE};
	
    private TYPE type;
    public TYPE getType() {return this.type;}
    public void setType(TYPE type) {this.type = type;}
    
    public PlaceType() {}
    
    public PlaceType(TYPE type, String name)
    {
    	super(name);
    	this.type = type;
    }
    
    public static TYPE getParentType(TYPE type)
    {
    	switch(type)
    	{
    		case NONE:
    			return TYPE.NONE;
    		case COUNTRY:
    			return TYPE.NONE;
    		case STATE:
    			return TYPE.COUNTRY;
    		case COUNTY:
    			return TYPE.STATE;
    		case CITY:
    			return TYPE.COUNTY;
    		case LOCALE:
    			return TYPE.CITY;
    	}
    	throw new RuntimeException("Invalid type code");
    }
    
    public static String getName(TYPE type)
    {
    	switch(type)
    	{
    		case NONE:
    			return "None";
    		case COUNTRY:
    			return "Country";
    		case STATE:
    			return "State";
    		case COUNTY:
    			return "County";
    		case CITY:
    			return "City";
    		case LOCALE:
    			return "Locale";
    	}
    	throw new RuntimeException("Invalid type code");
    }
    
    public static int getOrdinal(TYPE type)
    {
    	switch(type)
    	{
    		case NONE:
    			return 0;
    		case COUNTRY:
    			return 1;
    		case STATE:
    			return 2;
    		case COUNTY:
    			return 3;
    		case CITY:
    			return 4;
    		case LOCALE:
    			return 5;
    	}
    	throw new RuntimeException("Invalid type code");
    }
}
