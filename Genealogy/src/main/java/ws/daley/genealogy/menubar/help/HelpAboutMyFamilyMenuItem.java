package ws.daley.genealogy.menubar.help;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class HelpAboutMyFamilyMenuItem extends MyMenuItem
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(HelpAboutMyFamilyMenuItem.class);

	public HelpAboutMyFamilyMenuItem()
	{
		super("About My family", 0);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
