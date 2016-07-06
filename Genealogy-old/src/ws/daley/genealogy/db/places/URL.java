package ws.daley.genealogy.db.places;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import ws.daley.genealogy.db.IdNumber;

@Entity
public class URL extends IdNumber implements Serializable
{
	@Transient
	private static final long serialVersionUID = 1L;	

	@OneToOne
	private UrlType urlType;
	public UrlType getUrlType() {return this.urlType;}
	public void setUrlType(UrlType urlType) {this.urlType = urlType;}

	@Basic
	private String url;
	public String getUrl() {return this.url;}
	public void setUrl(String url) {this.url = url;}
	
	public URL() {super();}
	
	public URL(UrlType urlType, String url)
	{
		super();
		this.urlType = urlType;
		this.url = url;
	}
}
