package ws.daley.genealogy.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;

import org.apache.openjpa.lib.util.concurrent.ConcurrentHashMap;
import org.apache.openjpa.persistence.OpenJPAEntityManager;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import ws.daley.genealogy.db.places.Fixup;
import ws.daley.genealogy.db.places.Place;
import ws.daley.genealogy.db.places.URL;
import ws.daley.genealogy.db.places.UrlType;
import ws.daley.genealogy.util.Util;

public class PlacesXls
{
	private HSSFWorkbook wb = null;
	private HSSFSheet sheet = null;
	private HSSFRow titleRow = null;
	private int firstCell;
	private int lastCell;
	private static HashMap<Integer, String> titles = new HashMap<Integer, String>();
	public static HashMap<Integer, String> getTitles() {return titles;}
	public static ConcurrentHashMap urlTypes = new ConcurrentHashMap();
	
	private EntityManager entityManager;

	public PlacesXls(OpenJPAEntityManager em, String fileName) throws IOException
	{
		this.entityManager = em;
		File file = new File(fileName);
		FileInputStream stream = new FileInputStream(file);
		this.wb = new HSSFWorkbook(stream);
		this.sheet = this.wb.getSheetAt(0);
		this.titleRow = this.sheet.getRow(0);
		this.firstCell = this.titleRow.getFirstCellNum();
		this.lastCell = this.titleRow.getLastCellNum();
		for(int i = 1; i < this.lastCell; i++)
			titles.put(i, this.titleRow.getCell(i).getStringCellValue());
	}
	
	private String[][] getLocationEntries()
	{
		ArrayList<String[]> column = new ArrayList<String[]>();
		for(int i = this.sheet.getFirstRowNum(); i <= this.sheet.getLastRowNum(); i++)
		{
			String[] cell = new String[this.lastCell];
			cell[0] = this.sheet.getRow(i).getCell(0).getStringCellValue();
			String fixup = Fixup.getMap().get(cell[0].toLowerCase());
			if (fixup != null && fixup.length() > 0)
				cell[0] = fixup;
			for(int j = this.firstCell+1; j < this.lastCell; j++)
				if (this.sheet.getRow(i).getCell(j) != null)
					cell[j] = this.sheet.getRow(i).getCell(j).getStringCellValue();
				else
					cell[j] = null;
			column.add(cell);
		}
		String[][] ret = new String[column.size()][];
		return column.toArray(ret);
	}
	
    public ArrayList<String[][]> getLocationList()
	{
		String[][] locations = getLocationEntries();
		ArrayList<String[][]> list = new ArrayList<String[][]>();
		for(String[] location:locations)
		{
			String[][] array = new String[locations.length][];
			for(int i = 1; i < location.length; i++)
				array[i] = new String[]{location[i]};
			if ("Washington, District of Columbia".equals(location[0]))
			{
				array[0] = new String[]{"USA", location[0]};
			}
			else
			{
				String loc = location[0].replaceAll("''", "'");
				String fixup = Fixup.getMap().get(loc);
				if (fixup != null)
					loc = fixup;
				array[0] = loc.split(",");
				for(int i = 0; i < array[0].length; i++)
					array[0][i] = array[0][i].trim();
			}
			list.add(array);
		}
		return list;
	}
    
    public void insertLocations()
    {
    	Place.setTitles(getTitles());
    	ArrayList<String[][]> list = this.getLocationList();
		for(int i = 1; i < list.size(); i++)
			getPlace(list.get(i));
		for(Map.Entry<String, Place>entry:Place.getPlaceMap().entrySet())
			this.entityManager.persist(entry.getValue());
    }

	
	public static Place getPlace(String[][] nameArray)
	{
		if (nameArray != null && nameArray.length >0 && nameArray[0] != null && nameArray[0].length > 0)
		{
			TreeMap<String, Place> placeMap = Place.getPlaceMap(); 
			Place place = placeMap.get(Util.join(nameArray[0], ", "));
			if (place != null)
			{
				for(int i = 1; i < nameArray.length; i++)
					if (nameArray[i] != null && nameArray[i].length > 0 && nameArray[i][0] != null && nameArray[i][0].length() > 0)
					{
						UrlType urlType = (UrlType)urlTypes.get(i);
						if (urlType != null)
						{
							ArrayList<URL> urls = place.fetchUrls();
							if (urls != null && urls.size() > 0)
							{
								boolean titleFound = false;
								for(URL url:urls)
									if (urlType.getName().equals(url.getUrlType()))
										titleFound = true;
								if (!titleFound)
									place.addUrl(new URL(urlType, nameArray[i][0]));
							}
						}
					}
			}
			else
			{
				place =  Place.fetchPlace(nameArray[0]);
				for(int i = 1; i < nameArray.length; i++)
					if (nameArray[i] != null && nameArray[i][0] != null && nameArray[i][0].length() > 0)
						place.addUrl(new URL(((UrlType)urlTypes.get(i)), nameArray[i][0]));
			}
			return place;
		}
		return null;
	}
}
