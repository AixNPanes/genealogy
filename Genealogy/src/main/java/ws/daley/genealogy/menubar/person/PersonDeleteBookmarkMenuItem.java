package ws.daley.genealogy.menubar.person;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class PersonDeleteBookmarkMenuItem extends MyMenuItem
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PersonDeleteBookmarkMenuItem.class);

	public PersonDeleteBookmarkMenuItem()
	{
		super("Delete B&ookmark", 0);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
