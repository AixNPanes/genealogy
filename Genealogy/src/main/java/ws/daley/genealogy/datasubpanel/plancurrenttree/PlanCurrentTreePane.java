package ws.daley.genealogy.datasubpanel.plancurrenttree;

import static javax.swing.SpringLayout.EAST;
import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.SOUTH;
import static javax.swing.SpringLayout.WEST;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.RefreshData;
import ws.daley.genealogy.components.MyPane;
import ws.daley.genealogy.components.family.DateLabel;
import ws.daley.genealogy.components.family.HumanReadableIntegerLabel;
import ws.daley.genealogy.components.family.IntegerLabel;
import ws.daley.genealogy.components.family.Separator;
import ws.daley.genealogy.components.family.Sex;
import ws.daley.genealogy.components.family.SmallLabel;
import ws.daley.genealogy.datasubpanel.plancurrenttree.tasks.TasksTablePane;
import ws.daley.genealogy.datasubpanel.plancurrenttree.tasks.TasksToolbar;
import ws.daley.genealogy.util.Util;

@SuppressWarnings("serial")
public class PlanCurrentTreePane extends MyPane implements RefreshData
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PlanCurrentTreePane.class);

	private SpringLayout springLayout = new SpringLayout();
	private static final int xPad = 5;
	private static final int yPad = 5;

	private JLabel aboutThisFamilyTreeHeader = new JLabel("About This Family Tree");
	private JButton moreButton = new JButton("More...");
	private PlanFamilyTreePerson homePerson = new PlanFamilyTreePerson(PlanType.Home, Sex.MALE, "Tim Eugene Daley", "1948", "");
	private PlanFamilyTreePerson currentPerson = new PlanFamilyTreePerson(PlanType.Current, Sex.FEMALE, "Judy Marie Campbell", "1947", "");
	private JLabel peopleTitle = new JLabel("People:");
	private IntegerLabel people = new IntegerLabel(2205);
	private JLabel marriagesTitle = new JLabel("Marriages:");
	private IntegerLabel marriages = new IntegerLabel(627);
	private JLabel fileSizeTitle = new JLabel("File size:");
	private HumanReadableIntegerLabel fileSize = new HumanReadableIntegerLabel(12894000);
	private JLabel creationDateTitle = new JLabel("Creation date:");
	private DateLabel creationDate = new DateLabel((new GregorianCalendar(2015, 05, 8, 19, 51)).toZonedDateTime());
	private JLabel lastSavedDateTitle = new JLabel("Last saved:");
	private DateLabel lastSavedDate = new DateLabel((new GregorianCalendar(2016, 03, 14, 12, 11)).toZonedDateTime());
	private JLabel lastBackupDateTitle = new JLabel("Last Backup:");
	private DateLabel lastBackupDate = new DateLabel((Date)null);
	private Separator separator1 = new Separator(getAboutThisFamilyTreePreferredSize().width + arcs.width, 1);
	private Separator separator2 = new Separator(getAboutThisFamilyTreePreferredSize().width + arcs.width, 1);

	private JLabel treeSyncHeader = new JLabel("Tree Sync - Access Via Ancestry");
	private JLabel notUploaded = new JLabel("Not uploaded & Linked");
	private JLabel notLinked = new JLabel("This tree is not linked or shared online");
	private JLabel benefits = new SmallLabel(-3, "There are great benefits to having an online tree:");
	private JLabel benefit1 = new SmallLabel(-3, "\u2022It's free and easy to share with family and others");
	private JLabel benefit2 = new SmallLabel(-3, "\u2022You can access your tree from any Internet connection");
	private JLabel benefit3 = new SmallLabel(-3, "\u2022Changes made to either tree appear in both places");
	private JButton uploadButton = new JButton("Upload and Link to Ancestry...");
	private Separator separator3 = new Separator(getAboutThisFamilyTreePreferredSize().width + arcs.width, 1);

	private PlanCurrentTasksHeaderLabel tasksHeader = new PlanCurrentTasksHeaderLabel(0);
	private TasksToolbar tasksToolbar = new TasksToolbar();
	private TasksTablePane tasksTablePane = new TasksTablePane();

	private JLabel ancestryWebDashboardHeader = new JLabel("Ancestry Web Dashboard");
	private JButton dashboardOptionsButton = new JButton("Options");
	private JLabel links = new JLabel("Links");
	private ClickableLabel ancestryLink = new ClickableLabel("Ancestry", "http://www.ancestry.com");
	private ClickableLabel familyTreeMakerLink = new ClickableLabel("Family Tree Maker", "http://www.familytreemakere.com");
	private ClickableLabel rootsWebLink = new ClickableLabel("RottsWeb", "http://www.rootsweb.ancestry.com");
	private ClickableLabel fold3Link = new ClickableLabel("Fold3", "http://www.fold3.com");
	private ClickableLabel archivesLink = new ClickableLabel("Archives", "http://www.archives.com");
	private ClickableLabel newspapersLink = new ClickableLabel("Newspapers", "https://www.newspapers.com");

	private static final Color shadowColor = Color.BLACK;
	private static final int shadowAlpha = 150;
	private static final int strokeSize = 1;
	private static final Dimension arcs = new Dimension(20, 20);

	public PlanCurrentTreePane()
	{
		super("Current Tree", null, null);
		log.trace("Entering");
		this.setLayout(this.springLayout);
		this.setOpaque(false);
		this.add(this.aboutThisFamilyTreeHeader);
		this.add(this.moreButton);
		this.add(this.homePerson);
		this.add(this.currentPerson);
		this.add(this.separator1);
		this.add(this.peopleTitle);
		this.add(this.people);
		this.add(this.marriagesTitle);
		this.add(this.marriages);
		this.add(this.fileSizeTitle);
		this.add(this.fileSize);
		this.add(this.separator2);
		this.add(this.creationDateTitle);
		this.add(this.creationDate);
		this.add(this.lastSavedDateTitle);
		this.add(this.lastSavedDate);
		this.add(this.lastBackupDateTitle);
		this.add(this.lastBackupDate);

		this.springLayout.putConstraint(WEST, this.aboutThisFamilyTreeHeader, arcs.width / 2, WEST, this);
		this.springLayout.putConstraint(NORTH, this.aboutThisFamilyTreeHeader, arcs.height / 2, NORTH, this);
		this.springLayout.putConstraint(WEST, this.moreButton, 20, EAST, this.aboutThisFamilyTreeHeader);
		this.springLayout.putConstraint(NORTH, this.moreButton, -5, NORTH, this.aboutThisFamilyTreeHeader);
		this.springLayout.putConstraint(WEST, this.homePerson, 0, WEST, this.aboutThisFamilyTreeHeader);
		this.springLayout.putConstraint(NORTH, this.homePerson, arcs.height / 2 + yPad, SOUTH, this.aboutThisFamilyTreeHeader);
		this.springLayout.putConstraint(WEST, this.currentPerson, 0, WEST, this.aboutThisFamilyTreeHeader);
		this.springLayout.putConstraint(NORTH, this.currentPerson, yPad, SOUTH, this.homePerson);
		this.springLayout.putConstraint(WEST, this.separator1, 0, WEST, this);
		this.springLayout.putConstraint(NORTH, this.separator1, yPad, SOUTH, this.currentPerson);
		this.springLayout.putConstraint(WEST, this.peopleTitle, 0, WEST, this.aboutThisFamilyTreeHeader);
		this.springLayout.putConstraint(NORTH, this.peopleTitle, yPad, SOUTH, this.separator1);
		this.springLayout.putConstraint(WEST, this.people, xPad, EAST, this.peopleTitle);
		this.springLayout.putConstraint(NORTH, this.people, 0, NORTH, this.peopleTitle);
		this.springLayout.putConstraint(WEST, this.marriagesTitle, 0, WEST, this.aboutThisFamilyTreeHeader);
		this.springLayout.putConstraint(NORTH, this.marriagesTitle, yPad, SOUTH, this.peopleTitle);
		this.springLayout.putConstraint(WEST, this.marriages, xPad, EAST, this.marriagesTitle);
		this.springLayout.putConstraint(NORTH, this.marriages, 0, NORTH, this.marriagesTitle);
		this.springLayout.putConstraint(WEST, this.fileSizeTitle, 0, WEST, this.aboutThisFamilyTreeHeader);
		this.springLayout.putConstraint(NORTH, this.fileSizeTitle, yPad, SOUTH, this.marriagesTitle);
		this.springLayout.putConstraint(WEST, this.fileSize, xPad, EAST, this.fileSizeTitle);
		this.springLayout.putConstraint(NORTH, this.fileSize, 0, NORTH, this.fileSizeTitle);
		this.springLayout.putConstraint(WEST, this.separator2, 0, WEST, this);
		this.springLayout.putConstraint(NORTH, this.separator2, yPad, SOUTH, this.fileSizeTitle);
		this.springLayout.putConstraint(WEST, this.creationDateTitle, 0, WEST, this.aboutThisFamilyTreeHeader);
		this.springLayout.putConstraint(NORTH, this.creationDateTitle, yPad, SOUTH, this.separator2);
		this.springLayout.putConstraint(WEST, this.creationDate, xPad, EAST, this.creationDateTitle);
		this.springLayout.putConstraint(NORTH, this.creationDate, 0, NORTH, this.creationDateTitle);
		this.springLayout.putConstraint(WEST, this.lastSavedDateTitle, 0, WEST, this.aboutThisFamilyTreeHeader);
		this.springLayout.putConstraint(NORTH, this.lastSavedDateTitle, yPad, SOUTH, this.creationDateTitle);
		this.springLayout.putConstraint(WEST, this.lastSavedDate, xPad, EAST, this.lastSavedDateTitle);
		this.springLayout.putConstraint(NORTH, this.lastSavedDate, 0, NORTH, this.lastSavedDateTitle);
		this.springLayout.putConstraint(WEST, this.lastBackupDateTitle, 0, WEST, this.aboutThisFamilyTreeHeader);
		this.springLayout.putConstraint(NORTH, this.lastBackupDateTitle, yPad, SOUTH, this.lastSavedDateTitle);
		this.springLayout.putConstraint(WEST, this.lastBackupDate, xPad, EAST, this.lastBackupDateTitle);
		this.springLayout.putConstraint(NORTH, this.lastBackupDate, 0, NORTH, this.lastBackupDateTitle);

		this.moreButton.addActionListener(new ActionListener(){
			@SuppressWarnings("synthetic-access")
			@Override
			public void actionPerformed(@SuppressWarnings("unused") ActionEvent e)
			{
				String[] options = new String[]{"Help", "Close"};
				PlanCurrentTreeFileStatisticsPane messages = new PlanCurrentTreeFileStatisticsPane();
				messages.add(new JLabel("Statistical information about file:"));
				JOptionPane optionPane = new JOptionPane(messages, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION, null, options, options[1]);
				JDialog dialog = optionPane.createDialog(null, "File statistics");
				dialog.setVisible(true);
				String selectedValue = (String)optionPane.getValue();
				if ("Help".equals(selectedValue))
					throw new RuntimeException("Help not implemented");
				if ("uninitializedValue".equals(selectedValue))
					selectedValue = messages.individualId;
				log.trace("SelectedValue="+selectedValue);
				dialog.dispose();
			}
			
		});

		this.add(this.treeSyncHeader);
		this.add(this.notUploaded);
		this.add(this.notLinked);
		this.add(this.separator3);
		this.add(this.benefits);
		this.add(this.benefit1);
		this.add(this.benefit2);
		this.add(this.benefit3);
		this.add(this.uploadButton);

		this.uploadButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(@SuppressWarnings("unused") ActionEvent e)
			{
				throw new RuntimeException("Upload button not implemented");
			}
			
		});

		this.springLayout.putConstraint(WEST, this.treeSyncHeader, arcs.width / 2, WEST, this);
		this.springLayout.putConstraint(EAST, this.treeSyncHeader, -(arcs.width / 2), EAST, this);
		this.springLayout.putConstraint(NORTH, this.treeSyncHeader, arcs.height * 3 / 2, SOUTH, this.lastBackupDate);
		this.springLayout.putConstraint(WEST, this.notUploaded, arcs.width / 2, WEST, this);
		this.springLayout.putConstraint(NORTH, this.notUploaded, arcs.height / 2 + yPad, SOUTH, this.treeSyncHeader);
		this.springLayout.putConstraint(WEST, this.notLinked, arcs.width / 2, WEST, this);
		this.springLayout.putConstraint(NORTH, this.notLinked, yPad, SOUTH, this.notUploaded);
		this.springLayout.putConstraint(WEST, this.separator3, 0, WEST, this);
		this.springLayout.putConstraint(NORTH, this.separator3, yPad, SOUTH, this.notLinked);
		this.springLayout.putConstraint(WEST, this.benefits, arcs.width / 2, WEST, this);
		this.springLayout.putConstraint(NORTH, this.benefits, yPad, SOUTH, this.separator3);
		this.springLayout.putConstraint(WEST, this.benefit1, arcs.width / 2, WEST, this);
		this.springLayout.putConstraint(NORTH, this.benefit1, yPad, SOUTH, this.benefits);
		this.springLayout.putConstraint(WEST, this.benefit2, arcs.width / 2, WEST, this);
		this.springLayout.putConstraint(NORTH, this.benefit2, yPad, SOUTH, this.benefit1);
		this.springLayout.putConstraint(WEST, this.benefit3, arcs.width / 2, WEST, this);
		this.springLayout.putConstraint(NORTH, this.benefit3, yPad, SOUTH, this.benefit2);
		this.springLayout.putConstraint(WEST, this.uploadButton, arcs.width / 2, WEST, this);
		this.springLayout.putConstraint(NORTH, this.uploadButton, yPad, SOUTH, this.benefit3);

		this.add(this.tasksHeader);
		this.add(this.tasksToolbar);
		this.add(this.tasksTablePane);
		this.springLayout.putConstraint(WEST, this.tasksHeader, getAboutThisFamilyTreePreferredSize().width + arcs.width * 3 / 2, WEST, this);
		this.springLayout.putConstraint(EAST, this.tasksHeader, -(arcs.width / 2), EAST, this);
		this.springLayout.putConstraint(NORTH, this.tasksHeader, 0, NORTH, this.aboutThisFamilyTreeHeader);
		this.springLayout.putConstraint(WEST, this.tasksToolbar, 0, WEST, this.tasksHeader);
		this.springLayout.putConstraint(NORTH, this.tasksToolbar, yPad + arcs.height / 2, SOUTH, this.tasksHeader);
		this.springLayout.putConstraint(WEST, this.tasksTablePane, 0, WEST, this.tasksHeader);
		this.springLayout.putConstraint(NORTH, this.tasksTablePane, yPad, SOUTH, this.tasksToolbar);

		this.add(this.ancestryWebDashboardHeader);
		this.add(this.dashboardOptionsButton);
		this.add(this.links);
		this.add(this.ancestryLink);
		this.add(this.familyTreeMakerLink);
		this.add(this.rootsWebLink);
		this.add(this.fold3Link);
		this.add(this.archivesLink);
		this.add(this.newspapersLink);
		this.springLayout.putConstraint(WEST, this.ancestryWebDashboardHeader, 0, WEST, this.tasksHeader);
		this.springLayout.putConstraint(NORTH, this.ancestryWebDashboardHeader, arcs.width, SOUTH, this.tasksTablePane);
		this.springLayout.putConstraint(EAST, this.dashboardOptionsButton, 0, EAST, this);
		this.springLayout.putConstraint(NORTH, this.dashboardOptionsButton, -5, NORTH, this.ancestryWebDashboardHeader);
		this.springLayout.putConstraint(WEST, this.links, 0, WEST, this.tasksHeader);
		this.springLayout.putConstraint(NORTH, this.links, yPad + arcs.height / 2, SOUTH, this.ancestryWebDashboardHeader);
		this.springLayout.putConstraint(WEST, this.ancestryLink, 0, WEST, this.tasksHeader);
		this.springLayout.putConstraint(NORTH, this.ancestryLink, yPad, SOUTH, this.links);
		this.springLayout.putConstraint(WEST, this.familyTreeMakerLink, 0, WEST, this.tasksHeader);
		this.springLayout.putConstraint(NORTH, this.familyTreeMakerLink, yPad, SOUTH, this.ancestryLink);
		this.springLayout.putConstraint(WEST, this.rootsWebLink, 0, WEST, this.tasksHeader);
		this.springLayout.putConstraint(NORTH, this.rootsWebLink, yPad, SOUTH, this.familyTreeMakerLink);
		this.springLayout.putConstraint(WEST, this.fold3Link, 0, WEST, this.tasksHeader);
		this.springLayout.putConstraint(NORTH, this.fold3Link, yPad, SOUTH, this.rootsWebLink);
		this.springLayout.putConstraint(WEST, this.archivesLink, 0, WEST, this.tasksHeader);
		this.springLayout.putConstraint(NORTH, this.archivesLink, yPad, SOUTH, this.fold3Link);
		this.springLayout.putConstraint(WEST, this.newspapersLink, 0, WEST, this.tasksHeader);
		this.springLayout.putConstraint(NORTH, this.newspapersLink, yPad, SOUTH, this.archivesLink);
		log.trace("separator1="+this.separator1.getPreferredSize());
		log.trace("Exitting");
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Dimension aboutHeaderSize = new Dimension(
				getAboutThisFamilyTreePreferredSize().width,
				this.aboutThisFamilyTreeHeader.getPreferredSize().height);
		Rectangle aboutThisFamilyTreeHeaderBounds = new Rectangle(
				0,
				0,
				arcs.width + aboutHeaderSize.width,
				arcs.height + aboutHeaderSize.height);
		Rectangle aboutThisFamilyTreeBounds = new Rectangle(
				0,
				0,
				arcs.width + aboutHeaderSize.width,
				getAboutThisFamilyTreePreferredSize().height - strokeSize);
		paintHeaderBar(g, aboutThisFamilyTreeHeaderBounds, aboutThisFamilyTreeBounds, arcs);

		Dimension treeSyncHeaderSize = new Dimension(
				getTreeSyncPreferredSize().width,
				this.treeSyncHeader.getPreferredSize().height);
		Rectangle treeSyncHeaderBounds = new Rectangle(
				0,
				aboutThisFamilyTreeBounds.height,
				arcs.width + treeSyncHeaderSize.width,
				arcs.height + treeSyncHeaderSize.height);
		Rectangle treeSyncBounds = new Rectangle(
				0,
				aboutThisFamilyTreeBounds.height,
				arcs.width + treeSyncHeaderSize.width,
				getTreeSyncPreferredSize().height - strokeSize);
		paintHeaderBar(g, treeSyncHeaderBounds, treeSyncBounds, arcs);

		Dimension tasksHeaderSize = new Dimension(
				getTasksPreferredSize().width,
				this.tasksHeader.getPreferredSize().height);
		Rectangle tasksHeaderBounds = new Rectangle(
				getAboutThisFamilyTreePreferredSize().width + arcs.width - strokeSize,
				0,
				arcs.width + tasksHeaderSize.width,
				arcs.height + tasksHeaderSize.height);
		Rectangle tasksBounds = new Rectangle(
				getAboutThisFamilyTreePreferredSize().width + arcs.width - strokeSize,
				0,
				arcs.width + tasksHeaderSize.width,
				getTasksPreferredSize().height - strokeSize);
		paintHeaderBar(g, tasksHeaderBounds, tasksBounds, arcs);

		Dimension dashboardHeaderSize = new Dimension(
				getTasksPreferredSize().width,
				this.ancestryWebDashboardHeader.getPreferredSize().height);
		Rectangle dashboardHeaderBounds = new Rectangle(
				getAboutThisFamilyTreePreferredSize().width + arcs.width - strokeSize,
				tasksBounds.height,
				arcs.width + dashboardHeaderSize.width,
				arcs.height + dashboardHeaderSize.height);
		Rectangle dashboardBounds = new Rectangle(
				getAboutThisFamilyTreePreferredSize().width + arcs.width - strokeSize,
				tasksBounds.height,
				arcs.width + dashboardHeaderSize.width,
				getAncestryWebDashBoardPreferredSize().height - strokeSize);
		paintHeaderBar(g, dashboardHeaderBounds, dashboardBounds, arcs);
	}

	public void paintHeaderBar(Graphics g, Rectangle bounds1, Rectangle bounds2, Dimension arc)
	{
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,  RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(new Color(193, 197, 147));
		g2d.setStroke(new BasicStroke(strokeSize));
		g2d.fillRoundRect(bounds1.x, bounds1.y, bounds1.width, bounds1.height, arc.width, arc.height);
		g2d.setColor(new Color(shadowColor.getRed(), shadowColor.getGreen(), shadowColor.getBlue(), shadowAlpha));
		g2d.drawRoundRect(bounds1.x, bounds1.y, bounds1.width, bounds1.height, arc.width, arc.height);
		g2d.drawRoundRect(bounds2.x, bounds2.y, bounds2.width, bounds2.height, arc.width, arc.height);
		g2d.dispose();
	}

	private Dimension getAboutThisFamilyTreePreferredSize()
	{
		return new Dimension(
				Util.getMax(
					this.aboutThisFamilyTreeHeader.getPreferredSize().width,
					this.homePerson.getPreferredSize().width,
					this.currentPerson.getPreferredSize().width),
				this.lastBackupDateTitle.getY() + this.lastBackupDateTitle.getPreferredSize().height + arcs.height / 2
		);
	}

	private Dimension getTreeSyncPreferredSize()
	{
		return new Dimension(
				Util.getMax(
					this.treeSyncHeader.getPreferredSize().width,
					this.notUploaded.getPreferredSize().width,
					this.notLinked.getPreferredSize().width,
					this.benefits.getPreferredSize().width,
					this.benefit1.getPreferredSize().width,
					this.benefit2.getPreferredSize().width,
					this.benefit3.getPreferredSize().width,
					this.uploadButton.getPreferredSize().width - arcs.width),
				this.treeSyncHeader.getY() + this.treeSyncHeader.getPreferredSize().height + arcs.height / 2
		);
	}

	private Dimension getTasksPreferredSize()
	{
		return new Dimension(this.getWidth() - getAboutThisFamilyTreePreferredSize().width,
				this.tasksHeader.getPreferredSize().height + 
				this.tasksToolbar.getPreferredSize().height + 
				this.tasksTablePane.getPreferredSize().height + 
				yPad * 2 + arcs.height * 3 / 2
		);
	}

	private Dimension getAncestryWebDashBoardPreferredSize()
	{
		return new Dimension(getTasksPreferredSize().width,
				this.ancestryWebDashboardHeader.getPreferredSize().height +
				this.dashboardOptionsButton.getPreferredSize().height +
				this.links.getPreferredSize().height +
				this.ancestryLink.getPreferredSize().height +
				this.familyTreeMakerLink.getPreferredSize().height +
				this.rootsWebLink.getPreferredSize().height +
				this.fold3Link.getPreferredSize().height +
				this.archivesLink.getPreferredSize().height +
				this.newspapersLink.getPreferredSize().height +
				8 * yPad + arcs.height / 2
				);
	}

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(
				Math.max(getAboutThisFamilyTreePreferredSize().width, getTreeSyncPreferredSize().width) + Math.max(getTasksPreferredSize().width, getAncestryWebDashBoardPreferredSize().width),
				Math.max(getAboutThisFamilyTreePreferredSize().height + getTreeSyncPreferredSize().height + yPad, getTasksPreferredSize().height + getAncestryWebDashBoardPreferredSize().height + yPad)
		);
	}

	@Override
	public void refreshData()
	{
		throw new RuntimeException("RefreshData not implemented");
	}
}
