package ws.daley.genealogy.menubar.media;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class MediaLinkToPersonMenuItem extends MyMenuItem
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(MediaLinkToPersonMenuItem.class);

	public MediaLinkToPersonMenuItem()
	{
		super("Link To Person", 0);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
