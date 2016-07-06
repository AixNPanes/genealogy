package ws.daley.genealogy.gedcom.records;

import java.util.ArrayList;

import ws.daley.genealogy.gedcom.SyntaxException;
import ws.daley.genealogy.gedcom.gedcomrecords.GedcomRecord;
import ws.daley.genealogy.gedcom.structures.AssociationStructure;
import ws.daley.genealogy.gedcom.structures.ChangeDate;
import ws.daley.genealogy.gedcom.structures.ChildToFamilyLink;
import ws.daley.genealogy.gedcom.structures.EventDetail;
import ws.daley.genealogy.gedcom.structures.IndiRef;
import ws.daley.genealogy.gedcom.structures.IndividualAttributeStructure;
import ws.daley.genealogy.gedcom.structures.IndividualEventStructureEVEN2;
import ws.daley.genealogy.gedcom.structures.IndividualEventStructureEVEN3;
import ws.daley.genealogy.gedcom.structures.LDSIndividualOrdinance1;
import ws.daley.genealogy.gedcom.structures.LDSIndividualOrdinance2;
import ws.daley.genealogy.gedcom.structures.PersonalNameStructure;
import ws.daley.genealogy.gedcom.structures.SourceCitation;
import ws.daley.genealogy.gedcom.structures.SpouseToFamilyLink;
import ws.daley.genealogy.gedcom.structures.UserReferenceNumber;

