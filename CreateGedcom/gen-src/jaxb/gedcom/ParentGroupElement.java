//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.01.02 at 10:07:12 PM EST 
//


package jaxb.gedcom;

import javax.xml.bind.annotation.AccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import jaxb.gedcom.LinkElement;
import jaxb.gedcom.ParentGroupElement;


/**
 * <p>Java class for ParentGroup element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="ParentGroup">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element ref="{}Link"/>
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
    "link"
})
@XmlRootElement(name = "ParentGroup")
public class ParentGroupElement {

    @XmlElement(name = "Link")
    protected LinkElement link;

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

}
