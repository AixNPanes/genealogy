package ws.daley.genealogy.menubar.view;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class ViewMenuItem extends MyMenuItem
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(ViewMenuItem.class);

	public ViewMenuItem()
	{
		super("&Copy", 0);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
