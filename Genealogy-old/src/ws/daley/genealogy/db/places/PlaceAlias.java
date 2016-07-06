package ws.daley.genealogy.db.places;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class PlaceAlias extends PlaceType implements Serializable
{
	@Transient
	private static final long serialVersionUID = 1L;
	
	@Basic
	private String alias;
	public String getAlias() {return this.alias;}
	public void setAlias(String alias) {this.alias = alias;}

	public PlaceAlias() {super();}
	
	public PlaceAlias(String name, PlaceType.TYPE type, String alias)
	{
		super(name, type);
		this.alias = alias;
	}
}
