package ws.daley.genealogy.menubar.place;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenu;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class PlaceMenu extends MyMenu
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PlaceMenu.class);

	private static PlaceResolveThisPlaceNameMenuItem resolveThisPaceNameItem = new PlaceResolveThisPlaceNameMenuItem();
	private static PlaceChangePlaceNameMenuItem changePlaceNameItem = new PlaceChangePlaceNameMenuItem();
	private static PlaceIgnorePlaceWarningMenuItem ignorePlaceWarningItem = new PlaceIgnorePlaceWarningMenuItem();
	private static PlaceMoveToDescriptionMenuItem moveToDescriptionItem = new PlaceMoveToDescriptionMenuItem();
	private static PlaceReplaceWithOtherPlaceNameMenuItem replaceWithOtherPlaceNameItem = new PlaceReplaceWithOtherPlaceNameMenuItem();
	private static PlaceDeletePlaceMenuItem deletePlaceItem = new PlaceDeletePlaceMenuItem();

	private static List<List<MyMenuItem>> menuItemLists = new ArrayList<List<MyMenuItem>>
	(
		Arrays.asList(
			Arrays.asList(
				resolveThisPaceNameItem,
				changePlaceNameItem,
				ignorePlaceWarningItem,
				moveToDescriptionItem,
				replaceWithOtherPlaceNameItem
			),
			Arrays.asList(
				deletePlaceItem
			)
	));

	public PlaceMenu()
	{
		super("Place", menuItemLists);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
