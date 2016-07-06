package ws.daley.genealogy.db;

import java.io.Serializable;
import java.util.HashMap;

import org.apache.xerces.dom.TextImpl;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class GenealogyItem implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	protected HashMap<Integer, Node> nodeChildren = new HashMap<Integer, Node>();
    
    protected HashMap<String, Node> attributeMap = new HashMap<String, Node>();

    public GenealogyItem() {}
    
    public GenealogyItem(Node node)
    {
		NodeList childList = node.getChildNodes();
		if (childList != null)
			for(int i = 0; i < childList.getLength(); i ++)
			{
				Node child = childList.item(i);
				if (!(child instanceof TextImpl))
					this.nodeChildren.put(this.nodeChildren.size(), child);
			}
		NamedNodeMap namedNodeMap = node.getAttributes();
		if (namedNodeMap != null)
		{
			for(int i = 0; i < namedNodeMap.getLength(); i++)
			{
				Node namedNode = namedNodeMap.item(i);
				String nodeName = namedNode.getNodeName();
				this.attributeMap.put(nodeName, namedNode);
			}
		}
    }
}
