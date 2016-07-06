package ws.daley.genealogy.components.family;

import static javax.swing.SpringLayout.EAST;
import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SpringLayout.WEST;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;
import javax.swing.SpringLayout;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.MyFamily;
import ws.daley.genealogy.laf.family.FamilyFamiyEntryUI;
import ws.daley.genealogy.util.Util;

@SuppressWarnings("serial")
public class FamilyEntryBox extends JPanel
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(FamilyEntryBox.class);

	private SpringLayout springLayout = new SpringLayout();
	private int xPad = 45;
	private int yPad = 5;

	public FamilyLabeledEntryBox individual = new FamilyLabeledEntryBox("Name:");
	public FamilyLabeledEntryBox father = new FamilyLabeledEntryBox("Father's name:");
	public FamilyLabeledEntryBox mother = new FamilyLabeledEntryBox("Mother's name:");

	public FamilyEntryBox()
	{
		log.trace("Entering");
		FamilyFamiyEntryUI.createUI(this);
		this.setLayout(this.springLayout);
		this.setBackground(MyFamily.myFamily.getBackground());
		this.add(this.individual);
        this.individual.setPreferredSize(new Dimension(10, 10));
        this.individual.setMinimumSize(new Dimension(10, 10));
		this.add(this.father);
		this.add(this.mother);
		this.setPreferredSize(new Dimension(10, 10));
		log.trace("Exitting");
	}

	@Override
	public void paint(Graphics g)
	{
        log.trace(this.getClass().getSimpleName() + " Entering paint(" + g + ")");
        super.paint(g);
        this.individual.setPreferredSize(new Dimension(100, 100));
		this.springLayout.putConstraint(WEST, this.individual, 0, WEST, this);
		this.springLayout.putConstraint(NORTH, this.father, 0, NORTH, this);
		this.springLayout.putConstraint(WEST, this.father, this.xPad, EAST, this.individual);
		this.springLayout.putConstraint(WEST, this.mother, 0, WEST, this.father);
		this.springLayout.putConstraint(NORTH, this.mother, this.yPad, SOUTH, this.father);
        super.paint(g);
		this.springLayout.putConstraint(NORTH, this.individual, (this.father.getY() + this.mother.getY()) / 2, NORTH, this);

		Point pIndividual = this.individual.rightAttach;
		Point pFather = this.father.leftAttach;
		Point pMother = this.mother.leftAttach;
		int midX = (pIndividual.x + pFather.x) / 2;
		int midTop = pFather.y;
		int midBottom = pMother.y;
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawLine(midX, midTop, midX, midBottom);
		g2d.drawLine(midX, midTop, pFather.x, midTop);
		g2d.drawLine(midX, midBottom, pMother.x, midBottom);
		g2d.drawLine(midX, pIndividual.y, pIndividual.x, pIndividual.y);
		g2d.dispose();
		this.setPreferredSize(new Dimension(this.father.getX() + this.father.getWidth(), this.mother.getY() + this.mother.getHeight()));
		this.setMinimumSize(this.getPreferredSize());
		this.setMaximumSize(this.getPreferredSize());
        super.paint(g);
        log.trace(this.getClass().getSimpleName() + " Exitting paint(" + g + ")");
	}

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(
				this.individual.getPreferredSize().width +
						Util.getMax(this.father.getPreferredSize().width, this.mother.getPreferredSize().width) + this.xPad,
				this.father.getPreferredSize().height +
					this.mother.getPreferredSize().height + this.yPad);
	}
}
