package ws.daley.genealogy.shortcutbar;

import java.io.File;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

@SuppressWarnings("serial")
public class SourceAddShortcutButton extends MyShortcutButton
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(SourceAddShortcutButton.class);

	private static File activeIconFile = new File(shortcutIconFolder, "active_source_add.png");
	private static File inactiveIconFile = new File(shortcutIconFolder, "inactive_source_add.png");
	private static File hoverIconFile = new File(shortcutIconFolder, "hover_source_add.png");

	public SourceAddShortcutButton()
	{
		super(activeIconFile, inactiveIconFile, hoverIconFile);
		log.trace("Entering");
	}
}
