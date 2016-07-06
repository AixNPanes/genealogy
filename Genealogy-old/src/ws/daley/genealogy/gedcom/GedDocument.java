package ws.daley.genealogy.gedcom;

import java.io.ByteArrayInputStream;


import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.xerces.dom.DeferredDocumentImpl;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xerces.dom.ElementImpl;
import org.apache.xerces.dom.NodeImpl;
import org.apache.xerces.dom.TextImpl;
import org.apache.xerces.impl.Constants;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import ws.daley.genealogy.util.Util;

import com.sun.org.apache.xml.internal.dtm.ref.DTMNodeList;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import com.sun.org.apache.xpath.internal.NodeSet;
import com.sun.org.apache.xpath.internal.jaxp.XPathImpl;

public class GedDocument
{
    private static final long serialVersionUID = 1L;

	private static Pattern idPattern = Pattern.compile("^@(([a-zA-Z]*)(\\d+))@$");
    
	public static String SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
	XML_SCHEMA = "http://www.w3.org/2001/XMLSchema",
	SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

	public static final String NAMESPACES_FEATURE =
	    Constants.SAX_FEATURE_PREFIX + Constants.NAMESPACES_FEATURE;
	
	/** Feature identifier: include ignorable white space. */
	public static final String INCLUDE_IGNORABLE_WHITESPACE =
	    Constants.XERCES_FEATURE_PREFIX + Constants.INCLUDE_IGNORABLE_WHITESPACE;
	
	/** Feature identifier: create entiry ref children feature. */
	public static final String CREATE_ENTITY_REF_NODES_FEATURE =
	    Constants.XERCES_FEATURE_PREFIX + Constants.CREATE_ENTITY_REF_NODES_FEATURE;
	
	/** Feature identifier: include comments feature. */
	public static final String INCLUDE_COMMENTS_FEATURE =
	    Constants.XERCES_FEATURE_PREFIX + Constants.INCLUDE_COMMENTS_FEATURE;
	
	/** Feature identifier: create cdata children feature. */
	public static final String CREATE_CDATA_NODES_FEATURE =
	    Constants.XERCES_FEATURE_PREFIX + Constants.CREATE_CDATA_NODES_FEATURE;
	
	/** Feature identifier: XInclude processing */
	public static final String XINCLUDE_FEATURE = 
	    Constants.XERCES_FEATURE_PREFIX + Constants.XINCLUDE_FEATURE;
	
	/** feature identifier: XML Schema validation */
	public static final String XMLSCHEMA_VALIDATION_FEATURE =
	    Constants.XERCES_FEATURE_PREFIX + Constants.SCHEMA_VALIDATION_FEATURE;
	
	/** Feature identifier: validation */
	public static final String VALIDATION_FEATURE =
	    Constants.SAX_FEATURE_PREFIX + Constants.VALIDATION_FEATURE;
	
	/** Property identifier: security manager. */
	public static final String SECURITY_MANAGER =
	    Constants.XERCES_PROPERTY_PREFIX + Constants.SECURITY_MANAGER_PROPERTY;
	
	public static XPathImpl xpath = (XPathImpl)XPathFactory.newInstance().newXPath();
	
	private static GedDocument gedDocument;
	
	private static HashMap<String, Constructor<?>> constructors = new HashMap<String, Constructor<?>>();
	
	private TreeMap<String, ArrayList<Node>> attributes = new TreeMap<String, ArrayList<Node>>();
	
	public TreeMap<String, TreeMap<String, ArrayList<GedDocument>>> children = new TreeMap<String, TreeMap<String, ArrayList<GedDocument>>>();
	
	private NodeImpl node;
	
	protected boolean reorganized = false;
	
