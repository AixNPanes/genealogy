package ws.daley.genealogy.datasubpanel.plannewtree;

import static javax.swing.SpringLayout.EAST;
import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SpringLayout.WEST;

import java.awt.Dimension;

import javax.swing.SpringLayout;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.MyFamily;
import ws.daley.genealogy.components.MyPane;
import ws.daley.genealogy.util.Util;

@SuppressWarnings("serial")
public class PlanTreeFilePane extends MyPane
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PlanNewTreePane.class);

	private SpringLayout springLayout = new SpringLayout();
	@SuppressWarnings("unused")
	private static final int xPad = 5;
	private static final int yPad = 5;

	private PlanOpenType openType;

	public PlanTreeFileTypePane planTreeFileTypePane;
	public PlanTreeName treeName;

	public PlanTreeFilePane(PlanOpenType openType)
	{
		super("File Tree", null, null);
		log.trace("Entering");
		this.openType = openType;
		this.setOpaque(false);
		this.setLayout(this.springLayout);
		this.setBackground(MyFamily.myFamily.getBackground());

		this.treeName = new PlanTreeName(openType);

		this.add(this.treeName);

		if (PlanOpenType.SHOW.equals(openType))
		{
			this.planTreeFileTypePane = new PlanTreeFileTypePane();
			this.add(this.planTreeFileTypePane);
			this.springLayout.putConstraint(WEST, this.planTreeFileTypePane, 0, WEST, this);
			this.springLayout.putConstraint(NORTH, this.planTreeFileTypePane, 0, NORTH, this);
			this.springLayout.putConstraint(EAST, this.planTreeFileTypePane, 0, EAST, this);
			this.springLayout.putConstraint(WEST, this.treeName, 0, WEST, this);
			this.springLayout.putConstraint(NORTH, this.treeName, yPad, SOUTH, this.planTreeFileTypePane);
		}
		else
		{
			this.springLayout.putConstraint(WEST, this.treeName, 0, WEST, this);
			this.springLayout.putConstraint(NORTH, this.treeName, 0, NORTH, this);
			this.springLayout.putConstraint(EAST, this.treeName, 0, EAST, this);
		}

		log.trace("Esiting");
	}

	public PlanTreeFileTypePane getPlanTreeFileTypePane() {return this.planTreeFileTypePane;}

	@Override
	public Dimension getPreferredSize()
	{
		boolean b = PlanOpenType.SHOW.equals(this.openType); 
		int fileTypePaneWidth = b?this.planTreeFileTypePane.getPreferredSize().width:0;
		int fileTypePaneHeight = b?this.planTreeFileTypePane.getPreferredSize().height:0;
		int width = Util.getMax(
				fileTypePaneWidth,
				this.treeName.getPreferredSize().width);
		int height = fileTypePaneHeight +
				this.treeName.getPreferredSize().height + yPad * 1;
		return new Dimension(
				width, 
				height);
	}
}
