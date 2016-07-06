package ws.daley.genealogy.menubar.source;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class SourceCopySourceCitationMenuItem extends MyMenuItem
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(SourceCopySourceCitationMenuItem.class);

	public SourceCopySourceCitationMenuItem()
	{
		super("Copy Source Citation", 0);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
