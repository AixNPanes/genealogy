package ws.daley.genealogy.menubar.search;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class SearchCreatePageArchiveMenuItem extends MyMenuItem
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(SearchCreatePageArchiveMenuItem.class);

	public SearchCreatePageArchiveMenuItem()
	{
		super("Create Page Archive", 0);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
