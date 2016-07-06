package ws.daley.genealogy.db.structure.sourcecitation;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ws.daley.genealogy.db.record.GedRecord;
import ws.daley.genealogy.db.structure.sourcecitation.data.SourceCitationDataTextStructure;
import ws.daley.genealogy.util.Util;

/**
 * 
 * @author Tim.Daley
 *
 *				SOURCE_CITATION:=
				[ 
				n SOUR @<XREF:SOUR>@     pointer to source record 						{1:1} 
					+1 PAGE <WHERE_WITHIN_SOURCE>										{0:1} 
					+1 EVEN <EVENT_TYPE_CITED_FROM>										{0:1} 
						+2 ROLE <ROLE_IN_EVENT>											{0:1} 
					+1 DATA																{0:1} 
						+2 DATE <ENTRY_RECORDING_DATE>									{0:1} 
						+2 TEXT <TEXT_FROM_SOURCE>										{0:M} 
							+3 [ CONC | CONT ] <TEXT_FROM_SOURCE>						{0:M} 
					+1 QUAY <CERTAINTY_ASSESSMENT>										{0:1} 
					+1 <<MULTIMEDIA_LINK>>												{0:M} 
					+1 <<NOTE_STRUCTURE>>												{0:M} 
				|	 Systems not using source records 
				n SOUR <SOURCE_DESCRIPTION>												{1:1} 
					+1 [ CONC | CONT ] <SOURCE_DESCRIPTION>								{0:M} 
					+1 TEXT <TEXT_FROM_SOURCE>											{0:M} 
						+2 [CONC | CONT ] <TEXT_FROM_SOURCE>							{0:M} 
					+1 <<NOTE_STRUCTURE>>												{0:M} 
				]

 */

public class SourceCitationDataStructure extends GedRecord
{
    private static final long serialVersionUID = 1L;

	private String date;
    public String getDate() {return this.date;}
    public void setDate(String date) {this.date = date;}

    private String text;
    public String getText() {return this.text;}
    public void setText(String text) {this.text = text;}
    
    public SourceCitationDataStructure() {}
    
    public SourceCitationDataStructure(Node node)
    {
    	super(node);
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i ++)
		{
			Node childNode = nodeList.item(i);
			if ("DATE".equals(childNode.getNodeName()))
				this.setDate(Util.getNodeValue(childNode));
			else if ("TEXT".equals(childNode.getNodeName()))
				this.setText((new SourceCitationDataTextStructure(childNode)).getRecordValue());
			else if ("#text".equals(childNode.getNodeName()))
				;
			else
				throw new RuntimeException(childNode.getNodeName()+" not processed in "+this.getClass().getSimpleName());
		}
    }
}