	static{
		Class<?> parms = NodeImpl.class;
		try {
	        constructors.put("HeaderRecord", GedHeaderRecord.class.getConstructor(parms));
			constructors.put("IndividualRecord", GedIndividualRecord.class.getConstructor(parms));
			constructors.put("FamilyRecord", GedFamilyRecord.class.getConstructor(parms));
			constructors.put("NoteRecord",GedNoteRecord.class.getConstructor(parms));
			constructors.put("PlaceRecord",GedPlaceRecord.class.getConstructor(parms));
			constructors.put("SourceRecord",GedSourceRecord.class.getConstructor(parms));
			constructors.put("SubmitterRecord",GedSubmitterRecord.class.getConstructor(parms));
			constructors.put("RepositoryRecord", GedRepositoryRecord.class.getConstructor(parms));
	        constructors.put("gedcom55", Gedcom55.class.getConstructor(parms));
			constructors.put("Burial", GedEvent.class.getConstructor(parms));
			constructors.put("Address", GedAddress.class.getConstructor(parms));
			constructors.put("Adoption",GedEvent.class.getConstructor(parms));
			constructors.put("Alias", GedName.class.getConstructor(parms));
			constructors.put("Birth", GedEvent.class.getConstructor(parms));
			constructors.put("Blessing",GedEvent.class.getConstructor(parms));
			constructors.put("Character",GedDocument.class.getConstructor(parms));
			constructors.put("Child", GedChild.class.getConstructor(parms));
			constructors.put("ChildInFamily",GedDocument.class.getConstructor(parms));
			constructors.put("Christening",GedEvent.class.getConstructor(parms));
			constructors.put("Corporation", GedDocument.class.getConstructor(parms));
			constructors.put("Date", GedDate.class.getConstructor(parms));
			constructors.put("Death", GedEvent.class.getConstructor(parms));
			constructors.put("Destination",GedDocument.class.getConstructor(parms));
			constructors.put("EMail", GedEMail.class.getConstructor(parms));
			constructors.put("EVEN", GedEvent.class.getConstructor(parms));
			constructors.put("FileReference", GedDocument.class.getConstructor(parms));
			constructors.put("Form", GedDocument.class.getConstructor(parms));
			constructors.put("GEDC", GedDocument.class.getConstructor(parms));
			constructors.put("Husband", GedHusband.class.getConstructor(parms));
			constructors.put("LDSBaptism", GedOrdinance.class.getConstructor(parms));
			constructors.put("LDSEndowment", GedOrdinance.class.getConstructor(parms));
			constructors.put("LDSChildSealing", GedOrdinance.class.getConstructor(parms));
			constructors.put("Marriage", GedMarriage.class.getConstructor(parms));
			constructors.put("MilitaryService", GedDocument.class.getConstructor(parms));
			constructors.put("Name", GedName.class.getConstructor(parms));
			constructors.put("Occupation", GedDocument.class.getConstructor(parms));
			constructors.put("Note", GedNote.class.getConstructor(parms));
			constructors.put("Phone", GedPhone.class.getConstructor(parms));
			constructors.put("Place", GedPlace.class.getConstructor(parms));
			constructors.put("Publication", GedDocument.class.getConstructor(parms));
			constructors.put("RelationshipToFather", GedDocument.class.getConstructor(parms));
			constructors.put("RelationshipToMother", GedDocument.class.getConstructor(parms));
			constructors.put("Repository", GedDocument.class.getConstructor(parms));
			constructors.put("Residence", GedResidence.class.getConstructor(parms));
			constructors.put("Sex", GedSex.class.getConstructor(parms));
			constructors.put("SocialSecurityNumber", GedSSN.class.getConstructor(parms));
			constructors.put("Source", GedSource.class.getConstructor(parms));
			constructors.put("SourceCallNumber", GedDocument.class.getConstructor(parms));
			constructors.put("SourceOriginator", GedDocument.class.getConstructor(parms));
			constructors.put("SpouseInFamily",GedSpouseInFamily.class.getConstructor(parms));
			constructors.put("SubmitterRecord", GedSubmitter.class.getConstructor(parms));
			constructors.put("Title", GedTitle.class.getConstructor(parms));
			constructors.put("UserReferenceNumber", GedReferenceNumber.class.getConstructor(parms));
			constructors.put("UserReferenceType", GedDocument.class.getConstructor(parms));
			constructors.put("Version",GedDocument.class.getConstructor(parms));
			constructors.put("Wife", GedWife.class.getConstructor(parms));
        }
        catch (Throwable e) {}
	}
	
