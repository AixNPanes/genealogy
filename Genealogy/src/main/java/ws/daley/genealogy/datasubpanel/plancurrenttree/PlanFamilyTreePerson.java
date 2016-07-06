package ws.daley.genealogy.datasubpanel.plancurrenttree;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SpringLayout;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.components.MyPane;
import ws.daley.genealogy.components.family.Sex;
import ws.daley.genealogy.state.Individual;

@SuppressWarnings("serial")
public class PlanFamilyTreePerson extends MyPane
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PlanFamilyTreePerson.class);

	private SpringLayout springLayout = new SpringLayout();
	private static final int xPad = 5;
	private static final int yPad = 5;

	private PlanTypeLabel type = null;
	private PlanCurrentTreeIcon sexIcon = null;
	private JLabel personName = null;
	private PlanCurrentTreeLongevityLabel longevity = null;

	public PlanFamilyTreePerson(PlanType planType, Individual individual)
	{
		this(planType, individual.getSex(), individual.getName(), individual.getBirth(), individual.getDeath());
	}

	public PlanFamilyTreePerson(PlanType planType, Sex sex, String name, String birth, String death)
	{
		super(null, null, null);
		log.trace("Entering");
		this.setLayout(this.springLayout);
		this.setOpaque(false);

		this.type = new PlanTypeLabel(planType);
		this.sexIcon = new PlanCurrentTreeIcon(sex);
		this.personName = new JLabel(name);
		this.longevity = new PlanCurrentTreeLongevityLabel(birth, death);

		this.add(this.type);
		this.add(this.sexIcon);
		this.add(this.personName);
		this.add(this.longevity);

		this.springLayout.putConstraint(SpringLayout.NORTH, this.type, 0, SpringLayout.NORTH, this);
		this.springLayout.putConstraint(SpringLayout.WEST, this.type, 0, SpringLayout.WEST, this);

		this.springLayout.putConstraint(SpringLayout.NORTH, this.sexIcon, 0, SpringLayout.NORTH, this);
		this.springLayout.putConstraint(SpringLayout.WEST, this.sexIcon, xPad, SpringLayout.EAST, this.type);

		this.springLayout.putConstraint(SpringLayout.NORTH, this.personName, 0, SpringLayout.NORTH, this);
		this.springLayout.putConstraint(SpringLayout.WEST, this.personName, xPad, SpringLayout.EAST, this.sexIcon);

		this.springLayout.putConstraint(SpringLayout.NORTH, this.longevity, yPad, SpringLayout.SOUTH, this.personName);
		this.springLayout.putConstraint(SpringLayout.WEST, this.longevity, 0, SpringLayout.WEST, this.personName);

		log.trace("Exiting");
	}

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(this.type.getPreferredSize().width + this.sexIcon.getPreferredSize().width + xPad * 2 +
			Math.max(this.personName.getPreferredSize().width, this.longevity.getPreferredSize().width),
			Math.max(this.sexIcon.getY() + this.sexIcon.getHeight(), this.longevity.getY() + this.longevity.getHeight()));
	}
}
