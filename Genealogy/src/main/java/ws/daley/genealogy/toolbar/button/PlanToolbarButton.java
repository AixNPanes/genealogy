package ws.daley.genealogy.toolbar.button;

import java.io.File;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;

@SuppressWarnings("serial")
public class PlanToolbarButton extends MyToolbarButton
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PlanToolbarButton.class);

	private static File activeIconFile = new File(toolbarIconFolder, "active_plan.png");
	private static File inactiveIconFile = new File(toolbarIconFolder, "inactive_plan.png");
	private static File hoverIconFile = new File(toolbarIconFolder, "hover_plan.png");

	public PlanToolbarButton()
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
		this.setAssociatedEntries(this.myTabbedPane.planPanels, this.myMenuBar.planMenus, 
				this.myShortcutBar.planButtons,  this.myTabbedPane.planPanels);
		log.trace("Exitting setAssociatedEntries");
	}
}
