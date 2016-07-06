package ws.daley.genealogy.menubar.view;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class ViewPublishMenuItem extends MyMenuItem
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(ViewPublishMenuItem.class);

	public ViewPublishMenuItem()
	{
		super("P&ublish", 0);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
