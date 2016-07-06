package ws.daley.genealogy.db.xls;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class XlsPlace
{
	private static Long idNo = 1l;
	
	private static TreeMap<Long, XlsPlace> placeMap = new TreeMap<Long, XlsPlace>();
	public static TreeMap<Long, XlsPlace> getPlaceMap() {return placeMap;}
	public static void setPlaceMap(TreeMap<Long, XlsPlace> newPlaceMap) {placeMap = newPlaceMap;}
	public static void addPlace(Long newId, XlsPlace newPlace)
	{
		if (placeMap.get(newId) != null)
			throw new RuntimeException("placeMap already has key "+newId);
		placeMap.put(newId, newPlace);
	}
	
	private Long id = ++idNo;
	public Long getId() {return this.id;}
	public void setId(Long id) {this.id = id;}
    
    private String country;
    public String getCountry() {return this.country;}
    public void setCountry(String country) {this.country = country;}

    private String state;
    public String getState() {return this.state;}
    public void setState(String state) {this.state = state; }

    private String county;
    public String getCounty() {return this.county;}
    public void setCounty(String county) {this.county = county;}

    private String city;
    public String getCity() {return this.city;}
    public void setCity(String city) {this.city = city;}

    private String locale1;
    public String getLocale1() {return this.locale1;}
    public void setLocale1(String locale1) {this.locale1 = locale1;}

    private String locale2;
    public String getLocale2() {return this.locale2;}
    public void setLocale2(String locale2) {this.locale2 = locale2;}

    private String locale3;
    public String getLocale3() {return this.locale3;}
    public void setLocale3(String locale3) {this.locale3 = locale3;}
    
    private List<XlsUrl> urls = new ArrayList<XlsUrl>();
    public List<XlsUrl> getUrls() {return this.urls;}
    public void setUrls(List<XlsUrl> urls) {this.urls = urls;}
    public void addUrl(XlsUrl url) {this.urls.add(url);}
    
    private List<XlsPlaceName> names = new ArrayList<XlsPlaceName>();
    public List<XlsPlaceName> getNames() {return this.names;}
    public void setNames(List<XlsPlaceName> names) {this.names = names;}
    public void addName(XlsPlaceName name) {this.names.add(name);}

    public XlsPlace() {}
    
    public XlsPlace(String[] element)
    {
    	switch(element.length)
    	{
    		case 7:
    			setLocale3(element[6]);
    		case 6:
    			setLocale2(element[5]);
    		case 5:
    			setLocale1(element[4]);
    		case 4:
    			setCity(element[3]);
    		case 3:
    			setCounty(element[2]);
    		case 2:
    			setState(element[1]);
    		case 1:
    			setCountry(element[0]);
    	}
    }

    public XlsPlace(HSSFRow row)
    {
    	setCountry(getCell(row, 0));
    	setState(getCell(row, 1));
    	setCounty(getCell(row, 2));
    	setCity(getCell(row, 3));
    	setLocale1(getCell(row, 4));
    	setLocale2(getCell(row, 5));
    	setLocale3(getCell(row, 6));

    	XlsPlaceName placeName = new XlsPlaceName(this);
    	this.names.add(placeName);

    	for(int i = 1; i < 6; i++)
    	{
    		String url = getCell(row, i+6);
    		if (url != null)
    	    	addUrl(new XlsUrl(this, i, url));
    	}
    }
    
    private String getCell(HSSFRow row, int cell)
    {
    	if (row.getFirstCellNum() <= cell && cell <= row.getLastCellNum())
    		if (row.getCell(cell) != null)
    			return row.getCell(cell).getStringCellValue();
    	return null;
    }
    
    public static void buildXlsDBFromSpreadsheet(String fileName) throws IOException
    {
		File file = new File(fileName);
		FileInputStream stream = new FileInputStream(file);
		HSSFWorkbook wb = new HSSFWorkbook(stream);
		HSSFSheet sheet = wb.getSheetAt(0);
		for(int i = sheet.getFirstRowNum()+1; i <= sheet.getLastRowNum(); i++)
		{
			XlsPlace xls = new XlsPlace(sheet.getRow(i));
			XlsPlace.addPlace(xls.getId(), xls);
		}
    }
    
    public static void addPlaceLists()
    {
    	XlsCountry.addPlaceLists();
    	XlsState.addPlaceLists();
    	XlsCounty.addPlaceLists();
    }
}
