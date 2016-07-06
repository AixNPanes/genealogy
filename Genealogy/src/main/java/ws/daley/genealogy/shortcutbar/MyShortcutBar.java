package ws.daley.genealogy.shortcutbar;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JToolBar;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.MyFamily;

@SuppressWarnings("serial")
public class MyShortcutBar extends JToolBar
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(MyShortcutBar.class);

	private PeopleAddShortcutButton peopleAddButton = new PeopleAddShortcutButton();
    private PeopleDeleteShortcutButton peopleDeleteButton = new PeopleDeleteShortcutButton();
    private PlaceDeleteShortcutButton placeDeleteButton = new PlaceDeleteShortcutButton();
    private MediaAddShortcutButton mediaAddButton = new MediaAddShortcutButton();
    private MediaDeleteShortcutButton mediaDeleteButton = new MediaDeleteShortcutButton();
    private SourceAddShortcutButton sourceAddButton = new SourceAddShortcutButton();
    private SourceDeleteShortcutButton sourceDeleteButton = new SourceDeleteShortcutButton();
    private PrintShortcutButton printButton = new PrintShortcutButton();
    private ShareShortcutButton shareButton = new ShareShortcutButton();
    public List<MyShortcutButton> menuButtons = new ArrayList<MyShortcutButton>
    (
    	Arrays.asList(
   			this.peopleAddButton,
   			this.peopleDeleteButton,
   			this.placeDeleteButton,
   			this.mediaAddButton,
   			this.mediaDeleteButton,
   			this.sourceAddButton,
   			this.sourceDeleteButton,
   			this.printButton,
   			this.shareButton
    ));
    public List<MyShortcutButton> planButtons = new ArrayList<MyShortcutButton>(Arrays.asList(this.printButton));
    public List<MyShortcutButton> peopleButtons = new ArrayList<MyShortcutButton>(Arrays.asList(this.peopleAddButton, this.peopleDeleteButton, this.printButton, this.shareButton));
    public List<MyShortcutButton> placesButtons = new ArrayList<MyShortcutButton>(Arrays.asList(this.placeDeleteButton, this.printButton, this.shareButton));
    public List<MyShortcutButton> mediaButtons = new ArrayList<MyShortcutButton>(Arrays.asList(this.mediaAddButton, this.mediaDeleteButton, this.printButton, this.shareButton));
    public List<MyShortcutButton> sourcesButtons = new ArrayList<MyShortcutButton>(Arrays.asList(this.sourceAddButton, this.sourceDeleteButton, this.printButton, this.shareButton));
    public List<MyShortcutButton> publishButtons = new ArrayList<MyShortcutButton>(Arrays.asList(this.shareButton));
    public List<MyShortcutButton> webSearchButtons = new ArrayList<MyShortcutButton>(Arrays.asList(this.printButton, this.shareButton));

    public MyShortcutBar()
    {
    	log.trace("Entering");
		this.setBackground(MyFamily.myFamily.getBackground());
		for(MyShortcutButton myButton:this.menuButtons)
			this.add((Component)myButton);
		log.trace("Exitting");
    }

    public void deactivateAll()
    {
        log.trace(this.getClass().getSimpleName() + " Entering deactivateAll()");
		for(MyShortcutButton myButton:this.menuButtons)
			myButton.setInactiveIcon();
        log.trace(this.getClass().getSimpleName() + " Exitting deactivateAll()");
    }
}
