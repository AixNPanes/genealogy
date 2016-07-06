package ws.daley.genealogy.db.places;

public class URL
{
	private static Long idNo = new Long(-1);
	
	private Long id = ++idNo;
    public Long getId() {return this.id;}
    public void setId(Long id) {this.id = id;}

	private String name;
    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}

	private String title;
    public String getTitle() {return this.title;}
    public void setTitle(String title) {this.title = title;}

    public URL() {}
    
    public URL(String name, String title)
    {
    	this.name = name;
    	this.title = title;
    }
}
