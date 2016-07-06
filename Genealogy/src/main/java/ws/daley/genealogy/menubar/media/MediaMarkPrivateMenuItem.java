package ws.daley.genealogy.menubar.media;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class MediaMarkPrivateMenuItem extends MyMenuItem
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(MediaMarkPrivateMenuItem.class);

	public MediaMarkPrivateMenuItem()
	{
		super("Mark Private", 0);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
