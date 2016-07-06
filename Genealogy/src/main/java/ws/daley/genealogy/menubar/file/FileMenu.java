package ws.daley.genealogy.menubar.file;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenu;
import ws.daley.genealogy.menubar.MyMenuItem;
import ws.daley.genealogy.menubar.edit.EditUndoMenuItem;

@SuppressWarnings("serial")
public class FileMenu extends MyMenu
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(EditUndoMenuItem.class);

	private static OpenMenuItem openItem = new OpenMenuItem();
	private static OpenInNewWindowMenuItem openInNewWindowItem = new OpenInNewWindowMenuItem();
	private static ImportAsNewTreeMenuItem importAsNewTreeItem = new ImportAsNewTreeMenuItem();
	private static ImportBooksMenuItem importBooksItem = new ImportBooksMenuItem();
	private static MergeItemMenuItem mergeItem = new MergeItemMenuItem();
	private static CloseItemMenuItem closeItem = new CloseItemMenuItem();
	private static ExportItemMenuItem exportItem = new ExportItemMenuItem();
	private static BackupItemMenuItem backupItem = new BackupItemMenuItem();
	private static RestoreItemMenuItem restoreItem = new RestoreItemMenuItem();
	private static PrivateFileMenuItem privateFileItem = new PrivateFileMenuItem();
	private static GoOfflineMenuItem goOfflineItem = new GoOfflineMenuItem();
	private static ExitMenuItem exitItem = new ExitMenuItem();
	
	private static List<List<MyMenuItem>> menuItemLists = new ArrayList<List<MyMenuItem>>
	(
		Arrays.asList(
			Arrays.asList(
				openItem,
				openInNewWindowItem,
				importAsNewTreeItem,
				importBooksItem,
				mergeItem,
				closeItem),
			Arrays.asList(
				exportItem,
				restoreItem,
				backupItem,
				privateFileItem),
			Arrays.asList(
				goOfflineItem,
				exitItem)
	));

	public FileMenu()
	{
		super("&File", menuItemLists);
		log.trace("Entering");
		log.trace("Exitting");
	}
}
