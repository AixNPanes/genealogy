package ws.daley.genealogy.db.places;

import java.io.Serializable;
import java.util.HashMap;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Fixup implements Serializable
{
	@Transient
	private static final long serialVersionUID = 1L;	

	@Transient
	private static HashMap<String, String> map = new HashMap<String, String>();
    public static HashMap<String, String> getMap() {return map;}
    public static void setMap(HashMap<String, String> map) {Fixup.map = map;}
	
    @Id
	private String name;
	public String getName() {return this.name;}
	public void setName(String name) {this.name = name;}

	@Basic
	private String fixup;
	public String getFixup() {return this.fixup;}
	public void setFixup(String fixup) {this.fixup = fixup;}
	
	public Fixup() {super();}
	
	public Fixup(String name, String fixup)
	{
		this.name = name;
		this.fixup = fixup;
		map.put(name.toLowerCase(), fixup);
	}
}
