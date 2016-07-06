package ws.daley.genealogy.db.places;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@MappedSuperclass
public class PlaceType extends PlaceName implements Serializable
{
	@Transient
	private static final long serialVersionUID = 1L;	

	public enum TYPE{NONE, COUNTRY, STATE, COUNTY, CITY, LOCALE, SUB_LOCALE};
	
	@OneToOne
	private TYPE type;
	public TYPE getType() {return this.type;}
	public void setType(TYPE type) {this.type = type;}

	public PlaceType() {super();}
	
	public PlaceType(String name, TYPE type)
	{
		super(name);
		this.type = type;
	}
}
