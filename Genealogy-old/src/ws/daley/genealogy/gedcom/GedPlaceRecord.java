package ws.daley.genealogy.gedcom;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.xerces.dom.NodeImpl;

public class GedPlaceRecord extends GedID
{
	private static Pattern p = Pattern.compile("^@P(\\d+)@$");
	private String value = null;
	
	public GedPlaceRecord(NodeImpl node)
	{
		super(node);
		this.value = GedDocument.getChildAttribute(this, "value");
		Location.generateLocation(this);
	}
	
//	@Override
//    public void reorg()
//	{
//		if (this.reorganized)
//			return;
//		super.reorg();
//	}
    
    public String getValue() {return this.value;}
    public int getPlaceIdNumber() {
    	Matcher m = p.matcher(this.getId());
    	if (m.matches())
    		return Integer.parseInt(m.group(1));
    	return -1;
    }
}
