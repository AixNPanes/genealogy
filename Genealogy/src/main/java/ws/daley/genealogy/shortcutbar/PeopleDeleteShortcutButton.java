package ws.daley.genealogy.shortcutbar;

import java.io.File;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

@SuppressWarnings("serial")
public class PeopleDeleteShortcutButton extends MyShortcutButton
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PeopleDeleteShortcutButton.class);

	private static File activeIconFile = new File(shortcutIconFolder, "active_people_delete.png");
	private static File inactiveIconFile = new File(shortcutIconFolder, "inactive_people_delete.png");
	private static File hoverIconFile = new File(shortcutIconFolder, "hover_people_delete.png");

	public PeopleDeleteShortcutButton()
	{
		super(activeIconFile, inactiveIconFile, hoverIconFile);
		log.trace("Entering");
	}
}
