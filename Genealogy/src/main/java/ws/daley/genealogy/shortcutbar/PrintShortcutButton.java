package ws.daley.genealogy.shortcutbar;

import java.io.File;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

@SuppressWarnings("serial")
public class PrintShortcutButton extends MyShortcutButton
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PrintShortcutButton.class);

	private static File activeIconFile = new File(shortcutIconFolder, "active_print.png");
	private static File inactiveIconFile = new File(shortcutIconFolder, "inactive_print.png");
	private static File hoverIconFile = new File(shortcutIconFolder, "hover_print.png");

	public PrintShortcutButton()
	{
		super(activeIconFile, inactiveIconFile, hoverIconFile);
		log.trace("Entering");
	}
}
