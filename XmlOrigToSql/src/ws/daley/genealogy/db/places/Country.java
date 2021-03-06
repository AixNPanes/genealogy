package ws.daley.genealogy.db.places;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import ws.daley.genealogy.db.places.abstrct.PlaceStructure;
import ws.daley.genealogy.db.places.abstrct.PlaceType;
import ws.daley.genealogy.db.places.alias.CountryAlias;
import ws.daley.genealogy.db.xls.XlsPlaceName;

public class Country extends PlaceStructure
{
	
	private Country parentPlace;
	@Override
    public Country getParentPlaceStructure() {return this.parentPlace;}
	@Override
    public void setParentPlaceStructure(PlaceStructure parentPlace) {this.parentPlace = (Country)parentPlace;}


	private List<CountryAlias> countryAliases = new ArrayList<CountryAlias>();
    public List<CountryAlias> getCountryAliases() {return this.countryAliases;}
    public void setCountryAliases(List<CountryAlias> countryAliases) {this.countryAliases = countryAliases;}

	public Country() {}
	
	public Country(String[] name, String abbrev, PlaceStructure parentPlace, List<PlaceURL> urls)
	{
		super(TYPE.COUNTRY, name, abbrev, parentPlace, urls);
	}
	
	public Country(String[] name, String abbrev)
	{
		super(TYPE.COUNTRY, name, abbrev, null, null);
	}
	
    public static Country getPlace(String element)
    {
    	Country country = (Country)getPlace(PlaceType.TYPE.COUNTRY, new String[]{element});
    	return country;
    }
    