public class IndividualRec extends GedcomXMLRecord
	{
		/**
n @<XREF:INDI>@  INDI {1:1}
    +1 RESN <RESTRICTION_NOTICE>  {0:1}
    +1 <<PERSONAL_NAME_STRUCTURE>>  {0:M}
    +1 SEX <SEX_VALUE>   {0:1}
    +1 <<INDIVIDUAL_EVENT_STRUCTURE>>  {0:M}
    +1 <<INDIVIDUAL_ATTRIBUTE_STRUCTURE>>  {0:M}
    +1 <<LDS_INDIVIDUAL_ORDINANCE>>  {0:M}
    +1 <<CHILD_TO_FAMILY_LINK>>  {0:M}
    +1 <<SPOUSE_TO_FAMILY_LINK>>  {0:M}
    +1 SUBM @<XREF:SUBM>@  {0:M}
    +1 <<ASSOCIATION_STRUCTURE>>  {0:M}
    +1 ALIA @<XREF:INDI>@  {0:M}
    +1 ANCI @<XREF:SUBM>@  {0:M}
    +1 DESI @<XREF:SUBM>@  {0:M}
    +1 <<SOURCE_CITATION>>  {0:M}
    +1 <<MULTIMEDIA_LINK>>  {0:M}
    +1 <<NOTE_STRUCTURE>>  {0:M}
    +1 RFN <PERMANENT_RECORD_FILE_NUMBER>  {0:1}
    +1 AFN <ANCESTRAL_FILE_NUMBER>  {0:1}
    +1 REFN <USER_REFERENCE_NUMBER>  {0:M}
      +2 TYPE <USER_REFERENCE_TYPE>  {0:1}
    +1 RIN <AUTOMATED_RECORD_ID>  {0:1}
    +1 <<CHANGE_DATE>>  {0:1}
    */
		/**
		/**
  n[ BIRT | CHR ] [Y|<NULL>]  {1:1}
    +1 <<EVENT_DETAIL>>  {0:1}
    +1 FAMC @<XREF:FAM>@  {0:1}
    |
  n  ADOP [Y|<NULL>]  {1:1}
    +1 <<EVENT_DETAIL>>  {0:1}
    +1 FAMC @<XREF:FAM>@  {0:1}
      +2 ADOP <ADOPTED_BY_WHICH_PARENT>  {0:1}
    |
  n  [ DEAT | BURI | CREM | BAPM | BARM | BASM | BLES | CHRA | CONF | FCOM | ORDN | NATU | EMIG | IMMI | CENS | PROB | WILL | GRAD | RETI ] [Y|<NULL>]   {1:1}
    +1 <<EVENT_DETAIL>>  {0:1}
    |
  n  EVEN          {1:1}
    +1 <<EVENT_DETAIL>>  {0:1}
		 */
		/**
  n  CAST <CASTE_NAME>   {1:1}
    +1 <<EVENT_DETAIL>>  {0:1}
  |
  n  DSCR <PHYSICAL_DESCRIPTION>   {1:1}
    +1 <<EVENT_DETAIL>>  {0:1}
  |
  n  EDUC <SCHOLASTIC_ACHIEVEMENT>   {1:1}
    +1 <<EVENT_DETAIL>>  {0:1}
  |
  n  IDNO <NATIONAL_ID_NUMBER>   {1:1}*
    +1 <<EVENT_DETAIL>>  {0:1}
  |
  n  NATI <NATIONAL_OR_TRIBAL_ORIGIN>   {1:1}
    +1 <<EVENT_DETAIL>>  {0:1}
  |
  n  NCHI <COUNT_OF_CHILDREN>   {1:1}
    +1 <<EVENT_DETAIL>>  {0:1}
  |
  n  NMR <COUNT_OF_MARRIAGES>   {1:1}
    +1 <<EVENT_DETAIL>>  {0:1}
  |
  n  OCCU <OCCUPATION>   {1:1}
    +1 <<EVENT_DETAIL>>  {0:1}
  |
  n  PROP <POSSESSIONS>   {1:1}
    +1 <<EVENT_DETAIL>>  {0:1}
  |
  n  RELI <RELIGIOUS_AFFILIATION>   {1:1}
    +1 <<EVENT_DETAIL>>  {0:1}
  |
  n  RESI           {1:1}  
    +1 <<EVENT_DETAIL>>  {0:1}
  |
  n  SSN <SOCIAL_SECURITY_NUMBER>   {0:1}
    +1 <<EVENT_DETAIL>>  {0:1}
  |
  n  TITL <NOBILITY_TYPE_TITLE>  {1:1}
    +1 <<EVENT_DETAIL>>  {0:1}		 */
		public GedcomRecord resn = null;
		public ArrayList<PersonalNameStructure> name = new ArrayList<PersonalNameStructure>();
		public GedcomRecord sex = null;
		public ArrayList<EventDetail> event = new ArrayList<EventDetail>();
		public ArrayList<IndividualAttributeStructure> attr = new ArrayList<IndividualAttributeStructure>();
		public ArrayList<LDSIndividualOrdinance1> bapl = new ArrayList<LDSIndividualOrdinance1>();
		public ArrayList<LDSIndividualOrdinance1> conl = new ArrayList<LDSIndividualOrdinance1>();
		public ArrayList<LDSIndividualOrdinance1> endl = new ArrayList<LDSIndividualOrdinance1>();
		public ArrayList<LDSIndividualOrdinance2> slgc = new ArrayList<LDSIndividualOrdinance2>();
		public ArrayList<ChildToFamilyLink> childToFamilyLink = new ArrayList<ChildToFamilyLink>();
		public ArrayList<SpouseToFamilyLink> spouseToFamilyLink = new ArrayList<SpouseToFamilyLink>();
		public ArrayList<SubmRef> submRef = new ArrayList<SubmRef>();
		public ArrayList<AssociationStructure> association = new ArrayList<AssociationStructure>();
		public ArrayList<GedcomRecord> alia = new ArrayList<GedcomRecord>();
		public ArrayList<GedcomRecord> anci = new ArrayList<GedcomRecord>();
		public ArrayList<GedcomRecord> desi = new ArrayList<GedcomRecord>();
		public ArrayList<IndiRef> indiRef = new ArrayList<IndiRef>();
		public ArrayList<SubmRef> anciRef = new ArrayList<SubmRef>();
		public ArrayList<SubmRef> desiRef = new ArrayList<SubmRef>();
		public ArrayList<SourceCitation> sourceCitation = new ArrayList<SourceCitation>();
		public ArrayList<MultimediaLink> multimediaLink = new ArrayList<MultimediaLink>();
		public ArrayList<NoteStructure> noteStructure = new ArrayList<NoteStructure>();
		public GedcomRecord rfn = null;
		public GedcomRecord afn = null;
		public ArrayList<UserReferenceNumber> refn = new ArrayList<UserReferenceNumber>();
		public GedcomRecord rin = null;
		public ChangeDate chan;
		public IndividualEventStructureEVEN3 birt;
		public IndividualEventStructureEVEN3 chr;
		public IndividualEventStructureEVEN3 adop;
		public IndividualEventStructureEVEN2 deat;
		public IndividualEventStructureEVEN2 buri;
		public IndividualEventStructureEVEN2 crem;
		public IndividualEventStructureEVEN2 bapm;
		public IndividualEventStructureEVEN2 barm;
		public IndividualEventStructureEVEN2 basm;
		public IndividualEventStructureEVEN2 bles;
		public IndividualEventStructureEVEN2 chra;
		public IndividualEventStructureEVEN2 conf;
		public IndividualEventStructureEVEN2 fcom;
		public IndividualEventStructureEVEN2 ordn;
		public IndividualEventStructureEVEN2 natu;
		public IndividualEventStructureEVEN2 emig;
		public IndividualEventStructureEVEN2 immi;
		public IndividualEventStructureEVEN2 cens;
		public IndividualEventStructureEVEN2 prob;
		public IndividualEventStructureEVEN2 will;
		public IndividualEventStructureEVEN2 grad;
		public IndividualEventStructureEVEN2 reti;
		public EventDetail even;
		public EventDetail cast;
		public EventDetail dscr;
		public EventDetail educ;
		public EventDetail idno;
		public EventDetail nati;
		public EventDetail nchi;
		public EventDetail nmr;
		public EventDetail occu;
		public EventDetail prop;
		public EventDetail reli;
		public EventDetail resi;
		public EventDetail ssn;
		public EventDetail titl;
		public ArrayList<GedcomRecord> _photo = new ArrayList<GedcomRecord>();
		public ArrayList<GedcomRecord> _milt = new ArrayList<GedcomRecord>();
		public IndividualRec(GedcomRecord record)
		{
			super(record);
			for(GedcomRecord subRecord:record.records)
			{
				switch(subRecord.tag)
				{
					case "RESN": this.resn = subRecord; break;
					case "NAME": this.name.add(new PersonalNameStructure(subRecord)); break;
					case "SEX": this.sex = subRecord; break;
					case "EVEN": this.event.add(new EventDetail(subRecord)); break;
					case "ATTR": this.attr.add(new IndividualAttributeStructure(subRecord)); break;
					case "BAPL": this.bapl.add(new LDSIndividualOrdinance1(subRecord)); break;
					case "CONL": this.conl.add(new LDSIndividualOrdinance1(subRecord)); break;
					case "ENDL": this.endl.add(new LDSIndividualOrdinance1(subRecord)); break;
					case "SLGC": this.slgc.add(new LDSIndividualOrdinance2(subRecord)); break;
					case "FAMC": this.childToFamilyLink.add(new ChildToFamilyLink(subRecord)); break;
					case "FAMS": this.spouseToFamilyLink.add(new SpouseToFamilyLink(subRecord)); break;
					case "SUBM": this.submRef.add(new SubmRef(subRecord)); break;
					case "ASSO": this.association.add(new AssociationStructure(subRecord)); break;
					case "INDI": this.indiRef.add(new IndiRef(subRecord)); break;
					case "ALIA": this.alia.add(subRecord); break;
					case "ANCI": this.anciRef.add(new SubmRef(subRecord)); break;
					case "DESI": this.desiRef.add(new SubmRef(subRecord)); break;
					case "SOUR": this.sourceCitation.add(new SourceCitation(subRecord)); break;
					case "OBJE": this.multimediaLink.add(new MultimediaLink(subRecord)); break;
					case "NOTE": this.noteStructure.add(new NoteStructure(subRecord)); break;
					case "RFN": this.rfn = subRecord; break;
					case "AFN": this.afn = subRecord; break;
					case "REFN": this.refn.add(new UserReferenceNumber(subRecord)); break;
					case "RIN": this.rin = subRecord; break;
					case "CHAN": this.chan = new ChangeDate(subRecord); break;
					case "BIRT": this.birt = new IndividualEventStructureEVEN3(subRecord); break;
					case "CHR": this.chr = new IndividualEventStructureEVEN3(subRecord); break;
					case "ADOP": this.adop = new IndividualEventStructureEVEN3(subRecord); break;
					case "DEAT": this.deat = new IndividualEventStructureEVEN2(subRecord); break;
					case "BURI": this.buri = new IndividualEventStructureEVEN2(subRecord); break;
					case "CREM": this.crem = new IndividualEventStructureEVEN2(subRecord); break;
					case "BAPM": this.bapm = new IndividualEventStructureEVEN2(subRecord); break;
					case "BARM": this.barm = new IndividualEventStructureEVEN2(subRecord); break;
					case "BASM": this.basm = new IndividualEventStructureEVEN2(subRecord); break;
					case "BLES": this.basm = new IndividualEventStructureEVEN2(subRecord); break;
					case "CHRA": this.chra = new IndividualEventStructureEVEN2(subRecord); break;
					case "CONF": this.conf = new IndividualEventStructureEVEN2(subRecord); break;
					case "FCOM": this.fcom = new IndividualEventStructureEVEN2(subRecord); break;
					case "ORDN": this.ordn = new IndividualEventStructureEVEN2(subRecord); break;
					case "NATU": this.natu = new IndividualEventStructureEVEN2(subRecord); break;
					case "EMIG": this.emig = new IndividualEventStructureEVEN2(subRecord); break;
					case "IMMI": this.immi = new IndividualEventStructureEVEN2(subRecord); break;
					case "CENS": this.cens = new IndividualEventStructureEVEN2(subRecord); break;
					case "PROB": this.prob = new IndividualEventStructureEVEN2(subRecord); break;
					case "WILL": this.will = new IndividualEventStructureEVEN2(subRecord); break;
					case "GRAD": this.grad = new IndividualEventStructureEVEN2(subRecord); break;
					case "RETI": this.reti = new IndividualEventStructureEVEN2(subRecord); break;
					case "CAST": this.cast = new EventDetail(subRecord); break;
					case "DSCR": this.dscr = new EventDetail(subRecord); break;
					case "EDUC": this.educ = new EventDetail(subRecord); break;
					case "IDNO": this.idno = new EventDetail(subRecord); break;
					case "NATI": this.nati = new EventDetail(subRecord); break;
					case "NCHI": this.nchi = new EventDetail(subRecord); break;
					case "NRM": this.nmr = new EventDetail(subRecord); break;
					case "OCCU": this.occu = new EventDetail(subRecord); break;
					case "PROP": this.prop = new EventDetail(subRecord); break;
					case "RELI": this.reli = new EventDetail(subRecord); break;
					case "RESI": this.resi = new EventDetail(subRecord); break;
					case "SSN": this.ssn = new EventDetail(subRecord); break;
					case "TITL": this.titl = new EventDetail(subRecord); break;
					case "_PHOTO": this._photo.add(subRecord); break;
					case "_MILT": this._milt.add(subRecord); break;
					default: throw new SyntaxException(record, subRecord);
				}
			}
		}
		@Override
		public String toString()
		{
			String ret = " <IndividualRec Id=\"" + this.link + "\">\n" +
					"  <IndivName>\n";
			String subName = "";
			if (this.titl != null && this.titl.data != null)
				subName += "<NamePart type=\"title\">" + this.titl.data.trim() + "</NamePart>";
			if (this.name != null)
				for(PersonalNameStructure namePart: this.name)
					subName += namePart.toString();
			if (subName.length() > 0)
				ret += "   " + subName + "\n";
			if (this.sex != null)
				if ("M".equals(this.sex.lineValue))
					ret += "  <Gender>M</Gender>\n";
				else if ("F".equals(this.sex.lineValue))
					ret += "  <Gender>F</Gender>\n";
			if (this.noteStructure != null)
			{
				for(NoteStructure notes:this.noteStructure)
				{
					String noteStr = "<NoteStructure>" + (notes.data==null?"":notes.data);
					if (notes.conc.size() > 0)
					{
						for(GedcomRecord gedcomRecord:notes.conc)
						{
							if ("CONT".equals(gedcomRecord.tag))
								noteStr += "\n";
							noteStr += gedcomRecord.lineValue;
						}
					}
					noteStr += "</NoteStructure>";
					ret += "  " + noteStr + "\n";
				}
			}
			ret += "  <IndivName>\n";
			ret += " </IndividualRec>";
					return ret;
		}
	}