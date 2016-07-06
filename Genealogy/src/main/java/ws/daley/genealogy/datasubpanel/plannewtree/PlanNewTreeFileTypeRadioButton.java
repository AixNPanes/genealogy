package ws.daley.genealogy.datasubpanel.plannewtree;

import javax.swing.JRadioButton;

@SuppressWarnings("serial")
public class PlanNewTreeFileTypeRadioButton extends JRadioButton
{
	private PlanImportFileType importFileType;
	public PlanImportFileType getImportFileType() {return this.importFileType;}

	public PlanNewTreeFileTypeRadioButton(PlanImportFileType importFileType)
	{
		super(importFileType.getName());
		this.importFileType = importFileType;
		this.setActionCommand(this.importFileType.getActionCommand());
		this.setEnabled(this.importFileType.enabled());
	}
}
