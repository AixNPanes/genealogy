package ws.daley.genealogy.menubar.place;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class PlaceMoveToDescriptionMenuItem extends MyMenuItem
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PlaceMoveToDescriptionMenuItem.class);

	public PlaceMoveToDescriptionMenuItem()
	{
		super("Move To Description", 0);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
