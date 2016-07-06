package ws.daley.genealogy.menubar.help;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenu;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class HelpMenu extends MyMenu
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(HelpMenu.class);

	private static HelpHelpForMyFamilyMenuItem helpForMyFamilyItem = new HelpHelpForMyFamilyMenuItem();
	private static HelpOnlineHelpCenterMenuItem onlineHelpCenterItem = new HelpOnlineHelpCenterMenuItem();
	private static HelpTrainingTutorialsMenuItem trainingTutorialsItem = new HelpTrainingTutorialsMenuItem();
	private static HelpCompanionGuideMenuItem companionGuideItem = new HelpCompanionGuideMenuItem();
	private static HelpRegisterMyFamilyMenuItem registerMyFamilyItem = new HelpRegisterMyFamilyMenuItem();
	private static HelpActivateAncestrySubscriptionMenuItem activateAncestrySubscriptionItem = new HelpActivateAncestrySubscriptionMenuItem();
	private static HelpCheckForUpdateMenuItem checkForUpdateItem = new HelpCheckForUpdateMenuItem();
	private static HelpCustomerExperienceMenuItem customerExperienceItem = new HelpCustomerExperienceMenuItem();
	private static HelpAboutMyFamilyMenuItem aboutMyFamilyItem = new HelpAboutMyFamilyMenuItem();

	private static List<List<MyMenuItem>> menuItemLists = new ArrayList<List<MyMenuItem>>
	(
		Arrays.asList(
			Arrays.asList(
				helpForMyFamilyItem,
				onlineHelpCenterItem,
				trainingTutorialsItem,
				companionGuideItem
			),
			Arrays.asList(
				registerMyFamilyItem,
				activateAncestrySubscriptionItem,
				checkForUpdateItem
			),
			Arrays.asList(
				customerExperienceItem,
				aboutMyFamilyItem
			)
	));

	public HelpMenu()
	{
		super("Help", menuItemLists);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
