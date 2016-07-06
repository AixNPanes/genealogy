package ws.daley.genealogy.menubar.help;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class HelpOnlineHelpCenterMenuItem extends MyMenuItem
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(HelpOnlineHelpCenterMenuItem.class);

	public HelpOnlineHelpCenterMenuItem()
	{
		super("Online Help Center", 0);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
