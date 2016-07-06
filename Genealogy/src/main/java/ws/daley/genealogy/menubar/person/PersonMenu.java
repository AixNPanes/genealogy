package ws.daley.genealogy.menubar.person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenu;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class PersonMenu extends MyMenu
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(PersonMenu.class);

	private static PersonAddPersonMenuItem addPersonItem = new PersonAddPersonMenuItem();
	private static PersonAttachDetachPersonMenuItem attachDetachPersonItem = new PersonAttachDetachPersonMenuItem();
	private static PersonDeletePersonMenuItem deletePersonItem = new PersonDeletePersonMenuItem();
	private static PersonAddBookmarkMenuItem addBookmarkItem = new PersonAddBookmarkMenuItem();
	private static PersonDeleteBookmarkMenuItem deleteBookmarkItem = new PersonDeleteBookmarkMenuItem();
	private static PersonIndexOfIndividualsMenuItem indexOfIndividualsItem = new PersonIndexOfIndividualsMenuItem();
	private static PersonGoToHomePersonMenuItem goToHomePersonItem = new PersonGoToHomePersonMenuItem();
	private static PersonSetAsHomePersonMenuItem setAsHomePersonItem = new PersonSetAsHomePersonMenuItem();
	private static PersonSetSpouseOrderMenuItem setSpouseOrderItem = new PersonSetSpouseOrderMenuItem();
	private static PersonMergeTwoSpecificIndividualsMenuItem mergeTwoSpecificIndividualsItem = new PersonMergeTwoSpecificIndividualsMenuItem();
	private static PersonExportBranchMenuItem exportBranchItem = new PersonExportBranchMenuItem();

	private static List<List<MyMenuItem>> menuItemLists = new ArrayList<List<MyMenuItem>>
	(
		Arrays.asList(
			Arrays.asList(
				addPersonItem,
				attachDetachPersonItem,
				deletePersonItem
			),
			Arrays.asList(
				addBookmarkItem,
				deleteBookmarkItem
			),
			Arrays.asList(
				indexOfIndividualsItem,
				goToHomePersonItem,
				setAsHomePersonItem,
				setSpouseOrderItem,
				mergeTwoSpecificIndividualsItem
			),
			Arrays.asList(
				exportBranchItem
			)
	));

	public PersonMenu()
	{
		super("Pe&rson", menuItemLists);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
