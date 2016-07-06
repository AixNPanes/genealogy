package ws.daley.genealogy.menubar.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenu;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class ToolsMenu extends MyMenu
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(ToolsMenu.class);

	private static ToolsDateCalculatorMenuItem dateCalculatorItem = new ToolsDateCalculatorMenuItem();
	private static ToolsRelationshipCalculatorMenuItem relationshipCalculatorItem = new ToolsRelationshipCalculatorMenuItem();
	private static ToolsSoundexCalculatorMenuItem soundexCalculatorItem = new ToolsSoundexCalculatorMenuItem();
	private static ToolsGlobalSpellCheckMenuItem globalSpellCheckItem = new ToolsGlobalSpellCheckMenuItem();
	private static ToolsResolveAllPlaceNamesMenuItem resolveAllPlaceNamesItem = new ToolsResolveAllPlaceNamesMenuItem();
	private static ToolsConvertNameMenuItem convertNameItem = new ToolsConvertNameMenuItem();
	private static ToolsCompactFileMenuItem compactFileItem = new ToolsCompactFileMenuItem();
	private static ToolsSortAllChildrenByBirthMenuItem sortAllChildrenByBirthItem = new ToolsSortAllChildrenByBirthMenuItem();
	private static ToolsUserInformationMenuItem userInformationItem = new ToolsUserInformationMenuItem();
	private static ToolsPluginsMenuItem pluginsItem = new ToolsPluginsMenuItem();
	private static ToolsOptionsMenuItem optionsItem = new ToolsOptionsMenuItem();

	private static List<List<MyMenuItem>> menuItemLists = new ArrayList<List<MyMenuItem>>
	(
		Arrays.asList(
			Arrays.asList(
				dateCalculatorItem,
				relationshipCalculatorItem,
				soundexCalculatorItem,
				globalSpellCheckItem
			),
			Arrays.asList(
				resolveAllPlaceNamesItem,
				convertNameItem,
				compactFileItem,
				sortAllChildrenByBirthItem
			),
			Arrays.asList(
				userInformationItem
			),
			Arrays.asList(
				pluginsItem
			),
			Arrays.asList(
				optionsItem
			)
	));

	public ToolsMenu()
	{
		super("Tools", menuItemLists);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
