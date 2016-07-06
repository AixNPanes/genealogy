package ws.daley.genealogy.db.structure.source.data;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ws.daley.genealogy.db.record.GedRecord;
import ws.daley.genealogy.util.Util;

/**
 * 
 * @author Tim.Daley
 *
 *				SOURCE_RECORD:=
				n @<XREF:SOUR>@ SOUR													{1:1} 
					+1 DATA																{0:1} 
						+2 EVEN <EVENTS_RECORDED>										{0:M} 
							+3 DATE <DATE_PERIOD>										{0:1} 
							+3 PLAC <SOURCE_JURISDICTION_PLACE>							{0:1} 
						+2 AGNC <RESPONSIBLE_AGENCY>									{0:1} 
						+2 <<NOTE_STRUCTURE>>											{0:M} 
					+1 AUTH <SOURCE_ORIGINATOR>											{0:1} 
						+2 [CONT|CONC] <SOURCE_ORIGINATOR>								{0:M} 
					+1 TITL <SOURCE_DESCRIPTIVE_TITLE>									{0:1} 
						+2 [CONT|CONC] <SOURCE_DESCRIPTIVE_TITLE>						{0:M} 
					+1 ABBR <SOURCE_FILED_BY_ENTRY>										{0:1} 
					+1 PUBL <SOURCE_PUBLICATION_FACTS>									{0:1} 
						+2 [CONT|CONC] <SOURCE_PUBLICATION_FACTS>						{0:M} 
					+1 TEXT <TEXT_FROM_SOURCE>											{0:1} 
						+2 [CONT|CONC] <TEXT_FROM_SOURCE>								{0:M} 
					+1 <<SOURCE_REPOSITORY_CITATION>>									{0:1} 
					+1 <<MULTIMEDIA_LINK>>												{0:M}
					+1 <<NOTE_STRUCTURE>>												{0:M} 
					+1 REFN <USER_REFERENCE_NUMBER>										{0:M}
						+2 TYPE <USER_REFERENCE_TYPE>									{0:1} 
					+1 RIN <AUTOMATED_RECORD_ID>										{0:1} 
					+1 <<CHANGE_DATE>>													{0:1}

 */

public class SourceDataEventsRecordedStructure extends GedRecord
{
    private static final long serialVersionUID = 1L;

    private String date;
    public String getDate() {return this.date;}
    public void setDate(String date) {this.date = date;}

    private String jurisdictionPlace;
    public String getJurisdictionPlace() {return this.jurisdictionPlace;}
    public void setJurisdictionPlace(String jurisdictionPlace) {this.jurisdictionPlace = jurisdictionPlace;}

	public SourceDataEventsRecordedStructure() {}
    
    public SourceDataEventsRecordedStructure(Node node)
    {
    	super(node);
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i ++)
		{
			Node childNode = nodeList.item(i);
			if ("DATE".equals(childNode.getNodeName()))
				this.setDate(Util.getNodeValue(childNode));
			if ("PLAC".equals(childNode.getNodeName()))
				this.setJurisdictionPlace(Util.getNodeValue(childNode));
			else if ("#text".equals(childNode.getNodeName()))
				;
			else
				throw new RuntimeException(childNode.getNodeName()+" not processed in "+this.getClass().getSimpleName());
		}
    }
}
