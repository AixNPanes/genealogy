package ws.daley.genealogy.db.structure.source.repo;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ws.daley.genealogy.db.record.GedRecord;
import ws.daley.genealogy.util.Util;

/**
 * 
 * @author Tim.Daley
 *
 *	n REPO [ @XREF:REPO@ | <NULL>]			{1:1}
		+1 <<NOTE_STRUCTURE>>				{0:M}
		+1 CALN <SOURCE_CALL_NUMBER>		{0:M}
			+2 MEDI <SOURCE_MEDIA_TYPE>		{0:1}
 */

public class SourceRepoCallNumberStructure extends GedRecord
{
    private static final long serialVersionUID = 1L;

	private String sourceMediaType;
    public String getSourceMediaType() {return this.sourceMediaType;}
    public void setSourceMediaType(String sourceMediaType) {this.sourceMediaType = sourceMediaType;}
    
    public SourceRepoCallNumberStructure() {}
    
    public SourceRepoCallNumberStructure(Node node)
    {
    	super(node);
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i ++)
		{
			Node childNode = nodeList.item(i);
			if ("MEDI".equals(childNode.getNodeName()))
				this.setSourceMediaType(Util.getNodeValue(childNode));
			else if ("#text".equals(childNode.getNodeName()))
				;
			else
				throw new RuntimeException(childNode.getNodeName()+" not processed in "+this.getClass().getSimpleName());
		}
    }
}
