package ws.daley.genealogy.gedcom.object;


public class GcTags
{
	public static enum TAG {
		TAG_UNDEFINED,
		TAG_ABBR,TAG_ADDR,TAG_ADR1,TAG_ADR2,TAG_ADOP,TAG_AFN ,TAG_AGE ,TAG_AGNC,
		TAG_ALIA,TAG_ANCE,TAG_ANCI,TAG_ANUL,TAG_ARVL,TAG_ASSO,TAG_AUTH,TAG_BAPL,TAG_BAPM,
		TAG_BARM,TAG_BASM,TAG_BIRT,TAG_BLES,TAG_BLOB,TAG_BURI,TAG_CALN,TAG_CAST,
		TAG_CAUS,TAG_CENS,TAG_CHAN,TAG_CHAR,TAG_CHIL,TAG_CHR ,TAG_CHRA,TAG_CITY,
		TAG_CONC,TAG_CONF,TAG_CONL,TAG_CONT,TAG_COPR,TAG_CORP,TAG_CREM,TAG_CTRY,
		TAG_DATA,TAG_DATE,TAG_DEAT,TAG_DESC,TAG_DESI,TAG_DEST,TAG_DIV ,TAG_DIVF,
		TAG_DSCR,TAG_EDUC,TAG_EMIG,TAG_ENDL,TAG_ENGA,TAG_EVEN,TAG_FAM ,TAG_FAMC,
		TAG_FAMF,TAG_FAMS,TAG_FCOM,TAG_FILE,TAG_FORM,TAG_GEDC,TAG_GIVN,TAG_GRAD,
		TAG_HEAD,TAG_HUSB,TAG_IDNO,TAG_IMMI,TAG_INDI,TAG_LANG,TAG_LEGA,TAG_MARB,
		TAG_MARC,TAG_MARL,TAG_MARR,TAG_MARS,TAG_MEDI,TAG_MILS,TAG_NAME,TAG_NATI,TAG_NATU,
		TAG_NCHI,TAG_NICK,TAG_NMR ,TAG_NOTE,TAG_NPFX,TAG_NSFX,TAG_OBJE,TAG_OCCU,
		TAG_ORDI,TAG_ORDN,TAG_PAGE,TAG_PEDI,TAG_PHON,TAG_PLAC,TAG_POST,TAG_PROB,
		TAG_PROP,TAG_PUBL,TAG_QUAY,TAG_REFN,TAG_RELA,TAG_RELI,TAG_REPO,TAG_RESI,
		TAG_RESN,TAG_RETI,TAG_RFN ,TAG_RIN ,TAG_ROLE,TAG_SEX ,TAG_SLGC,TAG_SLGS,
		TAG_SOUR,TAG_SPFX,TAG_SSN ,TAG_STAE,TAG_STAT,TAG_SUBM,TAG_SUBN,TAG_SURN,
		TAG_TEMP,TAG_TEXT,TAG_TIME,TAG_TITL,TAG_TRLR,TAG_TYPE,TAG_VERS,TAG_WIFE,
		TAG_WILL,
		TAG__MILT,TAG__FOOT,TAG__FREL,TAG__MREL,TAG_EMAIL
	}

