package ws.daley.gedcom.xml;

import ws.daley.gedcom.Name;
import ws.daley.genealogy.gedcom.object.GcBaseElement;
import ws.daley.genealogy.gedcom.structure.record.GcHeaderRecord;
import ws.daley.genealogy.gedcom.structure.util.BaseElementVector;
import ws.daley.genealogy.gedcom.structure.util.BaseElementVectorMap;

public class GxFileCreationName extends Name
{
//	   protected String value;
//	    @XmlAttribute(name = "Type")
//	    protected String type;
//	    @XmlAttribute(namespace = "http://www.w3.org/XML/1998/namespace")
//	    protected String lang;
	public GxFileCreationName() {}
	
	public GxFileCreationName(GcHeaderRecord headerRecord)
	{
		if (headerRecord != null)
		{
			BaseElementVectorMap map = headerRecord.getVectorMap();
			if (map != null)
			{
				BaseElementVector fileVector = map.get("FILE");
				if (fileVector != null && fileVector.size() != 0)
				{
					GcBaseElement fileElement = fileVector.get(0);
					if (fileElement != null)
					{
						String fileName = fileElement.getParameters();
						if (fileName != null && "".equals(fileName))
							fileName = null;
						if (fileName != null)
						{
							this.value = fileName;
							this.type = "Filename";
							BaseElementVector charsetVector = map.get("CHAR");
							if (charsetVector != null && charsetVector.size() != 0)
							{
								GcBaseElement charsetElement = charsetVector.get(0);
								if (charsetElement != null)
								{
									String charsetName = charsetElement.getParameters();
									if (charsetName != null && "".equals(charsetName))
										charsetName = null;
									if (charsetName != null)
									{
										this.lang = charsetName;
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	public boolean empty()
	{
		 return this.value != null;
	}
}