    public static void persist(EntityManager em)
    {
		String[][][] elements = new String[][][]{
			{{"Afghanistan"}, {"AF"}},
			{{"Albania"}, {"AL"}},
			{{"Algeria"}, {"DZ"}},
			{{"American Samoa"}, {"AS"}},
			{{"Andorra"}, {"AD"}},
			{{"Angola"}, {"AO"}},
			{{"Anguilla"}, {"AI"}},
			{{"Antarctia"}, {"AQ"}},
			{{"Antigua BBarbuda"}, {"AG"}},
			{{"Argentina"}, {"AR"}},
			{{"Armenia"}, {"AM"}},
			{{"Aruba"}, {"AW"}},
			{{"Australia"}, {"AU"}},
			{{"Austria"}, {"AT"}},
			{{"Azerbaijan"}, {"AZ"}},
			{{"Bahamas"}, {"BS"}},
			{{"Bahrain"}, {"BH"}},
			{{"Bangladesh"}, {"BD"}},
			{{"Barbados"}, {"BB"}},
			{{"Belarus"}, {"BY"}},
			{{"Belgium"}, {"BE"}},
			{{"Belize"}, {"BZ"}},
			{{"Benin"}, {"BJ"}},
			{{"Bermuda"}, {"BM"}},
			{{"Bhutan"}, {"BT"}},
			{{"Bolivia"}, {"BO"}},
			{{"Bosnia Herzegovina"}, {"BA"}},
			{{"Botswana"}, {"BW"}},
			{{"Bouvet Island"}, {"BV"}},
			{{"Brazil"}, {"BR"}},
			{{"British Indian Ocean Territory"}, {"IO"}},
			{{"Brunei Darussalam"}, {"BN"}},
			{{"Bulgaria"}, {"BG"}},
			{{"Burkina Faso"}, {"BF"}},
			{{"Burundi"}, {"BI"}},
			{{"Cambodia"}, {"KH"}},
			{{"Cameroon"}, {"CM"}},
			{{"Canada"}, {"CA"}},
			{{"Cape Verde"}, {"CV"}},
			{{"Cayman Islands"}, {"KY"}},
			{{"CENTRAL AFRICAN REPUBLIC"}, {"CF"}},
			{{"CHAD"}, {"TD"}},
			{{"CHILE"}, {"CL"}},
			{{"CHINA"}, {"CN"}},
			{{"CHRISTMAS ISLAND"}, {"CX"}},
			{{"COCOS (KEELING) ISLANDS"}, {"CC"}},
			{{"COLOMBIA"}, {"CO"}},
			{{"COMOROS"}, {"KM"}},
			{{"CONGO"}, {"CG"}},
			{{"CONGO, THE DEMOCRATIC REPUBLIC OF"}, {"CD"}},
			{{"COOK ISLANDS"}, {"CK"}},
			{{"COSTA RICA"}, {"CR"}},
			{{"C�TE D''IVOIRE"}, {"CI"}},
			{{"CROATIA"}, {"HR"}},
			{{"CUBA"}, {"CU"}},
			{{"CYPRUS"}, {"CY"}},
			{{"CZECH REPUBLIC"}, {"CZ"}},
			{{"DENMARK"}, {"DK"}},
			{{"DJIBOUTI"}, {"DJ"}},
			{{"DOMINICA"}, {"DM"}},
			{{"DOMINICAN REPUBLIC"}, {"DO"}},
			{{"ECUADOR"}, {"EC"}},
			{{"EGYPT"}, {"EG"}},
			{{"EL SALVADOR"}, {"SV"}},
			{{"EQUATORIAL GUINEA"}, {"GQ"}},
			{{"ERITREA"}, {"ER"}},
			{{"ESTONIA"}, {"EE"}},
			{{"ETHIOPIA"}, {"ET"}},
			{{"FALKLAND ISLANDS (MALVINAS)"}, {"FK"}},
			{{"FAROE ISLANDS"}, {"FO"}},
			{{"FIJI"}, {"FJ"}},
			{{"FINLAND"}, {"FI"}},
			{{"FRANCE"}, {"FR"}},
			{{"FRENCH GUIANA"}, {"GF"}},
			{{"FRENCH POLYNESIA"}, {"PF"}},
			{{"FRENCH SOUTHERN TERRITORIES"}, {"TF"}},
			{{"GABON"}, {"GA"}},
			{{"GAMBIA"}, {"GM"}},
			{{"GEORGIA"}, {"GE"}},
			{{"GERMANY"}, {"DE"}},
			{{"GHANA"}, {"GH"}},
			{{"GIBRALTAR"}, {"GI"}},
			{{"GREECE"}, {"GR"}},
			{{"GREENLAND"}, {"GL"}},
			{{"GRENADA"}, {"GD"}},
			{{"GUADELOUPE"}, {"GP"}},
			{{"GUAM"}, {"GU"}},
			{{"GUATEMALA"}, {"GT"}},
			{{"GUINEA"}, {"GN"}},
			{{"GUINEA-BISSAU"}, {"GW"}},
			{{"GUYANA"}, {"GY"}},
			{{"HAITI"}, {"HT"}},
			{{"HEARD ISLAND MCDONALD ISLANDS"}, {"HM"}},
			{{"HOLY SEE (VATICAN CITY STATE)"}, {"VA"}},
			{{"HONDURAS"}, {"HN"}},
			{{"HONG KONG"}, {"HK"}},
			{{"HUNGARY"}, {"HU"}},
			{{"ICELAND"}, {"IS"}},
			{{"INDIA"}, {"IN"}},
			{{"INDONESIA"}, {"ID"}},
			{{"IRAN, ISLAMIC REPUBLIC OF"}, {"IR"}},
			{{"IRAQ"}, {"IQ"}},
			{{"IRELAND"}, {"IE"}},
			{{"ISRAEL"}, {"IL"}},
			{{"ITALY"}, {"IT"}},
			{{"JAMAICA"}, {"JM"}},
			{{"JAPAN"}, {"JP"}},
			{{"JORDAN"}, {"JO"}},
			{{"KAZAKHSTAN"}, {"KZ"}},
			{{"KENYA"}, {"KE"}},
			{{"KIRIBATI"}, {"KI"}},
			{{"KOREA, DEMOCRATIC PEOPLE''S REPUBLIC OF"}, {"KP"}},
			{{"KOREA, REPUBLIC OF"}, {"KR"}},
			{{"KUWAIT"}, {"KW"}},
			{{"KYRGYZSTAN"}, {"KG"}},
			{{"LAO PEOPLE''S DEMOCRATIC REPUBLIC"}, {"LA"}},
			{{"LATVIA"}, {"LV"}},
			{{"LEBANON"}, {"LB"}},
			{{"LESOTHO"}, {"LS"}},
			{{"LIBERIA"}, {"LR"}},
			{{"LIBYAN ARAB JAMAHIRIYA"}, {"LY"}},
			{{"LIECHTENSTEIN"}, {"LI"}},
			{{"LITHUANIA"}, {"LT"}},
			{{"LUXEMBOURG"}, {"LU"}},
			{{"MACAO"}, {"MO"}},
			{{"MACEDONIA, THE FORMER YUGOSLAV REPUBLIC OF"}, {"MK"}},
			{{"MADAGASCAR"}, {"MG"}},
			{{"MALAWI"}, {"MW"}},
			{{"MALAYSIA"}, {"MY"}},
			{{"MALDIVES"}, {"MV"}},
			{{"MALI"}, {"ML"}},
			{{"MALTA"}, {"MT"}},
			{{"MARSHALL ISLANDS"}, {"MH"}},
			{{"MARTINIQUE"}, {"MQ"}},
			{{"MAURITANIA"}, {"MR"}},
			{{"MAURITIUS"}, {"MU"}},
			{{"MAYOTTE"}, {"YT"}},
			{{"MEXICO"}, {"MX"}},
			{{"MICRONESIA, FEDERATED STATES OF"}, {"FM"}},
			{{"MOLDOVA, REPUBLIC OF"}, {"MD"}},
			{{"MONACO"}, {"MC"}},
			{{"MONGOLIA"}, {"MN"}},
			{{"MONTSERRAT"}, {"MS"}},
			{{"MOROCCO"}, {"MA"}},
			{{"MOZAMBIQUE"}, {"MZ"}},
			{{"MYANMAR"}, {"MM"}},
			{{"NAMIBIA"}, {"NA"}},
			{{"NAURU"}, {"NR"}},
			{{"NEPAL"}, {"NP"}},
			{{"NETHERLANDS"}, {"NL"}},
			{{"NETHERLANDS ANTILLES"}, {"AN"}},
			{{"NEW CALEDONIA"}, {"NC"}},
			{{"NEW ZEALAND"}, {"NZ"}},
			{{"NICARAGUA"}, {"NI"}},
			{{"NIGER"}, {"NE"}},
			{{"NIGERIA"}, {"NG"}},
			{{"NIUE"}, {"NU"}},
			{{"NORFOLK ISLAND"}, {"NF"}},
			{{"NORTHERN MARIANA ISLANDS"}, {"MP"}},
			{{"NORWAY"}, {"NO"}},
			{{"OMAN"}, {"OM"}},
			{{"PAKISTAN"}, {"PK"}},
			{{"PALAU"}, {"PW"}},
			{{"PALESTINIAN TERRITORY, OCCUPIED"}, {"PS"}},
			{{"PANAMA"}, {"PA"}},
			{{"PAPUA NEW GUINEA"}, {"PG"}},
			{{"PARAGUAY"}, {"PY"}},
			{{"PERU"}, {"PE"}},
			{{"PHILIPPINES"}, {"PH"}},
			{{"PITCAIRN"}, {"PN"}},
			{{"POLAND"}, {"PL"}},
			{{"PORTUGAL"}, {"PT"}},
			{{"PUERTO RICO"}, {"PR"}},
			{{"QATAR"}, {"QA"}},
			{{"R�UNION"}, {"RE"}},
			{{"ROMANIA"}, {"RO"}},
			{{"RUSSIAN FEDERATION"}, {"RU"}},
			{{"RWANDA"}, {"RW"}},
			{{"SAINT HELENA"}, {"SH"}},
			{{"SAINT KITTS NEVIS"}, {"KN"}},
			{{"SAINT LUCIA"}, {"LC"}},
			{{"SAINT PIERRE MIQUELON"}, {"PM"}},
			{{"SAINT VINCENT THE GRENADINES"}, {"VC"}},
			{{"SAMOA"}, {"WS"}},
			{{"SAN MARINO"}, {"SM"}},
			{{"SAO TOME PRINCIPE"}, {"ST"}},
			{{"SAUDI ARABIA"}, {"SA"}},
			{{"SENEGAL"}, {"SN"}},
			{{"SERBIA MONTENEGRO"}, {"CS"}},
			{{"SEYCHELLES"}, {"SC"}},
			{{"SIERRA LEONE"}, {"SL"}},
			{{"SINGAPORE"}, {"SG"}},
			{{"SLOVAKIA"}, {"SK"}},
			{{"SLOVENIA"}, {"SI"}},
			{{"SOLOMON ISLANDS"}, {"SB"}},
			{{"SOMALIA"}, {"SO"}},
			{{"SOUTH AFRICA"}, {"ZA"}},
			{{"SOUTH GEORGIA SOUTH SANDWICH ISLANDS"}, {"GS"}},
			{{"SPAIN"}, {"ES"}},
			{{"SRI LANKA"}, {"LK"}},
			{{"SUDAN"}, {"SD"}},
			{{"SURINAME"}, {"SR"}},
			{{"SVALBARD JAN MAYEN"}, {"SJ"}},
			{{"SWAZILAND"}, {"SZ"}},
			{{"SWEDEN"}, {"SE"}},
			{{"SWITZERLAND"}, {"CH"}},
			{{"SYRIAN ARAB REPUBLIC"}, {"SY"}},
			{{"TAIWAN, PROVINCE OF CHINA"}, {"TW"}},
			{{"TAJIKISTAN"}, {"TJ"}},
			{{"TANZANIA, UNITED REPUBLIC OF"}, {"TZ"}},
			{{"THAILAND"}, {"TH"}},
			{{"TIMOR-LESTE"}, {"TL"}},
			{{"TOGO"}, {"TG"}},
			{{"TOKELAU"}, {"TK"}},
			{{"TONGA"}, {"TO"}},
			{{"TRINIDAD TOBAGO"}, {"TT"}},
			{{"TUNISIA"}, {"TN"}},
			{{"TURKEY"}, {"TR"}},
			{{"TURKMENISTAN"}, {"TM"}},
			{{"TURKS CAICOS ISLANDS"}, {"TC"}},
			{{"TUVALU"}, {"TV"}},
			{{"UGANDA"}, {"UG"}},
			{{"UKRAINE"}, {"UA"}},
			{{"UNITED ARAB EMIRATES"}, {"AE"}},
			{{"UNITED KINGDOM"}, {"GB"}},
			{{"UNITED STATES"}, {"US"}},
			{{"UNITED STATES MINOR OUTLYING ISLANDS"}, {"UM"}},
			{{"URUGUAY"}, {"UY"}},
			{{"UZBEKISTAN"}, {"UZ"}},
			{{"VANUATU"}, {"VU"}},
			{{"VENEZUELA"}, {"VE"}},
			{{"VIET NAM"}, {"VN"}},
			{{"VIRGIN ISLANDS, BRITISH"}, {"VG"}},
			{{"VIRGIN ISLANDS, U.S."}, {"VI"}},
			{{"WALLIS FUTUNA"}, {"WF"}},
			{{"WESTERN SAHARA"}, {"EH"}},
			{{"YEMEN"}, {"YE"}},
			{{"ZAMBIA"}, {"ZM"}},
			{{"ZIMBABWE"}, {"ZW"}}};
			/**
			 * Vatican City XlsState see HOLY SEE
			 * Zaire see CONGO, DEMOCRATIC REPUBLIC OF
			 */
		XlsPlaceName.addNameList(elements);
    }
	
	public static Country getPlace() {return new Country();}
}
