package ws.daley.genealogy.db.places;

import java.io.Serializable;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import ws.daley.genealogy.db.IdNumber;

@Entity
public class UrlType extends IdNumber implements Serializable
{
	@Transient
	private static final long serialVersionUID = 1L;

	@Transient
	private static HashMap<Integer, UrlType> urlTypeMap = new HashMap<Integer, UrlType>();
	public static HashMap<Integer, UrlType> getUrlTypeMap() {return urlTypeMap;}
	public static void setUrlTypeMap(HashMap<Integer, UrlType> map) {urlTypeMap = map;}
	
	@Id
	private String name;
	public String getName() {return this.name;}
	public void setName(String name) {this.name = name;}

	public UrlType() {super();} 
	
	private UrlType(String name)
	{
		this();
		this.name = name;
		urlTypeMap.put(urlTypeMap.size()+1, this);
	}
	
	public static UrlType getUrlType(String urlTypeName)
	{
		UrlType urlType = urlTypeMap.get(urlTypeName);
		if (urlType != null)
			return urlType;
		return new UrlType(urlTypeName);
	}
	
}
