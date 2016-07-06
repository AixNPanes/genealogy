package ws.daley.genealogy.datasubpanel.plancurrenttree;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class PlanCurrentTasksHeaderLabel extends JLabel
{
	public PlanCurrentTasksHeaderLabel(int tasks)
	{
		super("Tasks: "+tasks);
	}
}
