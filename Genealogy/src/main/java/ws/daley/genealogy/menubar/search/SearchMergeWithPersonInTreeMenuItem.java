package ws.daley.genealogy.menubar.search;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class SearchMergeWithPersonInTreeMenuItem extends MyMenuItem
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(SearchMergeWithPersonInTreeMenuItem.class);

	public SearchMergeWithPersonInTreeMenuItem()
	{
		super("Merge With Person In Tree", 0);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
