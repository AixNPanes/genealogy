package ws.daley.genealogy.datasubpanel.plancurrenttree;

import static javax.swing.SpringLayout.EAST;
import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SpringLayout.WEST;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.components.family.HumanReadableIntegerLabel;
import ws.daley.genealogy.components.family.IntegerLabel;
import ws.daley.genealogy.components.family.Separator;
import ws.daley.genealogy.util.Util;

@SuppressWarnings("serial")
public class PlanCurrentTreeFileStatisticsPane extends JPanel
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PlanCurrentTreePane.class);

	private SpringLayout springLayout = new SpringLayout();
	private static final int xPad = 5;
	private static final int yPad = 5;

	private JLabel statistics = new JLabel("Statistical information about file:");
	private JLabel fileName = new JLabel("Daley");
	private JLabel peopleTitle = new JLabel("People:");
	private JLabel people = new IntegerLabel(2205);
	private JLabel marriagesTitle = new JLabel("Marriages:");
	private JLabel marriages = new IntegerLabel(627);
	private JLabel fileSizeTitle = new JLabel("File size:");
	private JLabel fileSize = new HumanReadableIntegerLabel(12894000);
	private JLabel generationsTitle = new JLabel("Generations:");
	private JLabel generations = new IntegerLabel(15);
	private JLabel surnamesTitle = new JLabel("Surnames");
	private JLabel surnames = new IntegerLabel(517);
	private JLabel additionalInformationTitle = new JLabel("Additional information:");
	private Separator separator1 = new Separator(100, 1);
	private JLabel averageLifespanTitle = new JLabel("Average lifespan:");
	private JLabel averageLifespan = new IntegerLabel((int)60.4);
	private JLabel earliestBirthDateTitle = new JLabel("Earliest birth date:");
	private JLabel earliestBirthDateName = new JLabel("Eva C. Butts");
	private JButton earliestBirthDateButton = new JButton("Go To");
	private JLabel earliestBirthDate = new JLabel("Abt. 191 AD");
	private JLabel mostRecentBirthDateTitle = new JLabel("Most recent birth date:");
	private JLabel mostRecentBirthDateName = new JLabel("Yoelies Milagros Soto");
	private JLabel mostRecentBirthDate = new JLabel("03 Mar 2004");
	private JButton mostRecentBirthDateButton = new JButton("Go To");
	private JLabel factsTitle = new JLabel("Facts:");
	private JLabel facts = new IntegerLabel(10244);
	private JLabel placesTitle = new JLabel("Places:");
	private JLabel places = new IntegerLabel(669);
	private JLabel souresTitle = new JLabel("Sources:");
	private JLabel soures = new IntegerLabel(476);
	private JLabel templatesUsedTitle = new JLabel("Templates used:");
	private JLabel templatesUsed = new IntegerLabel(110);
	private JLabel citationsTitle = new JLabel("Citations:");
	private JLabel citations = new IntegerLabel(7171);
	private JLabel mediaTitle = new JLabel("Media:");
	private JLabel media = new IntegerLabel(774);

	public String individualId = null;

	public PlanCurrentTreeFileStatisticsPane()
	{
		log.trace("Entering");
		this.setLayout(this.springLayout);
		this.add(this.statistics);
		this.add(this.fileName);
		this.add(this.peopleTitle);
		this.add(this.people);
		this.add(this.marriagesTitle);
		this.add(this.marriages);
		this.add(this.fileSizeTitle);
		this.add(this.fileSize);
		this.add(this.generationsTitle);
		this.add(this.generations);
		this.add(this.surnamesTitle);
		this.add(this.surnames);
		this.add(this.additionalInformationTitle);
		this.add(this.separator1);
		this.add(this.averageLifespanTitle);
		this.add(this.averageLifespan);
		this.add(this.earliestBirthDateTitle);
		this.add(this.earliestBirthDateName);
		this.add(this.earliestBirthDateButton);
		this.add(this.earliestBirthDate);
		this.add(this.mostRecentBirthDateTitle);
		this.add(this.mostRecentBirthDateName);
		this.add(this.mostRecentBirthDate);
		this.add(this.mostRecentBirthDateButton);
		this.add(this.factsTitle);
		this.add(this.facts);
		this.add(this.placesTitle);
		this.add(this.places);
		this.add(this.souresTitle);
		this.add(this.soures);
		this.add(this.templatesUsedTitle);
		this.add(this.templatesUsed);
		this.add(this.citationsTitle);
		this.add(this.citations);
		this.add(this.mediaTitle);
		this.add(this.media);

		this.springLayout.putConstraint(NORTH, this.statistics, 0, NORTH, this);
		this.springLayout.putConstraint(WEST, this.statistics, 0, WEST, this);
		this.springLayout.putConstraint(NORTH, this.fileName, 0, NORTH, this);
		this.springLayout.putConstraint(WEST, this.fileName, xPad, EAST, this.statistics);

		this.springLayout.putConstraint(NORTH, this.peopleTitle, 0, SOUTH, this.statistics);
		this.springLayout.putConstraint(WEST, this.peopleTitle, 0, WEST, this);
		this.springLayout.putConstraint(NORTH, this.people, 0, NORTH, this.peopleTitle);
		this.springLayout.putConstraint(WEST, this.people, 0, WEST, this.fileName);

		this.springLayout.putConstraint(NORTH, this.marriagesTitle, 0, SOUTH, this.peopleTitle);
		this.springLayout.putConstraint(WEST, this.marriagesTitle, 0, WEST, this);
		this.springLayout.putConstraint(NORTH, this.marriages, 0, NORTH, this.marriagesTitle);
		this.springLayout.putConstraint(WEST, this.marriages, 0, WEST, this.fileName);

		this.springLayout.putConstraint(NORTH, this.fileSizeTitle, 0, SOUTH, this.marriagesTitle);
		this.springLayout.putConstraint(WEST, this.fileSizeTitle, 0, WEST, this);
		this.springLayout.putConstraint(NORTH, this.fileSize, 0, NORTH, this.fileSizeTitle);
		this.springLayout.putConstraint(WEST, this.fileSize, 0, WEST, this.fileName);

		this.springLayout.putConstraint(NORTH, this.generationsTitle, 0, SOUTH, this.fileSizeTitle);
		this.springLayout.putConstraint(WEST, this.generationsTitle, 0, WEST, this);
		this.springLayout.putConstraint(NORTH, this.generations, 0, NORTH, this.generationsTitle);
		this.springLayout.putConstraint(WEST, this.generations, 0, WEST, this.fileName);

		this.springLayout.putConstraint(NORTH, this.surnamesTitle, 0, SOUTH, this.generationsTitle);
		this.springLayout.putConstraint(WEST, this.surnamesTitle, 0, WEST, this);
		this.springLayout.putConstraint(NORTH, this.surnames, 0, NORTH, this.surnamesTitle);
		this.springLayout.putConstraint(WEST, this.surnames, 0, WEST, this.fileName);

		this.springLayout.putConstraint(NORTH, this.additionalInformationTitle, 0, SOUTH, this.surnamesTitle);
		this.springLayout.putConstraint(WEST, this.additionalInformationTitle, 0, WEST, this);
		this.springLayout.putConstraint(NORTH, this.separator1, yPad, NORTH, this.additionalInformationTitle);
		this.springLayout.putConstraint(WEST, this.separator1, 0, WEST, this.fileName);

		this.springLayout.putConstraint(NORTH, this.averageLifespanTitle, 0, SOUTH, this.additionalInformationTitle);
		this.springLayout.putConstraint(WEST, this.averageLifespanTitle, 0, WEST, this);
		this.springLayout.putConstraint(NORTH, this.averageLifespan, 0, NORTH, this.averageLifespanTitle);
		this.springLayout.putConstraint(WEST, this.averageLifespan, 0, WEST, this.fileName);

		this.springLayout.putConstraint(NORTH, this.earliestBirthDateTitle, 0, SOUTH, this.averageLifespanTitle);
		this.springLayout.putConstraint(WEST, this.earliestBirthDateTitle, 0, WEST, this);
		this.springLayout.putConstraint(NORTH, this.earliestBirthDateName, 0, NORTH, this.earliestBirthDateTitle);
		this.springLayout.putConstraint(WEST, this.earliestBirthDateName, 0, WEST, this.fileName);
		this.springLayout.putConstraint(NORTH, this.earliestBirthDateButton, 0, NORTH, this.earliestBirthDateTitle);
		this.springLayout.putConstraint(WEST, this.earliestBirthDateButton, xPad, EAST, this.earliestBirthDateName);
		this.springLayout.putConstraint(NORTH, this.earliestBirthDate, 0, SOUTH, this.earliestBirthDateTitle);
		this.springLayout.putConstraint(WEST, this.earliestBirthDate, 0, WEST, this.fileName);

		this.springLayout.putConstraint(NORTH, this.mostRecentBirthDateTitle, 0, SOUTH, this.earliestBirthDate);
		this.springLayout.putConstraint(WEST, this.mostRecentBirthDateTitle, 0, WEST, this);
		this.springLayout.putConstraint(NORTH, this.mostRecentBirthDateName, 0, NORTH, this.mostRecentBirthDateTitle);
		this.springLayout.putConstraint(WEST, this.mostRecentBirthDateName, 0, WEST, this.fileName);
		this.springLayout.putConstraint(NORTH, this.mostRecentBirthDateButton, 0, NORTH, this.mostRecentBirthDateTitle);
		this.springLayout.putConstraint(WEST, this.mostRecentBirthDateButton, xPad, EAST, this.mostRecentBirthDateName);
		this.springLayout.putConstraint(NORTH, this.mostRecentBirthDate, 0, SOUTH, this.mostRecentBirthDateTitle);
		this.springLayout.putConstraint(WEST, this.mostRecentBirthDate, 0, WEST, this.fileName);

		this.springLayout.putConstraint(NORTH, this.factsTitle, 0, SOUTH, this.mostRecentBirthDate);
		this.springLayout.putConstraint(WEST, this.factsTitle, 0, WEST, this);
		this.springLayout.putConstraint(NORTH, this.facts, 0, NORTH, this.factsTitle);
		this.springLayout.putConstraint(WEST, this.facts, 0, WEST, this.fileName);

		this.springLayout.putConstraint(NORTH, this.placesTitle, 0, SOUTH, this.factsTitle);
		this.springLayout.putConstraint(WEST, this.placesTitle, 0, WEST, this);
		this.springLayout.putConstraint(NORTH, this.places, 0, NORTH, this.placesTitle);
		this.springLayout.putConstraint(WEST, this.places, xPad, EAST, this.statistics);

		this.springLayout.putConstraint(NORTH, this.souresTitle, 0, SOUTH, this.placesTitle);
		this.springLayout.putConstraint(WEST, this.souresTitle, 0, WEST, this);
		this.springLayout.putConstraint(NORTH, this.soures, 0, NORTH, this.souresTitle);
		this.springLayout.putConstraint(WEST, this.soures, xPad, EAST, this.statistics);

		this.springLayout.putConstraint(NORTH, this.templatesUsedTitle, 0, SOUTH, this.souresTitle);
		this.springLayout.putConstraint(WEST, this.templatesUsedTitle, 0, WEST, this);
		this.springLayout.putConstraint(NORTH, this.templatesUsed, 0, NORTH, this.templatesUsedTitle);
		this.springLayout.putConstraint(WEST, this.templatesUsed, xPad, EAST, this.statistics);

		this.springLayout.putConstraint(NORTH, this.citationsTitle, 0, SOUTH, this.templatesUsedTitle);
		this.springLayout.putConstraint(WEST, this.citationsTitle, 0, WEST, this);
		this.springLayout.putConstraint(NORTH, this.citations, 0, NORTH, this.citationsTitle);
		this.springLayout.putConstraint(WEST, this.citations, xPad, EAST, this.statistics);

		this.springLayout.putConstraint(NORTH, this.mediaTitle, 0, SOUTH, this.citationsTitle);
		this.springLayout.putConstraint(WEST, this.mediaTitle, 0, WEST, this);
		this.springLayout.putConstraint(NORTH, this.media, 0, NORTH, this.mediaTitle);
		this.springLayout.putConstraint(WEST, this.media, xPad, EAST, this.statistics);

		this.earliestBirthDateButton.addActionListener(new ActionListener() {
			@SuppressWarnings("synthetic-access")
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if ("Go To".equals(e.getActionCommand()))
				{
					JButton button = ((JButton)e.getSource());
					PlanCurrentTreeFileStatisticsPane pane = (PlanCurrentTreeFileStatisticsPane)button.getParent();
					JOptionPane optionPane = (JOptionPane)pane.getParent().getParent().getParent();
					JDialog p8 = (JDialog)optionPane.getParent().getParent().getParent().getParent();
					pane.individualId = pane.earliestBirthDateName.getText();
					p8.setVisible(false);
				}
			}});
		this.mostRecentBirthDateButton.addActionListener(new ActionListener() {
			@SuppressWarnings("synthetic-access")
			@Override
			public void actionPerformed(ActionEvent e)
			{
				if ("Go To".equals(e.getActionCommand()))
				{
					JButton button = ((JButton)e.getSource());
					PlanCurrentTreeFileStatisticsPane pane = (PlanCurrentTreeFileStatisticsPane)button.getParent();
					JOptionPane optionPane = (JOptionPane)pane.getParent().getParent().getParent();
					JDialog p8 = (JDialog)optionPane.getParent().getParent().getParent().getParent();
					pane.individualId = pane.mostRecentBirthDateName.getText();
					p8.setVisible(false);
				}
			}});
		log.trace("Exiting");
	}

	@Override
	public Dimension getSize()
	{
		return getPreferredSize();
	}

	@Override
	public Dimension getMinimumSize()
	{
		return getPreferredSize();
	}

	@Override
	public Dimension getPreferredSize()
	{
		int left = Util.getMax(
			this.statistics.getPreferredSize().width,
			this.peopleTitle.getPreferredSize().width,
			this.marriagesTitle.getPreferredSize().width,
			this.fileSizeTitle.getPreferredSize().width,
			this.generationsTitle.getPreferredSize().width,
			this.surnamesTitle.getPreferredSize().width,
			this.additionalInformationTitle.getPreferredSize().width,
			this.averageLifespanTitle.getPreferredSize().width,
			this.earliestBirthDateTitle.getPreferredSize().width,
			this.mostRecentBirthDateTitle.getPreferredSize().width,
			this.factsTitle.getPreferredSize().width,
			this.placesTitle.getPreferredSize().width,
			this.souresTitle.getPreferredSize().width,
			this.templatesUsedTitle.getPreferredSize().width,
			this.citationsTitle.getPreferredSize().width,
			this.mediaTitle.getPreferredSize().width);
		int right = Util.getMax(
			this.fileName.getPreferredSize().width,
			this.people.getPreferredSize().width,
			this.marriages.getPreferredSize().width,
			this.fileSize.getPreferredSize().width,
			this.generations.getPreferredSize().width,
			this.surnames.getPreferredSize().width,
			this.separator1.getPreferredSize().width,
			this.averageLifespan.getPreferredSize().width,
			this.earliestBirthDate.getPreferredSize().width + xPad + this.earliestBirthDateButton.getPreferredSize().width,
			this.mostRecentBirthDate.getPreferredSize().width + xPad + this.mostRecentBirthDateButton.getPreferredSize().width,
			this.facts.getPreferredSize().width,
			this.places.getPreferredSize().width,
			this.soures.getPreferredSize().width,
			this.templatesUsed.getPreferredSize().width,
			this.citations.getPreferredSize().width,
			this.media.getPreferredSize().width);
		int height = this.fileName.getPreferredSize().height + 
				this.people.getPreferredSize().height + 
				this.marriages.getPreferredSize().height + 
				this.fileSize.getPreferredSize().height + 
				this.generations.getPreferredSize().height + 
				this.surnames.getPreferredSize().height + 
				this.additionalInformationTitle.getPreferredSize().height + 
				this.averageLifespan.getPreferredSize().height + 
				this.earliestBirthDate.getPreferredSize().height +
				this.mostRecentBirthDate.getPreferredSize().height + 
				this.facts.getPreferredSize().height + 
				this.places.getPreferredSize().height + 
				this.soures.getPreferredSize().height + 
				this.templatesUsed.getPreferredSize().height + 
				this.citations.getPreferredSize().height + 
				this.media.getPreferredSize().height + 
				15 * yPad;
		return new Dimension(left + xPad + right + 50,
			height);
	}
}