	public static TAG getTag(String t)
	{
		String tagName = "TAG_" + t.trim();
		TAG[] tags = TAG.class.getEnumConstants();
		for(int i = 0; i < tags.length; i++)
		{
			if (tagName.equals(tags[i].toString()))
				return tags[i];
		}
		
		if ("ADDRESS".equals(tagName)) return TAG.TAG_ADDR;
		if ("ADOPTION".equals(tagName)) return TAG.TAG_ADOP;
		if ("AGENCY".equals(tagName)) return TAG.TAG_AGNC;
		if ("ALIAS".equals(tagName)) return TAG.TAG_ALIA;
		if ("ARRIVAL".equals(tagName)) return TAG.TAG_ANUL;
		if ("AUTHOR".equals(tagName)) return TAG.TAG_AUTH;
		if ("BAPTISM-LDS".equals(tagName)) return TAG.TAG_BAPL;
		if ("BAPTISM".equals(tagName)) return TAG.TAG_BAPM;
		if ("BIRTH".equals(tagName)) return TAG.TAG_BIRT;
		if ("BLESSING".equals(tagName)) return TAG.TAG_BLES;
		if ("BURIAL".equals(tagName)) return TAG.TAG_BURI;
		if ("CALL_NUMBER".equals(tagName)) return TAG.TAG_CALN;
		if ("CHARACTER".equals(tagName)) return TAG.TAG_CHAR;
		if ("CHILD".equals(tagName)) return TAG.TAG_CHIL;
		if ("CHRISTENING".equals(tagName)) return TAG.TAG_CHR;
		if ("CONCATENATION".equals(tagName)) return TAG.TAG_CONC;
		if ("CONTINUED".equals(tagName)) return TAG.TAG_CONT;
		if ("COPYRIGHT".equals(tagName)) return TAG.TAG_COPR;
		if ("CORPORATE".equals(tagName)) return TAG.TAG_CORP;
		if ("CREMATION".equals(tagName)) return TAG.TAG_CREM;
		if ("COUNTRY".equals(tagName)) return TAG.TAG_CTRY;
		if ("DEATH".equals(tagName)) return TAG.TAG_DEAT;
		if ("DESCRIPTION".equals(tagName)) return TAG.TAG_DESC;
		if ("DESTINATION".equals(tagName)) return TAG.TAG_DEST;
		if ("DIVORCE".equals(tagName)) return TAG.TAG_DIV;
		if ("EVENT".equals(tagName)) return TAG.TAG_EVEN;
		if ("FAMILY".equals(tagName)) return TAG.TAG_FAM;
		if ("FAMILY_CHILD".equals(tagName)) return TAG.TAG_FAMC;
		if ("FAMILY_SPOUSE".equals(tagName)) return TAG.TAG_FAMS;
		if ("GEDCOM".equals(tagName)) return TAG.TAG_GEDC;
		if ("HEADER".equals(tagName)) return TAG.TAG_HEAD;
		if ("HUSBAND".equals(tagName)) return TAG.TAG_HUSB;
		if ("INDIVIDUAL".equals(tagName)) return TAG.TAG_INDI;
		if ("LANGUAGE".equals(tagName)) return TAG.TAG_LANG;
		if ("MARRIAGE".equals(tagName)) return TAG.TAG_MARR;
		if ("MEDIA".equals(tagName)) return TAG.TAG_MEDI;
		if ("_MILITARY_SERVICE".equals(tagName)) return TAG.TAG_MEDI;
		if ("OBJECT".equals(tagName)) return TAG.TAG_OBJE;
		if ("OCCUPATION".equals(tagName)) return TAG.TAG_OCCU;
		if ("PEDIGREE".equals(tagName)) return TAG.TAG_PEDI;
		if ("PHONE".equals(tagName)) return TAG.TAG_PHON;
		if ("PLACE".equals(tagName)) return TAG.TAG_PLAC;
		if ("PROBATE".equals(tagName)) return TAG.TAG_PROB;
		if ("PUBLICATION".equals(tagName)) return TAG.TAG_PUBL;
		if ("REFERENCE".equals(tagName)) return TAG.TAG_REFN;
		if ("REPOSITORY".equals(tagName)) return TAG.TAG_REPO;
		if ("RESIDENCE".equals(tagName)) return TAG.TAG_RESI;
		if ("RESTRICTION".equals(tagName)) return TAG.TAG_RESN;
		if ("SOURCE".equals(tagName)) return TAG.TAG_SOUR;
		if ("SOC_SEC_NUMBER".equals(tagName)) return TAG.TAG_SSN;
		if ("SUBMITTER".equals(tagName)) return TAG.TAG_SUBM;
		if ("TITLE".equals(tagName)) return TAG.TAG_TITL;
		if ("TRAILER".equals(tagName)) return TAG.TAG_TRLR;
		if ("VERSION".equals(tagName)) return TAG.TAG_VERS;
		return TAG.TAG_UNDEFINED;
	}

	public static String getTagName(GcBaseElement e) {return e == null?"":getTagName(e.line);}
	public static String getTagName(GcInputLine l) {return l == null?"":getTagName(l.gcTag);}

	public static String getTagName(TAG t)
	{
		if (t == null)
			return "";
		return t.toString().substring(4);
	}
}
