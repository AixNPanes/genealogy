package ws.daley.genealogy.menubar.source;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class SourceAddSourceMenuItem extends MyMenuItem
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(SourceAddSourceMenuItem.class);

	public SourceAddSourceMenuItem()
	{
		super("Add Source", 0);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
