package ws.daley.genealogy.db.places;

import java.util.List;

import javax.persistence.EntityManager;

import ws.daley.genealogy.db.places.abstrct.PlaceStructure;
import ws.daley.genealogy.db.places.abstrct.PlaceType;

public class State extends PlaceStructure
{

	private Country parentPlace;
	@Override
    public Country getParentPlaceStructure() {return this.parentPlace;}
	@Override
    public void setParentPlaceStructure(PlaceStructure parentPlace) {this.parentPlace = (Country)parentPlace;}

	private Country country;
    public Country getCountry() {return this.country;}
    public void setCountry(Country country) {this.country = country;}
	
    public State() {}
    
	public State(String[] name, String stateAbbrev, Country country, List<PlaceURL> urls)
	{
		super(TYPE.STATE, name, stateAbbrev, country, urls);
		this.setCountry(this.getParentPlaceStructure());
	}
    
	public State(String[] name, String stateAbbrev)
	{
		this(name, stateAbbrev, null, null);
	}
    
    public static void persist(EntityManager em)
    {
    		em.persist(new State(new String[]{"Alabama", "United States"}, "AL"));
			em.persist(new State(new String[]{"Alaska", "United States"}, "AK"));
			em.persist(new State(new String[]{"American Samoa", "United States"}, "AS"));
			em.persist(new State(new String[]{"Arizona", "United States"}, "AZ"));
			em.persist(new State(new String[]{"Arkansas", "United States"}, "AR"));
			em.persist(new State(new String[]{"California", "United States"}, "CA"));
			em.persist(new State(new String[]{"Colorado", "United States"}, "CO"));
			em.persist(new State(new String[]{"Connecticut", "United States"}, "CT"));
			em.persist(new State(new String[]{"Delaware", "United States"}, "DE"));
			em.persist(new State(new String[]{"Washington, District of Columbia", "United States"}, "DC"));
			em.persist(new State(new String[]{"Federated States of Micronesia", "United States"}, "FM"));
			em.persist(new State(new String[]{"Florida", "United States"}, "FL"));
			em.persist(new State(new String[]{"Georgia", "United States"}, "GA"));
			em.persist(new State(new String[]{"Guam", "United States"}, "GU"));
			em.persist(new State(new String[]{"Hawaii", "United States"}, "HI"));
			em.persist(new State(new String[]{"Idaho", "United States"}, "ID"));
			em.persist(new State(new String[]{"Illinois", "United States"}, "IL"));
			em.persist(new State(new String[]{"Indiana", "United States"}, "IN"));
			em.persist(new State(new String[]{"Iowa", "United States"}, "IA"));
			em.persist(new State(new String[]{"Kansas", "United States"}, "KS"));
			em.persist(new State(new String[]{"Kentucky", "United States"}, "KY"));
			em.persist(new State(new String[]{"Louisiana", "United States"}, "LA"));
			em.persist(new State(new String[]{"Maine", "United States"}, "ME"));
			em.persist(new State(new String[]{"Marshall Islands", "United States"}, "MH"));
			em.persist(new State(new String[]{"Maryland", "United States"}, "MD"));
			em.persist(new State(new String[]{"Massachusetts", "United States"}, "MA"));
			em.persist(new State(new String[]{"Michigan", "United States"}, "MI"));
			em.persist(new State(new String[]{"Minnesota", "United States"}, "MN"));
			em.persist(new State(new String[]{"Mississippi", "United States"}, "MS"));
			em.persist(new State(new String[]{"Missouri", "United States"}, "MO"));
			em.persist(new State(new String[]{"Montana", "United States"}, "MT"));
			em.persist(new State(new String[]{"Nebraska", "United States"}, "NE"));
			em.persist(new State(new String[]{"Nevada", "United States"}, "NV"));
			em.persist(new State(new String[]{"New Hampshire", "United States"}, "NH"));
			em.persist(new State(new String[]{"New Jersey", "United States"}, "NJ"));
			em.persist(new State(new String[]{"New Mexico", "United States"}, "NM"));
			em.persist(new State(new String[]{"New York XlsState", "United States"}, "NY"));
			em.persist(new State(new String[]{"North Carolina", "United States"}, "NC"));
			em.persist(new State(new String[]{"North Dakota", "United States"}, "ND"));
			em.persist(new State(new String[]{"Northern Mariana Islands", "United States"}, "MP"));
			em.persist(new State(new String[]{"Ohio", "United States"}, "OH"));
			em.persist(new State(new String[]{"Oklahoma", "United States"}, "OK"));
			em.persist(new State(new String[]{"Oregon", "United States"}, "OR"));
			em.persist(new State(new String[]{"Palau", "United States"}, "PW"));
			em.persist(new State(new String[]{"Pennsylvania", "United States"}, "PA"));
			em.persist(new State(new String[]{"Puerto Rico", "United States"}, "PR"));
			em.persist(new State(new String[]{"Rhode Island", "United States"}, "RI"));
			em.persist(new State(new String[]{"South Carolina", "United States"}, "SC"));
			em.persist(new State(new String[]{"South Dakota", "United States"}, "SD"));
			em.persist(new State(new String[]{"Tennessee", "United States"}, "TN"));
			em.persist(new State(new String[]{"Texas", "United States"}, "TX"));
			em.persist(new State(new String[]{"Utah", "United States"}, "UT"));
			em.persist(new State(new String[]{"Vermont", "United States"}, "VT"));
			em.persist(new State(new String[]{"Virgin Islands", "United States"}, "VI"));
			em.persist(new State(new String[]{"Virginia", "United States"}, "VA"));
			em.persist(new State(new String[]{"Washington", "United States"}, "WA"));
			em.persist(new State(new String[]{"West Virginia", "United States"}, "WV"));
			em.persist(new State(new String[]{"Wisconsin", "United States"}, "WI"));
			em.persist(new State(new String[]{"Wyoming", "United States"}, "WY"));
			em.persist(new State(new String[]{"Armed Forces Africa", "United States"}, "AE"));
			em.persist(new State(new String[]{"Armed Forces Americas", "United States"}, "AA"));
			em.persist(new State(new String[]{"Armed Forces Canada", "United States"}, "AE"));
			em.persist(new State(new String[]{"Armed Forces Europe", "United States"}, "AE"));
			em.persist(new State(new String[]{"Armed Forces Middle East", "United States"}, "AE"));
			em.persist(new State(new String[]{"Armed Forces Pacific", "United States"}, "AP"));
			em.persist(new State(new String[]{"Alberta", "Canada"}, "AB"));
			em.persist(new State(new String[]{"British Columbia", "Canada"}, "BC"));
			em.persist(new State(new String[]{"Manitoba", "Canada"}, "MB"));
			em.persist(new State(new String[]{"New Brunswick", "Canada"}, "NB"));
			em.persist(new State(new String[]{"Newfoundland and Labrador", "Canada"}, "NL"));
			em.persist(new State(new String[]{"Northwest Territories", "Canada"}, "NT"));
			em.persist(new State(new String[]{"Nova Scotia", "Canada"}, "NS"));
			em.persist(new State(new String[]{"Nunavut", "Canada"}, "NU"));
			em.persist(new State(new String[]{"Ontario", "Canada"}, "ON"));
			em.persist(new State(new String[]{"Prince Edward Island", "Canada"}, "PE"));
			em.persist(new State(new String[]{"Quebec", "Canada"}, "QC"));
			em.persist(new State(new String[]{"Saskatchewan", "Canada"}, "SK"));
			em.persist(new State(new String[]{"Yukon", "Canada"}, "YT"));
			em.persist(new State(new String[]{"Aguascalientes", "Mexico"}, "AG"));
			em.persist(new State(new String[]{"Baja California", "Mexico"}, "BN"));
			em.persist(new State(new String[]{"Baja California Sur", "Mexico"}, "BS"));
			em.persist(new State(new String[]{"Campeche", "Mexico"}, "CM"));
			em.persist(new State(new String[]{"Chiapas", "Mexico"}, "CP"));
			em.persist(new State(new String[]{"Chihuahua", "Mexico"}, "CH"));
			em.persist(new State(new String[]{"Coahuila", "Mexico"}, "CO"));
			em.persist(new State(new String[]{"Colima", "Mexico"}, "CL"));
			em.persist(new State(new String[]{"Distrito Federal", "Mexico"}, "DF"));
			em.persist(new State(new String[]{"Durango", "Mexico"}, "DU"));
			em.persist(new State(new String[]{"Guanajuato", "Mexico"}, "GJ"));
			em.persist(new State(new String[]{"Guerrero", "Mexico"}, "GR"));
			em.persist(new State(new String[]{"Hidalgo", "Mexico"}, "HG"));
			em.persist(new State(new String[]{"Jalisco", "Mexico"}, "JA"));
			em.persist(new State(new String[]{"México", "Mexico"}, "MX"));
			em.persist(new State(new String[]{"Michoacán", "Mexico"}, "MC"));
			em.persist(new State(new String[]{"Morelos", "Mexico"}, "MR"));
			em.persist(new State(new String[]{"Nayarit", "Mexico"}, "NA"));
			em.persist(new State(new String[]{"Nuevo León", "Mexico"}, "NL"));
			em.persist(new State(new String[]{"Oaxaca", "Mexico"}, "OA"));
			em.persist(new State(new String[]{"Puebla", "Mexico"}, "PU"));
			em.persist(new State(new String[]{"Querétaro", "Mexico"}, "AE"));
			em.persist(new State(new String[]{"Quintana Roo", "Mexico"}, "QR"));
			em.persist(new State(new String[]{"San Luis Potosí", "Mexico"}, "SL"));
			em.persist(new State(new String[]{"Sinaloa", "Mexico"}, "SI"));
			em.persist(new State(new String[]{"Sonora", "Mexico"}, "SO"));
			em.persist(new State(new String[]{"Tabasco", "Mexico"}, "TB"));
			em.persist(new State(new String[]{"Tamaulipas", "Mexico"}, "TM"));
			em.persist(new State(new String[]{"Tlaxcala", "Mexico"}, "TL"));
			em.persist(new State(new String[]{"Veracruz", "Mexico"}, "VE"));
			em.persist(new State(new String[]{"Yucatán", "Mexico"}, "YU"));
			em.persist(new State(new String[]{"Zacatecas", "Mexico"}, "ZA"));
			em.persist(new State(new String[]{"Alsace-Lorraine", "France"}, ""));
			em.persist(new State(new String[]{"Aquitane", "France"}, ""));
			em.persist(new State(new String[]{"Auvergne", "France"}, ""));
			em.persist(new State(new String[]{"Bourgogne", "France"}, ""));
			em.persist(new State(new String[]{"Brittany", "France"}, ""));
			em.persist(new State(new String[]{"Centre", "France"}, ""));
			em.persist(new State(new String[]{"Champagne-Ardenne", "France"}, ""));
			em.persist(new State(new String[]{"Corsica", "France"}, ""));
			em.persist(new State(new String[]{"Franche-Comté", "France"}, ""));
			em.persist(new State(new String[]{"Île-de-France", "France"}, ""));
			em.persist(new State(new String[]{"Languedoc-Roussillon", "France"}, ""));
			em.persist(new State(new String[]{"Limousin", "France"}, ""));
			em.persist(new State(new String[]{"Lorraine", "France"}, ""));
			em.persist(new State(new String[]{"Midi-Pyrénées", "France"}, ""));
			em.persist(new State(new String[]{"Nord-Pas-de-Calais", "France"}, ""));
			em.persist(new State(new String[]{"Basse-Normandie", "France"}, ""));
			em.persist(new State(new String[]{"Haute-Normandie", "France"}, ""));
			em.persist(new State(new String[]{"Pays de la Loire", "France"}, ""));
			em.persist(new State(new String[]{"Picardy", "France"}, ""));
			em.persist(new State(new String[]{"Poitou-Charentes", "France"}, ""));
			em.persist(new State(new String[]{"Provence-Alpes-Côte d''Azur", "France"}, ""));
			em.persist(new State(new String[]{"Rhône-Alpes", "France"}, ""));
			em.persist(new State(new String[]{"French Guiana", "France"}, ""));
			em.persist(new State(new String[]{"Guadeloupe", "France"}, ""));
			em.persist(new State(new String[]{"Martinique", "France"}, ""));
			em.persist(new State(new String[]{"Réunion", "France"}, ""));
			em.persist(new State(new String[]{"Baden-Württemberg", "Germany"}, ""));
			em.persist(new State(new String[]{"Bayern", "Germany"}, ""));
			em.persist(new State(new String[]{"Berlin", "Germany"}, ""));
			em.persist(new State(new String[]{"Brandenburg", "Germany"}, ""));
			em.persist(new State(new String[]{"Bremen", "Germany"}, ""));
			em.persist(new State(new String[]{"Hamburg", "Germany"}, ""));
			em.persist(new State(new String[]{"Hessen", "Germany"}, ""));
			em.persist(new State(new String[]{"Mecklenburg-Vorpommern", "Germany"}, ""));
			em.persist(new State(new String[]{"Niedersachsen", "Germany"}, ""));
			em.persist(new State(new String[]{"Nordrhein-Westfalen", "Germany"}, ""));
			em.persist(new State(new String[]{"Rheinland-Pfalz", "Germany"}, ""));
			em.persist(new State(new String[]{"Saarland", "Germany"}, ""));
			em.persist(new State(new String[]{"Sachsen", "Germany"}, ""));
			em.persist(new State(new String[]{"Sachsen-Anhalt", "Germany"}, ""));
			em.persist(new State(new String[]{"Schleswig-Holstein", "Germany"}, ""));
			em.persist(new State(new String[]{"Thuringia", "Germany"}, ""));
			em.persist(new State(new String[]{"Drenthe", "Netherlands"}, ""));
			em.persist(new State(new String[]{"Flevoland", "Netherlands"}, ""));
			em.persist(new State(new String[]{"Friesland", "Netherlands"}, ""));
			em.persist(new State(new String[]{"Gelderland", "Netherlands"}, ""));
			em.persist(new State(new String[]{"Groningen", "Netherlands"}, ""));
			em.persist(new State(new String[]{"Limburg", "Netherlands"}, ""));
			em.persist(new State(new String[]{"North Brabant", "Netherlands"}, ""));
			em.persist(new State(new String[]{"North Holland", "Netherlands"}, ""));
			em.persist(new State(new String[]{"Overijssel", "Netherlands"}, ""));
			em.persist(new State(new String[]{"Utrecht", "Netherlands"}, ""));
			em.persist(new State(new String[]{"Zeeland", "Netherlands"}, ""));
			em.persist(new State(new String[]{"South Holland", "Netherlands"}, ""));
			em.persist(new State(new String[]{"Channel Islands", "United Kingdom"}, "CHI"));
			em.persist(new State(new String[]{"England", "United Kingdom"}, "ENG"));
			em.persist(new State(new String[]{"Isle of Man", "United Kingdom"}, "IOM"));
			em.persist(new State(new String[]{"Ireland", "United Kingdom"}, "IRL"));
			em.persist(new State(new String[]{"Northern Ireland", "United Kingdom"}, "NIR"));
			em.persist(new State(new String[]{"Scotland", "United Kingdom"}, "SCT"));
			em.persist(new State(new String[]{"Wales", "United Kingdom"}, "WAL"));
    }
	
    public static State getPlace(String element) {return (State)getPlace(PlaceType.TYPE.STATE, new String[]{element});}
}
