package ws.daley.genealogy.db.structure;

import java.io.Serializable;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * 
 * @author Tim.Daley
 *
 *				INDIVIDUAL_RECORD:=

				n @XREF:INDI@ INDI														{1:1} 
					+1 RESN <RESTRICTION_NOTICE>										{0:1} 
					+1 <<PERSONAL_NAME_STRUCTURE>>										{0:M} 
					+1 SEX <SEX_VALUE> 													{0:1} 
					+1 <<INDIVIDUAL_EVENT_STRUCTURE>>									{0:M} 
					+1 <<INDIVIDUAL_ATTRIBUTE_STRUCTURE>>								{0:M}
					+1 <<LDS_INDIVIDUAL_ORDINANCE>>										{0:M} 
					+1 <<CHILD_TO_FAMILY_LINK>>											{0:M} 
					+1 <<SPOUSE_TO_FAMILY_LINK>>										{0:M} 
					+1 SUBM @<XREF:SUBM>@												{0:M} 
					+1 <<ASSOCIATION_STRUCTURE>>										{0:M} 
					+1 ALIA @<XREF:INDI>@												{0:M} 
					+1 ANCI @<XREF:SUBM>@												{0:M} 
					+1 DESI @<XREF:SUBM>@												{0:M} 
					+1 <<SOURCE_CITATION>>												{0:M} 
					+1 <<MULTIMEDIA_LINK>>												{0:M} 
					+1 <<NOTE_STRUCTURE>>												{0:M} 
					+1 RFN <PERMANENT_RECORD_FILE_NUMBER>								{0:1} 
					+1 AFN <ANCESTRAL_FILE_NUMBER>										{0:1} 
					+1 REFN <USER_REFERENCE_NUMBER>										{0:M}
						+2 TYPE <USER_REFERENCE_TYPE>									{0:1} 
					+1 RIN <AUTOMATED_RECORD_ID>										{0:1} 
					+1 <<CHANGE_DATE>>													{0:1}

 */

public class AliasToIndividualLinkStructure implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private static long key = -1l;
    
    private Long recordKey = ++key;
    public Long getRecordKey() {return this.recordKey;}
    public void setRecordKey(Long recordKey) {this.recordKey = recordKey;}
    
    private String aliasToIndividualLink;
    public String getAliasToIndividualLink() {return this.aliasToIndividualLink;}
    public void setAliasToIndividualLink(String aliasToIndividualLink) {this.aliasToIndividualLink = aliasToIndividualLink;}

	public AliasToIndividualLinkStructure() {}
    
    public AliasToIndividualLinkStructure(Node node)
    {
    	NamedNodeMap map = node.getAttributes();
    	Node attr = map.getNamedItem("value");
    	String val = null;
    	if (attr != null)
    		val = attr.getNodeValue();
    	if (val != null && val.length() != 0)
    		this.setAliasToIndividualLink(val);
    }
}
