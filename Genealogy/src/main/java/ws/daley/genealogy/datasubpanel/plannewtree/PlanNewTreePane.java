package ws.daley.genealogy.datasubpanel.plannewtree;

import static javax.swing.SpringLayout.EAST;
import static javax.swing.SpringLayout.NORTH;
import static javax.swing.SpringLayout.WEST;

import javax.swing.SpringLayout;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.MyFamily;
import ws.daley.genealogy.RefreshData;
import ws.daley.genealogy.components.MyPane;
import ws.daley.genealogy.datasubpanel.plannewtree.panes.PlanNewTreeDownloadTreePane;
import ws.daley.genealogy.datasubpanel.plannewtree.panes.PlanNewTreeEnterWhatYouKnowPane;
import ws.daley.genealogy.datasubpanel.plannewtree.panes.PlanNewTreeGettingStartedPane;
import ws.daley.genealogy.datasubpanel.plannewtree.panes.PlanNewTreeImportTreePane;

@SuppressWarnings("serial")
public class PlanNewTreePane extends MyPane implements RefreshData
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PlanNewTreePane.class);

	public PlanNewTreeGettingStartedPane gettingStartedPane = new PlanNewTreeGettingStartedPane();
	public PlanNewTreeEnterWhatYouKnowPane enterWhatYouKnowPane = new PlanNewTreeEnterWhatYouKnowPane();
	public PlanNewTreeImportTreePane importTreePane = new PlanNewTreeImportTreePane();
	public PlanNewTreeDownloadTreePane downloadTreePane = new PlanNewTreeDownloadTreePane();

	private SpringLayout springLayout = new SpringLayout();
//	private static final int xPad = 5;
//	private static final int yPad = 5;
//	private static final Dimension arcs = new Dimension(20, 20);

	public PlanNewTreePane()
	{
		super("New Tree", null, null);
		log.trace("Entering");
		this.setOpaque(false);
		this.setLayout(this.springLayout);
		this.setBackground(MyFamily.myFamily.getBackground());

		this.add(this.gettingStartedPane);
		this.add(this.enterWhatYouKnowPane);
		this.add(this.importTreePane);
		this.add(this.downloadTreePane);

		this.springLayout.putConstraint(WEST, this.gettingStartedPane, 0, WEST, this);
		this.springLayout.putConstraint(NORTH, this.gettingStartedPane, 0, NORTH, this);

		this.springLayout.putConstraint(WEST, this.enterWhatYouKnowPane, 0, EAST, this.gettingStartedPane);
		this.springLayout.putConstraint(NORTH, this.enterWhatYouKnowPane, 0, NORTH, this);
		this.springLayout.putConstraint(EAST, this.enterWhatYouKnowPane, 0, EAST, this);

		this.springLayout.putConstraint(WEST, this.importTreePane, 0, EAST, this.gettingStartedPane);
		this.springLayout.putConstraint(NORTH, this.importTreePane, 0, NORTH, this);
		this.springLayout.putConstraint(EAST, this.importTreePane, 0, EAST, this);

		this.springLayout.putConstraint(WEST, this.downloadTreePane, 0, EAST, this.gettingStartedPane);
		this.springLayout.putConstraint(NORTH, this.downloadTreePane, 0, NORTH, this);
		this.springLayout.putConstraint(EAST, this.downloadTreePane, 0, EAST, this);

		this.gettingStartedPane.enableEnterWhatYouKnow(true);
		log.trace("Entering");
	}

	public PlanTreeFilePane getPlanTreeName() {return this.importTreePane.planTreeFileShowPane;}

	@Override
	public void refreshData()
	{
		throw new RuntimeException("refreshData not implemented");
	}
}
