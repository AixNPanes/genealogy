package ws.daley.genealogy.db.places;

import java.io.Serializable;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.Transient;

import ws.daley.genealogy.db.IdNumber;

@Entity
public class Header extends IdNumber implements Serializable
{
	@Transient
	private static final long serialVersionUID = 1L;
	
	@Transient
	private static HashMap<String, Header> headerMap = new HashMap<String, Header>();
	public static HashMap<String, Header> getHeaderMap() {return headerMap;}
	public static void setHeaderMap(HashMap<String, Header> map) {headerMap = map;}
	
	public Header() {super();}
	
	public static Header getHeader()
	{
		if (headerMap.size() == 0)
			headerMap.put("", new Header());
		return headerMap.entrySet().iterator().next().getValue();
	}
}