	public GedDocument(NodeImpl node)
	{
		if (gedDocument == null)
			gedDocument = this;
		this.node = node;
		NamedNodeMap attrMap = this.node.getAttributes();
		if (attrMap != null)
			for(int i = 0; i < attrMap.getLength(); i++)
			{
				Node attrNode = attrMap.item(i);
				String nodeName = attrNode.getNodeName();
				ArrayList<Node> list = this.attributes.get(nodeName);
				if (list == null)
					list = new ArrayList<Node>();
				list.add(attrNode);
				this.attributes.put(nodeName, list);
			}
		NodeList childList = this.node.getChildNodes();
		if (childList != null)
			for(int i = 0; i < childList.getLength(); i++)
			{
				Node childNode = childList.item(i);
				if (!(childNode instanceof TextImpl))
				{
					Element element = (Element)childNode;
					String nodeName = childNode.getNodeName();
					TreeMap<String, ArrayList<GedDocument>> map = this.children.get(nodeName);
					if (map == null)
						map = new TreeMap<String, ArrayList<GedDocument>>();
					String id = element.getAttribute("id");
					String value = element.getAttribute("value");
					String key = id == null?(value == null?"":value):id;
					ArrayList<GedDocument> list = map.get(key);
					if (list == null)
						list = new ArrayList<GedDocument>();
					GedDocument gedElement = null;
					Constructor<?> constructor = constructors.get(nodeName);
					if (constructor == null)
					{
						try{constructor = GedDocument.class.getConstructor(NodeImpl.class);}
						catch(Throwable e){}
					}
					try {gedElement = (GedDocument)constructor.newInstance(childNode);}
                    catch (Throwable e) {}
					list.add(gedElement);
					map.put(key, list);
					this.children.put(nodeName, map);
				}
			}
	}
	
	public static GedDocument getDocument() {return gedDocument;}
	
	public TreeMap<String, ArrayList<Node>> getAttributes() {return this.attributes;}
	
	public ArrayList<Node> getAttributeList(String nodeType) {return this.attributes.get(nodeType);}
	
	public TreeMap<String, TreeMap<String, ArrayList<GedDocument>>> getChildren() {return this.children;}
	
	public TreeMap<String, ArrayList<GedDocument>> getChildMap(String nodeType) {return this.children.get(nodeType);}
	
	public ArrayList<GedDocument> getChildList(String nodeType, String key)
	{
		TreeMap<String, ArrayList<GedDocument>> map = this.children.get(nodeType);
		if (map == null)
			return null;
		return map.get(key);
	}
	
	public Node getNode() {return this.node;}
	
	public String getNodeName()
	{
		if (this.node == null)
			return null;
		return this.node.getNodeName();
	}

	public static DeferredDocumentImpl parse(String schema, ByteArrayInputStream is) throws Throwable
	{
		File schemaLocation = Util.getInFile(schema);
	    DocumentBuilder documentBuilder = getDocumentBuilder(schemaLocation);
	    Document doc = documentBuilder.parse(is);
	    return (DeferredDocumentImpl)doc;
 	}
	
	private static DocumentBuilder getDocumentBuilder(File file) throws Throwable
	{
	    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	    documentBuilderFactory.setNamespaceAware(true);
	    SchemaFactory schemaFactory = SchemaFactory.newInstance(XML_SCHEMA);
	    schemaFactory.setErrorHandler(new ErrorPrinter());
	    Schema schema = null;
    	schema = schemaFactory.newSchema(file);
	    documentBuilderFactory.setSchema(schema);
	    DocumentBuilder documentBuilder = null;
    	documentBuilder = documentBuilderFactory.newDocumentBuilder();
    	documentBuilder.setErrorHandler(new ErrorPrinter());
    	return documentBuilder;
	}
	
	private static DocumentBuilder getDocumentBuilder(String filename) throws Throwable
	{
		return getDocumentBuilder(new File(filename));
	}
	
	public static DocumentImpl copyDocument(DeferredDocumentImpl inDoc, String xsdName) throws Throwable
	{
	    DocumentBuilder documentBuilder2 = getDocumentBuilder(xsdName);
	    DocumentImpl newDoc = (DocumentImpl)documentBuilder2.newDocument();
    	copyDocument(inDoc, newDoc);
		return newDoc;
	}
	
