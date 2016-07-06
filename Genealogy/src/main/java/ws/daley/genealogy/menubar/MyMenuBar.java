package ws.daley.genealogy.menubar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JMenuBar;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.menubar.edit.EditMenu;
import ws.daley.genealogy.menubar.file.FileMenu;
import ws.daley.genealogy.menubar.help.HelpMenu;
import ws.daley.genealogy.menubar.media.MediaMenu;
import ws.daley.genealogy.menubar.person.PersonMenu;
import ws.daley.genealogy.menubar.place.PlaceMenu;
import ws.daley.genealogy.menubar.search.SearchMenu;
import ws.daley.genealogy.menubar.source.SourceMenu;
import ws.daley.genealogy.menubar.tools.ToolsMenu;
import ws.daley.genealogy.menubar.view.ViewMenu;

@SuppressWarnings("serial")
public class MyMenuBar extends JMenuBar
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(MyMenuBar.class);

	private FileMenu fileMenu = new FileMenu();
	private EditMenu editMenu = new EditMenu();
	private ViewMenu viewMenu = new ViewMenu();
	private PersonMenu personMenu = new PersonMenu();
	private PlaceMenu placeMenu = new PlaceMenu();
	private MediaMenu mediaMenu = new MediaMenu();
	private SourceMenu sourceMenu = new SourceMenu();
	private SearchMenu searchMenu = new SearchMenu();
	private ToolsMenu toolsMenu = new ToolsMenu();
	private HelpMenu helpMenu = new HelpMenu();
	
	public List<MyMenu> myMenus = new ArrayList<MyMenu>(Arrays.asList(this.fileMenu, this.editMenu, this.viewMenu, this.personMenu, this.placeMenu, this.mediaMenu, this.sourceMenu, this.searchMenu, this.toolsMenu, this.helpMenu));

	public List<MyMenu> mediaMenus = new ArrayList<MyMenu>(Arrays.asList(this.fileMenu, this.editMenu, this.viewMenu, this.mediaMenu, this.toolsMenu, this.helpMenu));
	public List<MyMenu> peopleMenus = new ArrayList<MyMenu>(Arrays.asList(this.fileMenu, this.editMenu, this.viewMenu, this.personMenu, this.toolsMenu, this.helpMenu));
	public List<MyMenu> placesMenus = new ArrayList<MyMenu>(Arrays.asList(this.fileMenu, this.editMenu, this.viewMenu, this.placeMenu, this.toolsMenu, this.helpMenu));
	public List<MyMenu> planMenus = new ArrayList<MyMenu>(Arrays.asList(this.fileMenu, this.editMenu, this.viewMenu, this.toolsMenu, this.helpMenu));
	public List<MyMenu> publishMenus = new ArrayList<MyMenu>(Arrays.asList(this.fileMenu, this.editMenu, this.viewMenu, this.toolsMenu, this.helpMenu));
	public List<MyMenu> sourcesMenus = new ArrayList<MyMenu>(Arrays.asList(this.fileMenu, this.editMenu, this.viewMenu, this.sourceMenu, this.toolsMenu, this.helpMenu));
	public List<MyMenu> webSearchMenus = new ArrayList<MyMenu>(Arrays.asList(this.fileMenu, this.editMenu, this.viewMenu, this.toolsMenu, this.helpMenu));

	public MyMenuBar()
	{
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		for(MyMenu myMenu:this.myMenus)
			this.add(myMenu);
		this.setVisible(true);
		log.trace("Exitting");
	}
}
