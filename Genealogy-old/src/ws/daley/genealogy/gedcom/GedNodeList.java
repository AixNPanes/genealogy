package ws.daley.genealogy.gedcom;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.w3c.dom.Element;

import com.sun.org.apache.xml.internal.dtm.DTMIterator;
import com.sun.org.apache.xml.internal.dtm.ref.DTMNodeList;


public class GedNodeList implements Iterable<Element>
{
    private static final long serialVersionUID = 1L;
    
	private DTMNodeList nodeList;
	
	public class NodeListIterator implements Iterator<Element>
	{
		private DTMNodeList itNodeList;
		private DTMIterator nodeListIterator;
		
		public NodeListIterator(DTMNodeList nodeList)
		{
			this.itNodeList = nodeList;
			this.nodeListIterator = this.itNodeList.getDTMIterator();
		}
		
        public boolean hasNext()
        {
			if (this.nodeListIterator == null)
				return false;
			int nextNode = this.nodeListIterator.nextNode();
			this.nodeListIterator.previousNode();
	        return nextNode != -1;
        }

        public Element next()
        {
			if (!this.hasNext())
				throw new NoSuchElementException();
	        return (Element)this.itNodeList.item(this.nodeListIterator.nextNode());
        }

        public void remove()
        {
			throw new RuntimeException("remove not implemented");
        }
	}
	
	public GedNodeList(DTMNodeList nodeList)
	{
		if (nodeList == null || nodeList.getLength() == 0)
			this.nodeList = new DTMNodeList(null);
		else
			this.nodeList = nodeList;
	}
	
	public NodeListIterator getIterator() {return new NodeListIterator(this.nodeList);}

    public Iterator<Element> iterator()
    {
	    return this.getIterator();
    }
}