	private static void copyDocument(DeferredDocumentImpl inDoc, DocumentImpl newDoc)
	{
		copyDocumentAttributes(inDoc, newDoc);
    	if (inDoc.hasChildNodes())
    	{
    		NodeList children = inDoc.getChildNodes();
    		for(int i = 0; i < children.getLength(); i++)
    		{
    			Element child = (Element)children.item(i);
    			Element newChild = newDoc.createElementNS(null, child.getNodeName(), child.getLocalName());
    			copyElement(newDoc, child, newChild);
    			newDoc.appendChild(newChild);
    		}
    	}
    	
	}
	
	private static void copyDocumentAttributes(Document inDoc, Document newDoc)
	{
		
    	if (inDoc.hasAttributes())
    	{
        	NamedNodeMap attributes = inDoc.getAttributes();
	    	for(int i = 0; i < attributes.getLength(); i++)
	    	{
	    		Node node = attributes.item(i);
	    		String namespace = node.getNamespaceURI();
	    		String name = node.getNodeName();
	    		String value = node.getNodeValue();
	    		Attr attr = (namespace==null?newDoc.createAttribute(name):newDoc.createAttributeNS(namespace, name));
	    		attr.setNodeValue(value);
	    	}
    	}
	}
	
	public static void copyElement(Document doc, Element inEl, Element newEl)
	{
		copyElementAttributes(inEl, newEl);
    	if (inEl.hasChildNodes())
    	{
    		NodeList children = inEl.getChildNodes();
    		for(int i = 0; i < children.getLength(); i++)
    		{
    			Object o = children.item(i);
    			if (o instanceof TextImpl)
    			{
    				TextImpl child = (TextImpl)children.item(i);
    				Text newChild = doc.createTextNode(child.getData());
    				newEl.appendChild(newChild);
    			}
    			else
    			{
    				Object o2 = children.item(i);
    				ElementImpl child = (ElementImpl)o2;
    				Element newChild = doc.createElement(child.getNodeName());
        			copyElement(doc, child, newChild);
    				newEl.appendChild(newChild);
    			}
    		}
    	}
    	
	}
	
	private static void copyElementAttributes(Element inEl, Element newEl)
	{
		
    	if (inEl.hasAttributes())
    	{
        	NamedNodeMap attributes = inEl.getAttributes();
	    	for(int i = 0; i < attributes.getLength(); i++)
	    	{
	    		Attr attr = (Attr)attributes.item(i);
	    		newEl.setAttribute(attr.getNodeName(), attr.getValue());
	    	}
    	}
	}
	
	public static void reorgStructures(DeferredDocumentImpl document, String type, @SuppressWarnings("unused") String newPattern, @SuppressWarnings("unused") String format)
		throws Throwable
	{
		GedXref gedXref = new GedXref(document, type);
		gedXref.reorganize();
		@SuppressWarnings("unused")
        GedReference records = gedXref.getRecords();
		@SuppressWarnings("unused")
        GedReference references = gedXref.getReferences();
		@SuppressWarnings("unused")
        int i = 0;
	}
	
	@SuppressWarnings("unused")
    private static boolean structureEquals(Node el1, Node el2)
	{
		NamedNodeMap attr1 = el1.getAttributes();
		NamedNodeMap attr2 = el2.getAttributes();
		if (attr1.getLength() != attr2.getLength())
			return false;
		NodeList ch1 = el1.getChildNodes();
		NodeList ch2 = el2.getChildNodes();
		if (ch1.getLength() != ch2.getLength())
			return false;
		for(int i = 0; i < attr1.getLength(); i++)
		{
			Node n1 = attr1.item(i);
			Node n2 = attr2.item(i);
			if (!n1.equals(n2) || !"value".equals(n1.getNodeName()) || !"value".equals(n2.getNodeName()) || !n1.getNodeValue().equals(n2.getNodeValue().substring(0, 4)))
				return false;
		}
		for(int i = 0; i < ch1.getLength(); i++)
		{
			Node n1 = ch1.item(i);
			Node n2 = ch2.item(i);
			if (!structureEquals(n1, n2))
				return false;
		}
		return true;
	}
	
