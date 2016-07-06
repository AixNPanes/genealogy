package ws.daley.genealogy.db.record;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ws.daley.genealogy.db.Gedcom55;
import ws.daley.genealogy.db.structure.AddressStructure;
import ws.daley.genealogy.db.structure.MultimediaLinkStructure;
import ws.daley.genealogy.util.Util;

/**
 * 
 * @author Tim.Daley
 *
 *				SUBMITTER_RECORD:=

				n @<XREF:SUBM>@ SUBM													{1:1} 
					+1 NAME <SUBMITTER_NAME>											{1:1} 
					+1 <<ADDRESS_STRUCTURE>>											{0:1} 
					+1 <<MULTIMEDIA_LINK>>												{0:M} 
					+1 LANG <LANGUAGE_PREFERENCE>										{0:3} 
					+1 RFN <SUBMITTER_REGISTERED_RFN>									{0:1} 
					+1 RIN <AUTOMATED_RECORD_ID>										{0:1} 
					+1 <<CHANGE_DATE>>													{0:1}
 */

/**
 * 
 * @author Tim.Daley
 *
 *					SUBMITTER_RECORD:=

				n @<XREF:SUBM>@ SUBM													{1:1} 
					+1 NAME <SUBMITTER_NAME>											{1:1} 
					+1 <<ADDRESS_STRUCTURE>>											{0:1} 
					+1 <<MULTIMEDIA_LINK>>												{0:M} 
					+1 LANG <LANGUAGE_PREFERENCE>										{0:3} 
					+1 RFN <SUBMITTER_REGISTERED_RFN>									{0:1} 
					+1 RIN <AUTOMATED_RECORD_ID>										{0:1} 
					+1 <<CHANGE_DATE>>													{0:1}
 */

public class SubmitterRecord extends GedRecord
{
    private static final long serialVersionUID = 1L;
    
    private Gedcom55 gedcom55;
    public Gedcom55 getGedcom55() {return this.gedcom55;}
    public void setGedcom55(Gedcom55 gedcom55) {this.gedcom55 = gedcom55;}

	private String submitterName;
    public String getSubmitterName() {return this.submitterName;}
    public void setSubmitterName(String submitterName) {this.submitterName = submitterName;}

    private String submitterRegisteredRFN;
    public String getSubmitterRegisteredRFN() {return this.submitterRegisteredRFN;}
    public void setSubmitterRegisteredRFN(String submitterRegisteredRFN) {this.submitterRegisteredRFN = submitterRegisteredRFN;}

    private String automatedRecordId;
    public String getAutomatedRecordId() {return this.automatedRecordId;}
    public void setAutomatedRecordId(String automatedRecordIdStructure) {this.automatedRecordId = automatedRecordIdStructure;}

    private String changeDate;
    public String getChangeDate() {return this.changeDate;}
    public void setChangeDate(String changeDate) {this.changeDate = changeDate;}
    
    private AddressStructure addressStructure;
    public AddressStructure getAddressStructure() {return this.addressStructure;}
    public void setAddressStructure(AddressStructure addressStructure) {this.addressStructure = addressStructure;}

    private String submitterLanguagePreference1;
    public String getSubmitterLanguagePreference1() {return this.submitterLanguagePreference1;}
    public void setSubmitterLanguagePreference1(String languagePreference1) {this.submitterLanguagePreference1 = languagePreference1;}

    private String submitterLanguagePreference2;
    public String getSubmitterLanguagePreference2() {return this.submitterLanguagePreference2;}
    public void setSubmitterLanguagePreference2(String languagePreference2) {this.submitterLanguagePreference2 = languagePreference2;}

    private String submitterLanguagePreference3;
    public String getSubmitterLanguagePreference3() {return this.submitterLanguagePreference3;}
    public void setSubmitterLanguagePreference3(String languagePreference3) {this.submitterLanguagePreference3 = languagePreference3;}
    
