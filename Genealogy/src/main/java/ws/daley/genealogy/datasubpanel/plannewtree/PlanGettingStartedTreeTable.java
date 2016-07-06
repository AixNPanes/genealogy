package ws.daley.genealogy.datasubpanel.plannewtree;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.util.Util;

@SuppressWarnings("serial")
public class PlanGettingStartedTreeTable extends JTable
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PlanGettingStartedTreeTable.class);

	protected SpringLayout springLayout = new SpringLayout();
	protected static final int xPad = 5;
	protected static final int yPad = 5;

	private static String[] columnNames = { "Tree", "Date" };
	private static String[][] data = {
		{ "Daley.mft", "2016-03-01" }
	};

	private JTable treeTable = new JTable(data, columnNames);
	private JScrollPane scrollPane = new JScrollPane(this.treeTable);

	public PlanGettingStartedTreeTable()
	{
		log.trace("Entering");
		this.setLayout(new BorderLayout());
		this.add(this.scrollPane, BorderLayout.CENTER);
		log.trace("Exitting");
	}

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(
				Util.getMax(this.treeTable.getPreferredSize().width,
					this.scrollPane.getPreferredSize().width),
				this.treeTable.getPreferredSize().height +
					this.scrollPane.getPreferredSize().height + yPad);
	}
}
