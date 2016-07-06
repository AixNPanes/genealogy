package ws.daley.genealogy.menubar.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenu;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class ViewMenu extends MyMenu
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(ViewMenu.class);

	private static ViewBackMenuItem backItem = new ViewBackMenuItem();
	private static ViewForwardMenuItem forwardItem = new ViewForwardMenuItem();
	private static ViewPlanMenuItem planItem = new ViewPlanMenuItem();
	private static ViewPeopleMenuItem peopleItem = new ViewPeopleMenuItem();
	private static ViewPlacesMenuItem placesItem = new ViewPlacesMenuItem();
	private static ViewMediaMenuItem mediaItem = new ViewMediaMenuItem();
	private static ViewSourcesMenuItem sourcesItem = new ViewSourcesMenuItem();
	private static ViewPublishMenuItem publishItem = new ViewPublishMenuItem();
	private static ViewSearchMenuItem searchItem = new ViewSearchMenuItem();

	private static List<List<MyMenuItem>> menuItemLists = new ArrayList<List<MyMenuItem>>
	(
		Arrays.asList(
			Arrays.asList(
				backItem,
				forwardItem
			),
			Arrays.asList(
				planItem,
				peopleItem,
				placesItem,
				mediaItem,
				sourcesItem,
				publishItem,
				searchItem
			)
	));

	public ViewMenu()
	{
		super("&View", menuItemLists);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