	public static void reorgConc(DeferredDocumentImpl document) throws Throwable
	{
		DTMNodeList nodes = (DTMNodeList)xpath.evaluate("//CONC", document, XPathConstants.NODESET);
		if (nodes != null && nodes.getLength() > 0)
		{
			for(int i = 0; i < nodes.getLength(); i++)
			{
				Element element = (Element)nodes.item(i);
				Element parent = (Element)element.getParentNode();
				String elementValue = element.getAttribute("value");
				String parentValue = parent.getAttribute("value");
				elementValue = (elementValue==null)?"":elementValue;
				parentValue = (parentValue==null)?"":parentValue;
				String value = parentValue + elementValue;
				if (value.length() != 0)
					parent.setAttribute("value", value);
			}
			for(int i = 0; i < nodes.getLength(); i++)
			{
				Element element = (Element)nodes.item(i);
				Element parent = (Element)element.getParentNode();
				parent.removeChild(element);
			}
		}
	}
	
	public static void reorgData(DeferredDocumentImpl document) throws Throwable
	{
		DTMNodeList nodes = (DTMNodeList)xpath.evaluate("//DATA/TEXT[@value]", document, XPathConstants.NODESET);
		if (nodes != null && nodes.getLength() > 0)
		{
			for(int i = 0; i < nodes.getLength(); i++)
			{
				Element element = (Element)nodes.item(i);
				String value = element.getAttribute("value");
				Element parent = (Element)element.getParentNode();
				parent.setAttribute("value", value);
				parent.removeChild(element);
			}
		}
	}
	
