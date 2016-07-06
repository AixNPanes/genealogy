package ws.daley.genealogy.datasubpanel.plancurrenttree;

public enum PlanType
{
	Home ("Home person"),
	Current ("Current person");

	private final String planType;
	public String getPlanType() {return this.planType;}

	private PlanType(String planType)
	{
		this.planType = planType;
	}
}
