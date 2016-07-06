package ws.daley.genealogy.gedcom;

import java.util.ArrayList;
import java.util.TreeMap;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.xerces.dom.DeferredDocumentImpl;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.sun.org.apache.xml.internal.dtm.ref.DTMNodeList;
import com.sun.org.apache.xpath.internal.jaxp.XPathImpl;

public class GedReference extends TreeMap<String, ArrayList<Element>>
{
    private static final long serialVersionUID = 1L;

    public static XPathImpl xpath = (XPathImpl)XPathFactory.newInstance().newXPath();
    
    public GedReference() {}
    
    public GedReference(DeferredDocumentImpl document, String path)
    {
    	DTMNodeList nodes = null;
		try {
	        nodes = (DTMNodeList)xpath.evaluate(path, document, XPathConstants.NODESET);
        }
        catch (XPathExpressionException e) {
	        throw new RuntimeException(e);
        }

		for(int i = 0; i < nodes.getLength(); i++)
		{
			Element element = (Element)nodes.item(i);
			if (element != null)
			{
				NamedNodeMap namedNodeMap = element.getAttributes();
				Node namedItem = namedNodeMap.getNamedItem("value");
				if (namedItem == null)
					namedItem = namedNodeMap.getNamedItem("id");
				if (namedItem != null)
				{
					String key = namedItem.getNodeValue();
					ArrayList<Element> list = this.get(key);
					if (list == null )
						list = new ArrayList<Element>();
					list.add(element);
					this.put(key, list);
				}
			}
		}
    }
    
    public void addArrayElement(String key, Element e)
    {
    	ArrayList<Element> list = this.get(key);
    	if (list == null)
    		list = new ArrayList<Element>();
    	list.add(e);
    	this.put(key, list);
    }
}
