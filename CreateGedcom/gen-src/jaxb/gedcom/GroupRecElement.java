//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.01.02 at 10:07:12 PM EST 
//


package jaxb.gedcom;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.AccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import jaxb.gedcom.ChangedElement;
import jaxb.gedcom.ContactElement;
import jaxb.gedcom.EnrichmentElement;
import jaxb.gedcom.EvidenceElement;
import jaxb.gedcom.ExternalIDElement;
import jaxb.gedcom.GroupRecElement;
import jaxb.gedcom.MailAddressElement;
import jaxb.gedcom.MemberElement;
import jaxb.gedcom.NameElement;
import jaxb.gedcom.NoteElement;
import jaxb.gedcom.ParentGroupElement;
import jaxb.gedcom.PhoneElement;
import jaxb.gedcom.SubmitterElement;


/**
 *  group record 
 * 
 * <p>Java class for GroupRec element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="GroupRec">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element ref="{}Name"/>
 *           &lt;element ref="{}Contact" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{}Member" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{}ParentGroup" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{}MailAddress" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{}Phone" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{}Email" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{}URI" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{}ExternalID" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{}Submitter" minOccurs="0"/>
 *           &lt;element ref="{}Note" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{}Evidence" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{}Enrichment" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{}Changed" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/sequence>
 *         &lt;attribute name="Id" use="required" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *         &lt;attribute name="Type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;/restriction>
 *     &lt;/complexContent>
 *   &lt;/complexType>
 * &lt;/element>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(AccessType.FIELD)
@XmlType(name = "", propOrder = {
    "name",
    "contact",
    "member",
    "parentGroup",
    "mailAddress",
    "phone",
    "email",
    "uri",
    "externalID",
    "submitter",
    "note",
    "evidence",
    "enrichment",
    "changed"
})
@XmlRootElement(name = "GroupRec")
public class GroupRecElement {

    @XmlElement(name = "Name")
    protected NameElement name;
    @XmlElement(name = "Contact")
    protected List<ContactElement> contact;
    @XmlElement(name = "Member")
    protected List<MemberElement> member;
    @XmlElement(name = "ParentGroup")
    protected List<ParentGroupElement> parentGroup;
    @XmlElement(name = "MailAddress")
    protected List<MailAddressElement> mailAddress;
    @XmlElement(name = "Phone")
    protected List<PhoneElement> phone;
    @XmlElement(name = "Email")
    protected List<String> email;
    @XmlElement(name = "URI")
    protected List<String> uri;
    @XmlElement(name = "ExternalID")
    protected List<ExternalIDElement> externalID;
    @XmlElement(name = "Submitter")
    protected SubmitterElement submitter;
    @XmlElement(name = "Note")
    protected List<NoteElement> note;
    @XmlElement(name = "Evidence")
    protected List<EvidenceElement> evidence;
    @XmlElement(name = "Enrichment")
    protected List<EnrichmentElement> enrichment;
    @XmlElement(name = "Changed")
    protected List<ChangedElement> changed;
    @XmlAttribute(name = "Id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    protected String id;
    @XmlAttribute(name = "Type")
    protected String type;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link NameElement }
     *     
     */
    public NameElement getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link NameElement }
     *     
     */
    public void setName(NameElement value) {
        this.name = value;
    }

    /**
     * Gets the value of the contact property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contact property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContact().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContactElement }
     * 
     * 
     */
    public List<ContactElement> getContact() {
        if (contact == null) {
            contact = new ArrayList<ContactElement>();
        }
        return this.contact;
    }

    /**
     * Gets the value of the member property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the member property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMember().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MemberElement }
     * 
     * 
     */
    public List<MemberElement> getMember() {
        if (member == null) {
            member = new ArrayList<MemberElement>();
        }
        return this.member;
    }

    /**
     * Gets the value of the parentGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the parentGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParentGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ParentGroupElement }
     * 
     * 
     */
    public List<ParentGroupElement> getParentGroup() {
        if (parentGroup == null) {
            parentGroup = new ArrayList<ParentGroupElement>();
        }
        return this.parentGroup;
    }

    /**
     * Gets the value of the mailAddress property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mailAddress property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMailAddress().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MailAddressElement }
     * 
     * 
     */
    public List<MailAddressElement> getMailAddress() {
        if (mailAddress == null) {
            mailAddress = new ArrayList<MailAddressElement>();
        }
        return this.mailAddress;
    }

    /**
     * Gets the value of the phone property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the phone property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPhone().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PhoneElement }
     * 
     * 
     */
    public List<PhoneElement> getPhone() {
        if (phone == null) {
            phone = new ArrayList<PhoneElement>();
        }
        return this.phone;
    }

    /**
     * Gets the value of the email property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the email property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getEmail() {
        if (email == null) {
            email = new ArrayList<String>();
        }
        return this.email;
    }

    /**
     * Gets the value of the uri property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the uri property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getURI().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getURI() {
        if (uri == null) {
            uri = new ArrayList<String>();
        }
        return this.uri;
    }

    /**
     * Gets the value of the externalID property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the externalID property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExternalID().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExternalIDElement }
     * 
     * 
     */
    public List<ExternalIDElement> getExternalID() {
        if (externalID == null) {
            externalID = new ArrayList<ExternalIDElement>();
        }
        return this.externalID;
    }

    /**
     * Gets the value of the submitter property.
     * 
     * @return
     *     possible object is
     *     {@link SubmitterElement }
     *     
     */
    public SubmitterElement getSubmitter() {
        return submitter;
    }

    /**
     * Sets the value of the submitter property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubmitterElement }
     *     
     */
    public void setSubmitter(SubmitterElement value) {
        this.submitter = value;
    }

    /**
     * Gets the value of the note property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the note property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNote().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NoteElement }
     * 
     * 
     */
    public List<NoteElement> getNote() {
        if (note == null) {
            note = new ArrayList<NoteElement>();
        }
        return this.note;
    }

    /**
     * Gets the value of the evidence property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the evidence property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEvidence().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EvidenceElement }
     * 
     * 
     */
    public List<EvidenceElement> getEvidence() {
        if (evidence == null) {
            evidence = new ArrayList<EvidenceElement>();
        }
        return this.evidence;
    }

    /**
     * Gets the value of the enrichment property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the enrichment property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEnrichment().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EnrichmentElement }
     * 
     * 
     */
    public List<EnrichmentElement> getEnrichment() {
        if (enrichment == null) {
            enrichment = new ArrayList<EnrichmentElement>();
        }
        return this.enrichment;
    }

    /**
     * Gets the value of the changed property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the changed property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChanged().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ChangedElement }
     * 
     * 
     */
    public List<ChangedElement> getChanged() {
        if (changed == null) {
            changed = new ArrayList<ChangedElement>();
        }
        return this.changed;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

}
