package ws.daley.genealogy.db.structure;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ws.daley.genealogy.db.record.SubRecord;
import ws.daley.genealogy.util.Util;

/**
 * 
 * @author Tim.Daley
 *
 *	ADDRESS_STRUCTURE:=
		n ADDR <ADDRESS_LINE> {1:1} p.41
			+1 CONT <ADDRESS_LINE> {0:3} p.41
			+1 ADR1 <ADDRESS_LINE1> {0:1} p.41
			+1 ADR2 <ADDRESS_LINE2> {0:1} p.41
			+1 ADR3 <ADDRESS_LINE3> {0:1} p.41
			+1 CITY <ADDRESS_CITY> {0:1} p.41
			+1 STAE <ADDRESS_STATE> {0:1} p.42
			+1 POST <ADDRESS_POSTAL_CODE> {0:1} p.41
			+1 CTRY <ADDRESS_COUNTRY> {0:1} p.41
		n PHON <PHONE_NUMBER> {0:3} p.57
		n EMAIL <ADDRESS_EMAIL> {0:3} p.41
		n FAX <ADDRESS_FAX> {0:3} p.41
		n WWW <ADDRESS_WEB_PAGE> {0:3} p.42
 */
public class AddressStructure extends SubRecord
{
    private static final long serialVersionUID = 1L;

    private String addressLine;
    public String getAddressLine() {return this.addressLine;}
    public void setAddressLine(String addressLine) {this.addressLine = addressLine;}

    private String addressLine1;
    public String getAddressLine1() {return this.addressLine1;}
    public void setAddressLine1(String addressLine1) {this.addressLine1 = addressLine1;}

    private String addressLine2;
    public String getAddressLine2() {return this.addressLine2;}
    public void setAddressLine2(String addressLine2) {this.addressLine2 = addressLine2;}

    private String addressLine3;
    public String getAddressLine3() {return this.addressLine3;}
    public void setAddressLine3(String addressLine3) {this.addressLine3 = addressLine3;}

    private String addressCity;
    public String getAddressCity() {return this.addressCity;}
    public void setAddressCity(String addressCity) {this.addressCity = addressCity;}

    private String addressState;
    public String getAddressState() {return this.addressState;}
    public void setAddressState(String addressState) {this.addressState = addressState;}

    private String addressPostalCode;
    public String getAddressPostalCode() {return this.addressPostalCode;}
    public void setAddressPostalCode(String addressPostalCode) {this.addressPostalCode = addressPostalCode;}

    private String addressCountry;
    public String getAddressCountry() {return this.addressCountry;}
    public void setAddressCountry(String addressCountry) {this.addressCountry = addressCountry;}

    private String phoneNumber1;
    private String phoneNumber2;
    private String phoneNumber3;
    public List<String> getPhoneNumberStructures()
    {
    	List<String> phoneNumbers = new ArrayList<String>();
    	if (this.phoneNumber1 != null)
    		phoneNumbers.add(this.phoneNumber1);
    	if (this.phoneNumber2 != null)
    		phoneNumbers.add(this.phoneNumber2);
    	if (this.phoneNumber3 != null)
    		phoneNumbers.add(this.phoneNumber3);
    	return phoneNumbers;
    }
    public void addPhoneNumberStructure(String phoneNumber)
    {
    	if (this.phoneNumber1 == null)
    		this.phoneNumber1 = phoneNumber;
    	else if (this.phoneNumber2 == null)
    		this.phoneNumber2 = phoneNumber;
    	else if (this.phoneNumber3 == null)
    		this.phoneNumber3 = phoneNumber;
    }

    private String addressEmail1;
    private String addressEmail2;
    private String addressEmail3;
    public List<String> getAddressEmailStructures()
    {
    	List<String> addressEmails = new ArrayList<String>();
    	if (this.addressEmail1 != null)
        	addressEmails.add(this.addressEmail1); 
    	if (this.addressEmail2 != null)
        	addressEmails.add(this.addressEmail2); 
    	if (this.addressEmail3 != null)
        	addressEmails.add(this.addressEmail3); 
    	return addressEmails;
    }
    public void addAddressEmailStructure(String addressEmail)
    {
    	if (this.addressEmail1 == null)
    		this.addressEmail1 = addressEmail;
    	else if (this.addressEmail2 == null)
    		this.addressEmail2 = addressEmail;
    	else if (this.addressEmail3 == null)
    		this.addressEmail3 = addressEmail;
    }

