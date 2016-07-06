package ws.daley.genealogy;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.persistence.EntityManager;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import ws.daley.genealogy.db.places.City;
import ws.daley.genealogy.db.places.CompositePlace;
import ws.daley.genealogy.db.places.Country;
import ws.daley.genealogy.db.places.County;
import ws.daley.genealogy.db.places.Fixup;
import ws.daley.genealogy.db.places.Locale;
import ws.daley.genealogy.db.places.Place;
import ws.daley.genealogy.db.places.State;
import ws.daley.genealogy.db.places.URLType;
import ws.daley.genealogy.db.places.abstrct.PlaceStructure;
import ws.daley.genealogy.db.places.alias.CountryAlias;
import ws.daley.genealogy.db.places.alias.CountyAlias;
import ws.daley.genealogy.db.places.alias.StateAlias;
import ws.daley.genealogy.util.Util;

public class PlacesXls
{
//	private HSSFWorkbook wb = null;
//	private HSSFSheet sheet = null;
//	private HSSFRow titleRow = null;
//	private int firstCell;
//	private int lastNameCell;
//	private int firstMapCell;
//	private int lastCell;
//	private String[] mapTitles;
	private EntityManager entityManager;

	public PlacesXls() throws IOException
	{
		this.entityManager = XmlOrigToSQL.getEntityManager();
		generatePlaces();
	}

	public PlacesXls(String fileName) throws IOException
	{
		this.entityManager = XmlOrigToSQL.getEntityManager();
//		File file = new File(fileName);
//		FileInputStream stream = new FileInputStream(file);
//		this.wb = new HSSFWorkbook(stream);
//		this.sheet = this.wb.getSheetAt(0);
//		this.titleRow = this.sheet.getRow(0);
//		this.lastNameCell = 0;
//		this.firstMapCell = 1;
//		this.firstCell = this.titleRow.getFirstCellNum();
//		this.lastCell = this.titleRow.getLastCellNum();
//		for(int i = 1; i < this.lastCell; i++)
//		{
//			HSSFCell cell = this.titleRow.getCell(i);
//			if (cell == null)
//			{
//				this.lastNameCell = i;
//				this.firstMapCell = i+1;
//			}
//			else
//				break;
//		}
//		this.mapTitles = new String[this.lastCell - this.firstMapCell];
//		for(int i = 0; i < this.mapTitles.length; i++)
//			this.mapTitles[i] = this.titleRow.getCell(this.firstMapCell + i).getStringCellValue();
		generatePlaces();
	}
	
	public void generatePlaces() throws IOException
	{
		this.entityManager.getTransaction().begin();
		loadPlaces();
		this.entityManager.getTransaction().commit();
//		
//		this.entityManager.getTransaction().begin();
//		insertLocations();
//		this.entityManager.getTransaction().commit();
//		
//		this.entityManager.getTransaction().begin();
//		insertUrlTypes();
//		this.entityManager.getTransaction().commit();
//		
//		this.entityManager.getTransaction().begin();
//		buildCompositePlaces();
//		this.entityManager.getTransaction().commit();
	}
	
	public void loadPlaces()
	{
		this.entityManager.getTransaction().commit();
		this.entityManager.getTransaction().begin();
		Country.persist(this.entityManager);
		this.entityManager.getTransaction().commit();
		this.entityManager.getTransaction().begin();
		PlaceStructure.persistAliases(this.entityManager, "XlsCountry");
		this.entityManager.getTransaction().commit();
		this.entityManager.getTransaction().begin();
    	CountryAlias.load();
		this.entityManager.getTransaction().commit();
		
		this.entityManager.getTransaction().begin();
		State.persist(this.entityManager);
		this.entityManager.getTransaction().commit();
		this.entityManager.getTransaction().begin();
		PlaceStructure.persistAliases(this.entityManager, "XlsState");
		this.entityManager.getTransaction().commit();
		this.entityManager.getTransaction().begin();
    	StateAlias.load();
		this.entityManager.getTransaction().commit();
		
		this.entityManager.getTransaction().begin();
		County.persist(this.entityManager);
		this.entityManager.getTransaction().commit();
		this.entityManager.getTransaction().begin();
		PlaceStructure.persistAliases(this.entityManager, "County");
		this.entityManager.getTransaction().commit();
		this.entityManager.getTransaction().begin();
    	CountyAlias.load();
		this.entityManager.getTransaction().commit();
		
		this.entityManager.getTransaction().begin();
		City.persist(this.entityManager);
		this.entityManager.getTransaction().commit();
		this.entityManager.getTransaction().begin();
		PlaceStructure.persistAliases(this.entityManager, "City");
		this.entityManager.getTransaction().commit();
		
		this.entityManager.getTransaction().begin();
		Locale.persist(this.entityManager);
		this.entityManager.getTransaction().commit();
		this.entityManager.getTransaction().begin();
		PlaceStructure.persistAliases(this.entityManager, "Locale");
		this.entityManager.getTransaction().commit();
		this.entityManager.getTransaction().begin();
		
    	Fixup.load();
	}
	
