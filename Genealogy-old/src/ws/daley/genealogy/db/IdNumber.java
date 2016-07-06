package ws.daley.genealogy.db;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.w3c.dom.Node;

@Entity
public class IdNumber extends GenealogyItem
{
	@Transient
	private static final long serialVersionUID = 1L;	

	@Transient
	private static long idNo = -1;
	
	@Id
	private long id = ++idNo;
	public long getId() {return this.id;}
	public void setId(long id) {this.id = id;}
	
	public IdNumber() {super();}
	
	public IdNumber(Node node) {super(node);}
}
