package ws.daley.genealogy.datasubpanel.plannewtree.panes;

import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SpringLayout.WEST;

import java.awt.Dimension;

import javax.swing.JLabel;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.datasubpanel.plannewtree.PlanPane;
import ws.daley.genealogy.util.Util;

@SuppressWarnings("serial")
public class PlanNewTreeDownloadTreePane extends PlanPane
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PlanNewTreeDownloadTreePane.class);
	
	public JLabel letsStartByDownloadingYourOnlineTreeFromAncestry = new JLabel("Let's start by download your onnline tree from Ancestry");
	public JLabel x = new JLabel("x");
	
	public PlanNewTreeDownloadTreePane()
	{
		super("Download tree");
		log.trace("Entering");
		this.header = this.letsStartByDownloadingYourOnlineTreeFromAncestry;
		this.lastComponent = this.x;

		this.add(this.letsStartByDownloadingYourOnlineTreeFromAncestry);
		this.add(this.x);

		this.springLayout.putConstraint(WEST, this.letsStartByDownloadingYourOnlineTreeFromAncestry, arcs.width / 2, WEST, this);
		this.springLayout.putConstraint(NORTH, this.letsStartByDownloadingYourOnlineTreeFromAncestry, arcs.width / 2, NORTH, this);
		this.springLayout.putConstraint(WEST, this.x, 0, WEST, this.letsStartByDownloadingYourOnlineTreeFromAncestry);
		this.springLayout.putConstraint(NORTH, this.x, arcs.height, SOUTH, this.letsStartByDownloadingYourOnlineTreeFromAncestry);
		log.trace("Exiting");
	}

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(
				Util.getMax(this.letsStartByDownloadingYourOnlineTreeFromAncestry.getPreferredSize().width,
						this.x.getPreferredSize().width) + arcs.width,
				this.letsStartByDownloadingYourOnlineTreeFromAncestry.getPreferredSize().height +
				this.x.getPreferredSize().height + arcs.height * 3 / 2 + yPad * 1);
	}
}
