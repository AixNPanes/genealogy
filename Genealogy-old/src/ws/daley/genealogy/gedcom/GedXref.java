package ws.daley.genealogy.gedcom;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.xerces.dom.DeferredDocumentImpl;
import org.apache.xerces.dom.ElementNSImpl;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xpath.internal.jaxp.XPathImpl;

public class GedXref
{
	private static Pattern idPattern = Pattern.compile("^@(([a-zA-Z]*)(\\d+))@$");
	private static XPathImpl xpath = (XPathImpl)XPathFactory.newInstance().newXPath();
	private static ElementNSImpl gedcom55 = null;
	private String idFormat = "";
	private GedReference records;
	private GedReference references;
	private int maxId = -1;
	private GedReference variants = null;
	private String typeReference;
	private DeferredDocumentImpl document;
	private String idType = "";
	
	
	public GedXref(DeferredDocumentImpl document, String typeReference)
	{
		this.document = document;
		if (gedcom55 == null)
			try {gedcom55 = (ElementNSImpl)xpath.evaluate("/gedcom55", document, XPathConstants.NODE);}
	        catch (XPathExpressionException e) {throw new RuntimeException(e);}
		this.typeReference = typeReference;
		this.records = new GedReference(document, "//"+typeReference+"Record");
		this.references = new GedReference(document, "//"+typeReference);
	}
	
	public void reorganize()
	{
		if ("PLAC".equals(this.typeReference))
			reorganizePlace();
		else
			reorganizeOther();
	}
	
	public void reorganizePlace()
	{
		for(Map.Entry<String, ArrayList<Element>> mapEntry:this.references.entrySet())
		{
			this.maxId++;
			Element newRecord = this.document.createElement("PLACERecord");
			String idStr = String.format("@P%05d@", this.maxId);
			newRecord.setAttribute("id", idStr);
			newRecord.setAttribute("value", mapEntry.getKey());
			gedcom55.appendChild(newRecord);
			for(Element element:mapEntry.getValue())
			{
				element.setAttribute("id", idStr);
				element.removeAttribute("value");
			}
		}
	}
	
	public void reorganizeOther()
	{
		setMaxId();
		for(Map.Entry<String, ArrayList<Element>> mapEntry:this.references.entrySet())
		{
			this.variants = new GedReference();
			for(Element element:mapEntry.getValue())
			{
				NodeList nodeList = element.getChildNodes();
				Element dataElement[] = new Element[3];
				String dataNsme[] = new String[3];
				String dataValue[] = new String[]{"", "", ""};
				switch(nodeList.getLength())
				{
					case 0:
						{
							String value = element.getAttribute("value");
							String id = element.getAttribute("id");
							String ref = element.getAttribute("ref");
							if ("".equals(id) && "".equals(ref) && ! "".equals(value))
							{
								element.setAttribute("ref", value);
								element.removeAttribute("value");
							}
						}
						break;
					// cases 3, 5, and 7 must be ordered and grouped like this as they flow one
					// into the other
					case 7:
						dataElement[2] = (Element)nodeList.item(5);
						dataNsme[2] = dataElement[2].getNodeName();
						dataValue[2] = dataElement[2].getAttribute("value");
					case 5:
						dataElement[1] = (Element)nodeList.item(3);
						dataNsme[1] = dataElement[1].getNodeName();
						dataValue[1] = dataElement[1].getAttribute("value");
					case 3:
						dataElement[0] = (Element)nodeList.item(1);
						dataNsme[0] = dataElement[0].getNodeName();
						dataValue[0] = dataElement[0].getAttribute("value");
						String newText = dataValue[0]+"\\"+dataValue[1]+"\\"+dataValue[2];
						this.variants.addArrayElement(newText, element);
						break;
					default:
						throw new RuntimeException();
				}
			}
			int count = 0;
			for(Map.Entry<String, ArrayList<Element>> mapElement:this.variants.entrySet())
			{
				int sz = mapElement.getValue().size();
				count = sz>count?sz:count;
			}	
			for(Map.Entry<String, ArrayList<Element>> mapElement:this.variants.entrySet())
			{
				this.maxId++;
				String id = String.format(this.idFormat, this.maxId);
				Element newRecord = this.document.createElement(this.typeReference+"Record");
				ArrayList<Element> list = mapElement.getValue();
				Element listHead = list.get(0);
				String listIdValue = listHead.getAttribute("value");
				ArrayList<Element> listRecords = this.records.get(listIdValue);
				Element element = listRecords==null?listHead:listRecords.get(0);
				GedDocument.copyElement(this.document, element, newRecord);
				newRecord.setAttribute("id", id);
				newRecord.setAttribute("ref", element.getAttribute("id"));
				for(Element e2:list)
				{
					NodeList childList = e2.getChildNodes();
					for(int i=childList.getLength()-1; i >= 0; i--)
						e2.removeChild(childList.item(i));
					e2.setAttribute("ref", id);
					e2.removeAttribute("value");
				}
				gedcom55.appendChild(newRecord);
			}
		}
	}
	
	private void setMaxId()
	{
		if (this.records.size() == 0)
			this.maxId = 0;
		else
		{
			Matcher m = idPattern.matcher(this.records.lastKey());
			m.matches();
			String s = m.group(3);
			this.idType = m.group(2);
			this.idFormat = "@"+this.idType+"%05d@";
			this.maxId = (int)Math.pow(10,((int)Math.log10(Integer.parseInt(s))+1));
		}
	}
	
	public GedReference getRecords() {return this.records;}
	public GedReference getReferences() {return this.references;}
}
