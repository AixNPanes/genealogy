package ws.daley.genealogy.datasubpanel.plancurrenttree;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.components.MyPane;

@SuppressWarnings("serial")
public class PlanCurrentTreeTablePane extends MyPane
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PlanCurrentTreeTablePane.class);

	private SpringLayout springLayout = new SpringLayout();

	private static String[][] objectData = new String[][]{
		{"People:", "2205"},
		{"Marriages:", "677"},
		{"File Size", "12,893 KB"},
		{"Generations", "15"},
		{"Surnames:", "517"},
		{"Average lifespan:", "60.4"},
		{"Earliest birth date:", "Eva C. Butts"},
		{"", "Abt 191 AD"},
		{"Most recent birth date:", "Yoelies Milagros Soto"},
		{"", "03 Mar 2004"},
		{"Facts", "10,244"},
		{"Places", "663"},
		{"Sources", "476"},
		{"Citations", "7,171"},
		{"Media", "774"}
	};
	private static JTable table = new JTable(objectData, new String[]{"", ""});
	private JScrollPane scrollPane = new JScrollPane(table, VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_NEVER);

	public PlanCurrentTreeTablePane()
	{
		super(null, null, null);
		log.trace("Entering");
		this.setLayout(this.springLayout);
		this.setBackground(Color.RED);
		this.add(this.scrollPane);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		this.springLayout.putConstraint(SpringLayout.NORTH, this.scrollPane, 0, SpringLayout.NORTH, this);
		this.springLayout.putConstraint(SpringLayout.WEST, this.scrollPane, 0, SpringLayout.WEST, this);
		this.springLayout.putConstraint(SpringLayout.EAST, this.scrollPane, 0, SpringLayout.EAST, this);
		this.springLayout.putConstraint(SpringLayout.SOUTH, this.scrollPane, 0, SpringLayout.EAST, this);
//		SpringLayout.Constraints x = this.springLayout.getConstraints(this.scrollPane);
//		x.setWidth(Spring.constant(100));
//		x.setHeight(Spring.constant(500));
		log.trace("Exiting");
	}

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(100, 100);
	}
}
