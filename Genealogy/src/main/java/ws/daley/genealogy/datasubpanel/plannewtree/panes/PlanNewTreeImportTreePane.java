package ws.daley.genealogy.datasubpanel.plannewtree.panes;

import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SpringLayout.WEST;

import java.awt.Dimension;

import javax.swing.JLabel;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.datasubpanel.plannewtree.PlanOpenType;
import ws.daley.genealogy.datasubpanel.plannewtree.PlanPane;
import ws.daley.genealogy.datasubpanel.plannewtree.PlanTreeFilePane;
import ws.daley.genealogy.util.Util;

@SuppressWarnings("serial")
public class PlanNewTreeImportTreePane extends PlanPane
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PlanNewTreeImportTreePane.class);

	private JLabel startANewTreeHeader = new JLabel("Start a New Tree");
	public JLabel letsStartByImportingAnExistingTree = new JLabel("Let's start by importing an existing tree");
	public PlanTreeFilePane planTreeFileShowPane = new PlanTreeFilePane(PlanOpenType.SHOW);

	public PlanNewTreeImportTreePane()
	{
		super("Import Tree");
		log.trace("Entering");
		this.header = this.startANewTreeHeader;
		this.lastComponent = this.planTreeFileShowPane;
		this.add(this.startANewTreeHeader);
		this.add(this.letsStartByImportingAnExistingTree);
		this.add(this.planTreeFileShowPane);

		this.springLayout.putConstraint(WEST, this.startANewTreeHeader, arcs.width / 2, WEST, this);
//		this.springLayout.putConstraint(EAST, this.startANewTreeHeader, -arcs.width / 2, EAST, this);
		this.springLayout.putConstraint(NORTH, this.startANewTreeHeader, arcs.height / 2, NORTH, this);
		this.springLayout.putConstraint(WEST, this.letsStartByImportingAnExistingTree, 0, WEST, this.startANewTreeHeader);
		this.springLayout.putConstraint(NORTH, this.letsStartByImportingAnExistingTree, arcs.height , SOUTH, this.startANewTreeHeader);
		this.springLayout.putConstraint(WEST, this.planTreeFileShowPane, 0, WEST, this.startANewTreeHeader);
//		this.springLayout.putConstraint(EAST, this.planTreeFileShowPane, 0, EAST, this.letsStartByImportingAnExistingTree);
		this.springLayout.putConstraint(NORTH, this.planTreeFileShowPane, yPad, SOUTH, this.letsStartByImportingAnExistingTree);
		this.springLayout.putConstraint(SOUTH, this.planTreeFileShowPane, 0, SOUTH, this);
		log.trace("Exiting");
	}

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(
				Util.getMax(this.startANewTreeHeader.getPreferredSize().width,
						this.letsStartByImportingAnExistingTree.getPreferredSize().width,
						this.planTreeFileShowPane.getPreferredSize().width) + arcs.width,
				this.startANewTreeHeader.getPreferredSize().height +
				this.letsStartByImportingAnExistingTree.getPreferredSize().height+
				this.planTreeFileShowPane.getPreferredSize().height + 2 * arcs.height  + 2 * yPad );
	}
}
