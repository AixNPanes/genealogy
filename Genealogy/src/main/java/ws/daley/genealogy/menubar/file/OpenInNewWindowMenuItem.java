package ws.daley.genealogy.menubar.file;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenuItem;
import ws.daley.genealogy.menubar.edit.EditUndoMenuItem;

@SuppressWarnings("serial")
public class OpenInNewWindowMenuItem extends MyMenuItem
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(EditUndoMenuItem.class);

	public OpenInNewWindowMenuItem()
	{
		super("Open in New &Window", 0);
		log.trace("Entering");
		log.trace("Exitting");
	}
}
