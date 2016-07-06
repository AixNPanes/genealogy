package ws.daley.gedcom.xml;

import ws.daley.gedcom.FileCreation;
import ws.daley.gedcom.Name;
import ws.daley.gedcom.Product;
import ws.daley.genealogy.gedcom.structure.record.GcHeaderRecord;

public class GxFileCreation extends FileCreation
{
	public GxFileCreation() {}
	
//	   protected Product product;
//	    @XmlElement(name = "Copyright")
//	    protected String copyright;
//	    @XmlAttribute(name = "Date", required = true)
//	    protected String date;
//	    @XmlAttribute(name = "Time")
//	    protected String time;

	
	public GxFileCreation(GcHeaderRecord headerRecord)
	{
		this();
		
		GxProduct gxProduct = new GxProduct(headerRecord);
		if (!gxProduct.empty())
			this.product = gxProduct;
		
		FileCreation fileCreation = new FileCreation();
//		fileCreation.setCopyright(value);
		if (gcTimeLine != null)
			fileCreation.setDate(gcTimeLine.getParameters());
		Product product = new Product();
		Name productName = new Name();
		product.setName(productName);
		if (gcSourceNameLine != null)
			product.setProductId(gcSourceNameLine.getParameters());
//		Supplier supplier = new Supplier();
//		Link supplierLink = new Link();
//		supplierLink.setRef("Supplier");
////		supplierLink.setTarget(value);
//		supplier.setLink(supplierLink);
//		product.setSupplier(supplier);
		if (gcVersionLine != null)
			product.setVersion(gcVersionLine.getParameters());
		fileCreation.setProduct(product);
//		fileCreation.setTime(value);
		this.setFileCreation(fileCreation);
	}
	
	public static boolean isEmpty(FileCreation fileCreation) {return true;}
}
