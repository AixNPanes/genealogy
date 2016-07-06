package ws.daley.genealogy.db.places;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class PlaceStructure extends PlaceType implements Serializable
{
	@Transient
	private static final long serialVersionUID = 1L;	

	@Basic
	private String abbrev;
	public String getAbbrev() {return this.abbrev;}
	public void setAbbrev(String abbrev) {this.abbrev = abbrev;}
	   
	@OneToMany
	private ArrayList<PlaceAlias> aliases = new ArrayList<PlaceAlias>();
	public ArrayList<PlaceAlias> getAliases() {return this.aliases;}
	public void setAliases(ArrayList<PlaceAlias> aliases) {this.aliases = aliases;}
	   
	@OneToMany
	private ArrayList<URL> urls = new ArrayList<URL>();
	public ArrayList<URL> getUrls() {return this.urls;}
	public void setUrls(ArrayList<URL> urls) {this.urls = urls;}

	public PlaceStructure() {super();}
	
	public PlaceStructure(String name, PlaceType.TYPE type, String abbrev, ArrayList<PlaceAlias> aliases)
	{
		super(name, type);
		this.abbrev = abbrev;
		this.aliases = aliases!=null?aliases:(new ArrayList<PlaceAlias>());
	}
}
