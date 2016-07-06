package ws.daley.genealogy.datasubpanel.plannewtree.panes;

import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SpringLayout.WEST;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.datasubpanel.plannewtree.PlanButton;
import ws.daley.genealogy.datasubpanel.plannewtree.PlanGettingStartedTreeTable;
import ws.daley.genealogy.datasubpanel.plannewtree.PlanNewTreePane;
import ws.daley.genealogy.datasubpanel.plannewtree.PlanPane;
import ws.daley.genealogy.util.Util;

@SuppressWarnings("serial")
public class PlanNewTreeGettingStartedPane extends PlanPane implements ActionListener
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PlanNewTreeGettingStartedPane.class);

	private JLabel gettingStartedHeader = new JLabel("Getting Started");
	private JLabel startANewTreeLabel = new JLabel("Start a new tree:");
	public PlanButton enterWhatYouKnowButton = new PlanButton("Enter What You Know");
	private PlanButton importAnExistingTreeButton = new PlanButton("Import an Existing Tree");
	public PlanButton downloadATreeFromAncestryButton = new PlanButton("Download a Tree from Ancestry");
	private JLabel openATreeLabel = new JLabel("Open a tree:");
	public PlanGettingStartedTreeTable treeTable = new PlanGettingStartedTreeTable();

	public PlanNewTreeGettingStartedPane()
	{
		super("Getting started");
		log.trace("Entering");
		this.header = this.gettingStartedHeader;
		this.lastComponent = this.treeTable;

		this.enterWhatYouKnowButton.setActionCommand("enter");
		this.enterWhatYouKnowButton.addActionListener(this);
		this.importAnExistingTreeButton.setActionCommand("import");
		this.importAnExistingTreeButton.addActionListener(this);
		this.downloadATreeFromAncestryButton.setActionCommand("download");
		this.downloadATreeFromAncestryButton.addActionListener(this);
		
		this.add(this.gettingStartedHeader);
		this.add(this.startANewTreeLabel);
		this.add(this.enterWhatYouKnowButton);
		this.add(this.importAnExistingTreeButton);
		this.add(this.downloadATreeFromAncestryButton);
		this.add(this.openATreeLabel);
		this.add(this.treeTable);

		this.springLayout.putConstraint(WEST, this.gettingStartedHeader, arcs.width / 2, WEST, this);
		this.springLayout.putConstraint(NORTH, this.gettingStartedHeader, arcs.height / 2, NORTH, this);
		this.springLayout.putConstraint(WEST, this.startANewTreeLabel, arcs.width / 2, WEST, this);
//		this.springLayout.putConstraint(EAST, this.startANewTreeLabel, -(arcs.width / 2), EAST, this.gettingStartedHeader);
		this.springLayout.putConstraint(NORTH, this.startANewTreeLabel, arcs.height, SOUTH, this.gettingStartedHeader);
		this.springLayout.putConstraint(WEST, this.enterWhatYouKnowButton, arcs.width / 2, WEST, this);
//		this.springLayout.putConstraint(EAST, this.enterWhatYouKnowButton, -(arcs.width / 2), EAST, this.gettingStartedHeader);
		this.springLayout.putConstraint(NORTH, this.enterWhatYouKnowButton, 5, SOUTH, this.startANewTreeLabel);
		this.springLayout.putConstraint(WEST, this.importAnExistingTreeButton, arcs.width / 2, WEST, this);
//		this.springLayout.putConstraint(EAST, this.importAnExistingTreeButton, 0, EAST, this.gettingStartedHeader);
		this.springLayout.putConstraint(NORTH, this.importAnExistingTreeButton, 5, SOUTH, this.enterWhatYouKnowButton);
		this.springLayout.putConstraint(WEST, this.downloadATreeFromAncestryButton, arcs.width / 2, WEST, this);
//		this.springLayout.putConstraint(EAST, this.downloadATreeFromAncestryButton, -(arcs.width / 2), EAST, this.gettingStartedHeader);
		this.springLayout.putConstraint(NORTH, this.downloadATreeFromAncestryButton, 5, SOUTH, this.importAnExistingTreeButton);
		this.springLayout.putConstraint(WEST, this.openATreeLabel, arcs.width / 2, WEST, this);
//		this.springLayout.putConstraint(EAST, this.openATreeLabel, -(arcs.width / 2), EAST, this.gettingStartedHeader);
		this.springLayout.putConstraint(NORTH, this.openATreeLabel, 5, SOUTH, this.downloadATreeFromAncestryButton);
		this.springLayout.putConstraint(WEST, this.treeTable, arcs.width / 2, WEST, this);
//		this.springLayout.putConstraint(EAST, this.treeTable, -(arcs.width / 2), EAST, this.gettingStartedHeader);
		this.springLayout.putConstraint(NORTH, this.treeTable, 5, SOUTH, this.openATreeLabel);
		log.trace("Exiting");
	}

	public void enableEnterWhatYouKnow(boolean enable)
	{
		enableEnterWhatYouKnowSect(enable);
		importAnExistingTreeSect(!enable);
		downloadATreeFromAncestrySect(!enable);
	}

	public void importAnExistingTree(boolean enable)
	{
		enableEnterWhatYouKnowSect(!enable);
		importAnExistingTreeSect(enable);
		downloadATreeFromAncestrySect(!enable);
	}

	public void downloadATreeFromAncestry(boolean enable)
	{
		enableEnterWhatYouKnowSect(!enable);
		importAnExistingTreeSect(!enable);
		downloadATreeFromAncestrySect(enable);
	}

	public void enableEnterWhatYouKnowSect(boolean enable)
	{
		PlanNewTreePane parent = (PlanNewTreePane)this.getParent();
		parent.enterWhatYouKnowPane.setVisible(enable);
		this.enterWhatYouKnowButton.setGradientFill(enable);
	}

	public void importAnExistingTreeSect(boolean enable)
	{
		PlanNewTreePane parent = (PlanNewTreePane)this.getParent();
		parent.importTreePane.setVisible(enable);
		this.importAnExistingTreeButton.setGradientFill(enable);
	}

	public void downloadATreeFromAncestrySect(boolean enable)
	{
		PlanNewTreePane parent = (PlanNewTreePane)this.getParent();
		parent.downloadTreePane.setVisible(enable);
		this.downloadATreeFromAncestryButton.setGradientFill(enable);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object source = e.getSource();
		if (source instanceof PlanButton)
		{
			String command = ((PlanButton)source).getActionCommand();
			if ("enter".equals(command))
				enableEnterWhatYouKnow(true);
			else if ("import".equals(command))
				importAnExistingTree(true);
			else if ("download".equals(command))
				downloadATreeFromAncestry(true);
			else
			{
			}
		}
	}

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(
				Util.getMax(this.gettingStartedHeader.getPreferredSize().width,
						this.startANewTreeLabel.getPreferredSize().width,
						this.enterWhatYouKnowButton.getPreferredSize().width,
						this.importAnExistingTreeButton.getPreferredSize().width,
						this.downloadATreeFromAncestryButton.getPreferredSize().width,
						this.openATreeLabel.getPreferredSize().width,
						this.treeTable.getPreferredSize().width)+ arcs.width,
				this.gettingStartedHeader.getPreferredSize().height +
				this.startANewTreeLabel.getPreferredSize().height +
				this.enterWhatYouKnowButton.getPreferredSize().height +
				this.importAnExistingTreeButton.getPreferredSize().height +
				this.downloadATreeFromAncestryButton.getPreferredSize().height +
				this.openATreeLabel.getPreferredSize().height +
				this.treeTable.getPreferredSize().height + yPad * 6 + arcs.height * 3 / 2);
	}
}
