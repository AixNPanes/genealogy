package ws.daley.genealogy.menubar.help;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class HelpActivateAncestrySubscriptionMenuItem extends MyMenuItem
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(HelpActivateAncestrySubscriptionMenuItem.class);

	public HelpActivateAncestrySubscriptionMenuItem()
	{
		super("Activate Ancestry Subscription", 0);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
