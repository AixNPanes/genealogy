package ws.daley.genealogy.menubar.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenu;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class SearchMenu extends MyMenu
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(SearchMenu.class);

	private static SearchIgnoreThisRecordMenuItem ignoreThisRecordItem = new SearchIgnoreThisRecordMenuItem();
	private static SearchViewIgnoredRecordsMenuItem viewIgnoredRecordsItem = new SearchViewIgnoredRecordsMenuItem();
	private static SearchEnableWebClippingMenuItem enableWebClippingItem = new SearchEnableWebClippingMenuItem();
	private static SearchCreatePageArchiveMenuItem createPageArchiveItem = new SearchCreatePageArchiveMenuItem();
	private static SearchMergeWithPersonInTreeMenuItem mergeWithPersonInTreeItem = new SearchMergeWithPersonInTreeMenuItem();
	private static SearchIndexOfIndividualsMenuItem indexOfIndividualsItem = new SearchIndexOfIndividualsMenuItem();

	private static List<List<MyMenuItem>> menuItemLists = new ArrayList<List<MyMenuItem>>
	(
		Arrays.asList(
			Arrays.asList(
				ignoreThisRecordItem,
				viewIgnoredRecordsItem,
				enableWebClippingItem,
				createPageArchiveItem,
				mergeWithPersonInTreeItem,
				indexOfIndividualsItem
			)
	));

	public SearchMenu()
	{
		super("Search", menuItemLists);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
