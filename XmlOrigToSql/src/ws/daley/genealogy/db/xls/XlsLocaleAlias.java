package ws.daley.genealogy.db.xls;

import ws.daley.genealogy.db.places.Locale;
import ws.daley.genealogy.db.places.abstrct.PlaceAlias;
import ws.daley.genealogy.db.places.abstrct.PlaceStructure;
import ws.daley.genealogy.db.places.abstrct.PlaceType;

public class XlsLocaleAlias extends PlaceAlias
{
    private Locale place;
    @Override
    public Locale getPlaceStructure() {return this.place;}
    @Override
    public void setPlaceStructure(PlaceStructure place) {this.place = (Locale)place;}

    public XlsLocaleAlias() {}
	
	public XlsLocaleAlias(String name, Locale locale)
	{
		super(PlaceType.TYPE.LOCALE, name, locale);
	}

    public XlsLocaleAlias(Locale locale) {this(locale.getName(), locale);}
    
    public static void load() {}
}