    public List<String> getSubmitterLanguagePreferences()
    {
    	List<String> submitterLanguagePreferences = new ArrayList<String>();
    	if (this.submitterLanguagePreference1 != null)
    		submitterLanguagePreferences.add(this.submitterLanguagePreference1);
    	if (this.submitterLanguagePreference2 != null)
    		submitterLanguagePreferences.add(this.submitterLanguagePreference2);
    	if (this.submitterLanguagePreference3 != null)
    		submitterLanguagePreferences.add(this.submitterLanguagePreference3);
    	return submitterLanguagePreferences;
    }
    
    public void setSubmitterLanguagePreferences(List<String> submitterLanguagePreferences)
    {
    	switch(submitterLanguagePreferences.size())
    	{
    		case 3:
    			this.submitterLanguagePreference3 = submitterLanguagePreferences.get(2);
    		case 2:
    			this.submitterLanguagePreference3 = submitterLanguagePreferences.get(1);
    		case 1:
    			this.submitterLanguagePreference3 = submitterLanguagePreferences.get(0);
    		case 0:
    			break;
    		default:
    			throw new RuntimeException("too many elements in SubmitterLanguagePreferences");
    	}
    }
    
    public void addSubmitterLanguagePreference(String submitterLanguagePreference)
    {
    	if (this.submitterLanguagePreference1 == null)
    		this.submitterLanguagePreference1 = submitterLanguagePreference;
    	else if (this.submitterLanguagePreference2 == null)
    		this.submitterLanguagePreference2 = submitterLanguagePreference;
    	else if (this.submitterLanguagePreference3 == null)
    		this.submitterLanguagePreference3 = submitterLanguagePreference;
    }

    private List<MultimediaLinkStructure> multimediaLinkStructures = new ArrayList<MultimediaLinkStructure>();
    public List<MultimediaLinkStructure> getMultimediaLinkStructures() {return this.multimediaLinkStructures;}
    public void setMultimediaLinkStructures(List<MultimediaLinkStructure> multimediaLinkStructures) {this.multimediaLinkStructures = multimediaLinkStructures;}
    public void addMultimediaLinkStructure(MultimediaLinkStructure multimediaLinkStructure) {this.multimediaLinkStructures.add(multimediaLinkStructure);}

    public SubmitterRecord() {}
    
    public SubmitterRecord(Gedcom55 parent, Node node)
    {
    	super(node);
    	this.gedcom55 = parent;
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i ++)
		{
			Node childNode = nodeList.item(i);
			if ("NAME".equals(childNode.getNodeName()))
				this.setSubmitterName(Util.getNodeValue(childNode));
			else if ("ADDR".equals(childNode.getNodeName()))
				this.setAddressStructure(new AddressStructure(childNode));
			else if ("PHON".equals(childNode.getNodeName()))
				this.getAddressStructure().addPhoneNumberStructure(Util.getNodeValue(childNode));
			else if ("EMAIL".equals(childNode.getNodeName()))
				this.getAddressStructure().addAddressEmailStructure(Util.getNodeValue(childNode));
			else if ("FAX".equals(childNode.getNodeName()))
				this.getAddressStructure().addAddressFaxStructure(Util.getNodeValue(childNode));
			else if ("WWW".equals(childNode.getNodeName()))
				this.getAddressStructure().addAddressWWWStructure(Util.getNodeValue(childNode));
			else if ("OBJE".equals(childNode.getNodeName()))
				this.addMultimediaLinkStructure(new MultimediaLinkStructure(childNode));
			else if ("LANG".equals(childNode.getNodeName()))
				this.addSubmitterLanguagePreference(Util.getNodeValue(childNode));
			else if ("RFN".equals(childNode.getNodeName()))
				this.setSubmitterRegisteredRFN(Util.getNodeValue(childNode));
			else if ("RIN".equals(childNode.getNodeName()))
				this.setAutomatedRecordId(Util.getNodeValue(childNode));
			else if ("CHAN".equals(childNode.getNodeName()))
				this.setChangeDate(Util.getNodeValueWithChild(this.getClass(), childNode));
			else if ("#text".equals(childNode.getNodeName()))
				;
			else
				throw new RuntimeException(childNode.getNodeName()+" not processed in "+this.getClass().getSimpleName());
		}
    }
}
