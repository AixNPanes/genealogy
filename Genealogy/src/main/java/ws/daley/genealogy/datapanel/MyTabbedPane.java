package ws.daley.genealogy.datapanel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JTabbedPane;
import javax.swing.SpringLayout;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.MyFamily;
import ws.daley.genealogy.RefreshData;
import ws.daley.genealogy.components.MyPane;
import ws.daley.genealogy.datasubpanel.MediaCollectionPane;
import ws.daley.genealogy.datasubpanel.MediaDetailPane;
import ws.daley.genealogy.datasubpanel.PeoplePersonPane;
import ws.daley.genealogy.datasubpanel.PeopleTreePane;
import ws.daley.genealogy.datasubpanel.PlacesPlacesPane;
import ws.daley.genealogy.datasubpanel.PublishCollectionPane;
import ws.daley.genealogy.datasubpanel.PublishDetailPane;
import ws.daley.genealogy.datasubpanel.SourcesSourcesPane;
import ws.daley.genealogy.datasubpanel.WebSearchSearchPane;
import ws.daley.genealogy.datasubpanel.plancurrenttree.PlanCurrentTreePane;
import ws.daley.genealogy.datasubpanel.plannewtree.PlanNewTreePane;

@SuppressWarnings("serial")
public class MyTabbedPane extends JTabbedPane implements RefreshData
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(MyDataPanel.class);

	protected SpringLayout springLayout = new SpringLayout();
	protected static final int xPad = 5;
	protected static final int yPad = 5;

	public static MyTabbedPane myTabbedPane;

	public PlanNewTreePane planNewTreePane = new PlanNewTreePane();
	public PlanCurrentTreePane planCurrentTreePane = new PlanCurrentTreePane();
	public MediaCollectionPane mediaCollectionPane = new MediaCollectionPane();
	public MediaDetailPane mediaDetailPane = new MediaDetailPane();
	public PeoplePersonPane peoplePersonPane = new PeoplePersonPane();
	public PeopleTreePane peopleTreePane = new PeopleTreePane();
	public PlacesPlacesPane placesPlacesPane = new PlacesPlacesPane();
	public PublishCollectionPane publishCollectionPane = new PublishCollectionPane();
	public PublishDetailPane publishDetailPane = new PublishDetailPane();
	public SourcesSourcesPane sourcesSourcesPane = new SourcesSourcesPane();
	public WebSearchSearchPane webSearchSearchPane = new WebSearchSearchPane();

	public List<MyPane> planPanels = new ArrayList<MyPane>(Arrays.asList(this.planNewTreePane, this.planCurrentTreePane));
	public List<MyPane> mediaPanels = new ArrayList<MyPane>(Arrays.asList(this.mediaCollectionPane, this.mediaDetailPane));
	public List<MyPane> peoplePanels = new ArrayList<MyPane>(Arrays.asList(this.peopleTreePane, this.peoplePersonPane));
	public List<MyPane> placesPanels = new ArrayList<MyPane>(Arrays.asList(this.placesPlacesPane));
	public List<MyPane> publishPanels = new ArrayList<MyPane>(Arrays.asList(this.publishCollectionPane, this.publishDetailPane));
	public List<MyPane> sourcesPanels = new ArrayList<MyPane>(Arrays.asList(this.sourcesSourcesPane));
	public List<MyPane> webSearchPanels = new ArrayList<MyPane>(Arrays.asList(this.webSearchSearchPane));

	public List<MyPane> dataPanelList = new ArrayList<MyPane>(
			Arrays.asList(this.mediaCollectionPane, this.mediaDetailPane, this.peoplePersonPane, this.peopleTreePane,
					this.placesPlacesPane, this.planCurrentTreePane, this.planNewTreePane, this.publishCollectionPane,
					this.publishDetailPane, this.sourcesSourcesPane, this.webSearchSearchPane));

	public MyTabbedPane()
	{
		log.trace("Entering");
		myTabbedPane = this;
//		this.setLayout(this.springLayout);
		this.setBackground(MyFamily.myFamily.getBackground());
		this.setOpaque(false);
		for(MyPane myPane : this.planPanels)
		{
			this.addTab(myPane);
//			this.springLayout.putConstraint(SpringLayout.NORTH, myPane, 0, SpringLayout.NORTH, this);
//			this.springLayout.putConstraint(SpringLayout.EAST, myPane, 0, SpringLayout.EAST, this);
//			this.springLayout.putConstraint(SpringLayout.WEST, myPane, 0, SpringLayout.WEST, this);
//			this.springLayout.putConstraint(SpringLayout.SOUTH, myPane, 0, SpringLayout.SOUTH, this);
		}
//		this.setUI(new MyTabbedPaneUI());
		log.trace("Exiting");
	}

	public void disablePanels()
	{
		log.trace("Entering disablePanels()");
		this.removeAll();
		log.trace("Exitting disablePanels()");
	}

	public void addTab(MyPane myPane)
	{
		addTab(myPane.getTitle(), myPane.getIcon(), myPane, myPane.getTip());
	}

	@Override
	public void refreshData()
	{
		this.planNewTreePane.refreshData();
		this.planCurrentTreePane.refreshData();
		this.mediaCollectionPane.refreshData();
		this.mediaDetailPane.refreshData();
		this.peoplePersonPane.refreshData();
		this.peopleTreePane.refreshData();
		this.placesPlacesPane.refreshData();
		this.publishCollectionPane.refreshData();
		this.publishDetailPane.refreshData();
		this.sourcesSourcesPane.refreshData();
		this.webSearchSearchPane.refreshData();
	}
}