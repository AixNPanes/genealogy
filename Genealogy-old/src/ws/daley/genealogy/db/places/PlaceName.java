package ws.daley.genealogy.db.places;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Transient;

import ws.daley.genealogy.db.IdNumber;

@Entity
public class PlaceName extends IdNumber implements Serializable
{
	@Transient
	private static final long serialVersionUID = 1L;	
	 
	@Basic
	private String name;
	public String getName() {return this.name;}
	public void setName(String name) {this.name = name;}

	public PlaceName() {super();} 

	public PlaceName(String name)
	{
		super();
		this.name = name;
	} 
}