	@SuppressWarnings("unused")
    private static ElementImpl cloneNodeRecord(Element element)
	{
		DeferredDocumentImpl document = (DeferredDocumentImpl)element.getOwnerDocument();
		ElementImpl newElement = (ElementImpl)document.createElement(element.getNodeName()+"Record");
		NodeList nodeList = element.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i++)
			newElement.appendChild(nodeList.item(i).cloneNode(true));
		NamedNodeMap namedNodeMap = element.getAttributes();
		for(int i = 0; i < namedNodeMap.getLength(); i++)
		{
			Node attr = namedNodeMap.item(i);
			newElement.setAttribute(attr.getLocalName(), attr.getNodeValue());
		}
		return newElement;
	}
	
	public static void deleteElementContents(Element element)
	{
		NodeList list = element.getChildNodes();
		for(int j = 0; j < list.getLength(); j++)
			element.removeChild(list.item(j));
		NamedNodeMap nodeMap = element.getAttributes();
		for(int j = 0; j < nodeMap.getLength(); j++)
			element.removeAttribute(nodeMap.item(j).getLocalName());
	}
	
	@SuppressWarnings("unused")
    private static int getMaxID(DeferredDocumentImpl document, String tag) throws Throwable
	{
		DTMNodeList nodes = (DTMNodeList)xpath.evaluate("//"+tag, document, XPathConstants.NODESET);
		int n = -1;
		if (nodes != null && nodes.getLength() > 0)
		{
			for(int i = 0; i < nodes.getLength(); i++)
			{
				Element element = (Element)nodes.item(i);
				String id = element.getAttribute("value");
				Matcher m = idPattern.matcher(id);
				if (m.matches())
				{
					int newN = Integer.parseInt(m.group(3));
					if (newN > n)
						n = newN;
				}
			}
		}
		int order = (int)Math.pow(10, (int)Math.log10(n)+1);		
		return n==-1?n:(n + order - (n % order));
	}
	
	public static void serialize(DocumentImpl doc, OutputStream output)
	{
		serialize(doc.getDocumentElement(), output);
	}
	
	public static void serialize(Element element, OutputStream output)
	{
		OutputFormat format = new OutputFormat("html", "iso-8859-1", true);
		format.setStandalone(false);
		format.setOmitXMLDeclaration(false);
		format.setOmitDocumentType(false);
		format.setIndent(4);
		format.setLineSeparator("\r\n");
		XMLSerializer serial = new XMLSerializer(output, format);
		try {
			serial.asDOMSerializer();
			serial.serialize(element);
			output.flush();
		} catch (IOException e) {
			System.err.println(e);
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	public static void chanteTags(DeferredDocumentImpl document)
	{
		changeTag(document, "//FAMRecord", "FamilyRecord");
		changeTag(document, "//HEADRecord", "HeaderRecord");
		changeTag(document, "//INDIRecord", "IndividualRecord");
		changeTag(document, "//NOTERecord", "NoteRecord");
		changeTag(document, "//PLACERecord", "PlaceRecord");
		changeTag(document, "//REPORecord", "RepositoryRecord");
		changeTag(document, "//SOURRecord", "SourceRecord");
		changeTag(document, "//SUBMRecord", "SubmitterRecord");
		changeTag(document, "//TRLRRecord", null);
		changeTag(document, "//ABBR", "Abbreviation");
		changeTag(document, "//ADDR", "Address");
		changeTag(document, "//ADOP", "Adoption");
		changeTag(document, "//ADR1", "Address1");
		changeTag(document, "//ADR2", "Address2");
		changeTag(document, "//AFN", "AncestralFileNumber");
		changeTag(document, "//ALIA", "Alias");
		changeTag(document, "//ANUL", "Anullment");
		changeTag(document, "//ARVL", "Arrival");
		changeTag(document, "//AUTH", "SourceOriginator");
		changeTag(document, "//BAPL", "LDSBaptism");
		changeTag(document, "//BAPM", "Baptism");
		changeTag(document, "//BARM", "Barmitvah");
		changeTag(document, "//BASM", "Basmitvah");
		changeTag(document, "//BIRT", "Birth");
		changeTag(document, "//BLES", "Blessing");
		changeTag(document, "//BURI", "Burial");
		changeTag(document, "//CALN", "SourceCallNumber");
		changeTag(document, "//CAST", "Caste");
		changeTag(document, "//CAUS", "Cause");
		changeTag(document, "//CENS", "Census");
		changeTag(document, "//CHAR", "Character");
		changeTag(document, "//CHIL", "Child");
		changeTag(document, "//CHR", "Christening");
		changeTag(document, "//CHRA", "ChristeningAdult");
		changeTag(document, "//CITY", "City");
		changeTag(document, "//CONF", "Confirmation");
		changeTag(document, "//CREM", "Cremation");
		changeTag(document, "//COPR", "Copyright");
		changeTag(document, "//CORP", "Corporation");
		changeTag(document, "//CTRY", "Country");
		changeTag(document, "//DATE", "Date");
		changeTag(document, "//DEAT", "Death");
		changeTag(document, "//DESC", "Descendants");
		changeTag(document, "//DEST", "Destination");
		changeTag(document, "//DIV", "Divorce");
		changeTag(document, "//DIVF", "DivorceFiled");
		changeTag(document, "//DSCR", "PhysicalDescription");
		changeTag(document, "//EDUC", "Education");
		changeTag(document, "//EMAIL", "EMail");
		changeTag(document, "//EMIG", "Emigration");
		changeTag(document, "//ENDL", "LDSEndowment");
		changeTag(document, "//ENGA", "Engagement");
		changeTag(document, "//FAMC", "ChildInFamily");
		changeTag(document, "//FAMS", "SpouseInFamily");
		changeTag(document, "//FCOM", "FirstCommunion");
		changeTag(document, "//FILE", "FileReference");
		changeTag(document, "//FOST", "Foster");
		changeTag(document, "//FORM", "Form");
		changeTag(document, "//GIVN", "Given");
		changeTag(document, "//GRAD", "Graduation");
		changeTag(document, "//HUSB", "Husband");
		changeTag(document, "//ILLE", "Illegitimate");
		changeTag(document, "//IMMI", "Immigration");
		changeTag(document, "//LANG", "Language");
		changeTag(document, "//LEGA", "Legatee");
		changeTag(document, "//LVG", "Living");
		changeTag(document, "//MARB", "MarriageBann");
		changeTag(document, "//MARC", "MarriageContract");
		changeTag(document, "//MARL", "MarriageLicense");
		changeTag(document, "//MARR", "Marriage");
		changeTag(document, "//MARS", "MarriageSettlement");
		changeTag(document, "//MISC", "Miscellaneous");
		changeTag(document, "//NAME", "Name");
		changeTag(document, "//NATI", "Nationality");
		changeTag(document, "//NATU", "Naturalization");
		changeTag(document, "//NICK", "Nickname");
		changeTag(document, "//NOTE", "Note");
		changeTag(document, "//NPFX", "NamePrefix");
		changeTag(document, "//NSFX", "NameSuffix");
		changeTag(document, "//OBJE", "MultimediaLink");
		changeTag(document, "//OCCU", "Occupation");
		changeTag(document, "//ORDI", "OrdinationReligious");
		changeTag(document, "//ORDL", "OrdinationLDS");
		changeTag(document, "//ORDN", "Ordination");
		changeTag(document, "//PAGE", "Page");
		changeTag(document, "//PHON", "Phone");
		changeTag(document, "//PLAC", "Place");
		changeTag(document, "//POST", "PostalCode");
		changeTag(document, "//PRIV", "Private");
		changeTag(document, "//PROB", "Probate");
		changeTag(document, "//PUBL", "Publication");
		changeTag(document, "//QUAY", "CertaintyAssessment");
		changeTag(document, "//RACE", "Race");
		changeTag(document, "//REFN", "UserReferenceNumber");
		changeTag(document, "//RELI", "Religion");
		changeTag(document, "//REPO", "Repository");
		changeTag(document, "//RESI", "Residence");
		changeTag(document, "//RETI", "Retirement");
		changeTag(document, "//SEX", "Sex");
		changeTag(document, "//SLGC", "LDSChildSealing");
		changeTag(document, "//SLGS", "LDSSpouseSealing");
		changeTag(document, "//SOUR", "Source");
		changeTag(document, "//SPFX", "SurnamePrifix");
		changeTag(document, "//SSN", "SocialSecurityNumber");
		changeTag(document, "//STAE", "State");
		changeTag(document, "//STIL", "Stillborn");
		changeTag(document, "//SUBM", "SubmitterRecord");
		changeTag(document, "//SURN", "Surname");
		changeTag(document, "//TEL", "TelephoneNumber");
		changeTag(document, "//TEMP", "Temple");
		changeTag(document, "//TEXT", "Text");
		changeTag(document, "//TITL", "Title");
		changeTag(document, "//TIME", "Time");
		changeTag(document, "//TYPE", "UserReferenceType");
		changeTag(document, "//VERS", "Version");
		changeTag(document, "//WIFE", "Wife");
		changeTag(document, "//WILL", "Will");
		changeTag(document, "//_MILT", "MilitaryService");
		changeTag(document, "//_FREL", "RelationshipToFather");
		changeTag(document, "//_MREL", "RelationshipToMother");
	}
	
	private static void changeTag(DeferredDocumentImpl document, String path, String repl)
	{
		Object object = null;
		try {object = xpath.evaluate(path, document, XPathConstants.NODESET);}
        catch (XPathExpressionException e) {throw new RuntimeException(e);}
        ArrayList<Element> list = new ArrayList<Element>();
        if (object instanceof NodeSet)
        {
        	NodeSet nodeSet = (NodeSet)object;
        	
        	for(int i = 0; i < nodeSet.size(); i++)
        		list.add((Element)nodeSet.item(i));
//	        	NodeSet nodeSet = (NodeSet)object;
//	        	if (nodeSet.size() == 0)
//	        		return;
//	        	for(int i = nodeSet.size()-1; i >= 0; i--)
//	        	{
//	        		Element element = (Element)nodeSet.item(0);
//	        		Node parent = element.getParentNode();
//	        		Element newElement = document.createElement(repl);
//	        		GedDocument.copyElement(document, element, newElement);
//	        		parent.removeChild(element);
//	        		parent.appendChild(newElement);
//	        	}
        }
        else if (object instanceof DTMNodeList)
        {
        	DTMNodeList nodeSet = (DTMNodeList)object;
        	for(int i = 0; i < nodeSet.getLength(); i++)
        		list.add((Element)nodeSet.item(i));
//	        	DTMNodeList nodeSet = (DTMNodeList)object;
//	        	if (nodeSet.getLength() == 0)
//	        		return;
//	        	for(int i = nodeSet.getLength()-1; i >= 0 ; i--)
//	        	{
//	        		Element element = (Element)nodeSet.item(0);
//	        		Node parent = element.getParentNode();
//	        		Element newElement = document.createElement(repl);
//	        		GedDocument.copyElement(document, element, newElement);
//	        		parent.appendChild(newElement);
//	        		parent.removeChild(element);
//	        	}
        }
        else if (object instanceof ElementImpl)
        {
//	        	ElementImpl element = (ElementImpl)object;
//        		Node parent = element.getParentNode();
//        		Element newElement = document.createElement(repl);
//        		GedDocument.copyElement(document, element, newElement);
//        		parent.appendChild(newElement);
//        		parent.removeChild(element);
        }
        else
        	throw new RuntimeException();
        for(Element element:list)
        {
    		Node parent = element.getParentNode();
    		if (repl != null)
    		{
	    		Element newElement = document.createElement(repl);
	    		GedDocument.copyElement(document, element, newElement);
	    		parent.appendChild(newElement);
    		}
    		parent.removeChild(element);
        }
	}
	
//	public void reorg()
//	{
//		if (this.reorganized)
//			return;
//		TreeMap<String, TreeMap<String, ArrayList<GedDocument>>> map = this.getChildren();
//		if (map != null)
//		{
//			for(Map.Entry<String, TreeMap<String, ArrayList<GedDocument>>> mapEntry:map.entrySet())
//				for(Map.Entry<String, ArrayList<GedDocument>> mapEntryList:mapEntry.getValue().entrySet())
//					for(GedDocument doc:mapEntryList.getValue())
//						if (! (doc instanceof GedChild))
//							doc.reorg();
//			for(Map.Entry<String, TreeMap<String, ArrayList<GedDocument>>> mapEntry:map.entrySet())
//				for(Map.Entry<String, ArrayList<GedDocument>> mapEntryList:mapEntry.getValue().entrySet())
//					for(GedDocument doc:mapEntryList.getValue())
//						if (doc instanceof GedChild)
//							doc.reorg();
//		}
//		this.reorganized = true;
//	}
	
	public ArrayList<GedDocument> getChildRecords(String key)
	{
		ArrayList<GedDocument> documents = new ArrayList<GedDocument>();
		TreeMap<String, ArrayList<GedDocument>> map = this.getChildren().get(key);
		if (map != null && map.size() > 0)
		for(Map.Entry<String, ArrayList<GedDocument>> entry:map.entrySet())
			for(GedDocument doc:entry.getValue())
				documents.add(doc);
		return documents;
	}
	
	public ArrayList<GedDocument> getChildDocuments(String key)
	{
		TreeMap<String, ArrayList<GedDocument>> map = this.getChildren().get(key);
		if (map == null)
			return null;
		return map.entrySet().iterator().next().getValue();
	}
	
	public GedDocument getChildDocument(String key, @SuppressWarnings("unused") int pos)
	{
		ArrayList<GedDocument> list = getChildDocuments(key);
		if (list == null || list.size() == 0)
			return null;
		return list.get(0);
	}
	
	public GedDocument getChildDocument(String key)
	{
		return getChildDocument(key, 0);
	}

	public String getChildValue(String key)
	{
		return getChildValue(this.getChildDocument(key));
	}

	public static String getChildAttribute(GedDocument doc, String key)
	{
		if (doc == null)
			return null;
		ArrayList<Node> nodeList = doc.getAttributeList(key);
		if (nodeList == null || nodeList.size() == 0)
			return null;
		if (nodeList.size() > 1)
			for(Node node:nodeList)
				if (!nodeList.get(0).getNodeValue().equals(node.getNodeValue()))
					throw new RuntimeException();
		return nodeList.get(0).getNodeValue();
	}

	public static String getChildValue(GedDocument doc)
	{
		return getChildAttribute(doc, "value");
	}

	public static String getChildID(GedDocument doc)
	{
		return getChildAttribute(doc, "id");
	}

	public static String getChildRef(GedDocument doc)
	{
		return getChildAttribute(doc, "ref");
	}

	public static GedDocument getChildObject(GedDocument doc, String key)
	{
		if (doc == null)
			return null;
		TreeMap<String, TreeMap<String, ArrayList<GedDocument>>> map = doc.getChildren();
		if (map == null || map.size() == 0)
			return null;
		TreeMap<String, ArrayList<GedDocument>> mapEntry = map.get(key);
		if (mapEntry == null || mapEntry.size() == 0)
			return null;
		ArrayList<GedDocument> list = mapEntry.entrySet().iterator().next().getValue();
		if (list == null || list.size() == 0)
			return null;
		return list.get(0);
	}
}
