package ws.daley.genealogy.datasubpanel.plannewtree.panes;

import static javax.swing.SpringLayout.EAST;
import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SpringLayout.WEST;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.components.family.FamilyEntryBox;
import ws.daley.genealogy.components.family.FamilyLabeledEntryBox;
import ws.daley.genealogy.components.family.FamilySexSelectionBox;
import ws.daley.genealogy.datasubpanel.plannewtree.PlanOpenType;
import ws.daley.genealogy.datasubpanel.plannewtree.PlanPane;
import ws.daley.genealogy.datasubpanel.plannewtree.PlanTreeFilePane;
import ws.daley.genealogy.util.Util;

@SuppressWarnings("serial")
public class PlanNewTreeEnterWhatYouKnowPane extends PlanPane
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PlanNewTreeEnterWhatYouKnowPane.class);

	private JLabel startANewTreeHeader = new JLabel("Start a New Tree");
	public JLabel letsStartByEnteringWhatYouNew = new JLabel("Let's start by entering what you know");
	public FamilyEntryBox familyEntryBox = new FamilyEntryBox();
	public FamilySexSelectionBox sexSelectionBox = new FamilySexSelectionBox();
	public FamilyLabeledEntryBox birthDate = new FamilyLabeledEntryBox("Birth date:");
	public FamilyLabeledEntryBox birthPlace = new FamilyLabeledEntryBox("Birth place:");
	public PlanTreeFilePane planTreeFileNewPane = new PlanTreeFilePane(PlanOpenType.NEW);

	public PlanNewTreeEnterWhatYouKnowPane()
	{
		super("Enter what you know");
		log.trace("Entering");
		this.header = this.startANewTreeHeader;
		this.lastComponent = this.planTreeFileNewPane;
		this.add(this.startANewTreeHeader);
		this.add(this.letsStartByEnteringWhatYouNew);
		this.add(this.familyEntryBox);
		this.add(this.sexSelectionBox);
		this.add(this.birthDate);
		this.add(this.birthPlace);
		this.add(this.planTreeFileNewPane);

		this.familyEntryBox.individual.textField.getDocument().addDocumentListener(new DocumentListener()
		{
			private void updateTreeName()
			{
				String text = PlanNewTreeEnterWhatYouKnowPane.this.familyEntryBox.individual.textField.getText();
				PlanNewTreeEnterWhatYouKnowPane.this.planTreeFileNewPane.treeName.newTreeName.textField.setText(text);
			}

			@Override public void insertUpdate(@SuppressWarnings("unused") DocumentEvent e) {updateTreeName();}
			@Override public void removeUpdate(@SuppressWarnings("unused") DocumentEvent e) {updateTreeName();}
			@Override public void changedUpdate(@SuppressWarnings("unused") DocumentEvent e) {}
		});

		this.springLayout.putConstraint(WEST, this.startANewTreeHeader, arcs.width / 2, WEST, this);
		this.springLayout.putConstraint(EAST, this.startANewTreeHeader, -arcs.width / 2, EAST, this);
		this.springLayout.putConstraint(NORTH, this.startANewTreeHeader, arcs.height / 2, NORTH, this);
		this.springLayout.putConstraint(WEST, this.letsStartByEnteringWhatYouNew, 0, WEST, this.startANewTreeHeader);
		this.springLayout.putConstraint(NORTH, this.letsStartByEnteringWhatYouNew, arcs.height, SOUTH, this.startANewTreeHeader);
		this.springLayout.putConstraint(WEST, this.familyEntryBox, 0, WEST, this.startANewTreeHeader);
//		this.springLayout.putConstraint(EAST, this.familyEntryBox, 0, EAST, this);
		this.springLayout.putConstraint(NORTH, this.familyEntryBox, yPad, SOUTH, this.letsStartByEnteringWhatYouNew);
		this.springLayout.putConstraint(WEST, this.sexSelectionBox, 0, WEST, this.startANewTreeHeader);
//		this.springLayout.putConstraint(EAST, this.sexSelectionBox, 0, EAST, this);
		this.springLayout.putConstraint(NORTH, this.sexSelectionBox, yPad, SOUTH, this.familyEntryBox);
		this.springLayout.putConstraint(WEST, this.birthDate, 0, WEST, this.startANewTreeHeader);
		this.springLayout.putConstraint(NORTH, this.birthDate, yPad, SOUTH, this.sexSelectionBox);
		this.springLayout.putConstraint(WEST, this.birthPlace, 5, EAST, this.birthDate);
//		this.springLayout.putConstraint(EAST, this.birthPlace, 0, EAST, this);
		this.springLayout.putConstraint(NORTH, this.birthPlace, 0, NORTH, this.birthDate);
		this.springLayout.putConstraint(WEST, this.planTreeFileNewPane, 0, WEST, this.startANewTreeHeader);
//		this.springLayout.putConstraint(EAST, this.planTreeFileNewPane, 0, EAST, this);
		this.springLayout.putConstraint(NORTH, this.planTreeFileNewPane, yPad, SOUTH, this.birthPlace);
		this.springLayout.putConstraint(SOUTH, this.planTreeFileNewPane, 0, SOUTH, this);
		log.trace("Exiting");
	}

	@Override
	public Dimension getPreferredSize()
	{
		
		return new Dimension(
			Util.getMax(this.startANewTreeHeader.getPreferredSize().width,
					this.letsStartByEnteringWhatYouNew.getPreferredSize().width,
					this.familyEntryBox.getPreferredSize().width,
					this.sexSelectionBox.getPreferredSize().width,
					this.birthDate.getPreferredSize().width,
					this.birthPlace.getPreferredSize().width,
					this.planTreeFileNewPane.getPreferredSize().width) + arcs.width,
			this.startANewTreeHeader.getPreferredSize().width + 
				this.letsStartByEnteringWhatYouNew.getPreferredSize().width +
				this.familyEntryBox.getPreferredSize().width +
				this.sexSelectionBox.getPreferredSize().width +
				this.birthDate.getPreferredSize().width +
				this.birthPlace.getPreferredSize().width +
				this.planTreeFileNewPane.getPreferredSize().width + yPad * 6 * arcs.height
			);
	}
}