	public void buildCompositePlaces()
	{
		for(Map.Entry<String, Place> placeEntry:Place.getPlaceMap().entrySet())
		{
			Place place = placeEntry.getValue();
			CompositePlace compositePlace = new CompositePlace(place);
			this.entityManager.persist(compositePlace);
		}
	}
	
//	public void insertUrlTypes()
//	{
//		for(String mapTitle:this.mapTitles)
//			this.entityManager.persist(new URLType(mapTitle));
//	}
	
//	private String[][] getLocationEntries()
//	{
//		ArrayList<String[]> column = new ArrayList<String[]>();
//		for(int i = this.sheet.getFirstRowNum(); i <= this.sheet.getLastRowNum(); i++)
//		{
//			String[] cell = new String[this.lastCell];
//			cell[0] = this.sheet.getRow(i).getCell(0).getStringCellValue();
//			for(int j = this.firstCell+1; j < this.lastCell; j++)
//				if (this.sheet.getRow(i).getCell(j) != null)
//					cell[j] = this.sheet.getRow(i).getCell(j).getStringCellValue();
//				else
//					cell[j] = null;
//			column.add(cell);
//		}
//		String[][] ret = new String[column.size()][];
//		return column.toArray(ret);
//	}
	
//	public ArrayList<String[][]> getLocationList()
//	{
//		String[][] locations = getLocationEntries();
//		ArrayList<String[][]> list = new ArrayList<String[][]>();
//		boolean firstTime = true;
//		for(String[] location:locations)
//		{
//			if (firstTime)
//			{
//				firstTime = false;
//				continue;
//			}
//			String[][] array = new String[2][];
//			array[0] = new String[this.lastNameCell-this.firstCell+1];
//			array[1] = new String[this.lastCell-this.firstMapCell];
//			for(int i = 0; i < array[0].length; i++)
//				array[0][i] = location[i];
//			array[0] = Util.reverse(array[0]);
//			for(int i = 0; i < array[1].length; i++)
//				if (location.length < (array[0].length+i+1))
//					array[1][i] = null;
//				else
//					array[1][i] = location[i+array[0].length];
//			list.add(array);
//		}
//		return list;
//	}
    
//    public void insertLocations() throws IOException
//    {
//    	Place.setTitles(this.getMapTitle());
//    	ArrayList<String[][]> list = this.getLocationList();
//		this.entityManager.getTransaction().commit();
//		for(int i = 1; i < list.size(); i++)
//		{
//			this.entityManager.getTransaction().begin();
//			try{Place.getPlace(list.get(i));}
//	    	catch(ArrayIndexOutOfBoundsException aiobe) {throw aiobe;}
//			catch(Throwable t)
//			{
//				int j = 0;
//			}
//			this.entityManager.getTransaction().commit();
//		}
//		for(Map.Entry<String, Place>entry:Place.getPlaceMap().entrySet())
//		{
//			this.entityManager.getTransaction().begin();
//			try{this.entityManager.persist(entry.getValue());}
//			catch(Throwable t)
//			{
//				int i = 0;
//			}
//			this.entityManager.getTransaction().commit();
//		}
//		this.entityManager.getTransaction().begin();
//    }
	
//	public String[] getMapTitle() {return this.mapTitles;}
//	public int getLocationSize() {return this.lastNameCell - this.firstCell + 1;}
//	public int getMapSize() {return this.lastCell - this.firstMapCell + 1;}
}
