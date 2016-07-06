package ws.daley.genealogy.toolbar.button;

import java.io.File;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

@SuppressWarnings("serial")
public class PublishToolbarButton extends MyToolbarButton
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PublishToolbarButton.class);

	private static File activeIconFile = new File(toolbarIconFolder, "active_publish.png");
	private static File inactiveIconFile = new File(toolbarIconFolder, "inactive_publish.png");
	private static File hoverIconFile = new File(toolbarIconFolder, "hover_publish.png");

	public PublishToolbarButton()
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
		this.setAssociatedEntries(this.myTabbedPane.publishPanels, this.myMenuBar.publishMenus, 
				this.myShortcutBar.publishButtons,  this.myTabbedPane.publishPanels);
		log.trace("Exitting setAssociatedEntries");
	}
}
