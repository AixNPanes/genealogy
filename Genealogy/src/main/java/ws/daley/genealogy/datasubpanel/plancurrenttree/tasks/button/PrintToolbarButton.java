package ws.daley.genealogy.datasubpanel.plancurrenttree.tasks.button;

import java.io.File;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

@SuppressWarnings("serial")
public class PrintToolbarButton extends MyTasksToolbarButton
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PrintToolbarButton.class);

	private static File activeIconFile = new File(toolbarIconFolder, "print_hover.png");
	private static File inactiveIconFile = new File(toolbarIconFolder, "print_inactive.png");
	private static File hoverIconFile = new File(toolbarIconFolder, "print_hover.png");

	public PrintToolbarButton()
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
