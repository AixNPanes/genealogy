package ws.daley.genealogy.gedcom.structures;

import ws.daley.genealogy.gedcom.SyntaxException;
import ws.daley.genealogy.gedcom.gedcomrecords.GedcomRecord;
import ws.daley.genealogy.gedcom.records.GedcomXMLRecord;

public class IndividualEventStructure extends GedcomXMLRecord
	{
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
		public IndividualEventStructureEVEN3 birt;
		public IndividualEventStructureEVEN3 chr;
		public IndividualEventStructureEVEN3 adop;
		public IndividualEventStructureEVEN2 deat;
		public IndividualEventStructureEVEN2 buri;
		public IndividualEventStructureEVEN2 crem;
		public IndividualEventStructureEVEN2 bapm;
		public IndividualEventStructureEVEN2 barm;
		public IndividualEventStructureEVEN2 basm;
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
		public IndividualEventStructure(GedcomRecord record)
		{
			super(record);
			for(GedcomRecord subRecord:record.records)
			{
				switch(subRecord.tag)
				{
					case "BIRT": this.birt = new IndividualEventStructureEVEN3(subRecord); break;
					case "CHR": this.chr = new IndividualEventStructureEVEN3(subRecord); break;
					case "ADOP": this.adop = new IndividualEventStructureEVEN3(subRecord); break;
					case "BURI": this.buri = new IndividualEventStructureEVEN2(subRecord); break;
					case "CREM": this.crem = new IndividualEventStructureEVEN2(subRecord); break;
					case "BAPM": this.bapm = new IndividualEventStructureEVEN2(subRecord); break;
					case "BARM": this.barm = new IndividualEventStructureEVEN2(subRecord); break;
					case "BASM": this.basm = new IndividualEventStructureEVEN2(subRecord); break;
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
					case "EVEN": this.even = new EventDetail(subRecord); break;
					default: throw new SyntaxException(record, subRecord);
				}
			}
		}
	}