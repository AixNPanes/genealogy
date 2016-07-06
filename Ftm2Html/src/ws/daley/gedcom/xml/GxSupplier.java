package ws.daley.gedcom.xml;

import ws.daley.gedcom.Supplier;
import ws.daley.genealogy.gedcom.structure.GcCorporationStructure;
import ws.daley.genealogy.gedcom.structure.GcHeadSourceStructure;

public class GxSupplier extends Supplier
{
//    @XmlElement(name = "Link", required = true)
//    protected Link link;
	
	public GxSupplier() {}

	public GxSupplier(GcHeadSourceStructure headSourceStructure)
	{
		this();
		
		if (headSourceStructure != null)
		{
			GcCorporationStructure gcCorporationStructure = headSourceStructure.getCorporation();
			if (gcCorporationStructure != null)
			{
				String corporationName = gcCorporationStructure.getParameters();
			}
		}
	}
}
