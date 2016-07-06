package ws.daley.genealogy.gedcom.attribute;


/**
 * ENTRY_RECORDING_DATE:=	{Size=1:90} 
 * <DATE_VALUE> 
 * 
 * The date that this event data was entered into the original source document.
 */

public class GcEntryRecordingDateAttribute extends GcDateValueAttribute
{
	static{
		map = AttributeDescriptorMap.newFromArray(new AttributeDescriptor[]{
				new AttributeDescriptor("ENTRY_RECORDING_DATE", 1, 90, GcEntryRecordingDateAttribute.class),
		});
	}

}
