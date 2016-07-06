package ws.daley.genealogy.toolbar.button;

import java.io.File;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

@SuppressWarnings("serial")
public class WebSearchToolbarButton extends MyToolbarButton
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(WebSearchToolbarButton.class);

	private static File activeIconFile = new File(toolbarIconFolder, "active_web_search.png");
	private static File inactiveIconFile = new File(toolbarIconFolder, "inactive_web_search.png");
	private static File hoverIconFile = new File(toolbarIconFolder, "hover_web_search.png");

	public WebSearchToolbarButton()
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
		this.setAssociatedEntries(this.myTabbedPane.webSearchPanels, this.myMenuBar.webSearchMenus, 
				this.myShortcutBar.webSearchButtons,  this.myTabbedPane.webSearchPanels);
		log.trace("Exitting setAssociatedEntries");
	}
}
