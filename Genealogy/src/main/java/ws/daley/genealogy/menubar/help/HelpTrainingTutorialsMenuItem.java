package ws.daley.genealogy.menubar.help;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class HelpTrainingTutorialsMenuItem extends MyMenuItem
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(HelpTrainingTutorialsMenuItem.class);

	public HelpTrainingTutorialsMenuItem()
	{
		super("Training Tutorials", 0);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
