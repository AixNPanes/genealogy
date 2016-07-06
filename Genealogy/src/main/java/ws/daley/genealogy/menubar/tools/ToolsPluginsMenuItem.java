package ws.daley.genealogy.menubar.tools;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class ToolsPluginsMenuItem extends MyMenuItem
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(ToolsPluginsMenuItem.class);

	public ToolsPluginsMenuItem()
	{
		super("Plugins", 0);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
