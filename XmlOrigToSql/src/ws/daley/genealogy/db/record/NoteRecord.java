package ws.daley.genealogy.db.record;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ws.daley.genealogy.db.Gedcom55;
import ws.daley.genealogy.db.structure.SourceCitationStructure;
import ws.daley.genealogy.db.structure.UserReferenceNumberStructure;
import ws.daley.genealogy.util.Util;

/**
 * 
 * @author Tim.Daley
 *
 *				NOTE_RECORD:=
				n @<XREF:NOTE>@ NOTE <SUBMITTER_TEXT>									{1:1} 
					+1 [ CONC | CONT] <SUBMITTER_TEXT>									{0:M} 
					+1 <<SOURCE_CITATION>>												{0:M} 
					+1 REFN <USER_REFERENCE_NUMBER>										{0:M}
						+2 TYPE <USER_REFERENCE_TYPE>									{0:1} 
					+1 RIN <AUTOMATED_RECORD_ID>										{0:1} 
					+1 <<CHANGE_DATE>>													{0:1}

 */

public class NoteRecord extends GedRecord
{
    private static final long serialVersionUID = 1L;
    
    private Gedcom55 gedcom55;
    public Gedcom55 getGedcom55() {return this.gedcom55;}
    public void setGedcom55(Gedcom55 gedcom55) {this.gedcom55 = gedcom55;}
    
    private List<SourceCitationStructure> sourceCitationStructures = new ArrayList<SourceCitationStructure>();
    public List<SourceCitationStructure> getSourceCitationStructures() {return this.sourceCitationStructures;}
    public void setSourceCitationStructures(List<SourceCitationStructure> sourceCitationStructures) {this.sourceCitationStructures = sourceCitationStructures;}
    public void addSourceCitationStructure(SourceCitationStructure sourceCitationStructure) {this.sourceCitationStructures.add(sourceCitationStructure);}
    
    private List<UserReferenceNumberStructure> userReferenceNumberStructures = new ArrayList<UserReferenceNumberStructure>();
    public List<UserReferenceNumberStructure> getUserReferenceNumberStructures() {return this.userReferenceNumberStructures;}
    public void setUserReferenceNumberStructures(List<UserReferenceNumberStructure> userReferenceNumberStructures) {this.userReferenceNumberStructures = userReferenceNumberStructures;}
    public void addUserReferenceNumberStructure(UserReferenceNumberStructure userReferenceNumberStructure) {this.userReferenceNumberStructures.add(userReferenceNumberStructure);}
    
    private String automatedRecordId;
    public String getAutomatedRecordId() {return this.automatedRecordId;}
    public void setAutomatedRecordId(String automatedRecordId) {this.automatedRecordId = automatedRecordId;}
    
    private String changeDate;
    public String getChangeDate() {return this.changeDate;}
    public void setChangeDate(String changeDate) {this.changeDate = changeDate;}
    
    public NoteRecord() {}
    
    public NoteRecord(Gedcom55 parent, Node node)
    {
    	super(node);
    	this.gedcom55 = parent;
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i ++)
		{
			Node childNode = nodeList.item(i);
			if ("CONT".equals(childNode.getNodeName()))
				this.setRecordValue(Util.addContToValue(this.getRecordValue(), childNode, true));
			else if ("CONC".equals(childNode.getNodeName()))
				this.setRecordValue(Util.addContToValue(this.getRecordValue(), childNode, true));
			else if ("SOUR".equals(childNode.getNodeName()))
				this.addSourceCitationStructure(new SourceCitationStructure(childNode));
			else if ("REFN".equals(childNode.getNodeName()))
				this.addUserReferenceNumberStructure(new UserReferenceNumberStructure(childNode));
			else if ("RIN".equals(childNode.getNodeName()))
				this.setAutomatedRecordId(Util.getNodeValue(childNode));
			else if ("DATE".equals(childNode.getNodeName()))
				this.setChangeDate(Util.getNodeValueWithChild(this.getClass(), childNode));
			else if ("#text".equals(childNode.getNodeName()))
				;
			else
				throw new RuntimeException(childNode.getNodeName()+" not processed in "+this.getClass().getSimpleName());
		}
    }
}
