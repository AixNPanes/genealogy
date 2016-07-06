package ws.daley.genealogy.datasubpanel.plancurrenttree;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class PlanCurrentTreeLongevityLabel extends JLabel
{
	public PlanCurrentTreeLongevityLabel(String birthString, String deathString)
	{
		super();
		String birth = birthString == null?"":birthString;
		String death = deathString == null?"":deathString;
		String dash = (!"".equals(birthString) && !"".equals(deathString))?"-":"";
		super.setText(birth + dash + death);
	}
}
