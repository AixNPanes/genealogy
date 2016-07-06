package ws.daley.genealogy.menubar.search;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class SearchIndexOfIndividualsMenuItem extends MyMenuItem
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(SearchIndexOfIndividualsMenuItem.class);

	public SearchIndexOfIndividualsMenuItem()
	{
		super("Index Of Individuals", 0);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}