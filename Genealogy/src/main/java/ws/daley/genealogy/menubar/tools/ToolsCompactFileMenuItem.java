package ws.daley.genealogy.menubar.tools;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class ToolsCompactFileMenuItem extends MyMenuItem
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(ToolsCompactFileMenuItem.class);

	public ToolsCompactFileMenuItem()
	{
		super("CompactFile", 0);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
