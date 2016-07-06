package ws.daley.genealogy.menubar.view;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class ViewSearchMenuItem extends MyMenuItem
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(ViewSearchMenuItem.class);

	public ViewSearchMenuItem()
	{
		super("Se&arch", 0);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
