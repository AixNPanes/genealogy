package ws.daley.genealogy.datasubpanel.plannewtree;

public enum PlanOpenType
{
	NEW("new"),
	SHOW("show");

	private String openType;
	public String getOpenType() {return this.openType;}

	private PlanOpenType(String openType)
	{
		this.openType = openType;
	}
}