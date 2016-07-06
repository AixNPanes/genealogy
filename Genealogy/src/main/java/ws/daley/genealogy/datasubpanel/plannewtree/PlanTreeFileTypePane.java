package ws.daley.genealogy.datasubpanel.plannewtree;

import static javax.swing.SpringLayout.EAST;
import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SpringLayout.WEST;

import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.MyFamily;
import ws.daley.genealogy.components.MyPane;
import ws.daley.genealogy.util.Util;

@SuppressWarnings("serial")
public class PlanTreeFileTypePane extends MyPane
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PlanNewTreePane.class);

	private SpringLayout springLayout = new SpringLayout();
//	private static final int xPad = 5;
	private static final int yPad = 5;

	private JLabel familyTreeMakerCanImportTheFollowing = new JLabel("Family Tree Maker can import the following:");
	private PlanNewTreeFileTypeRadioButton familyTreeMakerVersion5OrNewer = new PlanNewTreeFileTypeRadioButton(PlanImportFileType.FAMILY_TREE_MAKER_5);
	private PlanNewTreeFileTypeRadioButton gedcom = new PlanNewTreeFileTypeRadioButton(PlanImportFileType.GEDCOM);
	private PlanNewTreeFileTypeRadioButton personalAncestralFilePAF = new PlanNewTreeFileTypeRadioButton(PlanImportFileType.PAF);
	private PlanNewTreeFileTypeRadioButton legacyFamilyTree = new PlanNewTreeFileTypeRadioButton(PlanImportFileType.LEGACY_FAMILY_TREE);
	public ButtonGroup importGroup = new ButtonGroup();

	public PlanTreeFileTypePane()
	{
		super("File Type", null, null);
		log.trace("Entering");
		this.setOpaque(false);
		this.setLayout(this.springLayout);
		this.setBackground(MyFamily.myFamily.getBackground());

		this.add(this.familyTreeMakerCanImportTheFollowing);
		this.add(this.familyTreeMakerVersion5OrNewer);
		this.add(this.personalAncestralFilePAF);
		this.add(this.legacyFamilyTree);
		this.add(this.gedcom);

		for(PlanNewTreeFileTypeRadioButton button:new PlanNewTreeFileTypeRadioButton[]{
				this.familyTreeMakerVersion5OrNewer,
				this.personalAncestralFilePAF,
				this.legacyFamilyTree,
				this.gedcom
			})
		{
			this.importGroup.add(button);
			if (button.isEnabled())
				this.importGroup.setSelected(button.getModel(), true);
		}
		this.importGroup.add(this.familyTreeMakerVersion5OrNewer);
		this.importGroup.add(this.personalAncestralFilePAF);
		this.importGroup.add(this.legacyFamilyTree);
		this.importGroup.add(this.gedcom);

		this.springLayout.putConstraint(EAST, this.familyTreeMakerCanImportTheFollowing, 0, EAST, this);
		this.springLayout.putConstraint(WEST, this.familyTreeMakerCanImportTheFollowing, 0, WEST, this);
		this.springLayout.putConstraint(NORTH, this.familyTreeMakerCanImportTheFollowing, 0, NORTH, this);

		this.springLayout.putConstraint(WEST, this.familyTreeMakerVersion5OrNewer, 0, WEST, this);
		this.springLayout.putConstraint(NORTH, this.familyTreeMakerVersion5OrNewer, yPad, SOUTH, this.familyTreeMakerCanImportTheFollowing);

		this.springLayout.putConstraint(WEST, this.personalAncestralFilePAF, 0, WEST, this);
		this.springLayout.putConstraint(NORTH, this.personalAncestralFilePAF, yPad, SOUTH, this.familyTreeMakerVersion5OrNewer);

		this.springLayout.putConstraint(WEST, this.legacyFamilyTree, 0, WEST, this);
		this.springLayout.putConstraint(NORTH, this.legacyFamilyTree, yPad, SOUTH, this.personalAncestralFilePAF);

		this.springLayout.putConstraint(WEST, this.gedcom, 0, WEST, this);
		this.springLayout.putConstraint(NORTH, this.gedcom, yPad, SOUTH, this.legacyFamilyTree);

		log.trace("Exiting");
	}

	public ButtonGroup getButtonGroup() {return this.importGroup;}

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(
				Util.getMax(this.familyTreeMakerCanImportTheFollowing.getPreferredSize().width,
					this.familyTreeMakerVersion5OrNewer.getPreferredSize().width,
					this.personalAncestralFilePAF.getPreferredSize().width,
					this.gedcom.getPreferredSize().width,
					this.legacyFamilyTree.getPreferredSize().width),
				this.familyTreeMakerCanImportTheFollowing.getPreferredSize().height +
					this.familyTreeMakerVersion5OrNewer.getPreferredSize().height +
					this.personalAncestralFilePAF.getPreferredSize().height +
					this.gedcom.getPreferredSize().height +
					this.gedcom.getPreferredSize().height + yPad * 4);
	}
}
