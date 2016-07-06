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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import jaxb.gedcom.CitationElement;
import jaxb.gedcom.ExtractElement;
import jaxb.gedcom.LinkElement;
import jaxb.gedcom.NoteElement;


/**
 * <p>Java class for Citation element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="Citation">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element ref="{}Link"/>
 *           &lt;element ref="{}Caption" minOccurs="0"/>
 *           &lt;element ref="{}WhereInSource" minOccurs="0"/>
 *           &lt;element ref="{}WhenRecorded" minOccurs="0"/>
 *           &lt;element ref="{}Extract" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element ref="{}Note" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;/sequence>
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
    "link",
    "caption",
    "whereInSource",
    "whenRecorded",
    "extract",
    "note"
})
@XmlRootElement(name = "Citation")
public class CitationElement {

    @XmlElement(name = "Link")
    protected LinkElement link;
    @XmlElement(name = "Caption")
    protected String caption;
    @XmlElement(name = "WhereInSource")
    protected String whereInSource;
    @XmlElement(name = "WhenRecorded")
    protected String whenRecorded;
    @XmlElement(name = "Extract")
    protected List<ExtractElement> extract;
    @XmlElement(name = "Note")
    protected List<NoteElement> note;

    /**
     * Gets the value of the link property.
     * 
     * @return
     *     possible object is
     *     {@link LinkElement }
     *     
     */
    public LinkElement getLink() {
        return link;
    }

    /**
     * Sets the value of the link property.
     * 
     * @param value
     *     allowed object is
     *     {@link LinkElement }
     *     
     */
    public void setLink(LinkElement value) {
        this.link = value;
    }

    /**
     * Gets the value of the caption property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaption() {
        return caption;
    }

    /**
     * Sets the value of the caption property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaption(String value) {
        this.caption = value;
    }

    /**
     * Gets the value of the whereInSource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhereInSource() {
        return whereInSource;
    }

    /**
     * Sets the value of the whereInSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhereInSource(String value) {
        this.whereInSource = value;
    }

    /**
     * Gets the value of the whenRecorded property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWhenRecorded() {
        return whenRecorded;
    }

    /**
     * Sets the value of the whenRecorded property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWhenRecorded(String value) {
        this.whenRecorded = value;
    }

    /**
     * Gets the value of the extract property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the extract property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExtract().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ExtractElement }
     * 
     * 
     */
    public List<ExtractElement> getExtract() {
        if (extract == null) {
            extract = new ArrayList<ExtractElement>();
        }
        return this.extract;
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

}