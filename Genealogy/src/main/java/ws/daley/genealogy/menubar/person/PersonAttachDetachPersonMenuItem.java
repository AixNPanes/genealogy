package ws.daley.genealogy.menubar.person;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class PersonAttachDetachPersonMenuItem extends MyMenuItem
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PersonAttachDetachPersonMenuItem.class);

	public PersonAttachDetachPersonMenuItem()
	{
		super("&Attach/Detach Person", 0);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
