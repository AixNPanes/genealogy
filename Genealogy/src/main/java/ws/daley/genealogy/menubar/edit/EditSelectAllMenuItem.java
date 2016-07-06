package ws.daley.genealogy.menubar.edit;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class EditSelectAllMenuItem extends MyMenuItem
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(EditSelectAllMenuItem.class);

	public EditSelectAllMenuItem()
	{
		super("Select A&ll", 0);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
