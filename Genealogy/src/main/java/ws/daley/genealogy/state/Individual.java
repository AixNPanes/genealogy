package ws.daley.genealogy.state;

import ws.daley.genealogy.components.family.Sex;

public class Individual
{
	private int id;
	public int getId() {return this.id;}
	public void setId(int id) {this.id = id;}

	private String firstName;
	public String getFirstName() {return this.firstName;}
	public void setFirstName(String firstName) {this.firstName = firstName;}

	private String middleName;
	public String getMiddleName() {return this.middleName;}
	public void setMiddleName(String middleName) {this.middleName = middleName;}

	private String surName;
	public String getSurName() {return this.surName;}
	public void setSurName(String surName) {this.surName = surName;}

	private String birth;
	public String getBirth() {return this.birth;}
	public void setBirth(String birth) {this.birth = birth;}

	private String death;
	public String getDeath() {return this.death;}
	public void setDeath(String death) {this.death = death;}

	private Sex sex;
	public Sex getSex() {return this.sex;}
	public void setSex(Sex sex) {this.sex = sex;}

	public String getName()
	{
		return this.firstName + " " + this.middleName + " " + this.surName;
	}
}
