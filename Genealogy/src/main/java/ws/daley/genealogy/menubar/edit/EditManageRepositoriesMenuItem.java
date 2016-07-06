package ws.daley.genealogy.menubar.edit;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class EditManageRepositoriesMenuItem extends MyMenuItem
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(EditManageRepositoriesMenuItem.class);

	public EditManageRepositoriesMenuItem()
	{
		super("M&anage Repositories", 0);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
