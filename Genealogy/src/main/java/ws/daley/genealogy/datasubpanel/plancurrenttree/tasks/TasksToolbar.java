package ws.daley.genealogy.datasubpanel.plancurrenttree.tasks;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.MyFamily;
import ws.daley.genealogy.datasubpanel.plancurrenttree.tasks.button.ColumnsToolbarButton;
import ws.daley.genealogy.datasubpanel.plancurrenttree.tasks.button.DeleteAllToolbarButton;
import ws.daley.genealogy.datasubpanel.plancurrenttree.tasks.button.DeleteToolbarButton;
import ws.daley.genealogy.datasubpanel.plancurrenttree.tasks.button.EditToolbarButton;
import ws.daley.genealogy.datasubpanel.plancurrenttree.tasks.button.FilterToolbarButton;
import ws.daley.genealogy.datasubpanel.plancurrenttree.tasks.button.GroupToolbarButton;
import ws.daley.genealogy.datasubpanel.plancurrenttree.tasks.button.NewToolbarButton;
import ws.daley.genealogy.datasubpanel.plancurrenttree.tasks.button.PrintToolbarButton;

@SuppressWarnings("serial")
public class TasksToolbar extends JToolBar implements SwingConstants
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(MyFamily.class);

	private NewToolbarButton newMenu = new NewToolbarButton();
	private EditToolbarButton editMenu = new EditToolbarButton();
	private DeleteToolbarButton deleteMenu = new DeleteToolbarButton();
	private DeleteAllToolbarButton deleteAllMenu = new DeleteAllToolbarButton();
	private GroupToolbarButton groupMenu = new GroupToolbarButton();
	private FilterToolbarButton filterMenu = new FilterToolbarButton();
	private ColumnsToolbarButton columnMenu = new ColumnsToolbarButton();
	private PrintToolbarButton printMenu = new PrintToolbarButton();

    public TasksToolbar()
    {
		log.trace("Entering");
		this.setLayout(new FlowLayout());
		this.setBackground(MyFamily.myFamily.getBackground());
		add((Component)this.newMenu);
		add((Component)this.editMenu);
		add((Component)this.deleteMenu);
		add((Component)this.deleteAllMenu);
		add((Component)this.groupMenu);
		add((Component)this.filterMenu);
		add((Component)this.columnMenu);
		add((Component)this.printMenu);
		setMinimumSize(new Dimension(450, 200));
		log.trace("Exitting");
    }
}
