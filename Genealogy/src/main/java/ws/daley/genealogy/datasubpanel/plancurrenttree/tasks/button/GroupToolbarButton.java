package ws.daley.genealogy.datasubpanel.plancurrenttree.tasks.button;

import java.io.File;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

@SuppressWarnings("serial")
public class GroupToolbarButton extends MyTasksToolbarButton
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(GroupToolbarButton.class);

	private static File activeIconFile = new File(toolbarIconFolder, "group_hover.png");
	private static File inactiveIconFile = new File(toolbarIconFolder, "group_inactive.png");
	private static File hoverIconFile = new File(toolbarIconFolder, "group_hover.png");

	public GroupToolbarButton()
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
