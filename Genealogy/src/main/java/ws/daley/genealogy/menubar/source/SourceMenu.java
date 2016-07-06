package ws.daley.genealogy.menubar.source;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenu;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class SourceMenu extends MyMenu
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(SourceMenu.class);

	private static SourceAddSourceMenuItem addSourceItem = new SourceAddSourceMenuItem();
	private static SourceAddSourceCitationMenuItem addSourceCitationItem = new SourceAddSourceCitationMenuItem();
	private static SourceEditSourceCitationMenuItem editSourceCitationItem = new SourceEditSourceCitationMenuItem();
	private static SourceDeleteSourceCitationMenuItem deleteSourceCitationItem = new SourceDeleteSourceCitationMenuItem();
	private static SourceReplaceSourceCitationMenuItem replaceSourceCitationItem = new SourceReplaceSourceCitationMenuItem();
	private static SourceCopySourceCitationMenuItem copySourceCitationItem = new SourceCopySourceCitationMenuItem();
	private static SourceLinkToPersonMenuItem linkToPersonItem = new SourceLinkToPersonMenuItem();

	private static List<List<MyMenuItem>> menuItemLists = new ArrayList<List<MyMenuItem>>
	(
		Arrays.asList(
			Arrays.asList(
				addSourceItem
			),
			Arrays.asList(
				addSourceCitationItem,
				editSourceCitationItem,
				deleteSourceCitationItem,
				replaceSourceCitationItem
			),
			Arrays.asList(
				copySourceCitationItem
			),
			Arrays.asList(
				linkToPersonItem
			)
	));

	public SourceMenu()
	{
		super("Source", menuItemLists);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
