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
import jaxb.gedcom.PlaceElement;
import jaxb.gedcom.PlaceNameElement;
import jaxb.gedcom.PlaceNameVarElement;


/**
 * <p>Java class for Place element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="Place">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element ref="{}PlaceName" minOccurs="0"/>
 *           &lt;element ref="{}Coordinates" minOccurs="0"/>
 *           &lt;element ref="{}PlaceNameVar" maxOccurs="unbounded" minOccurs="0"/>
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
    "placeName",
    "coordinates",
    "placeNameVar"
})
@XmlRootElement(name = "Place")
public class PlaceElement {

    @XmlElement(name = "PlaceName")
    protected PlaceNameElement placeName;
    @XmlElement(name = "Coordinates")
    protected String coordinates;
    @XmlElement(name = "PlaceNameVar")
    protected List<PlaceNameVarElement> placeNameVar;

    /**
     * Gets the value of the placeName property.
     * 
     * @return
     *     possible object is
     *     {@link PlaceNameElement }
     *     
     */
    public PlaceNameElement getPlaceName() {
        return placeName;
    }

    /**
     * Sets the value of the placeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link PlaceNameElement }
     *     
     */
    public void setPlaceName(PlaceNameElement value) {
        this.placeName = value;
    }

    /**
     * Gets the value of the coordinates property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoordinates() {
        return coordinates;
    }

    /**
     * Sets the value of the coordinates property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoordinates(String value) {
        this.coordinates = value;
    }

    /**
     * Gets the value of the placeNameVar property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the placeNameVar property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlaceNameVar().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PlaceNameVarElement }
     * 
     * 
     */
    public List<PlaceNameVarElement> getPlaceNameVar() {
        if (placeNameVar == null) {
            placeNameVar = new ArrayList<PlaceNameVarElement>();
        }
        return this.placeNameVar;
    }

}