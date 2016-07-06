package ws.daley.genealogy.toolbar;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.MyFamily;
import ws.daley.genealogy.menubar.MyMenu;
import ws.daley.genealogy.menubar.MyMenuBar;
import ws.daley.genealogy.shortcutbar.MyShortcutBar;
import ws.daley.genealogy.shortcutbar.MyShortcutButton;
import ws.daley.genealogy.toolbar.button.MediaToolbarButton;
import ws.daley.genealogy.toolbar.button.PeopleToolbarButton;
import ws.daley.genealogy.toolbar.button.PlacesToolbarButton;
import ws.daley.genealogy.toolbar.button.PlanToolbarButton;
import ws.daley.genealogy.toolbar.button.PublishToolbarButton;
import ws.daley.genealogy.toolbar.button.WebSearchToolbarButton;

@SuppressWarnings("serial")
public class MyToolBar extends JToolBar implements SwingConstants, ActionListener //GPanel implements ActionListener
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(MyFamily.class);

	private FileDropDownBox fileDropDownBox = new FileDropDownBox();
	private PlanToolbarButton planMenu = new PlanToolbarButton();
	private PeopleToolbarButton peopleMenu = new PeopleToolbarButton();
	private PlacesToolbarButton placesMenu = new PlacesToolbarButton();
	private MediaToolbarButton mediaMenu = new MediaToolbarButton();
	private PublishToolbarButton publishMenu = new PublishToolbarButton();
	private WebSearchToolbarButton webSearchMenu = new WebSearchToolbarButton();

    public MyToolBar()
    {
		log.trace("Entering");
		this.setLayout(new FlowLayout());
		this.setBackground(MyFamily.myFamily.getBackground());
		add(this.fileDropDownBox);
		add((Component)this.planMenu);
		add((Component)this.peopleMenu);
		add((Component)this.placesMenu);
		add((Component)this.mediaMenu);
		add((Component)this.publishMenu);
		add((Component)this.webSearchMenu);
		setMinimumSize(new Dimension(450, 200));

		this.planMenu.setEnabled(true);
		log.trace("Exitting");
    }

    public void setAssociatedEntries()
    {
		log.trace("Entering setAssociatedEntries()");
    	this.mediaMenu.setAssociatedEntries();
    	this.peopleMenu.setAssociatedEntries();
    	this.placesMenu.setAssociatedEntries();
    	this.planMenu.setAssociatedEntries();
    	this.publishMenu.setAssociatedEntries();
    	this.webSearchMenu.setAssociatedEntries();
		log.trace("Exitting setAssociatedEntries()");
    }

    public void initialDoClick()
    {
		log.trace("Entering initialDoClick()");
    	this.planMenu.doClick();
		log.trace("Exitting initialDoClick()");
    }

    public void deactivateAll()
    {
		log.trace("Entering deactivateAll()");
		MyMenuBar myMenuBar = MyFamily.myFamily.myMenuBar;
		MyShortcutBar myShortcutBar = MyFamily.myFamily.myAppArea.myHeaderPanel.myShortcutBar;
		for(MyMenu myMenu:myMenuBar.myMenus)
			myMenu.setVisible(false);
		for(MyShortcutButton myShortcutButton:myShortcutBar.menuButtons)
			myShortcutButton.setVisible(false);
		log.trace("Exitting deactivateAll()");
    }

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		log.trace("Entering actionPerformed(" + arg0.toString() + ")");
		log.trace("Exitting actionPerformed(" + arg0.toString() + ")");
	}
}
