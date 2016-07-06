package ws.daley.gedcom.xml;

import ws.daley.gedcom.Link;
import ws.daley.gedcom.Supplier;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.structure.record.GcHeaderRecord;
import ws.daley.genealogy.gedcom.structure.util.BaseElementVector;
import ws.daley.genealogy.gedcom.structure.util.BaseElementVectorMap;

public class GxFileCreationSupplier extends Supplier
{
//    @XmlElement(name = "Link", required = true)
//    protected Link link;
	public GxFileCreationSupplier() {}
	
	public GxFileCreationSupplier(GcHeaderRecord headerRecord)
	{
		this();
		
		if (headerRecord != null)
		{
			BaseElementVectorMap map = headerRecord.getVectorMap();
			if (map != null)
			{
				BaseElementVector supplierVector = map.get("SUBM");
				if (supplierVector != null && supplierVector.size() != 0)
				{
					GcBaseElement supplierElement = supplierVector.get(0);
					if (supplierElement != null)
					{
						String supplierName = supplierElement.getLine().getLink();
						if (supplierName != null && "".equals(supplierName))
							supplierName = null;
						if (supplierName != null)
						{
							Link supplierLink = new Link();
							supplierLink.setTarget(supplierName);
							supplierLink.setRef("Supplier");
							this.setLink(supplierLink);
						}
					}
				}
			}
		}
	}
	
	public boolean empty()
	{
		return this.getLink() != null;
	}
}
