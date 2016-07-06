package ws.daley.genealogy.gedcom.structures;

import ws.daley.genealogy.gedcom.gedcomrecords.GedcomRecord;
import ws.daley.genealogy.gedcom.records.GedcomXMLRecord;

public class IndividualAttributeStructure extends GedcomXMLRecord
	{
		/**
EVENT_DETAIL: = 
  n  TYPE <EVENT_DESCRIPTOR>  {0:1}
  n  DATE <DATE_VALUE>  {0:1}
  n  <<PLACE_STRUCTURE>>  {0:1}
  n  <<ADDRESS_STRUCTURE>>  {0:1}
  n  AGE <AGE_AT_EVENT>  {0:1}
  n  AGNC <RESPONSIBLE_AGENCY>  {0:1}
  n  CAUS <CAUSE_OF_EVENT>  {0:1}
  n  <<SOURCE_CITATION>>  {0:M}
    +1 <<NOTE_STRUCTURE>>  {0:M}
    +1 <<MULTIMEDIA_LINK>>  {0:M}
  n  <<MULTIMEDIA_LINK>>  {0:M}
  n  <<NOTE_STRUCTURE>>  {0:M}
  		 */
		
		public IndividualAttributeStructure(GedcomRecord record)
		{
			super(record);
			throw new RuntimeException();
		}
	}