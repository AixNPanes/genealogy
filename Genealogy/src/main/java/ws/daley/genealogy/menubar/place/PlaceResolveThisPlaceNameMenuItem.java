package ws.daley.genealogy.menubar.place;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class PlaceResolveThisPlaceNameMenuItem extends MyMenuItem
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PlaceResolveThisPlaceNameMenuItem.class);

	public PlaceResolveThisPlaceNameMenuItem()
	{
		super("Resolve This Place Name", 0);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
