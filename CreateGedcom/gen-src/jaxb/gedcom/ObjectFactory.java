//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.01.02 at 10:07:12 PM EST 
//


package jaxb.gedcom;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import jaxb.gedcom.AddrLineElement;
import jaxb.gedcom.AssocIndivElement;
import jaxb.gedcom.BasedOnElement;
import jaxb.gedcom.BrElement;
import jaxb.gedcom.ChangedElement;
import jaxb.gedcom.ChildElement;
import jaxb.gedcom.CitationElement;
import jaxb.gedcom.ContactElement;
import jaxb.gedcom.ContactRecElement;
import jaxb.gedcom.DateElement;
import jaxb.gedcom.DupIndivElement;
import jaxb.gedcom.EnrichmentElement;
import jaxb.gedcom.EventRecElement;
import jaxb.gedcom.EvidenceElement;
import jaxb.gedcom.ExternalIDElement;
import jaxb.gedcom.ExtractElement;
import jaxb.gedcom.FamilyRecElement;
import jaxb.gedcom.FileCreationElement;
import jaxb.gedcom.GEDCOMElement;
import jaxb.gedcom.GroupRecElement;
import jaxb.gedcom.HeaderRecElement;
import jaxb.gedcom.HusbFathElement;
import jaxb.gedcom.IndNameVariationElement;
import jaxb.gedcom.IndivNameElement;
import jaxb.gedcom.IndividualRecElement;
import jaxb.gedcom.LDSOrdRecElement;
import jaxb.gedcom.LinkElement;
import jaxb.gedcom.MailAddressElement;
import jaxb.gedcom.MemberElement;
import jaxb.gedcom.NameElement;
import jaxb.gedcom.NamePartElement;
import jaxb.gedcom.NoteElement;
import jaxb.gedcom.ObjectFactory;
import jaxb.gedcom.OrdStatElement;
import jaxb.gedcom.ParentGroupElement;
import jaxb.gedcom.ParticipantElement;
import jaxb.gedcom.PersInfoElement;
import jaxb.gedcom.PhoneElement;
import jaxb.gedcom.PlaceElement;
import jaxb.gedcom.PlaceNameElement;
import jaxb.gedcom.PlaceNameVarElement;
import jaxb.gedcom.PlacePartElement;
import jaxb.gedcom.ProductElement;
import jaxb.gedcom.RepositoryElement;
import jaxb.gedcom.RepositoryRecElement;
import jaxb.gedcom.SameIndivElement;
import jaxb.gedcom.SourceRecElement;
import jaxb.gedcom.SubmitterElement;
import jaxb.gedcom.SupplierElement;
import jaxb.gedcom.WifeMothElement;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the jaxb.gedcom package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Addressee_QNAME = new QName("", "Addressee");
    private final static QName _CallNbr_QNAME = new QName("", "CallNbr");
    private final static QName _Sub_QNAME = new QName("", "sub");
    private final static QName _Author_QNAME = new QName("", "Author");
    private final static QName _P_QNAME = new QName("", "p");
    private final static QName _Information_QNAME = new QName("", "Information");
    private final static QName _Blockquote_QNAME = new QName("", "blockquote");
    private final static QName _I_QNAME = new QName("", "i");
    private final static QName _TempleCode_QNAME = new QName("", "TempleCode");
    private final static QName _RelToFath_QNAME = new QName("", "RelToFath");
    private final static QName _Version_QNAME = new QName("", "Version");
    private final static QName _Public_QNAME = new QName("", "Public");
    private final static QName _Religion_QNAME = new QName("", "Religion");
    private final static QName _U_QNAME = new QName("", "u");
    private final static QName _Center_QNAME = new QName("", "center");
    private final static QName _Publishing_QNAME = new QName("", "Publishing");
    private final static QName _ProductId_QNAME = new QName("", "ProductId");
    private final static QName _URI_QNAME = new QName("", "URI");
    private final static QName _Sup_QNAME = new QName("", "sup");
    private final static QName _Gender_QNAME = new QName("", "Gender");
    private final static QName _Living_QNAME = new QName("", "Living");
    private final static QName _ChildNbr_QNAME = new QName("", "ChildNbr");
    private final static QName _WhenRecorded_QNAME = new QName("", "WhenRecorded");
    private final static QName _Association_QNAME = new QName("", "Association");
    private final static QName _Age_QNAME = new QName("", "Age");
    private final static QName _B_QNAME = new QName("", "b");
    private final static QName _Article_QNAME = new QName("", "Article");
    private final static QName _DeathStatus_QNAME = new QName("", "DeathStatus");
    private final static QName _Coordinates_QNAME = new QName("", "Coordinates");
    private final static QName _Copyright_QNAME = new QName("", "Copyright");
    private final static QName _FamilyNbr_QNAME = new QName("", "FamilyNbr");
    private final static QName _Email_QNAME = new QName("", "Email");
    private final static QName _RelToMoth_QNAME = new QName("", "RelToMoth");
    private final static QName _WhereInSource_QNAME = new QName("", "WhereInSource");
    private final static QName _Title_QNAME = new QName("", "Title");
    private final static QName _Role_QNAME = new QName("", "Role");
    private final static QName _Caption_QNAME = new QName("", "Caption");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: jaxb.gedcom
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link NoteElement }
     * 
     */
    public NoteElement createNoteElement() {
        return new NoteElement();
    }

    /**
     * Create an instance of {@link HusbFathElement }
     * 
     */
    public HusbFathElement createHusbFathElement() {
        return new HusbFathElement();
    }

    /**
     * Create an instance of {@link ChildElement }
     * 
     */
    public ChildElement createChildElement() {
        return new ChildElement();
    }

    /**
     * Create an instance of {@link ProductElement }
     * 
     */
    public ProductElement createProductElement() {
        return new ProductElement();
    }

    /**
     * Create an instance of {@link WifeMothElement }
     * 
     */
    public WifeMothElement createWifeMothElement() {
        return new WifeMothElement();
    }

    /**
     * Create an instance of {@link SameIndivElement }
     * 
     */
    public SameIndivElement createSameIndivElement() {
        return new SameIndivElement();
    }

    /**
     * Create an instance of {@link DateElement }
     * 
     */
    public DateElement createDateElement() {
        return new DateElement();
    }

    /**
     * Create an instance of {@link AddrLineElement }
     * 
     */
    public AddrLineElement createAddrLineElement() {
        return new AddrLineElement();
    }

    /**
     * Create an instance of {@link FileCreationElement }
     * 
     */
    public FileCreationElement createFileCreationElement() {
        return new FileCreationElement();
    }

    /**
     * Create an instance of {@link EvidenceElement }
     * 
     */
    public EvidenceElement createEvidenceElement() {
        return new EvidenceElement();
    }

    /**
     * Create an instance of {@link ContactRecElement }
     * 
     */
    public ContactRecElement createContactRecElement() {
        return new ContactRecElement();
    }

    /**
     * Create an instance of {@link MemberElement }
     * 
     */
    public MemberElement createMemberElement() {
        return new MemberElement();
    }

    /**
     * Create an instance of {@link ChangedElement }
     * 
     */
    public ChangedElement createChangedElement() {
        return new ChangedElement();
    }

    /**
     * Create an instance of {@link PlaceElement }
     * 
     */
    public PlaceElement createPlaceElement() {
        return new PlaceElement();
    }

    /**
     * Create an instance of {@link EventRecElement }
     * 
     */
    public EventRecElement createEventRecElement() {
        return new EventRecElement();
    }

    /**
     * Create an instance of {@link BasedOnElement }
     * 
     */
    public BasedOnElement createBasedOnElement() {
        return new BasedOnElement();
    }

    /**
     * Create an instance of {@link HeaderRecElement }
     * 
     */
    public HeaderRecElement createHeaderRecElement() {
        return new HeaderRecElement();
    }

    /**
     * Create an instance of {@link SupplierElement }
     * 
     */
    public SupplierElement createSupplierElement() {
        return new SupplierElement();
    }

    /**
     * Create an instance of {@link GroupRecElement }
     * 
     */
    public GroupRecElement createGroupRecElement() {
        return new GroupRecElement();
    }

    /**
     * Create an instance of {@link IndividualRecElement }
     * 
     */
    public IndividualRecElement createIndividualRecElement() {
        return new IndividualRecElement();
    }

    /**
     * Create an instance of {@link GEDCOMElement }
     * 
     */
    public GEDCOMElement createGEDCOMElement() {
        return new GEDCOMElement();
    }

    /**
     * Create an instance of {@link SubmitterElement }
     * 
     */
    public SubmitterElement createSubmitterElement() {
        return new SubmitterElement();
    }

    /**
     * Create an instance of {@link ContactElement }
     * 
     */
    public ContactElement createContactElement() {
        return new ContactElement();
    }

    /**
     * Create an instance of {@link PlaceNameVarElement }
     * 
     */
    public PlaceNameVarElement createPlaceNameVarElement() {
        return new PlaceNameVarElement();
    }

    /**
     * Create an instance of {@link ExternalIDElement }
     * 
     */
    public ExternalIDElement createExternalIDElement() {
        return new ExternalIDElement();
    }

    /**
     * Create an instance of {@link IndNameVariationElement }
     * 
     */
    public IndNameVariationElement createIndNameVariationElement() {
        return new IndNameVariationElement();
    }

    /**
     * Create an instance of {@link CitationElement }
     * 
     */
    public CitationElement createCitationElement() {
        return new CitationElement();
    }

    /**
     * Create an instance of {@link PhoneElement }
     * 
     */
    public PhoneElement createPhoneElement() {
        return new PhoneElement();
    }

    /**
     * Create an instance of {@link EnrichmentElement }
     * 
     */
    public EnrichmentElement createEnrichmentElement() {
        return new EnrichmentElement();
    }

    /**
     * Create an instance of {@link PersInfoElement }
     * 
     */
    public PersInfoElement createPersInfoElement() {
        return new PersInfoElement();
    }

    /**
     * Create an instance of {@link NameElement }
     * 
     */
    public NameElement createNameElement() {
        return new NameElement();
    }

    /**
     * Create an instance of {@link ParticipantElement }
     * 
     */
    public ParticipantElement createParticipantElement() {
        return new ParticipantElement();
    }

    /**
     * Create an instance of {@link IndivNameElement }
     * 
     */
    public IndivNameElement createIndivNameElement() {
        return new IndivNameElement();
    }

    /**
     * Create an instance of {@link ParentGroupElement }
     * 
     */
    public ParentGroupElement createParentGroupElement() {
        return new ParentGroupElement();
    }

    /**
     * Create an instance of {@link FamilyRecElement }
     * 
     */
    public FamilyRecElement createFamilyRecElement() {
        return new FamilyRecElement();
    }

    /**
     * Create an instance of {@link ExtractElement }
     * 
     */
    public ExtractElement createExtractElement() {
        return new ExtractElement();
    }

    /**
     * Create an instance of {@link DupIndivElement }
     * 
     */
    public DupIndivElement createDupIndivElement() {
        return new DupIndivElement();
    }

    /**
     * Create an instance of {@link PlaceNameElement }
     * 
     */
    public PlaceNameElement createPlaceNameElement() {
        return new PlaceNameElement();
    }

    /**
     * Create an instance of {@link BrElement }
     * 
     */
    public BrElement createBrElement() {
        return new BrElement();
    }

    /**
     * Create an instance of {@link AssocIndivElement }
     * 
     */
    public AssocIndivElement createAssocIndivElement() {
        return new AssocIndivElement();
    }

    /**
     * Create an instance of {@link PlacePartElement }
     * 
     */
    public PlacePartElement createPlacePartElement() {
        return new PlacePartElement();
    }

    /**
     * Create an instance of {@link RepositoryRecElement }
     * 
     */
    public RepositoryRecElement createRepositoryRecElement() {
        return new RepositoryRecElement();
    }

    /**
     * Create an instance of {@link OrdStatElement }
     * 
     */
    public OrdStatElement createOrdStatElement() {
        return new OrdStatElement();
    }

    /**
     * Create an instance of {@link MailAddressElement }
     * 
     */
    public MailAddressElement createMailAddressElement() {
        return new MailAddressElement();
    }

    /**
     * Create an instance of {@link NamePartElement }
     * 
     */
    public NamePartElement createNamePartElement() {
        return new NamePartElement();
    }

    /**
     * Create an instance of {@link LinkElement }
     * 
     */
    public LinkElement createLinkElement() {
        return new LinkElement();
    }

    /**
     * Create an instance of {@link LDSOrdRecElement }
     * 
     */
    public LDSOrdRecElement createLDSOrdRecElement() {
        return new LDSOrdRecElement();
    }

    /**
     * Create an instance of {@link RepositoryElement }
     * 
     */
    public RepositoryElement createRepositoryElement() {
        return new RepositoryElement();
    }

    /**
     * Create an instance of {@link SourceRecElement }
     * 
     */
    public SourceRecElement createSourceRecElement() {
        return new SourceRecElement();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Addressee")
    public JAXBElement<String> createAddressee(String value) {
        return new JAXBElement<String>(_Addressee_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "CallNbr")
    public JAXBElement<String> createCallNbr(String value) {
        return new JAXBElement<String>(_CallNbr_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "sub")
    public JAXBElement<String> createSub(String value) {
        return new JAXBElement<String>(_Sub_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Author")
    public JAXBElement<String> createAuthor(String value) {
        return new JAXBElement<String>(_Author_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "p")
    public JAXBElement<String> createP(String value) {
        return new JAXBElement<String>(_P_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Information")
    public JAXBElement<String> createInformation(String value) {
        return new JAXBElement<String>(_Information_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "blockquote")
    public JAXBElement<String> createBlockquote(String value) {
        return new JAXBElement<String>(_Blockquote_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "i")
    public JAXBElement<String> createI(String value) {
        return new JAXBElement<String>(_I_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "TempleCode")
    public JAXBElement<String> createTempleCode(String value) {
        return new JAXBElement<String>(_TempleCode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "RelToFath")
    public JAXBElement<String> createRelToFath(String value) {
        return new JAXBElement<String>(_RelToFath_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Version")
    public JAXBElement<String> createVersion(String value) {
        return new JAXBElement<String>(_Version_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Public")
    public JAXBElement<String> createPublic(String value) {
        return new JAXBElement<String>(_Public_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Religion")
    public JAXBElement<String> createReligion(String value) {
        return new JAXBElement<String>(_Religion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "u")
    public JAXBElement<String> createU(String value) {
        return new JAXBElement<String>(_U_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "center")
    public JAXBElement<String> createCenter(String value) {
        return new JAXBElement<String>(_Center_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Publishing")
    public JAXBElement<String> createPublishing(String value) {
        return new JAXBElement<String>(_Publishing_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ProductId")
    public JAXBElement<String> createProductId(String value) {
        return new JAXBElement<String>(_ProductId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "URI")
    public JAXBElement<String> createURI(String value) {
        return new JAXBElement<String>(_URI_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "sup")
    public JAXBElement<String> createSup(String value) {
        return new JAXBElement<String>(_Sup_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Gender")
    public JAXBElement<String> createGender(String value) {
        return new JAXBElement<String>(_Gender_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Living")
    public JAXBElement<String> createLiving(String value) {
        return new JAXBElement<String>(_Living_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "ChildNbr")
    public JAXBElement<String> createChildNbr(String value) {
        return new JAXBElement<String>(_ChildNbr_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "WhenRecorded")
    public JAXBElement<String> createWhenRecorded(String value) {
        return new JAXBElement<String>(_WhenRecorded_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Association")
    public JAXBElement<String> createAssociation(String value) {
        return new JAXBElement<String>(_Association_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Age")
    public JAXBElement<String> createAge(String value) {
        return new JAXBElement<String>(_Age_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "b")
    public JAXBElement<String> createB(String value) {
        return new JAXBElement<String>(_B_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Article")
    public JAXBElement<String> createArticle(String value) {
        return new JAXBElement<String>(_Article_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "DeathStatus")
    public JAXBElement<String> createDeathStatus(String value) {
        return new JAXBElement<String>(_DeathStatus_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Coordinates")
    public JAXBElement<String> createCoordinates(String value) {
        return new JAXBElement<String>(_Coordinates_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Copyright")
    public JAXBElement<String> createCopyright(String value) {
        return new JAXBElement<String>(_Copyright_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "FamilyNbr")
    public JAXBElement<String> createFamilyNbr(String value) {
        return new JAXBElement<String>(_FamilyNbr_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Email")
    public JAXBElement<String> createEmail(String value) {
        return new JAXBElement<String>(_Email_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "RelToMoth")
    public JAXBElement<String> createRelToMoth(String value) {
        return new JAXBElement<String>(_RelToMoth_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "WhereInSource")
    public JAXBElement<String> createWhereInSource(String value) {
        return new JAXBElement<String>(_WhereInSource_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Title")
    public JAXBElement<String> createTitle(String value) {
        return new JAXBElement<String>(_Title_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Role")
    public JAXBElement<String> createRole(String value) {
        return new JAXBElement<String>(_Role_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "Caption")
    public JAXBElement<String> createCaption(String value) {
        return new JAXBElement<String>(_Caption_QNAME, String.class, null, value);
    }

}
