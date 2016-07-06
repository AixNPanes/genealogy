package ws.daley.genealogy.gedcom.attribute;

/**
 * SOURCE_DESCRIPTIVE_TITLE: = {Size=1:248} 
 * The title of the work, record, or item and, when appropriate, the title of the larger work or series of which it is a part. 
 * 
 * For a published work, a book for example, might have a title plus the title of the series of which the book is a part. A magazine
 * article would have a title plus the title of the magazine that published the article. 
 * 
 * For An unpublished work, such as: 
 * 
 * A letter might include the date, the sender, and the receiver. 
 * 
 * A transaction between a buyer and seller might have their names and the transaction date. 
 * 
 * A family Bible containing genealogical information might have past and present owners and a physical description of the book. 
 * 
 * A personal interview would cite the informant and interviewer. 
 */

public class GcSourceDescriptiveTitleContAttribute extends Gc_Attribute
{
	public static AttributeDescriptorMap map = new AttributeDescriptorMap();
	
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("SOURCE_TITLE", 1, 248, GcSourceDescriptiveTitleContAttribute.class),
		});
	}

}
