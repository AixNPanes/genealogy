package ws.daley.genealogy.toolbar.button;

import java.io.File;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

@SuppressWarnings("serial")
public class MediaToolbarButton extends MyToolbarButton
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(MediaToolbarButton.class);

	private static File activeIconFile = new File(toolbarIconFolder, "active_media.png");
	private static File inactiveIconFile = new File(toolbarIconFolder, "inactive_media.png");
	private static File hoverIconFile = new File(toolbarIconFolder, "hover_media.png");

	public MediaToolbarButton()
	{
		super();
		this.setIcons(activeIconFile, inactiveIconFile, hoverIconFile);
		log.trace("Exitting");
	}

	@Override
	public void setAssociatedEntries()
	{
		super.setAssociatedEntries();
		log.trace("Entering setAssociatedEntries");
		this.setAssociatedEntries(this.myTabbedPane.mediaPanels, this.myMenuBar.mediaMenus, 
				this.myShortcutBar.mediaButtons,  this.myTabbedPane.mediaPanels);
		log.trace("Exitting setAssociatedEntries");
	}
}
