package ws.daley.genealogy.toolbar.button;

import java.io.File;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

@SuppressWarnings("serial")
public class PeopleToolbarButton extends MyToolbarButton
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PeopleToolbarButton.class);

	private static File activeIconFile = new File(toolbarIconFolder, "active_people.png");
	private static File inactiveIconFile = new File(toolbarIconFolder, "inactive_people.png");
	private static File hoverIconFile = new File(toolbarIconFolder, "hover_people.png");

	public PeopleToolbarButton()
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
		this.setAssociatedEntries(this.myTabbedPane.peoplePanels, this.myMenuBar.peopleMenus, 
				this.myShortcutBar.peopleButtons,  this.myTabbedPane.peoplePanels);
		log.trace("Exitting setAssociatedEntries");
	}
}
