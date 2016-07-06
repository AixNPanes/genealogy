package ws.daley.genealogy.menubar.person;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class PersonAddBookmarkMenuItem extends MyMenuItem
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PersonAddBookmarkMenuItem.class);

	public PersonAddBookmarkMenuItem()
	{
		super("Add &Bookmark", 0);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
