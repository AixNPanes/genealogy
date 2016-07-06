package ws.daley.genealogy.menubar.media;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.MyMenu;
import ws.daley.genealogy.menubar.MyMenuItem;

@SuppressWarnings("serial")
public class MediaMenu extends MyMenu
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(MediaMenu.class);

	private static MediaAddNewMediaMenuItem addNewMediaItem = new MediaAddNewMediaMenuItem();
	private static MediaCreateANewSmartStoryMenuItem createANewSmartStoryItem = new MediaCreateANewSmartStoryMenuItem();
	private static MediaScanMediaMenuItem scanMediaItem = new MediaScanMediaMenuItem();
	private static MediaCreateSlideShowMenuItem createSlideShowItem = new MediaCreateSlideShowMenuItem();
	private static MediaOpenMediaFileMenuItem openMediaFileItem = new MediaOpenMediaFileMenuItem();
	private static MediaViewMediaDetailsMenuItem viewMediaDetailsItem = new MediaViewMediaDetailsMenuItem();
	private static MediaRenameMediaFileMenuItem renameMediaFileItem = new MediaRenameMediaFileMenuItem();
	private static MediaCategorizeMediaMenuItem categorizeMediaItem = new MediaCategorizeMediaMenuItem();
	private static MediaMarkPrivateMenuItem markPrivateItem = new MediaMarkPrivateMenuItem();
	private static MediaDeleteMediaMenuItem deleteMediaItem = new MediaDeleteMediaMenuItem();
	private static MediaCopyMediaMenuItem copyMediaItem = new MediaCopyMediaMenuItem();
	private static MediaPasteMediaMenuItem pasteMediaItem = new MediaPasteMediaMenuItem();
	private static MediaLinkToPersonMenuItem linkToPersonItem = new MediaLinkToPersonMenuItem();
	private static MediaLinkToSourceCitationMenuItem linkToSourceCitationItem = new MediaLinkToSourceCitationMenuItem();
	private static MediaFindMissingMediaMenuItem findMissingMediaItem = new MediaFindMissingMediaMenuItem();
	private static MediaPrintMediaMenuItem printMediaItem = new MediaPrintMediaMenuItem();
	private static MediaRefreshThumbnailMenuItem refreshThumbnailItem = new MediaRefreshThumbnailMenuItem();
	private static MediaThumbnailSizeMenuItem thumbnailSizeItem = new MediaThumbnailSizeMenuItem();

	private static List<List<MyMenuItem>> menuItemLists = new ArrayList<List<MyMenuItem>>
	(
		Arrays.asList(
			Arrays.asList(
				addNewMediaItem,
				createANewSmartStoryItem,
				scanMediaItem,
				createSlideShowItem
			),
			Arrays.asList(
				openMediaFileItem,
				viewMediaDetailsItem,
				renameMediaFileItem,
				categorizeMediaItem,
				markPrivateItem
			),
			Arrays.asList(
				deleteMediaItem,
				copyMediaItem,
				pasteMediaItem
			),
			Arrays.asList(
				linkToPersonItem,
				linkToSourceCitationItem
			),
			Arrays.asList(
				findMissingMediaItem,
				printMediaItem,
				refreshThumbnailItem,
				thumbnailSizeItem
			)
	));

	public MediaMenu()
	{
		super("Media", menuItemLists);
		log.trace(this.getClass().getSimpleName() + " Exitting");
	}
}
