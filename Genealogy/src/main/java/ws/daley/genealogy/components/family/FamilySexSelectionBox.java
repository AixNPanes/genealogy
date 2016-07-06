package ws.daley.genealogy.components.family;

import javax.swing.JPanel;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.MyFamily;

@SuppressWarnings("serial")
public class FamilySexSelectionBox extends JPanel
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(FamilySexSelectionBox.class);

	private FamilySexLabel sexLabel = new FamilySexLabel();
	private FamilySexComboBox sexComboBox = new FamilySexComboBox();

	public FamilySexSelectionBox()
	{
		log.trace("Entering");
		this.setLayout(new FamilyLabeledEntryLayout(this, FamilyLabeledEntryLayout.Y_AXIS));
		this.setBackground(MyFamily.myFamily.getBackground());
		this.sexLabel.setAlignmentX(0);
		this.add(this.sexLabel);
		this.sexComboBox.setAlignmentX(0);
		this.add(this.sexComboBox);
		log.trace("Exitting");
	}
}
