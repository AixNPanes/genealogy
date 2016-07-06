package ws.daley.genealogy.menubar.edit;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class EditFindIndividualMenuItem extends MyMenuItem
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(EditFindIndividualMenuItem.class);

	public EditFindIndividualMenuItem()
	{
		super("Find &Individual", 0);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
