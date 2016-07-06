package ws.daley.gedcom.xml;

import ws.daley.gedcom.Name;
import ws.daley.gedcom.Product;
import ws.daley.genealogy.gedcom.structure.GcCorporationStructure;
import ws.daley.genealogy.gedcom.structure.GcHeadSourceStructure;
import ws.daley.genealogy.gedcom.structure.record.GcHeaderRecord;

public class GxProduct extends Product
{
//    @XmlElement(name = "ProductId")
//    protected String productId;
//    @XmlElement(name = "Version", required = true)
//    protected String version;
//    @XmlElement(name = "Name", required = true)
//    protected Name name;
//    @XmlElement(name = "Supplier")
//    protected Supplier supplier;

	public GxProduct() {}

	public GxProduct(GcHeaderRecord headerRecord)
	{
		this();
		xx;
		if (headerRecord != null)
		{
			GcHeadSourceStructure headSourceStructure = headerRecord.getSource();
			if (headSourceStructure != null)
			{
				String sourceName = headSourceStructure.getParameters();
				if (sourceName != null && "".equals(sourceName))
				{
					Name nam = new Name();
					nam.setValue(sourceName);
					nam.setType("ASCII");
					this.setName(nam);
				}
				String prod = headSourceStructure.getParameters();
				if (prod != null && !"".equals(prod))
					this.setProductId(prod);
				String vers = headSourceStructure.getParameters();
				if (prod != null && !"".equals(prod))
					this.setProductId(prod);
				GcCorporationStructure corporation = headSourceStructure.getCorporation();
				if (corporation != null)
				{
					GxSupplier supp = new GxSupplier(headSourceStructure);
					supp.setLink(value);
				}
			}
		}
	}
	
	public static boolean isEmpty(Product product)
	{
		return product.getProductId() != null && product.getProductId().length() >0 && 
			GxName.isEmpty(product.getName());
	}
}