    private String addressFax1;
    private String addressFax2;
    private String addressFax3;
    public List<String> getAddressFaxStructures()
    {
    	List<String> addressFaxes = new ArrayList<String>();
    	if (this.addressFax1 != null)
    		addressFaxes.add(this.addressFax1);
    	if (this.addressFax2 != null)
    		addressFaxes.add(this.addressFax2);
    	if (this.addressFax3 != null)
    		addressFaxes.add(this.addressFax3);
    	return addressFaxes;
    }
    public void addAddressFaxStructure(String addressFax)
    {
    	if (this.addressFax1 == null)
    		this.addressFax1 = addressFax;
    	else if (this.addressFax2 == null)
    		this.addressFax2 = addressFax;
    	else if (this.addressFax3 == null)
    		this.addressFax3 = addressFax;
    }

    private String addressWWW1;
    private String addressWWW2;
    private String addressWWW3;
    public List<String> getAddressWWWStructures()
    {
    	List<String> addressWWWs = new ArrayList<String>();
    	if (this.addressWWW1 != null)
    		addressWWWs.add(this.addressWWW1);
    	if (this.addressWWW2 != null)
    		addressWWWs.add(this.addressWWW2);
    	if (this.addressWWW3 != null)
    		addressWWWs.add(this.addressWWW3);
    	return addressWWWs;
    }
    public void addAddressWWWStructure(String addressWWW)
    {
    	if (this.addressWWW1 == null)
    		this.addressWWW1 = addressWWW;
    	else if (this.addressWWW2 == null)
    		this.addressWWW2 = addressWWW;
    	else if (this.addressWWW3 == null)
    		this.addressWWW3 = addressWWW;
    }
    
    public AddressStructure() {}
    
    public AddressStructure(Node node)
    {
    	super(node);
    	this.setAddressLine(Util.getNodeValue(node));
		NodeList nodeList = node.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i ++)
		{
			Node childNode = nodeList.item(i);
			if ("CONT".equals(childNode.getNodeName()))
				this.setAddressLine(Util.addContToValue(this.getAddressLine(), childNode, true));
			else if ("CONC".equals(childNode.getNodeName()))
				this.setAddressLine(Util.addContToValue(this.getAddressLine(), childNode, false));
			else if ("ADR1".equals(childNode.getNodeName()))
				this.setAddressLine1(Util.getNodeValue(childNode));
			else if ("ADR2".equals(childNode.getNodeName()))
				this.setAddressLine2(Util.getNodeValue(childNode));
			else if ("ADR3".equals(childNode.getNodeName()))
				this.setAddressLine3(Util.getNodeValue(childNode));
			else if ("CITY".equals(childNode.getNodeName()))
				this.setAddressCity(Util.getNodeValue(childNode));
			else if ("STAE".equals(childNode.getNodeName()))
				this.setAddressState(Util.getNodeValue(childNode));
			else if ("POST".equals(childNode.getNodeName()))
				this.setAddressPostalCode(Util.getNodeValue(childNode));
			else if ("CTRY".equals(childNode.getNodeName()))
				this.setAddressCountry(Util.getNodeValue(childNode));
			else if ("PHON".equals(childNode.getNodeName()))
				this.addPhoneNumberStructure(Util.getNodeValue(childNode));
			else if ("EMAIL".equals(childNode.getNodeName()))
				this.addAddressEmailStructure(Util.getNodeValue(childNode));
			else if ("FAX".equals(childNode.getNodeName()))
				this.addAddressFaxStructure(Util.getNodeValue(childNode));
			else if ("WWW".equals(childNode.getNodeName()))
				this.addAddressWWWStructure(Util.getNodeValue(childNode));
			else if ("#text".equals(childNode.getNodeName()))
				;
			else
				throw new RuntimeException(childNode.getNodeName()+" not processed in "+this.getClass().getSimpleName());
		}
    }
}
