package ws.daley.genealogy.menubar.media;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class MediaCategorizeMediaMenuItem extends MyMenuItem
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(MediaCategorizeMediaMenuItem.class);

	public MediaCategorizeMediaMenuItem()
	{
		super("Categorize Media", 0);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
