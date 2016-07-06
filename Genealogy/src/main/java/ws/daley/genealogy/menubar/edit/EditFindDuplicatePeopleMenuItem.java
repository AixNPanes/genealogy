package ws.daley.genealogy.menubar.edit;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class EditFindDuplicatePeopleMenuItem extends MyMenuItem
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(EditFindDuplicatePeopleMenuItem.class);

	public EditFindDuplicatePeopleMenuItem()
	{
		super("Fi&nd Duplicate People", 0);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
