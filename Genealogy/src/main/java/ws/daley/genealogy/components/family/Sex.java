package ws.daley.genealogy.components.family;

public enum Sex
{
	MALE ("Male"),
	FEMALE ("Female"),
	UNKNOWN("Unknown");

	private final String sex;
	public final String getSex() {return this.sex;}

	private Sex(String sex)
	{
		this.sex = sex;
	}

	public static Sex getSex(String sex)
	{
		String s = sex.toLowerCase();
		String male = "male";
		String female = "female";
		if (male.substring(0, Math.min(male.length(), s.length())).equals(s))
			return MALE;
		else if (female.substring(0, Math.min(female.length(), s.length())).equals(s))
			return FEMALE;
		return UNKNOWN;
	}
}
