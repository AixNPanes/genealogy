package ws.daley.genealogy.db.xls;

import java.util.TreeMap;

public class XlsUrl
{
	private static Long idNo = 1l;

	private static TreeMap<Long, XlsUrl> urlMap = new TreeMap<Long, XlsUrl>();
	public static TreeMap<Long, XlsUrl> getUrlMap() {return urlMap;}
	public static void setUrlMap(TreeMap<Long, XlsUrl> newUrlMap) {urlMap = newUrlMap;}
	public static void addUrl(Long newId, XlsUrl newUrl)
	{
		if (urlMap.get(newId) != null)
			throw new RuntimeException("urlMap already has key "+newId);
		urlMap.put(newId, newUrl);
	}

	private Long id = ++idNo;
	public Long getId() {return this.id;}
	public void setId(Long id) {this.id = id;}
	
	private XlsPlace xlsPlace;
	public XlsPlace getXlsPlace() {return this.xlsPlace;}
	public void setXlsPlace(XlsPlace xlsPlace) {this.xlsPlace = xlsPlace;}
	
	private int type;
	public int getType() {return this.type;}
	public void setType(int type) {this.type = type;}
	
	private String url;
	public String getUrl() {return this.url;}
	public void setUrl(String url) {this.url = url;}
	
	public XlsUrl() {}
	
	public XlsUrl(XlsPlace xlsPlace, int type, String url)
	{
		setXlsPlace(xlsPlace);
		setType(type);
		setUrl(url);
	}
}
