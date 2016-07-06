package ws.daley.genealogy.toolbar.button;

import java.io.File;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

@SuppressWarnings("serial")
public class SourcesToolbarButton extends MyToolbarButton
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(SourcesToolbarButton.class);

	private static File activeIconFile = new File(toolbarIconFolder, "active_sources.png");
	private static File inactiveIconFile = new File(toolbarIconFolder, "inactive_sources.png");
	private static File hoverIconFile = new File(toolbarIconFolder, "hover_sources.png");

	public SourcesToolbarButton()
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
		this.setAssociatedEntries(this.myTabbedPane.sourcesPanels, this.myMenuBar.sourcesMenus, 
				this.myShortcutBar.sourcesButtons,  this.myTabbedPane.sourcesPanels);
		log.trace("Exitting setAssociatedEntries");
	}
}
