package ws.daley.genealogy.datapanel;

import static java.awt.BorderLayout.EAST;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ws.daley.genealogy.MyFamily;
import ws.daley.genealogy.shortcutbar.MyShortcutBar;
import ws.daley.genealogy.toolbar.MyToolBar;

@SuppressWarnings("serial")
public class MyHeaderPanel extends JPanel
{
	private static final Logger log = ((LoggerContext)LoggerFactory.getILoggerFactory()).getLogger(MyHeaderPanel.class);

	public MyToolBar myToolBar = new MyToolBar();
	public MyShortcutBar myShortcutBar = new MyShortcutBar();
	
	public MyHeaderPanel()
	{
		log.trace("Entering");
		this.setLayout(new BorderLayout());
		this.setBackground(MyFamily.myFamily.getBackground());
		this.add(this.myToolBar, BorderLayout.WEST);
		this.add(this.myShortcutBar, EAST);
		log.trace("Exitting");
	}

	public void setAssociatedEntries()
	{
        log.trace(this.getClass().getSimpleName() + " Entering setAssociatedEntries()");
		this.myToolBar.setAssociatedEntries();
        log.trace(this.getClass().getSimpleName() + " Exitting setAssociatedEntries()");
	}
}
