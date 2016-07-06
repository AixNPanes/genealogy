package ws.daley.genealogy.toolbar.button;

import java.io.File;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

@SuppressWarnings("serial")
public class PlacesToolbarButton extends MyToolbarButton
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PlacesToolbarButton.class);

	private static File activeIconFile = new File(toolbarIconFolder, "active_places.png");
	private static File inactiveIconFile = new File(toolbarIconFolder, "inactive_places.png");
	private static File hoverIconFile = new File(toolbarIconFolder, "hover_places.png");

	public PlacesToolbarButton()
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
		this.setAssociatedEntries(this.myTabbedPane.placesPanels, this.myMenuBar.placesMenus, 
				this.myShortcutBar.placesButtons,  this.myTabbedPane.placesPanels);
		log.trace("Exitting setAssociatedEntries");
	}
}
