package ws.daley.genealogy.menubar.edit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenu;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class EditMenu extends MyMenu
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(EditMenu.class);
	
	private static EditUndoMenuItem undoItem = new EditUndoMenuItem();
	private static EditRedoMenuItem redoItem = new EditRedoMenuItem();
	private static EditCutMenuItem cutItem = new EditCutMenuItem();
	private static EditCopyMenuItem copyItem = new EditCopyMenuItem();
	private static EditPasteMenuItem pasteItem = new EditPasteMenuItem();
	private static EditDeleteMenuItem deleteItem = new EditDeleteMenuItem();
	private static EditSelectAllMenuItem selectAllItem = new EditSelectAllMenuItem();
	private static EditInsertSymbolMenuItem insertSymbolItem = new EditInsertSymbolMenuItem();
	private static EditEditPersonMenuItem editPersonItem = new EditEditPersonMenuItem();
	private static EditEditRelationshipMenuItem editRelationshipItem = new EditEditRelationshipMenuItem();
	private static EditFindDuplicatePeopleMenuItem findDuplicatePeopleItem = new EditFindDuplicatePeopleMenuItem();
	private static EditFindIndividualMenuItem findIndividualItem = new EditFindIndividualMenuItem();
	private static EditFindAndReplaceMenuItem findAndReplaceItem = new EditFindAndReplaceMenuItem();
	private static EditManageFactsMenuItem manageFactsItem = new EditManageFactsMenuItem();
	private static EditManageSourcesMenuItem manageSourcesItem = new EditManageSourcesMenuItem();
	private static EditManageRepositoriesMenuItem manageRepositoriesItem = new EditManageRepositoriesMenuItem();
	private static EditManageHistoricalEventsMenuItem manageHistoricalEventsItem = new EditManageHistoricalEventsMenuItem();

	private static List<List<MyMenuItem>> menuItemLists = new ArrayList<List<MyMenuItem>>
	(
		Arrays.asList(
			Arrays.asList(
				undoItem,
				redoItem
			),
			Arrays.asList(
				cutItem,
				copyItem,
				pasteItem
			),
			Arrays.asList(
				deleteItem,
				selectAllItem
			),
			Arrays.asList(
				insertSymbolItem
			),
			Arrays.asList(
				editPersonItem,
				editRelationshipItem,
				findDuplicatePeopleItem,
				findIndividualItem,
				findAndReplaceItem
			),
			Arrays.asList(
				manageFactsItem,
				manageSourcesItem,
				manageRepositoriesItem,
				manageHistoricalEventsItem
			)
	));

	public EditMenu()
	{
		super("&Edit", menuItemLists);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
