package ws.daley.genealogy.menubar.person;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class PersonGoToHomePersonMenuItem extends MyMenuItem
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PersonGoToHomePersonMenuItem.class);

	public PersonGoToHomePersonMenuItem()
	{
		super("&Go To Home Person", 0);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
